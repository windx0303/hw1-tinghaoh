package collection.reader;

import java.io.*;

import util.*;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;

public class InputFileCollectionReader extends CollectionReader_ImplBase {
	
	private static String encoding = "UTF-8";
	
	private String lineBuffer;
	private BufferedReader in;
	private File inputFile;
	   
	public void initialize() throws ResourceInitializationException {
		inputFile = new File(((String)getConfigParameterValue("inputFile")).trim());
		lineBuffer = "";
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), encoding));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void getNext(CAS aCAS) throws IOException, CollectionException {
		
		JCas jcas;
		
		try {
			jcas = aCAS.getJCas();
		} catch (CASException e) {
			throw new CollectionException(e);
		}
		
		//P00001606T0076 Comparison with alkaline phosphatases and 5-nucleotidase
		int splitIndex = lineBuffer.indexOf(" ");
		String nowId = lineBuffer.substring(0, splitIndex);
		String nowText = lineBuffer.substring(splitIndex+1).trim();
		
		jcas.setDocumentText(nowText);
		
		Sentence nowSent = new Sentence(jcas, 0, nowText.length());
		nowSent.setSentId(nowId);
		nowSent.setText(nowText);
		nowSent.addToIndexes();
		
	}

	@Override
	public boolean hasNext() throws IOException, CollectionException {
		lineBuffer = in.readLine();
		if(lineBuffer!=null){
			return true;
	    }
		return false;
	}

	@Override
	public Progress[] getProgress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		in.close();
	}

}
