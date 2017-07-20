package com.olp.jpa.domain.docu.po.model;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

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
import com.olp.jpa.domain.docu.be.model.LegalInfoBean;
import com.olp.jpa.domain.docu.be.model.Supplier;
import com.olp.jpa.domain.docu.be.model.SupplierEntity;
import com.olp.jpa.domain.docu.be.model.SupplierLocationEntity;
import com.olp.jpa.domain.docu.inv.model.ProductSkuEntity;
import com.olp.jpa.domain.docu.org.model.LocationEntity;

/**
 * Entity implementation class for Entity: PurchaseOrderEntity
 *
 */

@Entity
@Table(name="purchaseOrderLine", uniqueConstraints=@UniqueConstraint(columnNames={"tenant_id", "po_number"}))
@NamedQueries({
		@NamedQuery(name="PurchaseOrderLine.findByPurchaseOrderNumber", query="SELECT t from PurchaseOrderLineEntity t WHERE t.poNumber = :poNumber and t.tenantId = :tenant")
		})
@Cacheable(true)
@Indexed(index="SetupDataIndex")
@FullTextFilterDef(name="filter-purchaseorderline", impl=TenantBasedSearchFilterFactory.class)
@MultiTenant(level = MultiTenant.Levels.ONE_TENANT)
@SortCriteria(attributes={"lineNumber"})
public class PurchaseOrderLineEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="po-id", nullable=false)
	@Field(index=Index.NO,analyze=Analyze.NO,store=Store.NO)
	private Long id;
	
	@KeyAttribute
	@Field(store=Store.YES, analyze=Analyze.NO)
	@Column(name="tenant_id", nullable=false)
	private Long tenantId;
	
	@KeyAttribute
	@Column(name="po_number", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private String lineNumber;
	
	@Column(name="line_type", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private PoLineType lineType;
	
	@Column(name="sku_ref", nullable=false)
	@ManyToOne
	private ProductSkuEntity skuRef;

	@Column(name="sku_code", nullable=false)
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private String skuCode;

	@Column(name="prod_desc")
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.YES)
	private String prodDesc;

	@Column(name="quantity")
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private float quantity;

	@Column(name="uom")
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private String uom;
	
	@Column(name="unit_rate")
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private String unitRate;

	@Column(name="rate_variance")
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	private float rateVariance;
	
	@Column(name="warranty_terms")
	@Field(index=Index.YES, store=Store.NO, analyze=Analyze.YES)
	private float warrantyTerms;
	
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
	 * @return the lineNumber
	 */
	public String getLineNumber() {
		return lineNumber;
	}

	/**
	 * @param lineNumber the lineNumber to set
	 */
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * @return the lineType
	 */
	public PoLineType getLineType() {
		return lineType;
	}

	/**
	 * @param lineType the lineType to set
	 */
	public void setLineType(PoLineType lineType) {
		this.lineType = lineType;
	}

	/**
	 * @return the skuRef
	 */
	public ProductSkuEntity getSkuRef() {
		return skuRef;
	}

	/**
	 * @param skuRef the skuRef to set
	 */
	public void setSkuRef(ProductSkuEntity skuRef) {
		this.skuRef = skuRef;
	}

	/**
	 * @return the skuCode
	 */
	public String getSkuCode() {
		return skuCode;
	}

	/**
	 * @param skuCode the skuCode to set
	 */
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	/**
	 * @return the prodDesc
	 */
	public String getProdDesc() {
		return prodDesc;
	}

	/**
	 * @param prodDesc the prodDesc to set
	 */
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
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
	 * @return the unitRate
	 */
	public String getUnitRate() {
		return unitRate;
	}

	/**
	 * @param unitRate the unitRate to set
	 */
	public void setUnitRate(String unitRate) {
		this.unitRate = unitRate;
	}

	/**
	 * @return the rateVariance
	 */
	public float getRateVariance() {
		return rateVariance;
	}

	/**
	 * @param rateVariance the rateVariance to set
	 */
	public void setRateVariance(float rateVariance) {
		this.rateVariance = rateVariance;
	}

	/**
	 * @return the warrantyTerms
	 */
	public float getWarrantyTerms() {
		return warrantyTerms;
	}

	/**
	 * @param warrantyTerms the warrantyTerms to set
	 */
	public void setWarrantyTerms(float warrantyTerms) {
		this.warrantyTerms = warrantyTerms;
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

	public PurchaseOrderLine convertTo(int mode) {
		PurchaseOrderLine bean = new PurchaseOrderLine();

		if (mode < 1) {
			bean.setId(id);
		}
		bean.setTenantId(tenantId);
		bean.setLineNumber(lineNumber);
		bean.setLineType(lineType);
		bean.setSkuRef(skuRef);
		bean.setProdDesc(prodDesc);
		bean.setQuantity(quantity);
		bean.setUom(uom);
		bean.setUnitRate(unitRate);
		bean.setRateVariance(rateVariance);
		bean.setWarrantyTerms(warrantyTerms);
				return bean;
	}
   
}
