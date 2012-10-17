

/* First created by JCasGen Mon Oct 15 19:49:39 EDT 2012 */
package Tagger;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Oct 16 22:04:16 EDT 2012
 * XML source: /home/yipeiw/workspace/hw1-yipeiw/src/main/resources/RulePOSAnnotatorDescriptor.xml
 * @generated */
public class Gen extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Gen.class);
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
  public Gen() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Gen(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Gen(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Gen(JCas jcas, int begin, int end) {
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
    if (Gen_Type.featOkTst && ((Gen_Type)jcasType).casFeat_sentenceID == null)
      jcasType.jcas.throwFeatMissing("sentenceID", "Tagger.Gen");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Gen_Type)jcasType).casFeatCode_sentenceID);}
    
  /** setter for sentenceID - sets  
   * @generated */
  public void setSentenceID(String v) {
    if (Gen_Type.featOkTst && ((Gen_Type)jcasType).casFeat_sentenceID == null)
      jcasType.jcas.throwFeatMissing("sentenceID", "Tagger.Gen");
    jcasType.ll_cas.ll_setStringValue(addr, ((Gen_Type)jcasType).casFeatCode_sentenceID, v);}    
   
    
  //*--------------*
  //* Feature: source

  /** getter for source - gets 
   * @generated */
  public String getSource() {
    if (Gen_Type.featOkTst && ((Gen_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "Tagger.Gen");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Gen_Type)jcasType).casFeatCode_source);}
    
  /** setter for source - sets  
   * @generated */
  public void setSource(String v) {
    if (Gen_Type.featOkTst && ((Gen_Type)jcasType).casFeat_source == null)
      jcasType.jcas.throwFeatMissing("source", "Tagger.Gen");
    jcasType.ll_cas.ll_setStringValue(addr, ((Gen_Type)jcasType).casFeatCode_source, v);}    
   
    
  //*--------------*
  //* Feature: confidence

  /** getter for confidence - gets 
   * @generated */
  public float getConfidence() {
    if (Gen_Type.featOkTst && ((Gen_Type)jcasType).casFeat_confidence == null)
      jcasType.jcas.throwFeatMissing("confidence", "Tagger.Gen");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((Gen_Type)jcasType).casFeatCode_confidence);}
    
  /** setter for confidence - sets  
   * @generated */
  public void setConfidence(float v) {
    if (Gen_Type.featOkTst && ((Gen_Type)jcasType).casFeat_confidence == null)
      jcasType.jcas.throwFeatMissing("confidence", "Tagger.Gen");
    jcasType.ll_cas.ll_setFloatValue(addr, ((Gen_Type)jcasType).casFeatCode_confidence, v);}    
   
    
  //*--------------*
  //* Feature: StartOffset

  /** getter for StartOffset - gets 
   * @generated */
  public int getStartOffset() {
    if (Gen_Type.featOkTst && ((Gen_Type)jcasType).casFeat_StartOffset == null)
      jcasType.jcas.throwFeatMissing("StartOffset", "Tagger.Gen");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Gen_Type)jcasType).casFeatCode_StartOffset);}
    
  /** setter for StartOffset - sets  
   * @generated */
  public void setStartOffset(int v) {
    if (Gen_Type.featOkTst && ((Gen_Type)jcasType).casFeat_StartOffset == null)
      jcasType.jcas.throwFeatMissing("StartOffset", "Tagger.Gen");
    jcasType.ll_cas.ll_setIntValue(addr, ((Gen_Type)jcasType).casFeatCode_StartOffset, v);}    
  }

    