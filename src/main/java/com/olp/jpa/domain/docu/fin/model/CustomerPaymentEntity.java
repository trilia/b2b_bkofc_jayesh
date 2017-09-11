/**
 * 
 */
package com.olp.jpa.domain.docu.fin.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

/**
 * @author Jayesh
 *
 */

@Entity
@Table(name="trl_customer_payments", uniqueConstraints=@UniqueConstraint(columnNames={"tenant_id", "payment_number"}))
@NamedQueries({
		@NamedQuery(name="CustomerPayment.findbyPaymentNum", query="SELECT t from CustomerPaymentEntity t WHERE t.paymentNumber = :paymentNumber")
		})
@Cacheable(true)
@Indexed(index="SetupDataIndex")
@FullTextFilterDef(name="filter-customerpayment", impl=TenantBasedSearchFilterFactory.class)
@MultiTenant(level = MultiTenant.Levels.ONE_TENANT)
@SortCriteria(attributes={"paymentNumber"})
public class CustomerPaymentEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cust_payment_id", nullable=false)
	private Long id;
	
	@KeyAttribute
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	@Column(name="tenant_id", nullable=false)
	private Long tenantId;
	
	@KeyAttribute
	@Column(name="payment_number", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private String paymentNumber;
	
	@Column(name="payment_method", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	@Enumerated(EnumType.STRING)
	private FinEnums.PaymentMethod paymentMethod;

	@Column(name="transaction_ref", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private String transactionRef;

	@Column(name="description", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private String description;

	@Column(name="payment_date", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	@Temporal(TemporalType.DATE)
	private Date paymentDate;

	@Column(name="amount", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private float amount;

	@Column(name="currency", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private String currency;
	
	@ManyToOne
	@JoinColumn(name="invoice_ref")
	@IndexedEmbedded(includeEmbeddedObjectId=true, depth=1)
	private CustomerInvoiceEntity invoiceRef;

	@Column(name="invoice_number", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private String invoiceNumber;

	@Column(name="customer_number", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private String customerNumber;

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
	 * @return the paymentNumber
	 */
	public String getPaymentNumber() {
		return paymentNumber;
	}

	/**
	 * @param paymentNumber the paymentNumber to set
	 */
	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	/**
	 * @return the paymentMethod
	 */
	public FinEnums.PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(FinEnums.PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * @return the transactionRef
	 */
	public String getTransactionRef() {
		return transactionRef;
	}

	/**
	 * @param transactionRef the transactionRef to set
	 */
	public void setTransactionRef(String transactionRef) {
		this.transactionRef = transactionRef;
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
	 * @return the paymentDate
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
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
	 * @return the customerNumber
	 */
	public String getCustomerNumber() {
		return customerNumber;
	}

	/**
	 * @param customerNumber the customerNumber to set
	 */
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
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
	
	public CustomerPayment convertTo(int mode) {
		CustomerPayment bean = new CustomerPayment();

		if (mode < 1) {
			bean.setId(id);
		}
		bean.setTenantId(tenantId);
		bean.setPaymentNumber(paymentNumber);
		bean.setPaymentMethod(paymentMethod);
		bean.setTransactionRef(transactionRef);
		bean.setDescription(description);
		bean.setPaymentDate(paymentDate);
		bean.setAmount(amount);
		bean.setCurrency(currency);
		bean.setInvoiceRef(invoiceRef.getInvoiceNumber());
		
        if (this.revisionControl != null)
        	bean.setRevisionControl(this.revisionControl.clone());
		
		return bean;
	}
}
