package io.pivotal.poc.pcf.data;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class CustomerRepositoryImpl implements TableOperations {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Map<String, Object>> getTableDescription(String tableName) {
		return new JdbcTemplate(dataSource).query("describe " + tableName,new ColumnMapRowMapper());
	}

}
