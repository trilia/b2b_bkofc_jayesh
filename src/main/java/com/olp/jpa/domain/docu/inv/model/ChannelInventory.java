package com.olp.jpa.domain.docu.inv.model;

import java.util.List;

import com.olp.jpa.common.RevisionControlBean;
import com.olp.jpa.domain.docu.om.model.SalesOrderLineEntity;
import com.olp.jpa.domain.docu.sm.model.SalesChannelEntity;

/**
 * @author Jayesh
 *
 */
public class ChannelInventory {

	private Long id;
	
	private String tenantId;
	
	private String  skuRef;
	
	private String skuCode;

	private float availableQuantity;

	private String uom;

	private String pricingRule;
	
	private List<Integer> invLots;
	
	private String  salesChannelRef;
	
	private String channelCode;

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
	public String getSkuRef() {
		return skuRef;
	}

	/**
	 * @param skuRef the skuRef to set
	 */
	public void setSkuRef(String skuRef) {
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
	public List<Integer> getInvLots() {
		return invLots;
	}

	/**
	 * @param invLots the invLots to set
	 */
	public void setInvLots(List<Integer> invLots) {
		this.invLots = invLots;
	}

	/**
	 * @return the salesChannelRef
	 */
	public String getSalesChannelRef() {
		return salesChannelRef;
	}

	/**
	 * @param salesChannelRef the salesChannelRef to set
	 */
	public void setSalesChannelRef(String salesChannelRef) {
		this.salesChannelRef = salesChannelRef;
	}

	public String getChannelCode() {
		return channelCode;
	}

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

	public ChannelInventoryEntity convertTo(int mode) {
		ChannelInventoryEntity bean = new ChannelInventoryEntity();

		if (mode < 1) {
			bean.setId(id);
		}
		bean.setTenantId(tenantId);
		if (!(this.skuRef == null || "".equals(this.skuRef))) {
			ProductSkuEntity productSkuEntity = new ProductSkuEntity();
			productSkuEntity.setSkuCode(skuRef);
			bean.setSkuRef(productSkuEntity);
        }
		
		bean.setSkuCode(skuCode);
		bean.setAvailableQuantity(availableQuantity);
		bean.setUom(uom);
		bean.setPricingRule(pricingRule);
		bean.setChannelCode(channelCode);
		
		if (!(this.salesChannelRef == null || "".equals(this.salesChannelRef))) {
			SalesChannelEntity salesChannelEntity = new SalesChannelEntity();
			salesChannelEntity.setChannelCode(salesChannelRef);
			bean.setSalesChannelRef(salesChannelEntity);
        }
		
		if (mode <= 0) {
			bean.setRevisionControl(revisionControl);
		}
		return bean;
	}
	
}
