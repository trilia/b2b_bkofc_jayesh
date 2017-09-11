package com.olp.jpa.domain.docu.be.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.AbstractServiceImpl;
import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.be.model.BankAccountEntity;
import com.olp.jpa.domain.docu.be.model.SupplierLocationEntity;
import com.olp.jpa.domain.docu.be.service.BankAccountRepository;

/**
 *
 * @author Jayesh Jain
 */
@Service("supplierLocationService")
public class SupplierLocationServiceImpl extends AbstractServiceImpl<SupplierLocationEntity, Long> implements SupplierLocationService {
    
    @Autowired
    @Qualifier("supplierRepository")
    private SupplierRepository supplierRepo;
    
    @Autowired
    @Qualifier("supplierLocationRepository")
    private SupplierLocationRepository supplierLocRepo;
    
    @Autowired
    @Qualifier("bankAcctRepository")
    private BankAccountRepository bankAccRepo;
    
    public SupplierLocationServiceImpl() {
        super();
    }
    
    @Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
    public SupplierLocationEntity findBySupplierLocationCode(String supplierLocationCode) {
    	SupplierLocationEntity bean = supplierLocRepo.findByLocationCode(supplierLocationCode);
        return(bean);
    }
    
    @Override
    @Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
    public List<SupplierLocationEntity> findAll() {
    	List<SupplierLocationEntity> list = supplierLocRepo.findAll();
        return(list);
    }
    
    @Override
    @Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
    public SupplierLocationEntity find(Long id) {
    	SupplierLocationEntity supp = supplierLocRepo.findOne(id);
        return(supp);
    }
    
    @Override
    @Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
    public void delete(Long id) {
    }
    
    @Override
    protected JpaRepository<SupplierLocationEntity, Long> getRepository() {
        return(supplierLocRepo);
    }
    
    @Override
    protected ITextRepository<SupplierLocationEntity, Long> getTextRepository() {
        return(supplierLocRepo);
    }

    @Override
    protected String getAlternateKeyAsString(SupplierLocationEntity entity) {
        
        StringBuilder buff = new StringBuilder(50);
        buff.append("{ \"location_code\" : \"").append(entity.getLocationCode()).append("\" }");
        
        return(buff.toString());
    }
    
    
    @Override
    protected Outcome preProcess(int opCode, SupplierLocationEntity entity) {
        
        Outcome result = new Outcome();
        
        switch(opCode) {
            
            case DELETE : {
                removeReferences(entity);
                result.setResult(true);
                break;
            }
            case DELETE_BULK : {
                removeReferences(entity);
                result.setResult(true);
                break;
            }
            default : {
                result.setResult(true);
            }
        }
        
        return(result);
    }

    @Override
    protected Outcome postProcess(int opCode, SupplierLocationEntity entity) {
        
        Outcome result = new Outcome();
        result.setResult(true);
        
        return(result);
    }
    
    
    private void removeReferences(SupplierLocationEntity supp) {
        
    }

	/* (non-Javadoc)
	 * @see com.olp.jpa.common.AbstractServiceImpl#doUpdate(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected SupplierLocationEntity doUpdate(SupplierLocationEntity newEntity, SupplierLocationEntity oldEntity)
			throws EntityValidationException {
		// TODO Auto-generated method stub
		return super.doUpdate(newEntity, oldEntity);
	}

	@Override
	@Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
    public void validate(SupplierLocationEntity entity) throws EntityValidationException {
        List<BankAccountEntity> banks = entity.getBankAccounts();
        List<BankAccountEntity> bank2 = null;
        if (banks != null) {
        	for(BankAccountEntity bank : banks){
	            Long bankId = bank.getId();
	            if (bankId == null) {
	                String bankNum = bank.getBankAcctNum();
	                if (bankNum == null || "".equals(bankNum)) {
	                    throw new EntityValidationException("Could not validate Bank Account. Either Bank id or Bank Account Number should be present !");
	                } else {
	                	bank = bankAccRepo.findByAccountNumber(bankNum);
	                    if (bank == null) {
	                    	throw new EntityValidationException("Could not validate Bank Acount with Bank Account Number - " + bankNum);
	                    }
	                }
	                banks.add(bank);
	            } else {
	            	bank = bankAccRepo.findOne(bankId);
	                if (bank == null) {
	                	throw new EntityValidationException("Could not validate Bank Acount with Bank Account id - " + bankId);
	                }
	                banks.add(bank);
	            }
        	}
        }
        
        if (banks != null) {
        	entity.setBankAccounts(bank2);
        }
	}
}
