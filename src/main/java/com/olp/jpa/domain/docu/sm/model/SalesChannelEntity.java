/**
 * 
 */
package com.olp.jpa.domain.docu.sm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import com.olp.jpa.domain.docu.be.model.SupplierLocationEntity;
import com.olp.jpa.domain.docu.inv.model.ChannelInvLot;
import com.olp.jpa.domain.docu.inv.model.ChannelInventoryEntity;
import com.olp.jpa.domain.docu.om.model.SalesOrder;
import com.olp.jpa.domain.docu.po.model.PoType;
import com.olp.jpa.domain.docu.sm.model.SmEnums.ChannelType;

/**
 * @author Jayesh
 *
 */
@Entity
@Table(name="trl_sales_channel", uniqueConstraints=@UniqueConstraint(columnNames={"tenant_id", "channel_code"}))
@NamedQueries({
		@NamedQuery(name="SalesChannel.findByChannelCode", query="SELECT t from SalesChannelEntity t WHERE t.channelCode = :channelCode and t.tenantId = :tenant"),
		})
@Cacheable(true)
@Indexed(index="SetupDataIndex")
@FullTextFilterDef(name="filter-saleschannel", impl=TenantBasedSearchFilterFactory.class)
@MultiTenant(level = MultiTenant.Levels.ONE_TENANT)
@SortCriteria(attributes={"channelCode"})
public class SalesChannelEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sales_channel_id", nullable=false)
	private Long id;
	
	@KeyAttribute
	@Fields({
		@Field(index=Index.NO, store=Store.YES, analyze=Analyze.NO)
	})
	@Column(name="tenant_id", nullable=false)
	private String tenantId;
	
	@KeyAttribute
	@Column(name="channel_code", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.YES, analyze=Analyze.NO)
	})
	private String channelCode;
	
	@Column(name="channel_name", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.YES, analyze=Analyze.NO)
	})
	private String channelName;
	
	@Column(name="channel_type", nullable=false)
	@Fields({
		@Field(index=Index.YES, store=Store.YES, analyze=Analyze.NO)
	})
	@Enumerated(EnumType.STRING)
	private ChannelType channelType;

	@OneToMany(mappedBy="salesChannelRef", cascade=CascadeType.ALL)
    @IndexedEmbedded(includeEmbeddedObjectId=true, depth=1)
    private List<ChannelInventoryEntity> channelInvRef;

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
	 * @param channelName the channel_Name to set
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	/**
	 * @return the channelInvRef
	 */
	public List<ChannelInventoryEntity> getChannelInvRef() {
		return channelInvRef;
	}

	/**
	 * @param channelInvRef the channelInvRef to set
	 */
	public void setChannelInvRef(List<ChannelInventoryEntity> channelInvRef) {
		this.channelInvRef = channelInvRef;
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
	
	public SalesChannel convertTo(int mode) {
		SalesChannel bean = new SalesChannel();

		if (mode < 1) {
			bean.setId(id);
		}
		bean.setTenantId(tenantId);
		bean.setChannelCode(channelCode);
		bean.setChannelName(channelName);
		bean.setChannelType(channelType);
		
		ArrayList<String> channelinvList = this.channelInvRef == null ? null : new ArrayList<String>();
        for (int i=0; this.channelInvRef != null && i < this.channelInvRef.size(); i++) {
        	ChannelInventoryEntity inv = channelInvRef.get(i);
            channelinvList.add(inv.getSalesChannelRef().getChannelCode());
        }
		bean.setChannelInvRef(channelinvList);
		
		if (mode <= 0) {
			bean.setRevisionControl(revisionControl);
		}
		return bean;
	}
	
}
