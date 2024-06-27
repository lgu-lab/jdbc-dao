package org.demo.jdbc.commons;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegerMapper implements SqlMapper<Integer> {
	@Override
	public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Integer.valueOf(rs.getInt(1));
	}
}
