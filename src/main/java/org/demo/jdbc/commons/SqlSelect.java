package org.demo.jdbc.commons;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public abstract class SqlSelect {
//public abstract class SqlSelect {

//	private final String sqlRequest ;
//	private final Class<T> dtoClass ;
	
	
//	public SqlSelect(String sqlRequest, Class<T> dtoClass) {
//		super();
//		this.sqlRequest = sqlRequest;
//		this.dtoClass = dtoClass;
//	}
	protected SqlSelect() {
		super();
	}

	//public abstract void setRequestParameters(PreparedStatement preparedStatement, List<Object> parameters) throws SQLException;
	
	// public abstract void toDTO(ResultSet resultSet, Object dto) throws SQLException;

	// protected abstract <T> T mapRow(ResultSet rs, int rowNum) throws SQLException ;

	
	private <T> List<T> select(String sqlRequest, List<Object> parameters, SqlMapper<T> mapper, int max) throws SQLException {
        try (Connection connection = ConnectionProvider.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest) ) {
            	// Prepare SQL request
            	if ( parameters != null ) {
                	setRequestParameters(preparedStatement, parameters);
            	}
            	// Execute SQL request
            	try  (ResultSet resultSet = preparedStatement.executeQuery() ) {
        			List<T> list = new LinkedList<>();
        			int n = 0;
                    while ( resultSet.next() ) {
                    	n++; // 1 to N
                    	//list.add( mapRow(resultSet, n));
                    	list.add( mapper.mapRow(resultSet, n));
                    	if (n == max) break;
                    }
                    return list ;
                } // close resultSet (AutoCloseable)
            } // close preparedStatement (AutoCloseable)
        } // close connection (AutoCloseable)
	}
	
	private void setRequestParameters(PreparedStatement preparedStatement, List<Object> parameters) throws SQLException {
		int i = 0 ;
		for ( Object o : parameters ) {
			preparedStatement.setObject(++i, o);
		}
	}
	
	protected <T> T executeSelectOne(String sqlRequest, SqlMapper<T> mapper) throws SQLException {
		return executeSelectOne(sqlRequest, null, mapper);
	}
	
	protected <T> T executeSelectOne(String sqlRequest, List<Object> parameters, SqlMapper<T> mapper) throws SQLException {
		List<T> list = select(sqlRequest, parameters, mapper, 1);
		if ( list != null && ! list.isEmpty() ) {
			return list.get(0); // First element 
		}
		else {
			return null;
		}
	}
	
	protected <T> List<T> executeSelectMany(String sqlRequest, SqlMapper<T> mapper ) throws SQLException {
		return executeSelectMany(sqlRequest, null, mapper);
	}
	
	protected <T> List<T> executeSelectMany(String sqlRequest, List<Object> parameters, SqlMapper<T> mapper) throws SQLException {
		return select(sqlRequest, parameters, mapper, Integer.MAX_VALUE);
	}
}
