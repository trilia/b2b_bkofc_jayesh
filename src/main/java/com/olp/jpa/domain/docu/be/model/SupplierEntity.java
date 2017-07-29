package com.olp.jpa.domain.docu.be.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
import com.olp.jpa.domain.docu.be.model.LegalInfoBean;
import com.olp.jpa.domain.docu.be.model.Supplier;
import com.olp.jpa.domain.docu.org.model.Department;
import com.olp.jpa.domain.docu.org.model.EmployeeAssignmentEntity;
import com.olp.jpa.domain.docu.ut.model.EmployeeBean;

/**
 * @author Jayesh
 *
 */

@Entity
@Table(name="trl_suppliers", uniqueConstraints=@UniqueConstraint(columnNames={"tenant_id", "supplier_code"}))
@NamedQueries({
		@NamedQuery(name="Supplier.findBySupplierCode", query="SELECT t from SupplierEntity t WHERE t.supplierCode = :code and t.tenantId = :tenant"),
		@NamedQuery(name="Supplier.findBySupplierName", query="SELECT t from SupplierEntity t WHERE t.supplierName = :name and t.tenantId = :tenant")
		})
@Cacheable(true)
@Indexed(index="SetupDataIndex")
@FullTextFilterDef(name="filter-suppliers", impl=TenantBasedSearchFilterFactory.class)
@MultiTenant(level = MultiTenant.Levels.ONE_TENANT)
@SortCriteria(attributes={"supplierCode"})
public class SupplierEntity implements Serializable {
	
	private static final long serialVersionUID = 2631709848014113815L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="partner_id", nullable=false)
	private Long id;
	
	@KeyAttribute
	@Field(index=Index.NO,store=Store.YES, analyze=Analyze.NO)
	@Column(name="tenant_id", nullable=false)
	private String tenantId;
	
	@KeyAttribute
	@Column(name="supplier_code", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private String supplierCode;
	
	@Column(name="supplier_name", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private String supplierName;
	
	@Column(name="legal_info", nullable=false)
	@Embedded
	@IndexedEmbedded
	private List<LegalInfoBean> legalInfo;
	
	@Embedded
	@IndexedEmbedded
	private RevisionControlBean revisionControl;
	
	@OneToMany(mappedBy="locationCode_ref", cascade={javax.persistence.CascadeType.ALL})
	@IndexedEmbedded(includeEmbeddedObjectId=true, depth=1)
	private List<SupplierLocationEntity> supplierLocations;

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
	 * @return the supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * @param supplierName the supplierName to set
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * @return the legalInfo
	 */
	public List<LegalInfoBean> getLegalInfo() {
		return legalInfo;
	}

	/**
	 * @param legalInfo the legalInfo to set
	 */
	public void setLegalInfo(List<LegalInfoBean> legalInfo) {
		this.legalInfo = legalInfo;
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
	 * @return the supplierLocations
	 */
	public List<SupplierLocationEntity> getSupplierLocations() {
		return supplierLocations;
	}

	/**
	 * @param supplierLocations the supplierLocations to set
	 */
	public void setSupplierLocations(List<SupplierLocationEntity> supplierLocations) {
		this.supplierLocations = supplierLocations;
	}

	public Supplier convertTo(int mode) {
		Supplier bean = new Supplier();

		if (mode < 1) {
			bean.setId(id);
		}
		bean.setTenantId(tenantId);
		bean.setSupplierCode(supplierCode);
		bean.setSupplierName(supplierName);
		
		ArrayList<String> legalList = this.legalInfo == null ? null : new ArrayList<String>();
        for (int i=0; this.legalInfo != null && i < this.legalInfo.size(); i++) {
            LegalInfoBean emp = legalInfo.get(i);
            legalList.add(emp.getAttrName());
        }
		bean.setLegalInfo(legalList);
		
		ArrayList<String> suppLocationList = this.supplierLocations == null ? null : new ArrayList<String>();
        for (int i=0; this.supplierLocations != null && i < this.supplierLocations.size(); i++) {
            SupplierLocationEntity supp = supplierLocations.get(i);
            legalList.add(supp.getLocationCode());
        }
		bean.setSupplierLocations(suppLocationList);
		
		if (mode <= 0) {
			bean.setRevisionControl(revisionControl);
		}
		return bean;
	}

}
