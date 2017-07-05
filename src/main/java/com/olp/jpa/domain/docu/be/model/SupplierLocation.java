package com.olp.jpa.domain.docu.be.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.olp.jpa.common.RevisionControlBean;
import com.olp.jpa.domain.docu.org.model.LocationEntity;

/**
 * @author Jayesh
 *
 */
@XmlRootElement(name="supplier-location", namespace="http://trilia-cloud.com/schema/entity/be")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlAccessorOrder(value=XmlAccessOrder.UNDEFINED)
@XmlType(propOrder={"id", "supplierCode", "supplierName","legalInfo", "revisionControl", "supplierLocations"})
public class SupplierLocation {
	
	@XmlElement(name="partner-id")
    private Long id;
    
    @XmlTransient
    private String tenantId;
	
    @XmlElement(name="location-code")
	private String locationCode;
	
    @XmlElement(name="shipping-flag")
	private boolean isShippingLocation;
	
    @XmlElement(name="billing-flag")
	private boolean isBillingLocation;
	
    @XmlElement(name="location")
	private LocationEntity location;
	
	private RevisionControlBean revisionControl;
	
	@XmlElement(name="bank-account")
	private List<String> bankAccounts;

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
	public List<String> getBankAccounts() {
		return bankAccounts;
	}

	/**
	 * @param bankAccounts the bankAccounts to set
	 */
	public void setBankAccounts(List<String> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
	
public SupplierLocationEntity convertTo(int mode) {
        
	SupplierLocationEntity bean = new SupplierLocationEntity();
        
        bean.setId(this.id);
        bean.setTenantId(this.tenantId);
        bean.setLocationCode(this.locationCode);
        bean.setShippingLocation(this.isShippingLocation);
        bean.setBillingLocation(this.isBillingLocation);
        bean.setLocation(location);
        
        
        if (this.bankAccounts != null) {
            ArrayList<BankAccountEntity> bankAccountList = new ArrayList<>();
            for (String bankAccount : this.bankAccounts) {
            	BankAccountEntity bankAccountBean = new BankAccountEntity();
            	bankAccountBean.setBankAcctNum(bankAccount);
            	bankAccountList.add(bankAccountBean);
            }
            bean.setBankAccounts(bankAccountList);
        }
        
        if (this.revisionControl != null)
            bean.setRevisionControl(this.revisionControl.clone());
        
        
        return(bean);
    }

}
