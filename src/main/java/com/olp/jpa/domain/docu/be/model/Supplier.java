/**
 * 
 */
package com.olp.jpa.domain.docu.be.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.olp.jpa.common.RevisionControlBean;

/**
 * @author Jayesh
 *
 */

@XmlRootElement(name="suppliers", namespace="http://trilia-cloud.com/schema/entity/ut/ut-suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlAccessorOrder(value=XmlAccessOrder.UNDEFINED)
@XmlType(propOrder={"id", "supplierCode", "supplierName","legalInfo", "revisionControl", "supplierLocations"})
public class Supplier implements Serializable {
	
private static final long serialVersionUID = -1L;
    
    @XmlElement(name="partner-id")
    private Long id;
    
    @XmlTransient
    private String tenantId;
    
    @XmlAttribute(name="supplier-code")
    private String supplierCode;
    
    @XmlElement(name="supplier-name")
    private String supplierName;
    
    @XmlElement(name="legal-info")
    private List<String> legalInfo;
    
    private RevisionControlBean revisionControl;
    
    @XmlElement(name="supplier-location")
    private List<String> supplierLocations;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the tenantId
	 */
	public String getTenantId() {
		return tenantId;
	}

	/**
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return the supplierCode
	 */
	public String getSupplierCode() {
		return supplierCode;
	}

	/**
	 * @param supplierCode the supplierCode to set
	 */
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	/**
	 * @return the supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * @param supplierName the supplierName to set
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * @return the legalInfo
	 */
	public List<String> getLegalInfo() {
		return legalInfo;
	}

	/**
	 * @param legalInfo the legalInfo to set
	 */
	public void setLegalInfo(List<String> legalInfo) {
		this.legalInfo = legalInfo;
	}

	/**
	 * @return the revisionControl
	 */
	public RevisionControlBean getRevisionControl() {
		return revisionControl;
	}

	/**
	 * @param revisionControl the revisionControl to set
	 */
	public void setRevisionControl(RevisionControlBean revisionControl) {
		this.revisionControl = revisionControl;
	}

	/**
	 * @return the supplierLocations
	 */
	public List<String> getSupplierLocations() {
		return supplierLocations;
	}

	/**
	 * @param supplierLocations the supplierLocations to set
	 */
	public void setSupplierLocations(List<String> supplierLocations) {
		this.supplierLocations = supplierLocations;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
public SupplierEntity convertTo() {
        
        SupplierEntity bean = new SupplierEntity();
        
        bean.setId(this.id);
        bean.setTenantId(this.tenantId);
        bean.setSupplierCode(this.supplierCode);
        bean.setSupplierName(this.supplierName);
        
        if (this.legalInfo != null) {
            ArrayList<LegalInfoBean> legalInfos = new ArrayList<>();
            for (String legalInfoCode : this.legalInfo) {
            	LegalInfoBean legalInfoBean = new LegalInfoBean();
            	legalInfoBean.setAttrName(legalInfoCode);
                legalInfos.add(legalInfoBean);
            }
            bean.setLegalInfo(legalInfos);
        }
        
        if (this.revisionControl != null)
            bean.setRevisionControl(this.revisionControl.clone());
        
        if (this.supplierLocations != null) {
            ArrayList<SupplierLocationEntity> supps = new ArrayList<>();
            for (String supplierLocationCode : this.supplierLocations) {
            	SupplierLocationEntity supp = new SupplierLocationEntity();
            	supp.setLocationCode(supplierLocationCode);
            	supps.add(supp);
            }
            bean.setSupplierLocations(supps);
        }
        return(bean);
    }
}
