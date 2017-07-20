package com.olp.jpa.domain.docu.be.repo;

import javax.jws.WebMethod;

import org.springframework.data.repository.NoRepositoryBean;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.IJpaService;
import com.olp.jpa.domain.docu.be.model.SupplierLocationEntity;

/**
 *
 * @author Jayesh Jain
 */
@NoRepositoryBean
public interface SupplierLocationService extends IJpaService<SupplierLocationEntity, Long> {
    
    /*@Override
    @WebMethod(operationName="listAllSuppliersLocation")
    public List<SupplierLocationEntity> findAll();
    
    @Override
    @WebMethod(operationName="listAllSuppliersLocationSorted")
    public List<SupplierLocationEntity> findAll(@WebParam(name="sortCriteria") SortCriteriaBean sort);
    
    @Override
    @WebMethod(operationName="findSupplierLocationById")
    public SupplierLocationEntity find(@WebParam(name="SupplierLocationInternalId") Long id);*/
    
    @WebMethod(operationName="findSupplierLocationByCode")
    public SupplierLocationEntity findBySupplierLocationCode(String supplierCode);

    public void validate(SupplierLocationEntity entity) throws EntityValidationException;
    
    /*@Override
    @WebMethod(operationName="addSupplierLocation")
    public SupplierLocationEntity add(@WebParam(name="supplier") SupplierLocationEntity entity);
    
    @Override
    @WebMethod(operationName="updateSupplierLocation")
    public SupplierLocationEntity update(@WebParam(name="supplier") SupplierLocationEntity entity);
    
    @WebMethod(operationName="addSupplierLocation")
    public boolean addSupplierLocation(@WebParam(name="supplierCode") String supplierCode, @WebParam(name="supplierLocationCode") String supplierLocationCode);
    
    @Override
    @WebMethod(operationName="removeSupplierLocation")
    public void delete(@WebParam(name="supplierLocationInternalId") Long id);*/
}
