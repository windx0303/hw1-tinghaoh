/** 
 * IOEvaluation.java
 * 
 * Copyright (c) 2006, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek
 * 
 * Current version: 1.5	
 * Since version:   1.0
 *
 * Creation date: Aug 01, 2006 
 * 
 * Evaluation in IO (omitting B tag) mode.
 * 
 **/

package de.julielab.jnet.evaluation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class IOEvaluation {

	public String getType() {
		return "IO tags";
	}

	public static double[] evaluate(ArrayList gold_tok, ArrayList eval_tok) {
		// get taglist for goldstandard and model
		if (gold_tok.size() != eval_tok.size())
			System.err.println("gold size different from predicted");

		ArrayList<String> gold = new ArrayList<String>();
		ArrayList<String> eval = new ArrayList<String>();
		for (int i = 0; i < gold_tok.size(); i++) {
			String[] gTok = ((String) gold_tok.get(i)).split("\t");
			String[] eTok = ((String) eval_tok.get(i)).split("\t");

			// check format
			if (gTok.length != 2) {
				System.err
						.println("ERR: format error in gold file. IOB format must be: token<tab>label");
				System.exit(-1);
			} else if (eTok.length != 2) {
				System.err
						.println("ERR: format error in eval file. IOB format must be: token<tab>label");
				System.exit(-1);
			}

			gold.add(gTok[1]);
			eval.add(eTok[1]);
		}
		return getValuesIO(gold, eval);
	}

	private static double[] getValuesIO(ArrayList gold, ArrayList eval) {

		if (gold.size() != eval.size()) {
			System.err.println("error!, gold.size!=eval.size -> I quit!");
			System.exit(0);
		}

		HashMap gold_chunks = getChunksIO(gold);

		HashMap eval_chunks = getChunksIO(eval);

		int numcorr = 0;
		int numans = eval_chunks.size();
		int numref = gold_chunks.size();

		// now check the blocks
		for (Iterator iter = eval_chunks.keySet().iterator(); iter.hasNext();) {
			String offset = (String) iter.next();
			if (gold_chunks.containsKey(offset)) {
				String tags_eval = (String) eval_chunks.get(offset);
				String tags_gold = (String) gold_chunks.get(offset);
				if (tags_eval.equals(tags_gold)) {
					numcorr++;
				}
			}
		}

		double precision = 0;
		double recall = 0;
		double fscore = 0;

		if (numans > 0)
			precision = numcorr / (double) numans;
		if (numref > 0)
			recall = numcorr / (double) numref;
		if (precision + recall > 0)
			fscore = 2 * precision * recall / (precision + recall);

		double[] values = new double[] { recall, precision, fscore };
		return values;
	}

	public static HashMap getChunksIO(ArrayList taglist) {
		int begin = -1;
		int end = -1;

		HashMap<String, String> blocks = new HashMap<String, String>();
		String old_tag = "O";
		String curr_tag = "";
		for (int i = 0; i < taglist.size(); i++) {
			curr_tag = (String) taglist.get(i);

			if (curr_tag.equals(old_tag)) {
				// we are inside the same entity ... do nothing
			} else {
				// tags change

				// if we came from an entity: save old one
				if (begin > -1) {
					end = i - 1;
					String info = "";
					for (int j = begin; j < end + 1; j++) {

						if (info.length() > 0)
							info += "#";
						info += (String) taglist.get(j);

					}

					blocks.put(begin + "," + end, info);
				}

				// reset offsets
				if (!curr_tag.equals("O"))
					begin = i; // if a new entity starts
				else
					begin = -1; // if we are outside

				end = -1;
			}
			old_tag = curr_tag;
		}

		return blocks;
	}

}
