package com.olp.jpa.domain.docu.fin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
import com.olp.jpa.domain.docu.cs.model.CustomerEntity;
import com.olp.jpa.domain.docu.mkt.model.PromotionGroupEntity;
import com.olp.jpa.domain.docu.om.model.SalesOrderEntity;
import com.olp.jpa.domain.docu.om.model.SalesOrderLine;
import com.olp.jpa.domain.docu.ut.model.EmployeeBean;

/**
 * @author Jayesh
 *
 */

@Entity
@Table(name="trl_customer_invoices", uniqueConstraints=@UniqueConstraint(columnNames={"tenant_id", "invoice_number"}))
@NamedQueries({
		@NamedQuery(name="CustomerInvoice.findbyInvoiceNumber", query="SELECT t from CustomerInvoiceEntity t WHERE t.invoiceNumber = :invoiceNumber")
		})
@Cacheable(true)
@Indexed(index="SetupDataIndex")
@FullTextFilterDef(name="filter-customerInvoice", impl=TenantBasedSearchFilterFactory.class)
@MultiTenant(level = MultiTenant.Levels.ONE_TENANT)
@SortCriteria(attributes={"invoiceNumber"})
public class CustomerInvoiceEntity implements Serializable {

	private static final long serialVersionUID = -7328641883311278045L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="invoice_id", nullable=false)
	private Long id;
	
	@KeyAttribute
	@Field(store=Store.YES, analyze=Analyze.NO)
	@Column(name="tenant_id", nullable=false)
	private Long tenantId;
	
	@KeyAttribute
	@Column(name="invoice_number", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private String invoiceNumber;

	@Column(name="invoice_date", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	@Temporal(TemporalType.DATE)
	private Date invoiceDate;
	
	@Column(name="invoice_type", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	@Enumerated(EnumType.STRING)
	private FinEnums.CustInvoiceType invoiceType;

	@Column(name="payment_term", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	@Enumerated(EnumType.STRING)
	private FinEnums.PaymentTerm paymentTerm;

	@Column(name="customer_ref", nullable=false)
	@ManyToOne
	private CustomerEntity customerRef;
	
	@Column(name="customer_code", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private String customerCode;

	@Column(name="billing_address", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private String billingAddress;

	@Column(name="order_ref", nullable=false)
	@OneToOne(cascade = CascadeType.ALL)
	private SalesOrderEntity orderRef;
	
	@Column(name="order_number", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private String orderNumber;
	
	@Column(name="tax_group_ref", nullable=false)
	@ManyToOne
	private TaxGroupEntity taxGroupRef;
	
	@Column(name="tax_group_code", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private String taxGroupCode;
	
	@Column(name="invoice_currency", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private String invoiceCurrency;
	
	@Column(name="promo_group_ref", nullable=false)
	@ManyToOne
	private PromotionGroupEntity promoGroupRef;
	
	@Column(name="promo_group_code", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private String promoGroupCode;
	
	@Column(name="base_total_amount", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private float baseTotalAmount;
	
	@Column(name="disc_total_amount", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private float discTotalAmount;
	
	@Column(name="net_total_amount", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private float netTotalAmount;
	
	@Column(name="tax_total_amount", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private float taxTotalAmount;
	
	@Column(name="invoice_total_amount", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private float invoiceTotalAmount;

	@OneToMany(mappedBy="custInvoiceRef", cascade={javax.persistence.CascadeType.PERSIST})
	private List<CustomerInvoiceLineEntity> invoiceLines;

	@OneToMany(mappedBy="custPaymentRef", cascade={javax.persistence.CascadeType.PERSIST})
	private List<CustomerPaymentEntity> payments;
	
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
	 * @return the invoiceDate
	 */
	public Date getInvoiceDate() {
		return invoiceDate;
	}

	/**
	 * @param invoiceDate the invoiceDate to set
	 */
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	/**
	 * @return the invoiceType
	 */
	public FinEnums.CustInvoiceType getInvoiceType() {
		return invoiceType;
	}

	/**
	 * @param invoiceType the invoiceType to set
	 */
	public void setInvoiceType(FinEnums.CustInvoiceType invoiceType) {
		this.invoiceType = invoiceType;
	}

	/**
	 * @return the paymentTerm
	 */
	public FinEnums.PaymentTerm getPaymentTerm() {
		return paymentTerm;
	}

	/**
	 * @param paymentTerm the paymentTerm to set
	 */
	public void setPaymentTerm(FinEnums.PaymentTerm paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	/**
	 * @return the customerRef
	 */
	public CustomerEntity getCustomerRef() {
		return customerRef;
	}

	/**
	 * @param customerRef the customerRef to set
	 */
	public void setCustomerRef(CustomerEntity customerRef) {
		this.customerRef = customerRef;
	}

	/**
	 * @return the customerCode
	 */
	public String getCustomerCode() {
		return customerCode;
	}

	/**
	 * @param customerCode the customerCode to set
	 */
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	/**
	 * @return the billingAddress
	 */
	public String getBillingAddress() {
		return billingAddress;
	}

	/**
	 * @param billingAddress the billingAddress to set
	 */
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	/**
	 * @return the orderRef
	 */
	public SalesOrderEntity getOrderRef() {
		return orderRef;
	}

	/**
	 * @param orderRef the orderRef to set
	 */
	public void setOrderRef(SalesOrderEntity orderRef) {
		this.orderRef = orderRef;
	}

	/**
	 * @return the orderNumber
	 */
	public String getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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
	 * @return the invoiceCurrency
	 */
	public String getInvoiceCurrency() {
		return invoiceCurrency;
	}

	/**
	 * @param invoiceCurrency the invoiceCurrency to set
	 */
	public void setInvoiceCurrency(String invoiceCurrency) {
		this.invoiceCurrency = invoiceCurrency;
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
	 * @return the baseTotalAmount
	 */
	public float getBaseTotalAmount() {
		return baseTotalAmount;
	}

	/**
	 * @param baseTotalAmount the baseTotalAmount to set
	 */
	public void setBaseTotalAmount(float baseTotalAmount) {
		this.baseTotalAmount = baseTotalAmount;
	}

	/**
	 * @return the discTotalAmount
	 */
	public float getDiscTotalAmount() {
		return discTotalAmount;
	}

	/**
	 * @param discTotalAmount the discTotalAmount to set
	 */
	public void setDiscTotalAmount(float discTotalAmount) {
		this.discTotalAmount = discTotalAmount;
	}

	/**
	 * @return the netTotalAmount
	 */
	public float getNetTotalAmount() {
		return netTotalAmount;
	}

	/**
	 * @param netTotalAmount the netTotalAmount to set
	 */
	public void setNetTotalAmount(float netTotalAmount) {
		this.netTotalAmount = netTotalAmount;
	}

	/**
	 * @return the taxTotalAmount
	 */
	public float getTaxTotalAmount() {
		return taxTotalAmount;
	}

	/**
	 * @param taxTotalAmount the taxTotalAmount to set
	 */
	public void setTaxTotalAmount(float taxTotalAmount) {
		this.taxTotalAmount = taxTotalAmount;
	}

	/**
	 * @return the invoiceTotalAmount
	 */
	public float getInvoiceTotalAmount() {
		return invoiceTotalAmount;
	}

	/**
	 * @param invoiceTotalAmount the invoiceTotalAmount to set
	 */
	public void setInvoiceTotalAmount(float invoiceTotalAmount) {
		this.invoiceTotalAmount = invoiceTotalAmount;
	}

	/**
	 * @return the invoiceLines
	 */
	public List<CustomerInvoiceLineEntity> getInvoiceLines() {
		return invoiceLines;
	}

	/**
	 * @param invoiceLines the invoiceLines to set
	 */
	public void setInvoiceLines(List<CustomerInvoiceLineEntity> invoiceLines) {
		this.invoiceLines = invoiceLines;
	}

	/**
	 * @return the payments
	 */
	public List<CustomerPaymentEntity> getPayments() {
		return payments;
	}

	/**
	 * @param payments the payments to set
	 */
	public void setPayments(List<CustomerPaymentEntity> payments) {
		this.payments = payments;
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
	
	public CustomerInvoice convertTo(int mode) {
		CustomerInvoice bean = new CustomerInvoice();

		if (mode < 1) {
			bean.setId(id);
		}
		bean.setTenantId(tenantId);
		bean.setInvoiceNumber(invoiceNumber);
		bean.setInvoiceDate(invoiceDate);
		bean.setInvoiceType(invoiceType);
		bean.setPaymentTerm(paymentTerm);
		bean.setCustomerRef(customerRef.getCustomerCode());
		bean.setBillingAddress(billingAddress);
		bean.setOrderRef(orderRef.getOrderNumber());
		bean.setTaxGroupRef(taxGroupRef.getGroupCode());
		bean.setInvoiceCurrency(invoiceCurrency);
		bean.setPromoGroupRef(promoGroupRef.getGroupCode());
		bean.setBaseTotalAmount(baseTotalAmount);
		bean.setDiscTotalAmount(discTotalAmount);
		bean.setNetTotalAmount(netTotalAmount);
		bean.setTaxTotalAmount(taxTotalAmount);
		bean.setInvoiceTotalAmount(invoiceTotalAmount);
		
		ArrayList<Integer> list = this.invoiceLines == null ? null : new ArrayList<Integer>();
        for (int i=0; this.invoiceLines != null && i < this.invoiceLines.size(); i++) {
        	CustomerInvoiceLineEntity invLine = invoiceLines.get(i);
            list.add(invLine.getInvoiceLineNumber());
        }
        bean.setInvoiceLines(list);

		ArrayList<String> payList = this.payments == null ? null : new ArrayList<String>();
        for (int i=0; this.payments != null && i < this.payments.size(); i++) {
        	CustomerPaymentEntity payLine = payments.get(i);
        	payList.add(payLine.getPaymentNumber());
        }
        bean.setPayments(payList);
        
        if (this.revisionControl != null)
        	bean.setRevisionControl(this.revisionControl.clone());
		
		return bean;
	}
	
}
