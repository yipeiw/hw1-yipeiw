package Tagger.Component;

import java.util.Iterator;
import java.util.Map;

import Tagger.Gen;
import Tagger.PosTagNamedEntityRecognizer;
import Tagger.Utility;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

/**
 * Use the POSTaggerNamedEntityRecognizer to do the annotation
 * responsible for transform the result of tool into annotation
 */

public class POSAnnotator extends JCasAnnotator_ImplBase {

  @Override
  public void process(JCas aJcas) throws AnalysisEngineProcessException {
    String text = aJcas.getDocumentText();
    text = text.trim();

    //System.out.println("tagger");

    // extract id and sentence text
    int firstSpace = text.indexOf(' ');

    String ID = text.substring(0, firstSpace);
    String sentence = text.substring(firstSpace + 1);

    // annotate Gen
    PosTagNamedEntityRecognizer tool = null;
    try {
      tool = new PosTagNamedEntityRecognizer();

    } catch (ResourceInitializationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    Map<Integer, Integer> GenPos = tool.getGeneSpans(sentence);
    Iterator iter = GenPos.keySet().iterator();

    while (iter.hasNext()) {
      int Begin = (Integer) iter.next();
      int End = GenPos.get(Begin);

      int PreNum = 0;
      if (Begin > 0) {
        String Pre = sentence.substring(0, Begin - 1);
        PreNum = Utility.CountNonSpaceChar(Pre.trim());
      }

      Gen annot = new Gen(aJcas);

      annot.setBegin(Begin + firstSpace + 1);
      annot.setEnd(End + firstSpace + 1);
      annot.setStartOffset(PreNum);

      annot.setSentenceID(ID);
      annot.setSource("POS");

      annot.addToIndexes();
    }
  }

}
