package cas.consumer;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.ProcessTrace;

import com.aliasi.chunk.Chunk;

import util.*;

/**
 * a Cas consumer which take the final JCas and output all NamedEntity annotations into output.  
 * The duplicate span, i.e., the span with same begin and end, will be ignored.
 */
public class OutputWriter extends CasConsumer_ImplBase {
	
	private static String encoding = "UTF-8";
	
	private File outputFile;
	private BufferedWriter fOut;
	private Hashtable<Integer, Integer> indexMap;
	
	/*
	 * new the BufferedWriter 
	 * (non-Javadoc)
	 * @see org.apache.uima.collection.CasConsumer_ImplBase#initialize()
	 */
	public void initialize() throws ResourceInitializationException {
		outputFile = new File(((String)getConfigParameterValue("outputFile")).trim());
		indexMap = new Hashtable<Integer, Integer>();
		try {
			fOut = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile, false), encoding));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * take the JCas and output to file
	 * (non-Javadoc)
	 * @see org.apache.uima.collection.base_cpm.CasObjectProcessor#processCas(org.apache.uima.cas.CAS)
	 */
	@Override
	public void processCas(CAS aCAS) throws ResourceProcessException {
		//P00027739T0000|0 28|Serum gamma glutamyltransferase
		
		JCas jcas;
		try {
			
			ArrayList<NamedEntity> result = new ArrayList<NamedEntity>();
			
			jcas = aCAS.getJCas();
			
			indexMap = buildIndexMap(jcas);
			
			JFSIndexRepository idx = jcas.getJFSIndexRepository();
			AnnotationIndex neAnnotIndex = idx.getAnnotationIndex(NamedEntity.typeIndexID);
			FSIterator neIter = neAnnotIndex.iterator();
			
			while(neIter.hasNext()){
				
				NamedEntity nowNE = (NamedEntity) neIter.next();
				
				if(!containsNE(nowNE, result)){
					result.add(nowNE);
					fOut.write(nowNE.getSentId());
					fOut.write("|");
					int outputBegin = indexMap.get(nowNE.getBegin());
					fOut.write(Integer.toString(outputBegin));
					fOut.write(" ");
					int outputEnd = indexMap.get(nowNE.getEnd()-1);
					fOut.write(Integer.toString(outputEnd));
					fOut.write("|");
					fOut.write(nowNE.getText());
					fOut.newLine();
				}
				
			}
			
			result.clear();
			
		} catch (CASException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Return true if the NamedEntity list contains the same input NamedEntity
	 * If one NamedEntity's span has overlap with the other, we consider they're different  
	 */
	private boolean containsNE(NamedEntity nowNE, ArrayList<NamedEntity> result){
		for(int i=0;i<result.size();i++){
			NamedEntity nowResult = result.get(i);
			int resultBegin = nowResult.getBegin();
			int resultEnd = nowResult.getEnd();
			if(nowNE.getBegin()==resultBegin&&nowNE.getEnd()==resultEnd){
				return true;
			}
		}
		return false;
	}
	
	/*
	 * build the map which maps the original Java index to the required output index 
	 */
	private Hashtable<Integer, Integer> buildIndexMap(JCas jcas){
		Hashtable<Integer, Integer> result = new Hashtable<Integer, Integer>();
		String nowText = jcas.getDocumentText();
		Pattern spaceMatcher = Pattern.compile("[\\s]+");
		int outputIndex = 0;
		for(int i=0;i<nowText.length();i++){
			result.put(new Integer(i), new Integer(outputIndex));
			if(!spaceMatcher.matcher(nowText.substring(i, i+1)).matches()){//not a space
				outputIndex++;
			}
		}
		return result;
	}
	
	/*
	 * finalize the component, close the BufferedWriter
	 * (non-Javadoc)
	 * @see org.apache.uima.collection.CasConsumer_ImplBase#collectionProcessComplete(org.apache.uima.util.ProcessTrace)
	 */
	public void collectionProcessComplete(ProcessTrace aTrace)
            throws ResourceProcessException,
                   IOException{
		try {
			System.out.println("write over");
			fOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
