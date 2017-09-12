package com.olp.jpa.domain.docu.po.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
import com.olp.jpa.domain.docu.inv.model.ProductSkuEntity;

/**
 * Entity implementation class for Entity: PurchaseOrderEntity
 *
 */

@Entity
@Table(name="trl_purchase_order_lines", uniqueConstraints=@UniqueConstraint(columnNames={"tenant_id", "po_number", "line_number"}))
@NamedQueries({
		@NamedQuery(name="PurchaseOrderLine.findByPurchaseOrderNumber", query="SELECT t from PurchaseOrderLineEntity t WHERE t.poNumber = :poNumber and t.lineNumber = :line and t.tenantId = :tenant")
		})
@Cacheable(true)
@Indexed(index="SetupDataIndex")
@FullTextFilterDef(name="filter-purchaseorderline", impl=TenantBasedSearchFilterFactory.class)
@MultiTenant(level = MultiTenant.Levels.ONE_TENANT)
@SortCriteria(attributes={"poNumber","lineNumber"})
public class PurchaseOrderLineEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="po_line_id", nullable=false)
	private Long id;
	
	@KeyAttribute
	@Fields({
		@Field(index=Index.NO, store=Store.YES, analyze=Analyze.NO)
	})
	@Column(name="tenant_id", nullable=false)
	private String tenantId;
	
	@KeyAttribute
	@Column(name="line_number", nullable=false)
    @Fields({
        @Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
    })
	private String lineNumber;
	
	@KeyAttribute 
    @Column(name="po_number")
    @Fields({
        @Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
    })
    private String poNumber;  
	
	@Column(name="line_type", nullable=false)
    @Fields({
        @Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
    })
	private PoLineType lineType;
	
	@ManyToOne
	@JoinColumn(name="purchase_order_ref")
	@ContainedIn
	private PurchaseOrderEntity purchaseOrderRef;
	 
	@ManyToOne
	@JoinColumn(name="sku_ref")
	@ContainedIn
	private ProductSkuEntity skuRef;

	@Column(name="sku_code", nullable=false)
	@Fields({
        @Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
    })
	private String skuCode;

	@Column(name="prod_desc")
    @Fields({
        @Field(index=Index.YES, store=Store.NO, analyze=Analyze.YES)
    })
	private String prodDesc;

	@Column(name="quantity")
	@Fields({
        @Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
    })
	private float quantity;

	@Column(name="uom")
	@Fields({
        @Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
    })
	private String uom;
	
	@Column(name="unit_rate")
	@Fields({
        @Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
    })
	private String unitRate;

	@Column(name="rate_variance")
	@Fields({
        @Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
    })
	private float rateVariance;
	
	@Column(name="warranty_terms")
	@Fields({
        @Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
    })
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
	 * @return the purchaseOrderRef
	 */
	public PurchaseOrderEntity getPurchaseOrderRef() {
		return purchaseOrderRef;
	}

	/**
	 * @param purchaseOrderRef the purchaseOrderRef to set
	 */
	public void setPurchaseOrderRef(PurchaseOrderEntity purchaseOrderRef) {
		this.purchaseOrderRef = purchaseOrderRef;
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
