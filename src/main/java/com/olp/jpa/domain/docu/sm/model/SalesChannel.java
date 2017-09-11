/**
 * 
 */
package com.olp.jpa.domain.docu.sm.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import com.olp.jpa.common.RevisionControlBean;
import com.olp.jpa.domain.docu.inv.model.ChannelInventoryEntity;
import com.olp.jpa.domain.docu.inv.model.ProductSkuEntity;
import com.olp.jpa.domain.docu.om.model.SalesOrderLineEntity;
import com.olp.jpa.domain.docu.po.model.PoType;
import com.olp.jpa.domain.docu.sm.model.SmEnums.ChannelType;

/**
 * @author Jayesh
 *
 */
public class SalesChannel {

	@XmlElement(name="sales-channel-id")
	private Long id;
	
	@XmlTransient
	private String tenantId;

	@XmlElement(name="channel-code")
	private String channelCode;

	@XmlElement(name="channel-name")
	private String channelName;

	@XmlElement(name="")
	private ChannelType channelType;

	@XmlElement(name="channel-inv")
    private List<String> channelInvRef;

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
	 * @return the channel_Name
	 */
	public String getChannelName() {
		return channelName;
	}

	/**
	 * @param channel_Name the channel_Name to set
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	/**
	 * @return the channelType
	 */
	public ChannelType getChannelType() {
		return channelType;
	}

	/**
	 * @param channelType the channelType to set
	 */
	public void setChannelType(ChannelType channelType) {
		this.channelType = channelType;
	}

	/**
	 * @return the channelInvRef
	 */
	public List<String> getChannelInvRef() {
		return channelInvRef;
	}

	/**
	 * @param channelInvRef the channelInvRef to set
	 */
	public void setChannelInvRef(List<String> channelInvRef) {
		this.channelInvRef = channelInvRef;
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

	public SalesChannelEntity convertTo(int mode) {
		SalesChannelEntity bean = new SalesChannelEntity();

		if (mode < 1) {
			bean.setId(id);
		}
		bean.setTenantId(tenantId);
		bean.setChannelCode(channelCode);
		bean.setChannelName(channelName);
		bean.setChannelType(channelType);
		
		if (!(this.channelInvRef == null || "".equals(this.channelInvRef))) {
			ChannelInventoryEntity channelInventoryEntity = new ChannelInventoryEntity();
			//channelInventoryEntity.set
			//bean.setProductSkuRef(productSkuEntity);
        }
		
		if (mode <= 0) {
			bean.setRevisionControl(revisionControl);
		}
		return bean;
	}
	
}
