

/* First created by JCasGen Tue Oct 16 21:18:21 EDT 2012 */
package Tagger;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Oct 16 22:04:16 EDT 2012
 * XML source: /home/yipeiw/workspace/hw1-yipeiw/src/main/resources/RulePOSAnnotatorDescriptor.xml
 * @generated */
public class CombineGen extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(CombineGen.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  public CombineGen() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public CombineGen(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public CombineGen(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public CombineGen(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: sentenceID

  /** getter for sentenceID - gets 
   * @generated */
  public String getSentenceID() {
    if (CombineGen_Type.featOkTst && ((CombineGen_Type)jcasType).casFeat_sentenceID == null)
      jcasType.jcas.throwFeatMissing("sentenceID", "Tagger.CombineGen");
    return jcasType.ll_cas.ll_getStringValue(addr, ((CombineGen_Type)jcasType).casFeatCode_sentenceID);}
    
  /** setter for sentenceID - sets  
   * @generated */
  public void setSentenceID(String v) {
    if (CombineGen_Type.featOkTst && ((CombineGen_Type)jcasType).casFeat_sentenceID == null)
      jcasType.jcas.throwFeatMissing("sentenceID", "Tagger.CombineGen");
    jcasType.ll_cas.ll_setStringValue(addr, ((CombineGen_Type)jcasType).casFeatCode_sentenceID, v);}    
   
    
  //*--------------*
  //* Feature: source

  /** getter for source - gets 
   * @generated */
  public String getSource() {
    if (CombineGen_Type.featOkTst && ((CombineGen_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "Tagger.CombineGen");
    return jcasType.ll_cas.ll_getStringValue(addr, ((CombineGen_Type)jcasType).casFeatCode_source);}
    
  /** setter for source - sets  
   * @generated */
  public void setSource(String v) {
    if (CombineGen_Type.featOkTst && ((CombineGen_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "Tagger.CombineGen");
    jcasType.ll_cas.ll_setStringValue(addr, ((CombineGen_Type)jcasType).casFeatCode_source, v);}    
   
    
  //*--------------*
  //* Feature: confidence

  /** getter for confidence - gets 
   * @generated */
  public float getConfidence() {
    if (CombineGen_Type.featOkTst && ((CombineGen_Type)jcasType).casFeat_confidence == null)
      jcasType.jcas.throwFeatMissing("confidence", "Tagger.CombineGen");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((CombineGen_Type)jcasType).casFeatCode_confidence);}
    
  /** setter for confidence - sets  
   * @generated */
  public void setConfidence(float v) {
    if (CombineGen_Type.featOkTst && ((CombineGen_Type)jcasType).casFeat_confidence == null)
      jcasType.jcas.throwFeatMissing("confidence", "Tagger.CombineGen");
    jcasType.ll_cas.ll_setFloatValue(addr, ((CombineGen_Type)jcasType).casFeatCode_confidence, v);}    
   
    
  //*--------------*
  //* Feature: StartOffset

  /** getter for StartOffset - gets 
   * @generated */
  public int getStartOffset() {
    if (CombineGen_Type.featOkTst && ((CombineGen_Type)jcasType).casFeat_StartOffset == null)
      jcasType.jcas.throwFeatMissing("StartOffset", "Tagger.CombineGen");
    return jcasType.ll_cas.ll_getIntValue(addr, ((CombineGen_Type)jcasType).casFeatCode_StartOffset);}
    
  /** setter for StartOffset - sets  
   * @generated */
  public void setStartOffset(int v) {
    if (CombineGen_Type.featOkTst && ((CombineGen_Type)jcasType).casFeat_StartOffset == null)
      jcasType.jcas.throwFeatMissing("StartOffset", "Tagger.CombineGen");
    jcasType.ll_cas.ll_setIntValue(addr, ((CombineGen_Type)jcasType).casFeatCode_StartOffset, v);}    
  }

    