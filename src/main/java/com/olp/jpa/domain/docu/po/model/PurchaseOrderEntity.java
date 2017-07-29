package com.olp.jpa.domain.docu.po.model;

import java.io.Serializable;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.ContainedIn;
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
import com.olp.jpa.domain.docu.be.model.SupplierEntity;
import com.olp.jpa.domain.docu.be.model.SupplierLocationEntity;
import com.olp.jpa.domain.docu.org.model.LocationEntity;

/**
 * Entity implementation class for Entity: PurchaseOrderEntity
 *
 */

@Entity
@Table(name="purchaseOrder", uniqueConstraints=@UniqueConstraint(columnNames={"tenant_id", "po_number"}))
@NamedQueries({
		@NamedQuery(name="PurchaseOrder.findByPurchaseOrder", query="SELECT t from PurchaseOrderEntity t WHERE t.poNumber = :poNumber and t.tenantId = :tenant"),
		@NamedQuery(name="PurchaseOrder.findByStatus", query="SELECT t from PurchaseOrderEntity t WHERE t.status = :name and t.tenantId = :tenant")
		})
@Cacheable(true)
@Indexed(index="SetupDataIndex")
@FullTextFilterDef(name="filter-purchaseorder", impl=TenantBasedSearchFilterFactory.class)
@MultiTenant(level = MultiTenant.Levels.ONE_TENANT)
@SortCriteria(attributes={"poNumber"})
public class PurchaseOrderEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="po_id", nullable=false)
	private Long id;
	
	@KeyAttribute
	@Field(index=Index.YES,store=Store.YES, analyze=Analyze.NO)
	@Column(name="tenant_id", nullable=false)
	private Long tenantId;
	
	@KeyAttribute
	@Column(name="po_number", nullable=false)
	@Field(index=Index.YES, store=Store.YES, analyze=Analyze.NO)
	private String poNumber;
	
	@Column(name="po_date", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	@Temporal(TemporalType.DATE)
	private Date poDate;
	
	@Column(name="description", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.YES)
	private String description;

	@Column(name="po_type", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	@Enumerated(EnumType.STRING)
	private PoType poType;

	@Column(name="fulfillment_date")
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	@Temporal(TemporalType.DATE)
	private Date fulfillmentDate;

	@Column(name="status")
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private String status;
	
	@OneToMany(mappedBy="purchaseOrderRef", cascade=CascadeType.ALL)
    @IndexedEmbedded(includeEmbeddedObjectId=true, depth=1)
    private List<PurchaseOrderLineEntity> poLines;

	@Column(name="pay_terms")
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.YES)
	private String payTerms;
	
	@Column(name="freight_terms")
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.YES)
	private String freightTerms;

	@Column(name="other_terms")
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.YES)
	private String otherTerms;
	
	@ManyToOne
	@JoinColumn(name="supplier_ref")
	@ContainedIn
	private SupplierEntity supplierRef;
	
	@Column(name="supplier_code")
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private String supplierCode;

	@ManyToOne
	@JoinColumn(name="supplier_loc_ref")
	@ContainedIn
	private SupplierLocationEntity supplierLocRef;
	
	@Column(name="location_code")
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private String locationCode;

	@ManyToOne
	@JoinColumn(name="shipping_location")
	@ContainedIn
	private LocationEntity shippingLocation;

	@ManyToOne
	@JoinColumn(name="billing_location")
	@ContainedIn
	private LocationEntity billingLocation;
	
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
	 * @return the poLines
	 */
	public List<PurchaseOrderLineEntity> getPoLines() {
		return poLines;
	}

	/**
	 * @param poLines the poLines to set
	 */
	public void setPoLines(List<PurchaseOrderLineEntity> poLines) {
		this.poLines = poLines;
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
	 * @return the locationCode
	 */
	public String getLocationCode() {
		return locationCode;
	}

	/**
	 * @param locationCode the locationCode to set
	 */
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	/**
	 * @return the supplierRef
	 */
	public SupplierEntity getSupplierRef() {
		return supplierRef;
	}

	/**
	 * @param supplierRef the supplierRef to set
	 */
	public void setSupplierRef(SupplierEntity supplierRef) {
		this.supplierRef = supplierRef;
	}

	/**
	 * @return the supplierLocRef
	 */
	public SupplierLocationEntity getSupplierLocRef() {
		return supplierLocRef;
	}

	/**
	 * @param supplierLocRef the supplierLocRef to set
	 */
	public void setSupplierLocRef(SupplierLocationEntity supplierLocRef) {
		this.supplierLocRef = supplierLocRef;
	}

	/**
	 * @return the shippingLocation
	 */
	public LocationEntity getShippingLocation() {
		return shippingLocation;
	}

	/**
	 * @param shippingLocation the shippingLocation to set
	 */
	public void setShippingLocation(LocationEntity shippingLocation) {
		this.shippingLocation = shippingLocation;
	}

	/**
	 * @return the billingLocation
	 */
	public LocationEntity getBillingLocation() {
		return billingLocation;
	}

	/**
	 * @param billingLocation the billingLocation to set
	 */
	public void setBillingLocation(LocationEntity billingLocation) {
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

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public PurchaseOrder convertTo(int mode) {
		PurchaseOrder bean = new PurchaseOrder();

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
		
		if (this.supplierRef != null){
			bean.setSupplierRef(supplierRef.getSupplierCode());
		}
		
		if (this.supplierLocRef != null){
			bean.setSupplierLocRef(supplierLocRef.getLocationCode());
		}
		
		if (this.shippingLocation != null){
			bean.setShippingLocation(shippingLocation.getLocationAlias());
		}
		
		if (this.billingLocation != null){
			bean.setBillingLocation(billingLocation.getLocationAlias());
		}
	           
		if (mode <= 0) {
			bean.setRevisionControl(revisionControl);
		}
		return bean;
	}
   
}