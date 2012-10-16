package analyze.engine;

import java.util.Map;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.resource.ResourceInitializationException;

import util.*;
import zi.*;

public class NamedEntityAnnotator extends JCasAnnotator_ImplBase {
	
	@Override
	public void process(JCas nowJCas) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		try {
			PosTagNamedEntityRecognizer nowTagger = new PosTagNamedEntityRecognizer();
			JFSIndexRepository idx = nowJCas.getJFSIndexRepository();
			AnnotationIndex sentenceAnnotIndex = idx.getAnnotationIndex(Sentence.typeIndexID);
			FSIterator sentIter = sentenceAnnotIndex.iterator();
			while(sentIter.hasNext()){
				Sentence nowSent = (Sentence) sentIter.next();
				String nowText = nowSent.getText();
				Map<Integer, Integer> result = nowTagger.getGeneSpans(nowText);
				annotateNamedEntity(result, nowJCas, nowSent.getSentId());
			}
		} catch (ResourceInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void annotateNamedEntity(Map<Integer, Integer> spanMap, JCas nowJCas, String sentId){
		
		for(Map.Entry<Integer, Integer> entry : spanMap.entrySet()){
			
			Integer nowBeginIndex = entry.getKey();
			Integer nowEndIndex = spanMap.get(nowBeginIndex);
			
			NamedEntity nowNE = new NamedEntity(nowJCas, nowBeginIndex, nowEndIndex);
			nowNE.setText(nowJCas.getDocumentText().substring(nowBeginIndex, nowEndIndex));
			nowNE.setSentId(sentId);
			nowNE.addToIndexes();
			
		}
		
	}
	
	

}
