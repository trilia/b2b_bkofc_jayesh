/**
 * 
 */
package com.olp.jpa.domain.docu.po.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.olp.annotations.KeyAttribute;
import com.olp.jpa.common.RevisionControlBean;
import com.olp.jpa.domain.docu.inv.model.ProductSkuEntity;

/**
 * @author Jayesh
 *
 */
@XmlRootElement(name="purchaseOrderLine", namespace="http://trilia-cloud.com/schema/entity/ut/ut-purchaseorderline")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlAccessorOrder(value=XmlAccessOrder.UNDEFINED)
@XmlType(propOrder={"id", "poNumber", "poDate","description", "poType", "fulfillmentDate","status","payTerms","freightTerms","otherTerms","supplierRef","supplierLocRef","shippingLocation","billingLocation","revisionControl"})
public class PurchaseOrderLine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5658903805482308969L;
	
	@XmlElement(name="po-id")
	private Long id;
	
	@XmlTransient
	private Long tenantId;
	
	@XmlElement(name="po-number")
	private String lineNumber;
	
	@XmlElement(name="line-type")
	private PoLineType lineType;
	
	@XmlElement(name="sku-ref")
	private ProductSkuEntity skuRef;

	@XmlElement(name="prod-desc")
	private String prodDesc;

	@XmlElement(name="quantity")
	private float quantity;

	@XmlElement(name="uom")
	private String uom;
	
	@XmlElement(name="unit-rate")
	private String unitRate;

	@XmlElement(name="rate-variance")
	private float rateVariance;
	
	@XmlElement(name="warranty-terms")
	private float warrantyTerms;
	
	private RevisionControlBean revisionControl;

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
	public Long getTenantId() {
		return tenantId;
	}

	/**
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return the lineNumber
	 */
	public String getLineNumber() {
		return lineNumber;
	}

	/**
	 * @param lineNumber the lineNumber to set
	 */
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * @return the lineType
	 */
	public PoLineType getLineType() {
		return lineType;
	}

	/**
	 * @param lineType the lineType to set
	 */
	public void setLineType(PoLineType lineType) {
		this.lineType = lineType;
	}

	/**
	 * @return the skuRef
	 */
	public ProductSkuEntity getSkuRef() {
		return skuRef;
	}

	/**
	 * @param skuRef the skuRef to set
	 */
	public void setSkuRef(ProductSkuEntity skuRef) {
		this.skuRef = skuRef;
	}

	/**
	 * @return the prodDesc
	 */
	public String getProdDesc() {
		return prodDesc;
	}

	/**
	 * @param prodDesc the prodDesc to set
	 */
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}

	/**
	 * @return the quantity
	 */
	public float getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the uom
	 */
	public String getUom() {
		return uom;
	}

	/**
	 * @param uom the uom to set
	 */
	public void setUom(String uom) {
		this.uom = uom;
	}

	/**
	 * @return the unitRate
	 */
	public String getUnitRate() {
		return unitRate;
	}

	/**
	 * @param unitRate the unitRate to set
	 */
	public void setUnitRate(String unitRate) {
		this.unitRate = unitRate;
	}

	/**
	 * @return the rateVariance
	 */
	public float getRateVariance() {
		return rateVariance;
	}

	/**
	 * @param rateVariance the rateVariance to set
	 */
	public void setRateVariance(float rateVariance) {
		this.rateVariance = rateVariance;
	}

	/**
	 * @return the warrantyTerms
	 */
	public float getWarrantyTerms() {
		return warrantyTerms;
	}

	/**
	 * @param warrantyTerms the warrantyTerms to set
	 */
	public void setWarrantyTerms(float warrantyTerms) {
		this.warrantyTerms = warrantyTerms;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PurchaseOrderLineEntity convertTo(int mode) {
		PurchaseOrderLineEntity bean = new PurchaseOrderLineEntity();

		if (mode < 1) {
			bean.setId(id);
		}
		bean.setTenantId(tenantId);
		bean.setLineNumber(lineNumber);
		bean.setLineType(lineType);
		bean.setSkuRef(skuRef);
		bean.setProdDesc(prodDesc);
		bean.setQuantity(quantity);
		bean.setUom(uom);
		bean.setUnitRate(unitRate);
		bean.setRateVariance(rateVariance);
		bean.setWarrantyTerms(warrantyTerms);
				return bean;
	}
	
}
