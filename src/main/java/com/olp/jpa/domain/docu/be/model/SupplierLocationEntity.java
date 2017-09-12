package com.olp.jpa.domain.docu.be.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.ContainedIn;
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
import com.olp.jpa.domain.docu.org.model.LocationEntity;

/**
 * @author Jayesh
 *
 */
@Entity
@Table(name="trl_supplier_locations", uniqueConstraints={@javax.persistence.UniqueConstraint(columnNames={"tenant_id", "location_code"})})
@Indexed(index="SetupDataIndex")
@FullTextFilterDef(name="filter-locations", impl=TenantBasedSearchFilterFactory.class)
@NamedQueries({@javax.persistence.NamedQuery(name="Supplier.findByLocationCode", query="SELECT t from SupplierLocationEntity t WHERE t.locationCode = :code ")})
@MultiTenant(level=MultiTenant.Levels.ONE_TENANT)
@SortCriteria(attributes={"locationCode"})
public class SupplierLocationEntity  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="supplier_loc_id", nullable=false)
	private Long id;
	
	@KeyAttribute
	@Fields({
		@Field(index=Index.NO, store=Store.YES, analyze=Analyze.NO)
	})
	@Column(name="tenant_id", nullable=false)
	private String tenantId;
	
	@KeyAttribute
	@Column(name="location_code", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private String locationCode;
	
	@Column(name="shipping_location", nullable=false)
	@Fields({
        @Field,
        @Field(name="shippingLocation-sort", index=Index.YES, store=Store.NO, analyze=Analyze.NO)
    })
	private boolean isShippingLocation;
	
	@Column(name="billing_location", nullable=false)
	@Fields({
        @Field,
        @Field(name="billingLocation-sort", index=Index.YES, store=Store.NO, analyze=Analyze.NO)
    })
	private boolean isBillingLocation;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="location_ref")
	private LocationEntity location;
	
	@Embedded
	@IndexedEmbedded
	private RevisionControlBean revisionControl;
	
	@OneToMany(mappedBy="supplierLocRef", cascade={javax.persistence.CascadeType.ALL})
	@IndexedEmbedded(includeEmbeddedObjectId=true, depth=1)
	private List<BankAccountEntity> bankAccounts = new ArrayList();
	
	@ManyToOne
	@JoinColumn(name="supplier_ref")
	@ContainedIn
	private SupplierEntity supplierRef;

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
	 * @return the isShippingLocation
	 */
	public boolean isShippingLocation() {
		return isShippingLocation;
	}

	/**
	 * @param isShippingLocation the isShippingLocation to set
	 */
	public void setShippingLocation(boolean isShippingLocation) {
		this.isShippingLocation = isShippingLocation;
	}

	/**
	 * @return the isBillingLocation
	 */
	public boolean isBillingLocation() {
		return isBillingLocation;
	}

	/**
	 * @param isBillingLocation the isBillingLocation to set
	 */
	public void setBillingLocation(boolean isBillingLocation) {
		this.isBillingLocation = isBillingLocation;
	}

	/**
	 * @return the location
	 */
	public LocationEntity getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(LocationEntity location) {
		this.location = location;
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
	 * @return the bankAccounts
	 */
	public List<BankAccountEntity> getBankAccounts() {
		return bankAccounts;
	}

	/**
	 * @param bankAccounts the bankAccounts to set
	 */
	public void setBankAccounts(List<BankAccountEntity> bankAccounts) {
		this.bankAccounts = bankAccounts;
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


}
