package Tagger.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Tagger.Gen;
import Tagger.CombineGen;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
/**
 * Responsible to combine the annotation results from other annotators
 * support select annotation from one source or take the intersections
 */

public class CombineAnnotator extends JCasAnnotator_ImplBase {

  @Override
  public void process(JCas aJcas) throws AnalysisEngineProcessException {
    
    String type ="Common";
    
    if(type=="POS" || type == "Rule")
    {
      SelectGen(aJcas, type);
    }else if(type=="Common")
    {
      CommonGen(aJcas); 
    }else
    {
      System.out.printf("Required Type %s is not supported\n",type);
    }
  }
  
  public void CommonGen(JCas aJcas){
    FSIndex GenIndex = aJcas.getAnnotationIndex(Gen.type);
    Iterator GenIter = GenIndex.iterator();
    
    Map<Integer, Integer> Annotated = new HashMap<Integer, Integer>();
    
    while(GenIter.hasNext())
    {
      Gen annot = (Gen) GenIter.next();
      if(annot.getSource()=="POS")
      {
        Annotated.put(annot.getBegin(), annot.getEnd());
      }
    }
    
    GenIter = GenIndex.iterator();
    while(GenIter.hasNext())
    {
      Gen annot = (Gen) GenIter.next();
      if(annot.getSource()=="Rule")
      {
        if(Annotated.get(annot.getBegin())!=null)
        {
          if(annot.getEnd() == Annotated.get(annot.getBegin())){
            
            CombineGen Cannot = new CombineGen(aJcas);
            
            Cannot.setBegin(annot.getBegin());
            Cannot.setEnd(annot.getEnd());
            Cannot.setStartOffset(annot.getStartOffset());
            Cannot.setSentenceID(annot.getSentenceID());
            Cannot.setSource("Common");
            
            Cannot.addToIndexes();
          }            
        }
      }
    }
    
  }
  
  public void SelectGen(JCas aJcas, String source)
  {
    FSIndex GenIndex = aJcas.getAnnotationIndex(Gen.type);
    Iterator GenIter = GenIndex.iterator();
    
    while(GenIter.hasNext())
    {
      Gen annot = (Gen) GenIter.next();
      if(source == annot.getSource())
      {
        CombineGen Cannot = new CombineGen(aJcas);
        
        Cannot.setBegin(annot.getBegin());
        Cannot.setEnd(annot.getEnd());
        Cannot.setStartOffset(annot.getStartOffset());
        
        Cannot.setSentenceID(annot.getSentenceID());       
        Cannot.setSource(source);
        
        Cannot.addToIndexes();
      }
    }
  }

}
