package io.pivotal.poc.pcf.web;

import io.pivotal.poc.pcf.data.Customer;
import io.pivotal.poc.pcf.data.CustomerRepository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/customer")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	private List<String> fieldNames;
	
	public CustomerController() {
		fieldNames = reflectObject();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public Iterable<Customer> getAll() {
		return customerRepository.findAll();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Customer save(@RequestBody Customer customer) {
		if (customer.getId() == null) {
			customer.setId(UUID.randomUUID().toString());
		}//end if
		return customerRepository.save(customer);
	}
	
	@RequestMapping(value="/fieldNames",method=RequestMethod.GET)
	public List<String> getCustomerObject() {
		return fieldNames;
	}
	
	private List<String> reflectObject() {
		List<String> names = new ArrayList<String>();
		Field[] fields = Customer.class.getDeclaredFields();
		for (Field field : fields) {
			names.add(field.getName());
		}//end for
		return names;
	}
}
