package com.olp.jpa.domain.docu.po.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.AbstractServiceImpl;
import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.be.model.SupplierEntity;
import com.olp.jpa.domain.docu.be.model.SupplierLocationEntity;
import com.olp.jpa.domain.docu.be.repo.SupplierRepository;
import com.olp.jpa.domain.docu.be.service.SupplierLocationRepository;
import com.olp.jpa.domain.docu.org.model.LocationEntity;
import com.olp.jpa.domain.docu.org.repo.LocationRepository;
import com.olp.jpa.domain.docu.po.model.PurchaseOrderEntity;

/**
 * @author Jayesh
 *
 */
@Service("purchaseOrderService")
public class PurchaseOrderServiceImpl extends AbstractServiceImpl<PurchaseOrderEntity, Long> implements PurchaseOrderService {

	 @Autowired
	 @Qualifier("purchaseOrderRepository")
	 private PurchaseOrderRepository purchaseOrderRepo;
	 
	 @Autowired
	 @Qualifier("supplierRepository")
	 private SupplierRepository supplierRepo;
	 
	 @Autowired
	 @Qualifier("supplierLocationRepository")
	 private SupplierLocationRepository supplierLocRepo;
	 
	 @Autowired
	 @Qualifier("locationRepository")
	 private LocationRepository locRepo;
	    
	/* (non-Javadoc)
	 * @see com.olp.jpa.domain.docu.po.repo.PurchaseOrderService#findByPurchaseOrder(java.lang.String)
	 */
	@Override
	public PurchaseOrderEntity findByPurchaseOrder(String purchaseOrder) {
		PurchaseOrderEntity bean = purchaseOrderRepo.findByPurchaseOrder(purchaseOrder);
		return bean;
	}

	@Override
	protected JpaRepository<PurchaseOrderEntity, Long> getRepository() {
		return purchaseOrderRepo;
	}

	@Override
	protected ITextRepository<PurchaseOrderEntity, Long> getTextRepository() {
		return purchaseOrderRepo;
	}

	@Override
	protected String getAlternateKeyAsString(PurchaseOrderEntity entity) {
		StringBuilder buff = new StringBuilder(50);
        buff.append("{ \"po_number\" : \"").append(entity.getPoNumber()).append("\" }");
        
        return(buff.toString());
	}

	@Override
	@Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
    public void validate(PurchaseOrderEntity entity) throws EntityValidationException {
        SupplierEntity supp = entity.getSupplierRef(), supp2 = null;
        if (supp != null) {
            Long suppId = supp.getId();
            if (suppId == null) {
                String suppCode = supp.getSupplierCode();
                if (suppCode == null || "".equals(suppCode)) {
                    throw new EntityValidationException("Could not validate supplier. Either supplier id or code should be present !");
                } else {
                	supp2 = supplierRepo.findBySupplierCode(suppCode);
                    if (supp2 == null) {
                    	throw new EntityValidationException("Could not validate supplier with supplier code - " + suppCode);
                    }
                }
            } else {
            	supp2 = supplierRepo.findOne(suppId);
                if (supp2 == null) {
                	throw new EntityValidationException("Could not validate supplier with supplier id - " + suppId);
                }
            }
        }
        
        if (supp2 != null) {
        	entity.setSupplierRef(supp2);
        }
        
        SupplierLocationEntity loc = entity.getSupplierLocRef(), loc2 = null;
        if (loc != null) {
            Long locId = loc.getId();
            if (locId == null) {
                String locCode = loc.getLocationCode();
                if (locCode == null || "".equals(locCode)) {
                	throw new EntityValidationException("Could not validate Supplier Location entity. Either id or Supplier Location code should be present !");
                }
                loc2 = supplierLocRepo.findByLocationCode(locCode);
                if (loc2 == null) {
                	throw new EntityValidationException("Could not validate manager with supplier location code - " + locCode);
                }
            } else {
                loc2 = supplierLocRepo.findOne(locId);
                if (loc2 == null) {
                	throw new EntityValidationException("Could not validate manager with supplier location id - " + locId);
                }
            }
        }
        
        if (loc2 != null) {
            entity.setSupplierLocRef(loc2);
        }
        
        LocationEntity shipLoc = entity.getShippingLocation(), shipLoc2 = null;
        if (loc != null) {
            Long locId = shipLoc.getId();
            if (locId == null) {
                String locCode = shipLoc.getLocationAlias();
                if (locCode == null || "".equals(locCode)) {
                	throw new EntityValidationException("Could not validate Shipping Location entity. Either id or Shipping Location alias should be present !");
                }
                shipLoc2 = locRepo.findByLocationAlias(locCode);
                if (shipLoc2 == null) {
                	throw new EntityValidationException("Could not validate manager with shipping location alias - " + locCode);
                }
            } else {
            	shipLoc2 = locRepo.findOne(locId);
                if (shipLoc2 == null) {
                	throw new EntityValidationException("Could not validate manager with shipping location id - " + locId);
                }
            }
        }
        
        if (shipLoc2 != null) {
            entity.setShippingLocation(shipLoc2);
        }
        
        LocationEntity billLoc = entity.getBillingLocation(), billLoc2 = null;
        if (billLoc != null) {
            Long locId = billLoc.getId();
            if (locId == null) {
                String locCode = billLoc.getLocationAlias();
                if (locCode == null || "".equals(locCode)) {
                	throw new EntityValidationException("Could not validate Billing Location entity. Either id or Billing Location alias should be present !");
                }
                billLoc2 = locRepo.findByLocationAlias(locCode);
                if (billLoc2 == null) {
                	throw new EntityValidationException("Could not validate manager with billing location alias - " + locCode);
                }
            } else {
            	billLoc2 = locRepo.findOne(locId);
                if (billLoc2 == null) {
                	throw new EntityValidationException("Could not validate manager with shipping location id - " + locId);
                }
            }
        }
        
        if (shipLoc2 != null) {
            entity.setBillingLocation(shipLoc2);
        }
	}
	
	@Override
	protected AbstractServiceImpl<PurchaseOrderEntity, Long>.Outcome preProcess(int opCode,
			PurchaseOrderEntity entity) throws EntityValidationException {
		Outcome result = new Outcome();
        switch(opCode) {
            
            case ADD : {
                result = preProcessAdd(entity);
                result.setResult(true);
                break;
            }
            case ADD_BULK : {
                result = preProcessAdd(entity);
                result.setResult(true);
                break;
            }
            default: {
                result.setResult(true);
            }
            
        }
            
        return(result);
	}

	private AbstractServiceImpl<PurchaseOrderEntity, Long>.Outcome preProcessAdd(PurchaseOrderEntity entity) throws EntityValidationException {
		Outcome result = new Outcome();
        if (entity == null) {
            result.setResult(false);
            result.setErrorMessage("Null entity found for PurchaseOrderEntity !!");
        }
        validate(entity);
        updateTenantWithRevision(entity);   
        return(result);
	}

	@Override
	protected AbstractServiceImpl<PurchaseOrderEntity, Long>.Outcome postProcess(int paramInt,
			PurchaseOrderEntity paramT) throws EntityValidationException {
		Outcome result = new Outcome();
        result.setResult(true);
        
        return(result);
	}

	

}
