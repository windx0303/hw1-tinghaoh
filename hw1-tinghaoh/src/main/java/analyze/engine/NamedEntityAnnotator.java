package analyze.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.resource.ResourceInitializationException;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.Chunking;
import com.aliasi.dict.ExactDictionaryChunker;
import com.aliasi.util.AbstractExternalizable;

import de.julielab.jnet.tagger.*;
import de.julielab.jnet.tagger.Sentence;
import edu.umass.cs.mallet.base.pipe.Pipe;
import edu.umass.cs.mallet.base.types.Instance;
import edu.umass.cs.mallet.base.types.Sequence;

import util.*;
import zi.*;

public class NamedEntityAnnotator extends JCasAnnotator_ImplBase {
	
	public static File configFile = new File("src/main/resources/defaultFeatureConf.conf");
	public static File modelFile = new File("src/main/resources/models/pennbio_genes.mod.gz");
	
	public static File lingpipeModelFile = new File("src/main/resources/models/ne-en-bio-genetag.HmmChunker");
	
	Chunker chunker;
	
	NETagger nowNETagger;
	
	@Override
	public void process(JCas nowJCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		
		try {
			
			//nowNETagger.readModel(modelFile.getAbsolutePath());
			//System.out.println("annotator called:");
			//System.out.println(nowJCas.getDocumentText());
			PosTagNamedEntityRecognizer nowTagger = new PosTagNamedEntityRecognizer();
			JFSIndexRepository idx = nowJCas.getJFSIndexRepository();
			AnnotationIndex sentenceAnnotIndex = idx.getAnnotationIndex(util.Sentence.typeIndexID);
			FSIterator sentIter = sentenceAnnotIndex.iterator();
			while(sentIter.hasNext()){
				util.Sentence nowSent = (util.Sentence) sentIter.next();
				String nowText = nowSent.getText();
				ArrayList<Unit> nowTokenList = nowTagger.getTokeList(nowText);
				Sentence nowJnetSent = new Sentence(nowTokenList);
				ArrayList<Unit> result = nowNETagger.getIOBList(nowJnetSent);
				annotateNamedEntity(result, nowJCas, nowSent.getSentId());
				ArrayList<Unit> lingPiptResult = getLingPiptResult(nowText);
				annotateLingPipeNE(lingPiptResult, nowJCas, nowSent.getSentId());
				/*for(int i=0;i<result.size();i++){
					System.out.println(result.get(i).toString());
				}*/
			}
		} catch (ResourceInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JNETException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}
	
	public void annotateLingPipeNE(ArrayList<Unit> lingPiptResult, JCas nowJCas, String sentId){
		
		for(int i=0;i<lingPiptResult.size();i++){
			
			Unit nowUnit = lingPiptResult.get(i);
			
			int nowBegin = nowUnit.begin;
			int nowEnd = nowUnit.end;
			
			NamedEntity nowNE = new NamedEntity(nowJCas, nowBegin, nowEnd);
			nowNE.setText(nowUnit.getRep());
			System.out.println(nowUnit.getRep());
			nowNE.setSentId(sentId);
			nowNE.addToIndexes();
			
		}
		
	}
	
	public ArrayList<Unit> getLingPiptResult(String nowText){
		
		ArrayList<Unit> result = new ArrayList<Unit>();
		
		Chunking chunking = chunker.chunk(nowText);
	    for (Chunk chunk : chunking.chunkSet()) {
	        int start = chunk.start();
	        int end = chunk.end();
	        String type = chunk.type();
	        String phrase = nowText.substring(start, end);
	        Unit nowUnit = new Unit(start, end, phrase, type);
	        result.add(nowUnit);
	    }
	    
	    return result;
		
	}
	
	public void initialize(UimaContext aContext) throws ResourceInitializationException{
		
		super.initialize(aContext);
		
		try {
			nowNETagger = new NETagger(configFile);
			nowNETagger.readModel(modelFile.getAbsolutePath());
			chunker = (Chunker) AbstractExternalizable.readObject(lingpipeModelFile);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}
	
	/*public void initialize() throws ResourceInitializationException {
		
		
		
	}*/
	
	private void annotateNamedEntity(ArrayList<Unit> result, JCas nowJCas, String sentId){
		boolean inNE = false;
		ArrayList<Integer> beginList = new  ArrayList<Integer>();
		ArrayList<Integer> endList = new  ArrayList<Integer>();
		for(int i=0;i<result.size();i++){
			Unit nowToken = result.get(i);
			if(!nowToken.getLabel().equals("O")){
				inNE = true;
				beginList.add(nowToken.begin);
				endList.add(nowToken.end);
			}else{
				if(inNE){
					
					int nowBegin = beginList.get(0).intValue();
					int nowEnd = endList.get(endList.size()-1).intValue();
					
					NamedEntity nowNE = new NamedEntity(nowJCas, nowBegin, nowEnd);
					System.out.println(nowJCas.getDocumentText().substring(nowBegin, nowEnd));
					nowNE.setText(nowJCas.getDocumentText().substring(nowBegin, nowEnd));
					nowNE.setSentId(sentId);
					nowNE.addToIndexes();
					
					inNE = false;
					beginList.clear();
					endList.clear();
					
				}
			}
		}
	}
	
	/*private void annotateNamedEntity(Map<Integer, Integer> spanMap, JCas nowJCas, String sentId){
		
		for(Map.Entry<Integer, Integer> entry : spanMap.entrySet()){
			
			Integer nowBeginIndex = entry.getKey();
			Integer nowEndIndex = spanMap.get(nowBeginIndex);
			
			NamedEntity nowNE = new NamedEntity(nowJCas, nowBeginIndex, nowEndIndex);
			nowNE.setText(nowJCas.getDocumentText().substring(nowBeginIndex, nowEndIndex));
			nowNE.setSentId(sentId);
			nowNE.addToIndexes();
			
		}
		
	}*/
	
	

}
