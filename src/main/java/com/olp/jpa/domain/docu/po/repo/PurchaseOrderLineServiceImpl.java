package com.olp.jpa.domain.docu.po.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.AbstractServiceImpl;
import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.inv.model.ProductSkuEntity;
import com.olp.jpa.domain.docu.inv.repo.ProductSkuRepository;
import com.olp.jpa.domain.docu.po.model.PurchaseOrderLineEntity;

/**
 * @author Jayesh
 *
 */
public class PurchaseOrderLineServiceImpl extends AbstractServiceImpl<PurchaseOrderLineEntity,Long> implements PurchaseOrderLineService {

	@Autowired
	@Qualifier("purchaseOrderLineRepository")
	private PurchaseOrderLineRepository repo;
	
	@Autowired
	@Qualifier("prodSkuRepository")
	private ProductSkuRepository skuRepo;
	
	@Override
	protected JpaRepository<PurchaseOrderLineEntity, Long> getRepository() {
		return repo;
	}

	@Override
	protected ITextRepository<PurchaseOrderLineEntity, Long> getTextRepository() {
		return repo;
	}

	@Override
	protected String getAlternateKeyAsString(PurchaseOrderLineEntity entity) {
		StringBuilder buff = new StringBuilder(50);
        buff.append("{ \"po_number\" : \"").append(entity.getLineNumber()).append("\" }");
        
        return(buff.toString());
	}

	@Override
	public PurchaseOrderLineEntity findByPurchaseOrderNumber(String purchaseOrderNum) {
		PurchaseOrderLineEntity bean = repo.findByPurchaseOrderNumber(purchaseOrderNum);
		return bean;
	}
	
	@Override
	protected AbstractServiceImpl<PurchaseOrderLineEntity, Long>.Outcome preProcess(int opCode,
			PurchaseOrderLineEntity entity) throws EntityValidationException {
		Outcome result = new Outcome();
        switch(opCode) {
            
            case ADD : {
                result = preProcessAdd(entity);
                break;
            }
            case ADD_BULK : {
                result = preProcessAdd(entity);
                break;
            }
            default: {
                result.setResult(true);
            }
            
        }
            
        return(result);
	}

	private AbstractServiceImpl<PurchaseOrderLineEntity, Long>.Outcome preProcessAdd(PurchaseOrderLineEntity entity) {
		return null;
	}

	@Override
	protected AbstractServiceImpl<PurchaseOrderLineEntity, Long>.Outcome postProcess(int paramInt,
			PurchaseOrderLineEntity paramT) throws EntityValidationException {
		Outcome result = new Outcome();
        result.setResult(true);
        
        return(result);
	}

	@Override
	public void validate(PurchaseOrderLineEntity entity) throws EntityValidationException {
		ProductSkuEntity sku = entity.getSkuRef(), sku2 = null;
        if (sku != null) {
            Long skuId = sku.getId();
            if (skuId == null) {
                String skuCode = sku.getSkuCode();
                if (skuCode == null || "".equals(skuCode)) {
                	throw new EntityValidationException("Could not validate Sku entity. Either id orSku code should be present !");
                }
                sku2 = skuRepo.findBySkuCode(skuCode);
                if (sku2 == null) {
                	throw new EntityValidationException("Could not validate Sku with Sku Code - " + skuCode);
                }
            } else {
            	sku2 = skuRepo.findOne(skuId);
                if (sku2 == null) {
                	throw new EntityValidationException("Could not validate Sku with Sku id - " + skuId);
                }
            }
        }
        
        if (sku2 != null) {
            entity.setSkuRef(sku2);
        }
	}
}
