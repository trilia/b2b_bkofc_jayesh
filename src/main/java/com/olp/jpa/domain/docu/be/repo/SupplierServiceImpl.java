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
import com.olp.jpa.domain.docu.be.model.SupplierEntity;

/**
 *
 * @author Jayesh Jain
 */
@Service("supplierService")
public class SupplierServiceImpl extends AbstractServiceImpl<SupplierEntity, Long> implements SupplierService {
    
    @Autowired
    @Qualifier("supplierRepository")
    private SupplierRepository supplierRepo;
    
    @Autowired
    @Qualifier("supplierLocationRepository")
    private SupplierLocationRepository supplierLocRepo;
    
    public SupplierServiceImpl() {
        super();
    }
    
    @Override
    @Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
    public SupplierEntity findBySupplierCode(String supplierCode) {
    	SupplierEntity bean = supplierRepo.findBySupplierCode(supplierCode);
        return(bean);
    }
    
    @Override
    @Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
    public List<SupplierEntity> findAll() {
    	List<SupplierEntity> list = supplierRepo.findAll();
        return(list);
    }
    
    @Override
    @Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
    public SupplierEntity find(Long id) {
    	SupplierEntity supp = supplierRepo.findOne(id);
        return(supp);
    }
    
    @Override
    @Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
    public void delete(Long id) {
    }
    
    @Override
    protected JpaRepository<SupplierEntity, Long> getRepository() {
        return(supplierRepo);
    }
    
    @Override
    protected ITextRepository<SupplierEntity, Long> getTextRepository() {
        return(supplierRepo);
    }

    @Override
    protected String getAlternateKeyAsString(SupplierEntity entity) {
        
        StringBuilder buff = new StringBuilder(50);
        buff.append("{ \"supplier_code\" : \"").append(entity.getSupplierCode()).append("\" }");
        
        return(buff.toString());
    }
    
    @Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
    public void validate(SupplierEntity entity) throws EntityValidationException {
        /*List<LegalInfoBean> legal = entity.getLegalInfo(), legal2 = null;
        if (legal != null) {
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
        }*/
	}
    
    @Override
    protected Outcome preProcess(int opCode, SupplierEntity entity) {
        
        Outcome result = new Outcome();
        
        switch(opCode) {
            
            case DELETE : {
                //removeReferences(entity);
                result.setResult(true);
                break;
            }
            case DELETE_BULK : {
                //removeReferences(entity);
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
    protected Outcome postProcess(int opCode, SupplierEntity entity) {
        
        Outcome result = new Outcome();
        result.setResult(true);
        
        return(result);
    }
}
