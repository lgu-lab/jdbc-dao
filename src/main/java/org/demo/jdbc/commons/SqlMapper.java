package org.demo.jdbc.commons;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SqlMapper<T> {
	
	T mapRow(ResultSet rs, int rowNum) throws SQLException ;
	
}
