
/* First created by JCasGen Tue Oct 16 09:18:23 EDT 2012 */
package util;

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
 * Updated by JCasGen Tue Oct 16 17:06:01 EDT 2012
 * @generated */
public class NamedEntity_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (NamedEntity_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = NamedEntity_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new NamedEntity(addr, NamedEntity_Type.this);
  			   NamedEntity_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new NamedEntity(addr, NamedEntity_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = NamedEntity.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("util.NamedEntity");
 
  /** @generated */
  final Feature casFeat_neType;
  /** @generated */
  final int     casFeatCode_neType;
  /** @generated */ 
  public String getNeType(int addr) {
        if (featOkTst && casFeat_neType == null)
      jcas.throwFeatMissing("neType", "util.NamedEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_neType);
  }
  /** @generated */    
  public void setNeType(int addr, String v) {
        if (featOkTst && casFeat_neType == null)
      jcas.throwFeatMissing("neType", "util.NamedEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_neType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_text;
  /** @generated */
  final int     casFeatCode_text;
  /** @generated */ 
  public String getText(int addr) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "util.NamedEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_text);
  }
  /** @generated */    
  public void setText(int addr, String v) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "util.NamedEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_text, v);}
    
  
 
  /** @generated */
  final Feature casFeat_sentId;
  /** @generated */
  final int     casFeatCode_sentId;
  /** @generated */ 
  public String getSentId(int addr) {
        if (featOkTst && casFeat_sentId == null)
      jcas.throwFeatMissing("sentId", "util.NamedEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_sentId);
  }
  /** @generated */    
  public void setSentId(int addr, String v) {
        if (featOkTst && casFeat_sentId == null)
      jcas.throwFeatMissing("sentId", "util.NamedEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_sentId, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public NamedEntity_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_neType = jcas.getRequiredFeatureDE(casType, "neType", "uima.cas.String", featOkTst);
    casFeatCode_neType  = (null == casFeat_neType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_neType).getCode();

 
    casFeat_text = jcas.getRequiredFeatureDE(casType, "text", "uima.cas.String", featOkTst);
    casFeatCode_text  = (null == casFeat_text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_text).getCode();

 
    casFeat_sentId = jcas.getRequiredFeatureDE(casType, "sentId", "uima.cas.String", featOkTst);
    casFeatCode_sentId  = (null == casFeat_sentId) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentId).getCode();

  }
}



    