package com.engage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.engage.elastic.models.Employee;
import com.engage.elastic.repo.EmployeeRepository;

@Controller
@EnableElasticsearchRepositories(basePackages = "com.engage.elastic.repo")

public class TestController {

	@Autowired
	private EmployeeRepository repository;

	@Autowired
	private ElasticsearchTemplate template;

	String message = "Welcome to Spring MVC!";

	@RequestMapping("/")
	public String showHome(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			ModelMap map) {
		System.out.println("in controller");

		map.put("message", message);
		map.put("name", name);
		return "hello";
	}

	@RequestMapping("/hello")
	public String showHello(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			ModelMap map) {
		System.out.println("Add employees");
		addEmployees();
		System.out.println("Find all employees");
		findAllEmployees();
		System.out.println("Find employee by name 'Joe'");
		findEmployee("Joe");
		System.out.println("Find employee by name 'John'");
		findEmployee("John");
		System.out.println("Find employees by age");
		findEmployeesByAge(32);

		return "hello";
	}

	public void addEmployees() {
		Employee joe = new Employee("01", "Joe", 32);
		Employee johnS = new Employee("02", "John S", 32);
		Employee johnP = new Employee("03", "John P", 42);
		Employee sam = new Employee("04", "Sam", 30);

		template.putMapping(Employee.class);
		IndexQuery indexQuery = new IndexQuery();
		indexQuery.setId(joe.getId());
		indexQuery.setObject(joe);
		template.index(indexQuery);

		template.refresh(Employee.class);
		repository.save(johnS);
		repository.save(johnP);
		repository.save(sam);
	}

	public void findAllEmployees() {
		repository.findAll();
	}

	public void findEmployee(String name) {
		List empList = repository.findEmployeesByName(name);
		System.out.println("Employee list: " + empList);
	}

	public void findEmployeesByAge(int age) {
		List empList = repository.findEmployeesByAge(age);
		System.out.println("Employee list: " + empList);
	}

}
