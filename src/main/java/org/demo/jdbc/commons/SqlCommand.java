package org.demo.jdbc.commons;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlCommand {

    public static boolean execute(Connection conn, String sql) throws SQLException {
    	
    	try ( Statement statement = conn.createStatement() ) {
	        return statement.execute(sql);
    	} // close statement (AutoCloseable)
    }
}
