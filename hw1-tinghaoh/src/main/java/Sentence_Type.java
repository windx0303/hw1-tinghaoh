
/* First created by JCasGen Sun Oct 14 20:53:07 EDT 2012 */

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Sun Oct 14 20:53:13 EDT 2012
 * @generated */
public class Sentence_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Sentence_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Sentence_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Sentence(addr, Sentence_Type.this);
  			   Sentence_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Sentence(addr, Sentence_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Sentence.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("Sentence");
 
  /** @generated */
  final Feature casFeat_sentId;
  /** @generated */
  final int     casFeatCode_sentId;
  /** @generated */ 
  public String getSentId(int addr) {
        if (featOkTst && casFeat_sentId == null)
      jcas.throwFeatMissing("sentId", "Sentence");
    return ll_cas.ll_getStringValue(addr, casFeatCode_sentId);
  }
  /** @generated */    
  public void setSentId(int addr, String v) {
        if (featOkTst && casFeat_sentId == null)
      jcas.throwFeatMissing("sentId", "Sentence");
    ll_cas.ll_setStringValue(addr, casFeatCode_sentId, v);}
    
  
 
  /** @generated */
  final Feature casFeat_neList;
  /** @generated */
  final int     casFeatCode_neList;
  /** @generated */ 
  public int getNeList(int addr) {
        if (featOkTst && casFeat_neList == null)
      jcas.throwFeatMissing("neList", "Sentence");
    return ll_cas.ll_getRefValue(addr, casFeatCode_neList);
  }
  /** @generated */    
  public void setNeList(int addr, int v) {
        if (featOkTst && casFeat_neList == null)
      jcas.throwFeatMissing("neList", "Sentence");
    ll_cas.ll_setRefValue(addr, casFeatCode_neList, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Sentence_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_sentId = jcas.getRequiredFeatureDE(casType, "sentId", "uima.cas.String", featOkTst);
    casFeatCode_sentId  = (null == casFeat_sentId) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentId).getCode();

 
    casFeat_neList = jcas.getRequiredFeatureDE(casType, "neList", "uima.cas.FSList", featOkTst);
    casFeatCode_neList  = (null == casFeat_neList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_neList).getCode();

  }
}



    