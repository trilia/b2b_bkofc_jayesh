package com.olp.jpa.domain.docu.sm.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.AbstractServiceImpl;
import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.sm.model.SalesChannelEntity;

/**
 * @author Jayesh
 *
 */
public class SalesChannelServiceImpl extends AbstractServiceImpl<SalesChannelEntity,Long> implements SalesChannelService {

	@Autowired
	@Qualifier("salesChannelRepository")
	private SalesChannelRepository salesChannelRepo;
	 
	@Override
	public SalesChannelEntity findByChannelCode(String channelCode){
		SalesChannelEntity bean = salesChannelRepo.findByChannelCode(channelCode);
        return(bean);
	}

	@Override
	protected JpaRepository<SalesChannelEntity, Long> getRepository() {
		return salesChannelRepo;
	}

	@Override
	protected ITextRepository<SalesChannelEntity, Long> getTextRepository() {
		return salesChannelRepo;
	}

	@Override
	protected String getAlternateKeyAsString(SalesChannelEntity entity) {
		StringBuilder buff = new StringBuilder(50);
        buff.append("{ \"channel_code\" : \"").append(entity.getChannelCode()).append("\" }");
        
        return(buff.toString());
	}

	@Override
	protected AbstractServiceImpl<SalesChannelEntity, Long>.Outcome preProcess(int opCode,
			SalesChannelEntity entity) throws EntityValidationException {
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

	private void preProcessAdd(SalesChannelEntity entity) throws EntityValidationException {
		validate(entity);
		updateTenantWithRevision(entity); 
	}

	@Override
	protected AbstractServiceImpl<SalesChannelEntity, Long>.Outcome postProcess(int opCode,
			SalesChannelEntity entity) throws EntityValidationException {
		AbstractServiceImpl<SalesChannelEntity, Long>.Outcome result = new AbstractServiceImpl.Outcome();
		result.setResult(true);

		return result;
	}

	@Override
	public void validate(SalesChannelEntity entity) throws EntityValidationException {
		/*entity.setTaxGroupCode(entity.getTaxGroupRef().getGroupCode());
		entity.setPromoGroupCode(entity.getPromoGroupRef().getGroupCode());
		entity.setInvoiceLineNumber(entity.getOrderLineRef().getLineNumber());*/
		//entity.setInvoiceNumber(entity.getInvoiceRef().getInvoiceNumber());
		
	}
}
