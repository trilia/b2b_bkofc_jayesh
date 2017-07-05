package com.olp.jpa.domain.docu.be.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olp.jpa.common.AbstractServiceImpl;
import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.be.model.SupplierLocationEntity;

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
    
    public SupplierLocationServiceImpl() {
        super();
    }
    
    @Transactional(readOnly=true)
    public SupplierLocationEntity findBySupplierLocationCode(String supplierLocationCode) {
    	SupplierLocationEntity bean = supplierLocRepo.findByLocationCode(supplierLocationCode);
        return(bean);
    }
    
    @Override
    @Transactional(readOnly=true)
    public List<SupplierLocationEntity> findAll() {
    	List<SupplierLocationEntity> list = supplierLocRepo.findAll();
        return(list);
    }
    
    @Override
    @Transactional(readOnly=true)
    public SupplierLocationEntity find(Long id) {
    	SupplierLocationEntity supp = supplierLocRepo.findOne(id);
        return(supp);
    }
    
    @Override
    @Transactional
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
}
