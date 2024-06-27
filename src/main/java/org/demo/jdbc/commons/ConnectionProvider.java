package org.demo.jdbc.commons;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionProvider {
	
	private static final DataSource dataSource = initDataSource();
	
    private static DataSource initDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:mem:AZ;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        config.setUsername("sa");
        config.setPassword("");
        config.setDriverClassName("org.h2.Driver");
        return new HikariDataSource(config);
    }
    
	public static final Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
