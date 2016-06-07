package com.engage.elastic.repo;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.engage.elastic.models.Employee;

public interface EmployeeRepository extends ElasticsearchRepository<Employee,String> {
    List<Employee> findEmployeesByAge(int age); 
    List<Employee> findEmployeesByName(String name);
}
