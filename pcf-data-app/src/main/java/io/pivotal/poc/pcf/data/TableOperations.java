package io.pivotal.poc.pcf.data;

import java.util.List;
import java.util.Map;

public interface TableOperations {

	List<Map<String,Object>> getTableDescription(String tableName);
	
}
