package com.example.springapp.Repository;



import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

    private final EntityManager entityManager;

    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addColumn(String columnName, String dataType) {
        String sql = "ALTER TABLE Employee ADD COLUMN " + columnName + " " + dataType;
        entityManager.createNativeQuery(sql).executeUpdate();
    }
    
 

    
   
}