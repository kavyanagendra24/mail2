package com.example.springapp.Repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.springapp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	public static final EntityManager entityManager = null;

//	@Query("SELECT e FROM Employee e WHERE e.name LIKE %?1%")
//     List<Employee> searchUsersByName(String name);
//	
	@Query("SELECT DISTINCT e.name FROM Employee e WHERE e.name LIKE %:search%")
    List<String> findSuggestionsByName(@Param("search") String search);
	
	 @Query("SELECT e FROM Employee e WHERE e.name LIKE %:name% AND e.datetimeField BETWEEN :fromDateTime AND :toDateTime")
	    List<Employee> searchUsersByNameAndDateTimeRange(
	            @Param("name") String name,
	            @Param("fromDateTime") LocalDateTime fromDateTime,
	            @Param("toDateTime") LocalDateTime toDateTime
	    );
	

	


	
	
}
