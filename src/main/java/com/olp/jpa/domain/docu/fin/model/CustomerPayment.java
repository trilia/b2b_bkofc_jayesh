/**
 * 
 */
package com.olp.jpa.domain.docu.fin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.olp.jpa.common.RevisionControlBean;
import com.olp.jpa.domain.docu.mkt.model.PromotionGroupEntity;
import com.olp.jpa.domain.docu.om.model.SalesOrderLineEntity;

/**
 * @author Jayesh
 *
 */

public class CustomerPayment implements Serializable {
	
	private static final long serialVersionUID = 2230244850813663071L;

	private Long id;
	
	private Long tenantId;
	
	private String paymentNumber;
	
	private FinEnums.PaymentMethod paymentMethod;

	private String transactionRef;

	private String description;

	private Date paymentDate;

	private float amount;

	private String currency;
	
	private String invoiceRef;

	private String invoiceNumber;

	private String customerNumber;

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

	public CustomerPaymentEntity convertTo(int mode) {
		CustomerPaymentEntity bean = new CustomerPaymentEntity();

		if (mode < 1) {
			bean.setId(id);
		}
		bean.setTenantId(tenantId);
		bean.setPaymentMethod(paymentMethod);
		bean.setTransactionRef(transactionRef);
		bean.setDescription(description);
		bean.setPaymentDate(paymentDate);
		bean.setAmount(amount);
		bean.setCurrency(currency);
		if (!(this.invoiceRef == null || "".equals(this.invoiceRef))) {
			CustomerInvoiceEntity custInvEntity = new CustomerInvoiceEntity();
			custInvEntity.setInvoiceNumber(invoiceRef);
			bean.setInvoiceRef(custInvEntity);
			bean.setInvoiceNumber(custInvEntity.getInvoiceNumber());
        }
		
        if (this.revisionControl != null)
        	bean.setRevisionControl(this.revisionControl.clone());
		
		return bean;
	}
	
}
