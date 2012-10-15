

/* First created by JCasGen Sun Oct 14 20:53:07 EDT 2012 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sun Oct 14 20:53:13 EDT 2012
 * XML source: C:/Documents and Settings/windx/git/hw1-tinghaoh/hw1-tinghaoh/typeSystem/hw1TypeSystem.xml
 * @generated */
public class NamedEntity extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(NamedEntity.class);
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
  protected NamedEntity() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public NamedEntity(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public NamedEntity(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public NamedEntity(JCas jcas, int begin, int end) {
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
  //* Feature: neType

  /** getter for neType - gets 
   * @generated */
  public String getNeType() {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_neType == null)
      jcasType.jcas.throwFeatMissing("neType", "NamedEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_neType);}
    
  /** setter for neType - sets  
   * @generated */
  public void setNeType(String v) {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_neType == null)
      jcasType.jcas.throwFeatMissing("neType", "NamedEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_neType, v);}    
   
    
  //*--------------*
  //* Feature: sentence

  /** getter for sentence - gets 
   * @generated */
  public Sentence getSentence() {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_sentence == null)
      jcasType.jcas.throwFeatMissing("sentence", "NamedEntity");
    return (Sentence)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_sentence)));}
    
  /** setter for sentence - sets  
   * @generated */
  public void setSentence(Sentence v) {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_sentence == null)
      jcasType.jcas.throwFeatMissing("sentence", "NamedEntity");
    jcasType.ll_cas.ll_setRefValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_sentence, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    