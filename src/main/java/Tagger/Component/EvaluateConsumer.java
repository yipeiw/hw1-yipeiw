package Tagger.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceProcessException;

import Tagger.Gen;
import Tagger.Utility;

/**
 * Evaluate the performance.
 * support measure of precision and recall
 * load the whole answer set at the first time called
 */

public class EvaluateConsumer extends CasConsumer_ImplBase {

  public class Annotation {
    int begin;

    int end;

    String word;
  }

  private static HashMap<String, ArrayList<Annotation>> Answer = null;

  private static int SentenceNum;

  private static String currentID;

  private static String LastID;

  private static boolean firstTime = true;

  private static int correct = 0;

  private static int TotalAnnot = 0;

  private static int TotalAnswer = 0;

  private static int call = 0;

  private static final String FileName = "/home/yipeiw/workspace/hw1-yipeiw/src/main/resources/data/sample.out";

  @Override
  public void processCas(CAS cas) throws ResourceProcessException {
    if (firstTime) {
      LoadAnswer();
      System.out.printf("load answer success: %d from %d\n", Answer.size(), TotalAnswer);
      firstTime = false;
    }

    // System.out.printf("load answer success: %d\n", Answer.size());

    JCas aJcas = null;
    try {
      aJcas = cas.getJCas();
    } catch (CASException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    // update evaluation
    Annotation[] Result = LoadResult(aJcas);
    TotalAnnot += Result.length;

    System.out.printf("load result success: %d", Result.length);

    Annotation[] Original = FindAnswer(currentID);

    if (Original != null) {
      Compare(Result, Original);
      call++;
    }

    CalculateEval();

    System.out.printf("call time: %d\n", call);
  }

  public Annotation[] LoadResult(JCas aJcas) {
    FSIndex GenIndex = aJcas.getAnnotationIndex(Gen.type);
    Iterator GenIter = GenIndex.iterator();

    String ID;
    ArrayList<Annotation> Result = new ArrayList<Annotation>();
    while (GenIter.hasNext()) {
      Gen annot = (Gen) GenIter.next();
      ID = annot.getSentenceID();
      if (ID != currentID) {
        SentenceNum++;
        currentID = ID;
      }

      Annotation newAnnot = new Annotation();

      newAnnot.begin = annot.getStartOffset();
      newAnnot.end = newAnnot.begin + Utility.CountNonSpaceChar(annot.getCoveredText()) - 1;

      newAnnot.word = annot.getCoveredText();

      //System.out.printf("transform result: %d %d %s\n", newAnnot.begin, newAnnot.end, newAnnot.word);

      Result.add(newAnnot);
    }

    Annotation[] temp = new Annotation[Result.size()];
    temp = Result.toArray(temp);
    return temp;
  }

  public void LoadAnswer() {
    Answer = new HashMap<String, ArrayList<Annotation>>();

    FileReader fr;
    try {
      fr = new FileReader(FileName);
      BufferedReader br = new BufferedReader(fr);

      String line;
      // int linenum = 0;

      while ((line = br.readLine()) != null) {
        // linenum++;
        Annotation annot = ExtractAnnotation(line);
        String ID = line.split("[|]")[0];
        LastID = ID;

        // System.out.println("Load Answer :"+ ID);

        ArrayList<Annotation> current = Answer.get(ID);

        if (current != null) {
          current.add(annot);
          Answer.put(ID, current);

          // System.out.printf("update item in answer: %s\n", ID);

        } else {
          ArrayList<Annotation> newAdd = new ArrayList<Annotation>();
          newAdd.add(annot);
          Answer.put(ID, newAdd);

          // System.out.printf("add new item in answer:%s\n", ID);
        }

        TotalAnswer++;
      }

      // System.out.printf("total line of sample out: %d", linenum);

      br.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  public Annotation ExtractAnnotation(String line) {
    int pos = line.indexOf("|");
    // System.out.printf("positon of |: %d\n", pos);

    String[] lines = line.trim().split("[|]");

    if (lines.length != 3)
      System.out.println("error " + line + " " + lines.length);

    // System.out.println(lines[0]);
    // System.out.println(lines[1]);

    String word = lines[2];
    String[] nums = lines[1].trim().split("[ ]");

    if (nums.length != 2)
      System.out.println("error num:" + lines[1] + " " + nums.length);

    int begin = Integer.parseInt(nums[0]);
    int end = Integer.parseInt(nums[1]);

    Annotation annot = new Annotation();
    annot.begin = begin;
    annot.end = end;
    annot.word = word;

    return annot;
  }

  public Annotation[] FindAnswer(String sentenceID) {
    ArrayList<Annotation> Annot = Answer.get(sentenceID);

    Annotation[] temp = null;
    if (Annot != null) {
      temp = new Annotation[Annot.size()];
      temp = Annot.toArray(temp);

      System.out.printf("find answer:%s, %d\n", sentenceID, temp.length);
    } else {
      System.out.printf("cannot find answer:%s\n", sentenceID);
    }

    return temp;
  }

  public void Compare(Annotation[] Result, Annotation[] Original) {

    for (int i = 0; i < Original.length; i++) {
      Annotation oa = Original[i];
      for (int j = 0; j < Result.length; j++) {
        Annotation ra = Result[j];

        System.out.printf("compare pair: %s, %s\n", ra.word, oa.word);

        System.out.printf("position of result:%d %d, original : %d %d\n", ra.begin, ra.end,
                oa.begin, oa.end);

        // if(ra.begin == oa.begin && ra.end == oa.end && ra.word == oa.word)
        if (ra.begin == oa.begin && ra.end == oa.end) {
          correct++;
        }
      }
    }

  }

  public void CalculateEval() {
    System.out.printf("correct: %d\n", correct);
    System.out.printf("annot: %d\n", TotalAnnot);
    System.out.printf("answer: %d\n", TotalAnswer);

    if (TotalAnswer > 0 && TotalAnnot > 0) {
      System.out.printf("recall: %f\n", (float) correct / (float) TotalAnswer);
      System.out.printf("precision: %f\n", (float) correct / (float) TotalAnnot);
    }
  }

}
