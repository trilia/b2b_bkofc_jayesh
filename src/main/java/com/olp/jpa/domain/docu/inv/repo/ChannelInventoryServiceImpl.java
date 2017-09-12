package com.olp.jpa.domain.docu.inv.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.AbstractServiceImpl;
import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.inv.model.ChannelInventoryEntity;

/**
 * @author Jayesh
 *
 */
@Service("channelInventoryService")
public class ChannelInventoryServiceImpl extends AbstractServiceImpl<ChannelInventoryEntity,Long> implements ChannelInventoryService {

	@Autowired
	@Qualifier("channelInventoryRepository")
	private ChannelInventoryRepository channelInventoryRepo;
	 
	@Override
	public ChannelInventoryEntity findChannelInventoryBySku(String skuCode,String channelCode){
		ChannelInventoryEntity bean = channelInventoryRepo.findChannelInventoryBySku(skuCode, channelCode);
        return(bean);
	}

	@Override
	protected JpaRepository<ChannelInventoryEntity, Long> getRepository() {
		return channelInventoryRepo;
	}

	@Override
	protected ITextRepository<ChannelInventoryEntity, Long> getTextRepository() {
		return channelInventoryRepo;
	}

	@Override
	protected String getAlternateKeyAsString(ChannelInventoryEntity entity) {
		StringBuilder buff = new StringBuilder(50);
        buff.append("{ \"channel_code\" : \"").append(entity.getChannelCode()).append("\" }");
        
        return(buff.toString());
	}

	@Override
	protected AbstractServiceImpl<ChannelInventoryEntity, Long>.Outcome preProcess(int opCode,
			ChannelInventoryEntity entity) throws EntityValidationException {
		Outcome result = new Outcome();
		result.setResult(Boolean.TRUE);
        switch(opCode) {
            
            case ADD : {
                preProcessAdd(entity);
                break;
            }
            case ADD_BULK : {
                preProcessAdd(entity);
                break;
            }
            default: {
                result.setResult(true);
            }
            
        }
            
        return(result);
	}

	private void preProcessAdd(ChannelInventoryEntity entity) throws EntityValidationException {
		entity.setSkuCode(entity.getSkuRef().getSkuCode());
		validate(entity);
		updateTenantWithRevision(entity); 
	}

	@Override
	protected AbstractServiceImpl<ChannelInventoryEntity, Long>.Outcome postProcess(int opCode,
			ChannelInventoryEntity entity) throws EntityValidationException {
		AbstractServiceImpl<ChannelInventoryEntity, Long>.Outcome result = new AbstractServiceImpl.Outcome();
		result.setResult(true);

		return result;
	}

	@Override
	public void validate(ChannelInventoryEntity entity) throws EntityValidationException {
		/*entity.setTaxGroupCode(entity.getTaxGroupRef().getGroupCode());
		entity.setPromoGroupCode(entity.getPromoGroupRef().getGroupCode());
		entity.setInvoiceLineNumber(entity.getOrderLineRef().getLineNumber());*/
		//entity.setInvoiceNumber(entity.getInvoiceRef().getInvoiceNumber());
		
	}
}
