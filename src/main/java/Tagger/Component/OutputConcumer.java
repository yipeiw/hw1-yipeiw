package Tagger.Component;

import java.io.*;
import java.util.Iterator;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;

import Tagger.CombineGen;
import Tagger.Gen;
import Tagger.Utility;

public class OutputConcumer extends CasConsumer_ImplBase {

  public static final String PRAM_OUTFile="Output";
  
  private FileWriter fw = null;
  private BufferedWriter bw = null;
  
  private static boolean first = true;
  @Override
 
  public void processCas(CAS cas) throws ResourceProcessException {

    String FileName=(String)getConfigParameterValue(PRAM_OUTFile);
    
    try {
      System.out.println(FileName);
      if(first){     
        fw = new FileWriter(FileName);
        first = false;
      }else
      {
        fw = new FileWriter(FileName, true);
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    bw = new BufferedWriter(fw);
    
    JCas aJcas = null;
    try {
      aJcas = cas.getJCas();
    } catch (CASException e1) {
      e1.printStackTrace();
    }
    
    FSIndex GenIndex = aJcas.getAnnotationIndex(CombineGen.type);  ///////////
    Iterator GenIter = GenIndex.iterator();
    
    while(GenIter.hasNext())
    {
      //Gen annot = (Gen) GenIter.next();
      CombineGen annot = (CombineGen) GenIter.next();
      try {
        Output(bw, annot);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
    try {
      bw.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  //public void Output(BufferedWriter bw, Gen annot) throws IOException{
  public void Output(BufferedWriter bw, CombineGen annot) throws IOException{
    int Begin = annot.getStartOffset();
    
    int End = Utility.CountNonSpaceChar(annot.getCoveredText()) + Begin-1;
    
    String str = String.format("%s|%d %d|%s", annot.getSentenceID(), Begin, End, annot.getCoveredText());
    
    System.out.println(str);
    
    bw.write(str + "\n");
  }

}
