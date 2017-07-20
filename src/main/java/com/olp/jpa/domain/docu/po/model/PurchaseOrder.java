package com.olp.jpa.domain.docu.po.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.olp.jpa.common.RevisionControlBean;
import com.olp.jpa.domain.docu.be.model.LegalInfoBean;
import com.olp.jpa.domain.docu.be.model.Supplier;
import com.olp.jpa.domain.docu.be.model.SupplierEntity;
import com.olp.jpa.domain.docu.be.model.SupplierLocationEntity;
import com.olp.jpa.domain.docu.org.model.LocationEntity;
import com.olp.jpa.domain.docu.ut.model.DepartmentBean;

/**
 * Entity implementation class for Entity: PurchaseOrderEntity
 *
 */

@XmlRootElement(name="purchaseOrder", namespace="http://trilia-cloud.com/schema/entity/ut/ut-purchaseorder")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlAccessorOrder(value=XmlAccessOrder.UNDEFINED)
@XmlType(propOrder={"id", "poNumber", "poDate","description", "poType", "fulfillmentDate","status","payTerms","freightTerms","otherTerms","supplierRef","supplierLocRef","shippingLocation","billingLocation","revisionControl"})
public class PurchaseOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="po-id")
	private Long id;
	
	@XmlTransient
	private Long tenantId;
	
	@XmlElement(name="po-number")
	private String poNumber;

	@XmlElement(name="po-date")
	private Date poDate;

	@XmlElement(name="description")
	private String description;

	@XmlElement(name="po-type")
	private PoType poType;

	@XmlElement(name="fulfillment-date")
	private Date fulfillmentDate;

	@XmlElement(name="status")
	private String status;

	@XmlElement(name="pay-terms")
	private String payTerms;

	@XmlElement(name="freight-terms")
	private String freightTerms;

	@XmlElement(name="other-terms")
	private String otherTerms;

	@XmlElement(name="supplier-ref")
	private String supplierRef;

	@XmlElement(name="supplier-loc-ref")
	private String supplierLocRef;

	@XmlElement(name="shipping-location")
	private String shippingLocation;

	@XmlElement(name="billing-location")
	private String billingLocation;

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
	 * @return the poNumber
	 */
	public String getPoNumber() {
		return poNumber;
	}

	/**
	 * @param poNumber the poNumber to set
	 */
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	/**
	 * @return the poDate
	 */
	public Date getPoDate() {
		return poDate;
	}

	/**
	 * @param poDate the poDate to set
	 */
	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the poType
	 */
	public PoType getPoType() {
		return poType;
	}

	/**
	 * @param poType the poType to set
	 */
	public void setPoType(PoType poType) {
		this.poType = poType;
	}

	/**
	 * @return the fulfillmentDate
	 */
	public Date getFulfillmentDate() {
		return fulfillmentDate;
	}

	/**
	 * @param fulfillmentDate the fulfillmentDate to set
	 */
	public void setFulfillmentDate(Date fulfillmentDate) {
		this.fulfillmentDate = fulfillmentDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the payTerms
	 */
	public String getPayTerms() {
		return payTerms;
	}

	/**
	 * @param payTerms the payTerms to set
	 */
	public void setPayTerms(String payTerms) {
		this.payTerms = payTerms;
	}

	/**
	 * @return the freightTerms
	 */
	public String getFreightTerms() {
		return freightTerms;
	}

	/**
	 * @param freightTerms the freightTerms to set
	 */
	public void setFreightTerms(String freightTerms) {
		this.freightTerms = freightTerms;
	}

	/**
	 * @return the otherTerms
	 */
	public String getOtherTerms() {
		return otherTerms;
	}

	/**
	 * @param otherTerms the otherTerms to set
	 */
	public void setOtherTerms(String otherTerms) {
		this.otherTerms = otherTerms;
	}

	/**
	 * @return the supplierRef
	 */
	public String getSupplierRef() {
		return supplierRef;
	}

	/**
	 * @param supplierRef the supplierRef to set
	 */
	public void setSupplierRef(String supplierRef) {
		this.supplierRef = supplierRef;
	}

	/**
	 * @return the supplierLocRef
	 */
	public String getSupplierLocRef() {
		return supplierLocRef;
	}

	/**
	 * @param supplierLocRef the supplierLocRef to set
	 */
	public void setSupplierLocRef(String supplierLocRef) {
		this.supplierLocRef = supplierLocRef;
	}

	/**
	 * @return the shippingLocation
	 */
	public String getShippingLocation() {
		return shippingLocation;
	}

	/**
	 * @param shippingLocation the shippingLocation to set
	 */
	public void setShippingLocation(String shippingLocation) {
		this.shippingLocation = shippingLocation;
	}

	/**
	 * @return the billingLocation
	 */
	public String getBillingLocation() {
		return billingLocation;
	}

	/**
	 * @param billingLocation the billingLocation to set
	 */
	public void setBillingLocation(String billingLocation) {
		this.billingLocation = billingLocation;
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
	
	public PurchaseOrderEntity convertTo(int mode) {
		PurchaseOrderEntity bean = new PurchaseOrderEntity();

		if (mode < 1) {
			bean.setId(id);
		}
		bean.setTenantId(tenantId);
		bean.setPoNumber(poNumber);
		bean.setPoDate(poDate);
		bean.setDescription(description);
		bean.setPoType(getPoType());
		bean.setFulfillmentDate(fulfillmentDate);
		bean.setStatus(status);
		bean.setPayTerms(payTerms);
		bean.setFreightTerms(freightTerms);
		bean.setOtherTerms(otherTerms);
		
		if (!(this.supplierRef == null || "".equals(this.supplierRef))) {
            SupplierEntity suppBean = new SupplierEntity();
            suppBean.setSupplierCode(supplierRef);
            bean.setSupplierRef(suppBean);
        }
		
		if (!(this.supplierLocRef == null || "".equals(this.supplierLocRef))) {
			SupplierLocationEntity suppLocBean = new SupplierLocationEntity();
			suppLocBean.setLocationCode(supplierLocRef);
			bean.setSupplierLocRef(suppLocBean);
        }
		
		if (!(this.shippingLocation == null || "".equals(this.shippingLocation))) {
			LocationEntity locBean = new LocationEntity();
			locBean.setLocationAlias(shippingLocation);
			bean.setShippingLocation(locBean);
        }

		if (!(this.billingLocation == null || "".equals(this.billingLocation))) {
			LocationEntity locBean = new LocationEntity();
			locBean.setLocationAlias(billingLocation);
			bean.setBillingLocation(locBean);
        }
		
		if (mode <= 0) {
			bean.setRevisionControl(revisionControl);
		}
		return bean;
	}

}
