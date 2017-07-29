package com.olp.jpa.domain.docu.fin.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FullTextFilterDef;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.olp.annotations.KeyAttribute;
import com.olp.annotations.MultiTenant;
import com.olp.annotations.SortCriteria;
import com.olp.jpa.common.RevisionControlBean;
import com.olp.jpa.common.TenantBasedSearchFilterFactory;
import com.olp.jpa.domain.docu.mkt.model.PromotionGroupEntity;
import com.olp.jpa.domain.docu.om.model.SalesOrderEntity;
import com.olp.jpa.domain.docu.om.model.SalesOrderLineEntity;

/**
 * @author Jayesh
 *
 */

public class CustomerInvoiceLine implements Serializable {

	private static final long serialVersionUID = -7328641883311278045L;

	private Long id;
	
	private Long tenantId;

	private int invoiceLineNumber;
	
	private String invoiceRef;
	
	private String orderLineRef;
	
	private String lineDescription;
	
	private float quantity;
	
	private String uom;
	
	private float listPrice;
	
	private float unitPrice;
	
	private String taxGroupRef;
	
	private String promoGroupRef;
	
	private float baseAmount;
	
	private float discountAmount;
	
	private float netAmount;
	
	private float taxAmount;
	
	private float lineTotalAmount;
	
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
	 * @return the invoiceLineNumber
	 */
	public int getInvoiceLineNumber() {
		return invoiceLineNumber;
	}

	/**
	 * @param invoiceLineNumber the invoiceLineNumber to set
	 */
	public void setInvoiceLineNumber(int invoiceLineNumber) {
		this.invoiceLineNumber = invoiceLineNumber;
	}

	/**
	 * @return the invoiceRef
	 */
	public String getInvoiceRef() {
		return invoiceRef;
	}

	/**
	 * @param invoiceRef the invoiceRef to set
	 */
	public void setInvoiceRef(String invoiceRef) {
		this.invoiceRef = invoiceRef;
	}

	/**
	 * @return the orderLineRef
	 */
	public String getOrderLineRef() {
		return orderLineRef;
	}

	/**
	 * @param orderLineRef the orderLineRef to set
	 */
	public void setOrderLineRef(String orderLineRef) {
		this.orderLineRef = orderLineRef;
	}

	/**
	 * @return the lineDescription
	 */
	public String getLineDescription() {
		return lineDescription;
	}

	/**
	 * @param lineDescription the lineDescription to set
	 */
	public void setLineDescription(String lineDescription) {
		this.lineDescription = lineDescription;
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
	 * @return the listPrice
	 */
	public float getListPrice() {
		return listPrice;
	}

	/**
	 * @param listPrice the listPrice to set
	 */
	public void setListPrice(float listPrice) {
		this.listPrice = listPrice;
	}

	/**
	 * @return the unitPrice
	 */
	public float getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the taxGroupRef
	 */
	public String getTaxGroupRef() {
		return taxGroupRef;
	}

	/**
	 * @param taxGroupRef the taxGroupRef to set
	 */
	public void setTaxGroupRef(String taxGroupRef) {
		this.taxGroupRef = taxGroupRef;
	}

	/**
	 * @return the promoGroupRef
	 */
	public String getPromoGroupRef() {
		return promoGroupRef;
	}

	/**
	 * @param promoGroupRef the promoGroupRef to set
	 */
	public void setPromoGroupRef(String promoGroupRef) {
		this.promoGroupRef = promoGroupRef;
	}

	/**
	 * @return the baseAmount
	 */
	public float getBaseAmount() {
		return baseAmount;
	}

	/**
	 * @param baseAmount the baseAmount to set
	 */
	public void setBaseAmount(float baseAmount) {
		this.baseAmount = baseAmount;
	}

	/**
	 * @return the discountAmount
	 */
	public float getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * @param discountAmount the discountAmount to set
	 */
	public void setDiscountAmount(float discountAmount) {
		this.discountAmount = discountAmount;
	}

	/**
	 * @return the netAmount
	 */
	public float getNetAmount() {
		return netAmount;
	}

	/**
	 * @param netAmount the netAmount to set
	 */
	public void setNetAmount(float netAmount) {
		this.netAmount = netAmount;
	}

	/**
	 * @return the taxAmount
	 */
	public float getTaxAmount() {
		return taxAmount;
	}

	/**
	 * @param taxAmount the taxAmount to set
	 */
	public void setTaxAmount(float taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * @return the lineTotalAmount
	 */
	public float getLineTotalAmount() {
		return lineTotalAmount;
	}

	/**
	 * @param lineTotalAmount the lineTotalAmount to set
	 */
	public void setLineTotalAmount(float lineTotalAmount) {
		this.lineTotalAmount = lineTotalAmount;
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
	
	public CustomerInvoiceLineEntity convertTo(int mode) {
		CustomerInvoiceLineEntity bean = new CustomerInvoiceLineEntity();

		if (mode < 1) {
			bean.setId(id);
		}
		bean.setTenantId(tenantId);
		bean.setInvoiceLineNumber(invoiceLineNumber);
		if (!(this.invoiceRef == null || "".equals(this.invoiceRef))) {
			CustomerInvoiceEntity custInvEntity = new CustomerInvoiceEntity();
			custInvEntity.setInvoiceNumber(invoiceRef);
			bean.setInvoiceRef(custInvEntity);
			bean.setInvoiceNumber(custInvEntity.getInvoiceNumber());
        }
		
		if (!(this.orderLineRef == null || "".equals(this.orderLineRef))) {
			SalesOrderLineEntity salesEntity = new SalesOrderLineEntity();
			salesEntity.setOrderNumber(orderLineRef);
			bean.setOrderLineRef(salesEntity);
			bean.setOrderLineNumber(salesEntity.getLineNumber());
        }
		
		bean.setLineDescription(lineDescription);
		bean.setQuantity(quantity);
		bean.setUom(uom);
		bean.setListPrice(listPrice);
		bean.setUnitPrice(unitPrice);
		
		if (!(this.taxGroupRef == null || "".equals(this.taxGroupRef))) {
			TaxGroupEntity taxGroup = new TaxGroupEntity();
			taxGroup.setGroupCode(taxGroupRef);
			bean.setTaxGroupRef(taxGroup);
			bean.setTaxGroupCode(taxGroup.getGroupCode());
        }
		
		if (!(this.promoGroupRef == null || "".equals(this.promoGroupRef))) {
			PromotionGroupEntity promoGroup = new PromotionGroupEntity();
			promoGroup.setGroupCode(taxGroupRef);
			bean.setPromoGroupRef(promoGroup);
			bean.setPromoGroupCode(promoGroup.getGroupCode());
        }
		
		bean.setBaseAmount(baseAmount);
		bean.setDiscountAmount(discountAmount);
		bean.setNetAmount(netAmount);
		bean.setTaxAmount(taxAmount);
		bean.setLineTotalAmount(lineTotalAmount);
		
        if (this.revisionControl != null)
        	bean.setRevisionControl(this.revisionControl.clone());
		
		return bean;
	}

}
