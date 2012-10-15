

/* First created by JCasGen Sun Oct 14 20:53:07 EDT 2012 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sun Oct 14 20:53:13 EDT 2012
 * XML source: C:/Documents and Settings/windx/git/hw1-tinghaoh/hw1-tinghaoh/typeSystem/hw1TypeSystem.xml
 * @generated */
public class Sentence extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Sentence.class);
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
  protected Sentence() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Sentence(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Sentence(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Sentence(JCas jcas, int begin, int end) {
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
  //* Feature: sentId

  /** getter for sentId - gets 
   * @generated */
  public String getSentId() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_sentId == null)
      jcasType.jcas.throwFeatMissing("sentId", "Sentence");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Sentence_Type)jcasType).casFeatCode_sentId);}
    
  /** setter for sentId - sets  
   * @generated */
  public void setSentId(String v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_sentId == null)
      jcasType.jcas.throwFeatMissing("sentId", "Sentence");
    jcasType.ll_cas.ll_setStringValue(addr, ((Sentence_Type)jcasType).casFeatCode_sentId, v);}    
   
    
  //*--------------*
  //* Feature: neList

  /** getter for neList - gets 
   * @generated */
  public FSList getNeList() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_neList == null)
      jcasType.jcas.throwFeatMissing("neList", "Sentence");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_neList)));}
    
  /** setter for neList - sets  
   * @generated */
  public void setNeList(FSList v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_neList == null)
      jcasType.jcas.throwFeatMissing("neList", "Sentence");
    jcasType.ll_cas.ll_setRefValue(addr, ((Sentence_Type)jcasType).casFeatCode_neList, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    