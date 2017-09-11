package com.olp.jpa.domain.docu.inv.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
import com.olp.jpa.domain.docu.sm.model.SalesChannelEntity;

/**
 * @author Jayesh
 *
 */
@Entity
@Table(name="trl_channel_inventories", uniqueConstraints=@UniqueConstraint(columnNames={"tenant_id"}))
@NamedQueries({
		@NamedQuery(name="ChannelInventory.findChannelInventoryBySku", query="SELECT t from ChannelInventoryEntity t WHERE t.skuCode = :sku AND t.channelCode = :channel"),
		})
@Cacheable(true)
@Indexed(index="SetupDataIndex")
@FullTextFilterDef(name="filter-channelinventory", impl=TenantBasedSearchFilterFactory.class)
@MultiTenant(level = MultiTenant.Levels.ONE_TENANT)
@SortCriteria(attributes={"skuCode"})
public class ChannelInventoryEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="channel_inv_id", nullable=false)
	private Long id;
	
	@KeyAttribute
	@Fields({
		@Field(index=Index.NO, store=Store.YES, analyze=Analyze.NO)
	})
	@Column(name="tenant_id", nullable=false)
	private String tenantId;
	
	@ManyToOne
	@JoinColumn(name="sku_ref")
	@ContainedIn
	private ProductSkuEntity  skuRef;
	
	@Column(name="sku_code", nullable=false)
	@Fields({
		@Field(index=Index.NO, store=Store.YES, analyze=Analyze.NO)
	})
	private String skuCode;

	@Column(name="available_quantity", nullable=false)
	@Fields({
		@Field(index=Index.NO, store=Store.YES, analyze=Analyze.NO)
	})
	private float availableQuantity;

	@Column(name="uom", nullable=false)
	@Fields({
		@Field(index=Index.NO, store=Store.YES, analyze=Analyze.NO)
	})
	private String uom;

	@Column(name="pricing_rule", nullable=false)
	@Fields({
		@Field(index=Index.NO, store=Store.YES, analyze=Analyze.NO)
	})
	private String pricingRule;
	
	@Embedded
	@ElementCollection
	private List<ChannelInvLot> invLots;
	
	@ManyToOne
	@JoinColumn(name="sales_channel_ref")
	@ContainedIn
	private SalesChannelEntity  salesChannelRef;

	@Column(name="channel_code", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private String channelCode;

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
	 * @return the availableQuantity
	 */
	public float getAvailableQuantity() {
		return availableQuantity;
	}

	/**
	 * @param availableQuantity the availableQuantity to set
	 */
	public void setAvailableQuantity(float availableQuantity) {
		this.availableQuantity = availableQuantity;
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
	 * @return the pricingRule
	 */
	public String getPricingRule() {
		return pricingRule;
	}

	/**
	 * @param pricingRule the pricingRule to set
	 */
	public void setPricingRule(String pricingRule) {
		this.pricingRule = pricingRule;
	}

	/**
	 * @return the invLots
	 */
	public List<ChannelInvLot> getInvLots() {
		return invLots;
	}

	/**
	 * @param invLots the invLots to set
	 */
	public void setInvLots(List<ChannelInvLot> invLots) {
		this.invLots = invLots;
	}

	/**
	 * @return the salesChannelRef
	 */
	public SalesChannelEntity getSalesChannelRef() {
		return salesChannelRef;
	}

	/**
	 * @param salesChannelRef the salesChannelRef to set
	 */
	public void setSalesChannelRef(SalesChannelEntity salesChannelRef) {
		this.salesChannelRef = salesChannelRef;
	}

	/**
	 * @return the channelCode
	 */
	public String getChannelCode() {
		return channelCode;
	}

	/**
	 * @param channelCode the channelCode to set
	 */
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
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
	
	public ChannelInventory convertTo(int mode) {
		ChannelInventory bean = new ChannelInventory();

		if (mode < 1) {
			bean.setId(id);
		}
		bean.setTenantId(tenantId);
		bean.setSkuRef(skuRef.getSkuCode());
		bean.setSkuCode(skuCode);
		bean.setAvailableQuantity(availableQuantity);
		bean.setUom(uom);
		bean.setPricingRule(pricingRule);
		bean.setSalesChannelRef(salesChannelRef.getChannelCode());
		bean.setChannelCode(channelCode);
		
		ArrayList<Integer> channelinvLotList = this.invLots == null ? null : new ArrayList<Integer>();
        for (int i=0; this.invLots != null && i < this.invLots.size(); i++) {
        	ChannelInvLot inv = invLots.get(i);
        	channelinvLotList.add(inv.getLotSequence());
        }
		bean.setInvLots(channelinvLotList);
		
		if (mode <= 0) {
			bean.setRevisionControl(revisionControl);
		}
		return bean;
	}
}
