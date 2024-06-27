package org.demo.jdbc.commons;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StringMapper implements SqlMapper<String> {
	@Override
	public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		return rs.getString(1);
	}
}
