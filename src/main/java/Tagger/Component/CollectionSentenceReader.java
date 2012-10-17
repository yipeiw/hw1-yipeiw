package Tagger.Component;

import java.io.*;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.Progress;

public class CollectionSentenceReader extends CollectionReader_ImplBase {

  public static final String PARAM_INPUT="Input";
  
  private FileReader fr = null;
  private BufferedReader br = null;
  
  private static int num = 0;
  
  private String currentLine;
  
  public void initialize()
  {
    String FileName=(String)getConfigParameterValue(PARAM_INPUT);
    
    System.out.println(FileName);
    
    try {
      fr = new FileReader(FileName);
      br = new BufferedReader(fr);
      currentLine = br.readLine();
      
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    System.out.println(currentLine);
  }
  
  @Override
  public void getNext(CAS aCAS) throws IOException, CollectionException {
    JCas jcas = null;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      e.printStackTrace();
    }
    
    jcas.setDocumentText(currentLine);
    
    currentLine = br.readLine();

    System.out.printf("number of readin sentence: %d\n", num);
  }

  @Override
  public void close() throws IOException {
    System.out.printf("number of readin sentence: %d\n", num);
    br.close();
  }

  @Override
  public Progress[] getProgress() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean hasNext() throws IOException, CollectionException {
    num++;
    return (currentLine!=null);
  }

}
