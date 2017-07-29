package com.olp.jpa.domain.docu.fin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.olp.jpa.common.RevisionControlBean;
import com.olp.jpa.domain.docu.cs.model.CustomerEntity;
import com.olp.jpa.domain.docu.mkt.model.PromotionGroupEntity;
import com.olp.jpa.domain.docu.om.model.SalesOrderEntity;

/**
 * @author Jayesh
 *
 */

public class CustomerInvoice implements Serializable {

	private static final long serialVersionUID = -7328641883311278045L;

	private Long id;
	
	private Long tenantId;
	
	private String invoiceNumber;

	private Date invoiceDate;
	
	private FinEnums.CustInvoiceType invoiceType;

	private FinEnums.PaymentTerm paymentTerm;

	private String customerRef;
	
	private String billingAddress;

	private String orderRef;
	
	private String taxGroupRef;
	
	private String invoiceCurrency;
	
	private String promoGroupRef;
	
	private float baseTotalAmount;
	
	private float discTotalAmount;
	
	private float netTotalAmount;
	
	private float taxTotalAmount;
	
	private float invoiceTotalAmount;

	private List<Integer> invoiceLines;

	private List<String> payments;
	
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
	public String getCustomerRef() {
		return customerRef;
	}

	/**
	 * @param customerRef the customerRef to set
	 */
	public void setCustomerRef(String customerRef) {
		this.customerRef = customerRef;
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
	public String getOrderRef() {
		return orderRef;
	}

	/**
	 * @param orderRef the orderRef to set
	 */
	public void setOrderRef(String orderRef) {
		this.orderRef = orderRef;
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
	public List<Integer> getInvoiceLines() {
		return invoiceLines;
	}

	/**
	 * @param invoiceLines the invoiceLines to set
	 */
	public void setInvoiceLines(List<Integer> invoiceLines) {
		this.invoiceLines = invoiceLines;
	}

	/**
	 * @return the payments
	 */
	public List<String> getPayments() {
		return payments;
	}

	/**
	 * @param payments the payments to set
	 */
	public void setPayments(List<String> payments) {
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

	public CustomerInvoiceEntity convertTo(int mode) {
		CustomerInvoiceEntity bean = new CustomerInvoiceEntity();

		if (mode < 1) {
			bean.setId(id);
		}
		bean.setTenantId(tenantId);
		bean.setInvoiceNumber(invoiceNumber);
		bean.setInvoiceDate(invoiceDate);
		bean.setInvoiceType(invoiceType);
		bean.setPaymentTerm(paymentTerm);
		if (!(this.customerRef == null || "".equals(this.customerRef))) {
			CustomerEntity customerBean = new CustomerEntity();
			customerBean.setCustomerCode(customerRef);
			bean.setCustomerRef(customerBean);
			bean.setCustomerCode(customerBean.getCustomerCode());
        }
		
		bean.setBillingAddress(billingAddress);
		
		if (!(this.orderRef == null || "".equals(this.orderRef))) {
			SalesOrderEntity salesOrderBean = new SalesOrderEntity();
			salesOrderBean.setOrderNumber(orderRef);
			bean.setOrderRef(salesOrderBean);
			bean.setOrderNumber(salesOrderBean.getOrderNumber());
        }
		
		if (!(this.taxGroupRef == null || "".equals(this.taxGroupRef))) {
			TaxGroupEntity taxGroup = new TaxGroupEntity();
			taxGroup.setGroupCode(taxGroupRef);
			bean.setTaxGroupRef(taxGroup);
			bean.setTaxGroupCode(taxGroup.getGroupCode());
        }
		
		bean.setInvoiceCurrency(invoiceCurrency);
		
		if (!(this.promoGroupRef == null || "".equals(this.promoGroupRef))) {
			PromotionGroupEntity promoGroup = new PromotionGroupEntity();
			promoGroup.setGroupCode(taxGroupRef);
			bean.setPromoGroupRef(promoGroup);
			bean.setPromoGroupCode(promoGroup.getGroupCode());
        }
		
		bean.setBaseTotalAmount(baseTotalAmount);
		bean.setDiscTotalAmount(discTotalAmount);
		bean.setNetTotalAmount(netTotalAmount);
		bean.setTaxTotalAmount(taxTotalAmount);
		bean.setInvoiceTotalAmount(invoiceTotalAmount);
		
		if (this.invoiceLines != null) {
            ArrayList<CustomerInvoiceLineEntity> invLines = new ArrayList<>();
            for (int invLine : this.invoiceLines) {
            	CustomerInvoiceLineEntity invLineEntity = new CustomerInvoiceLineEntity();
            	invLineEntity.setInvoiceLineNumber(invLine);
            	invLines.add(invLineEntity);
            }
            bean.setInvoiceLines(invLines);
        }
		
		if (this.payments != null) {
            ArrayList<CustomerPaymentEntity> payments = new ArrayList<>();
            for (String paymentNumber : this.payments) {
            	CustomerPaymentEntity paymentEntity = new CustomerPaymentEntity();
            	paymentEntity.setPaymentNumber(paymentNumber);
            	payments.add(paymentEntity);
            }
            bean.setPayments(payments);
        }
		
		if (mode <= 0) {
			bean.setRevisionControl(revisionControl);
		}
		return bean;
	}
	
}
