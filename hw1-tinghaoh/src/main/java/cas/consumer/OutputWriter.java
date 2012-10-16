package cas.consumer;

import java.io.*;

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

import util.*;

public class OutputWriter extends CasConsumer_ImplBase {
	
	private static String encoding = "UTF-8";
	
	private File outputFile;
	private BufferedWriter fOut;
	   
	public void initialize() throws ResourceInitializationException {
		outputFile = new File(((String)getConfigParameterValue("outputFile")).trim());
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
	
	@Override
	public void processCas(CAS aCAS) throws ResourceProcessException {
		//P00027739T0000|0 28|Serum gamma glutamyltransferase
		
		JCas jcas;
		try {
			jcas = aCAS.getJCas();
			JFSIndexRepository idx = jcas.getJFSIndexRepository();
			AnnotationIndex neAnnotIndex = idx.getAnnotationIndex(NamedEntity.typeIndexID);
			FSIterator neIter = neAnnotIndex.iterator();
			while(neIter.hasNext()){
				NamedEntity nowNE = (NamedEntity) neIter.next();
				try {
					fOut.write(nowNE.getSentId());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fOut.write("|");
				fOut.write(Integer.toString(nowNE.getBegin()));
				fOut.write(" ");
				fOut.write(Integer.toString(nowNE.getEnd()));
				fOut.write("|");
				fOut.write(nowNE.getText());
				fOut.newLine();
			}
		} catch (CASException e) {
			try {
				throw new CollectionException(e);
			} catch (CollectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) {
			try {
				throw new IOException(e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	public void finalize() throws IOException {
		fOut.close();
	}

}
