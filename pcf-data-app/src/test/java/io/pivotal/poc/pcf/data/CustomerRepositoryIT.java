package io.pivotal.poc.pcf.data;

import io.pivotal.poc.pcf.Application;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
public class CustomerRepositoryIT {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void test() {
		List<Map<String,Object>> map = new JdbcTemplate(dataSource).query("describe customer", new ColumnMapRowMapper());
		System.out.println(map);
	}
	
	@Test
	public void testTableOperations() {
		List<Map<String,Object>> map = customerRepository.getTableDescription("customer");
		System.out.println(map);
	}

}
