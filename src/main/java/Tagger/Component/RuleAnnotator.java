package Tagger.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.util.CoreMap;

import Tagger.Gen;
import Tagger.RuleTagNamedEntityRecognizer;
import Tagger.Utility;


/**
 * Use the RuleTaggerNamedEntityRecognizer to do the annotation
 * responsible for transform the result of tool into annotation
 */

public class RuleAnnotator extends JCasAnnotator_ImplBase {

  @Override
  public void process(JCas aJcas) throws AnalysisEngineProcessException {

    String text = aJcas.getDocumentText();
    text = text.trim();

    // extract id and sentence text
    int firstSpace = text.indexOf(' ');

    String ID = text.substring(0, firstSpace);
    String sentence = text.substring(firstSpace + 1);

    // annotate Gen
    RuleTagNamedEntityRecognizer tool = null;
    try {
      tool = new RuleTagNamedEntityRecognizer();

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
      annot.setSource("Rule");

      annot.addToIndexes();
    }
  }

}
