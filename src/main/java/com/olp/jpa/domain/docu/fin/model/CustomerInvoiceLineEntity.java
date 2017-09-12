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
import org.hibernate.search.annotations.Fields;
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
import com.olp.jpa.domain.docu.om.model.SalesOrderLineEntity;

/**
 * @author Jayesh
 *
 */

@Entity
@Table(name="trl_customer_invoice_lines", uniqueConstraints=@UniqueConstraint(columnNames={"tenant_id", "invoice_line_number"}))
@NamedQueries({
		@NamedQuery(name="CustomerInvoiceLine.findbyInvoiceLine", query="SELECT t from CustomerInvoiceLineEntity t WHERE t.invoiceNumber = :invoiceNumber and t.invoiceLineNumber = :invoiceLineNumber")
		})
@Cacheable(true)
@Indexed(index="SetupDataIndex")
@FullTextFilterDef(name="filter-customerInvoiceLine", impl=TenantBasedSearchFilterFactory.class)
@MultiTenant(level = MultiTenant.Levels.ONE_TENANT)
@SortCriteria(attributes={"invoiceLineNumber"})
public class CustomerInvoiceLineEntity implements Serializable {

	private static final long serialVersionUID = -7328641883311278045L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="invoice_line_id", nullable=false)
	private Long id;
	
	@KeyAttribute
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	@Column(name="tenant_id", nullable=false)
	private String tenantId;
	
	@KeyAttribute
	@Column(name="invoice_line_number", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private int invoiceLineNumber;
	
	@ManyToOne
	@JoinColumn(name="invoice_ref")
	@IndexedEmbedded(includeEmbeddedObjectId=true, depth=1)
	private CustomerInvoiceEntity invoiceRef;
	
	@Column(name="invoice_number", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private String invoiceNumber;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="order_line_ref")
	@IndexedEmbedded
	private SalesOrderLineEntity orderLineRef;
	
	@Column(name="order_line_number", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private int orderLineNumber;
	
	@Column(name="line_description", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private String lineDescription;
	
	@Column(name="quantity", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private float quantity;
	
	@Column(name="uom", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private String uom;
	
	@Column(name="list_price", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private float listPrice;
	
	@Column(name="unit_price", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private float unitPrice;
	
	@ManyToOne
	@JoinColumn(name="tax_group_ref")
	@IndexedEmbedded(includeEmbeddedObjectId=true, depth=1)
	private TaxGroupEntity taxGroupRef;
	
	@Column(name="tax_group_code", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private String taxGroupCode;
	
	@ManyToOne
	@JoinColumn(name="promo_group_ref")
	@IndexedEmbedded(includeEmbeddedObjectId=true, depth=1)
	private PromotionGroupEntity promoGroupRef;
	
	@Column(name="promo_group_code", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private String promoGroupCode;
	
	@Column(name="base_amount", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private float baseAmount;
	
	@Column(name="discount_amount", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private float discountAmount;
	
	@Column(name="net_amount", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private float netAmount;
	
	@Column(name="tax_amount", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private float taxAmount;
	
	@Column(name="line_total_amount", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private float lineTotalAmount;
	
	@Embedded
	@IndexedEmbedded
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
	public CustomerInvoiceEntity getInvoiceRef() {
		return invoiceRef;
	}

	/**
	 * @param invoiceRef the invoiceRef to set
	 */
	public void setInvoiceRef(CustomerInvoiceEntity invoiceRef) {
		this.invoiceRef = invoiceRef;
	}

	/**
	 * @return the invoiceNumber
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	/**
	 * @param invoiceNumber the invoiceNumber to set
	 */
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	/**
	 * @return the orderLineRef
	 */
	public SalesOrderLineEntity getOrderLineRef() {
		return orderLineRef;
	}

	/**
	 * @param orderLineRef the orderLineRef to set
	 */
	public void setOrderLineRef(SalesOrderLineEntity orderLineRef) {
		this.orderLineRef = orderLineRef;
	}

	/**
	 * @return the orderLineNumber
	 */
	public int getOrderLineNumber() {
		return orderLineNumber;
	}

	/**
	 * @param orderLineNumber the orderLineNumber to set
	 */
	public void setOrderLineNumber(int orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
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
	public TaxGroupEntity getTaxGroupRef() {
		return taxGroupRef;
	}

	/**
	 * @param taxGroupRef the taxGroupRef to set
	 */
	public void setTaxGroupRef(TaxGroupEntity taxGroupRef) {
		this.taxGroupRef = taxGroupRef;
	}

	/**
	 * @return the taxGroupCode
	 */
	public String getTaxGroupCode() {
		return taxGroupCode;
	}

	/**
	 * @param taxGroupCode the taxGroupCode to set
	 */
	public void setTaxGroupCode(String taxGroupCode) {
		this.taxGroupCode = taxGroupCode;
	}

	/**
	 * @return the promoGroupRef
	 */
	public PromotionGroupEntity getPromoGroupRef() {
		return promoGroupRef;
	}

	/**
	 * @param promoGroupRef the promoGroupRef to set
	 */
	public void setPromoGroupRef(PromotionGroupEntity promoGroupRef) {
		this.promoGroupRef = promoGroupRef;
	}

	/**
	 * @return the promoGroupCode
	 */
	public String getPromoGroupCode() {
		return promoGroupCode;
	}

	/**
	 * @param promoGroupCode the promoGroupCode to set
	 */
	public void setPromoGroupCode(String promoGroupCode) {
		this.promoGroupCode = promoGroupCode;
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
	
	public CustomerInvoiceLine convertTo(int mode) {
		CustomerInvoiceLine bean = new CustomerInvoiceLine();

		if (mode < 1) {
			bean.setId(id);
		}
		bean.setTenantId(tenantId);
		bean.setInvoiceLineNumber(invoiceLineNumber);
		bean.setInvoiceRef(invoiceRef.getInvoiceNumber());
		bean.setOrderLineRef(orderLineRef.getOrderNumber());
		bean.setLineDescription(lineDescription);
		bean.setQuantity(quantity);
		bean.setUom(uom);
		bean.setListPrice(listPrice);
		bean.setUnitPrice(unitPrice);
		bean.setTaxGroupRef(taxGroupRef.getGroupCode());
		bean.setPromoGroupRef(promoGroupRef.getGroupCode());
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
