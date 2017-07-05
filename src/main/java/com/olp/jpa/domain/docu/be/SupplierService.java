package com.olp.jpa.domain.docu.be;

import javax.jws.WebMethod;

import org.springframework.data.repository.NoRepositoryBean;

import com.olp.jpa.common.IJpaService;
import com.olp.jpa.domain.docu.be.model.SupplierEntity;

/**
 *
 * @author Jayesh Jain
 */
@NoRepositoryBean
public interface SupplierService extends IJpaService<SupplierEntity, Long> {
    
    /*@Override
    @WebMethod(operationName="listAllSuppliers")
    public List<SupplierEntity> findAll();
    
    @Override
    @WebMethod(operationName="listAllSuppliersSorted")
    public List<SupplierEntity> findAll(@WebParam(name="sortCriteria") SortCriteriaBean sort);
    
    @Override
    @WebMethod(operationName="findSupplierById")
    public SupplierEntity find(@WebParam(name="supplierInternalId") Long id);*/
    
    @WebMethod(operationName="findSupplierByCode")
    public SupplierEntity findBySupplierCode(String supplierCode);
    
    /*@Override
    @WebMethod(operationName="addSupplier")
    public SupplierEntity add(@WebParam(name="supplier") SupplierEntity entity);
    
    @Override
    @WebMethod(operationName="updateSupplier")
    public SupplierEntity update(@WebParam(name="supplier") SupplierEntity entity);
    
    @WebMethod(operationName="addSupplierLocation")
    public boolean addSupplierLocation(@WebParam(name="supplierCode") String supplierCode, @WebParam(name="supplierLocationCode") String supplierLocationCode);
    
    @Override
    @WebMethod(operationName="removeSupplier")
    public void delete(@WebParam(name="supplierInternalId") Long id);*/
}
