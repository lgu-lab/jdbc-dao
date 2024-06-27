package org.demo.jdbc.commons;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SqlSelectRequest<T> {

	private final String sqlRequest ;
	
	private final SqlMapper<T> mapper ;

	/**
	 * Constructor
	 * @param sqlRequest
	 * @param mapper
	 */
	public SqlSelectRequest(String sqlRequest, SqlMapper<T> mapper) {
		super();
		this.sqlRequest = sqlRequest;
		this.mapper = mapper;
	}

	private List<T> select(List<Object> parameters, int max) throws SQLException {
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

	
	
	public T executeSelectOne() throws SQLException {
		return executeSelectOne(null);
	}
	
	public T executeSelectOne(List<Object> parameters) throws SQLException {
		List<T> list = select(parameters, 1);
		if ( list != null && ! list.isEmpty() ) {
			return list.get(0); // First element 
		}
		else {
			return null;
		}
	}
	
	public List<T> executeSelectMany() throws SQLException {
		return executeSelectMany(null);
	}
	
	public List<T> executeSelectMany(List<Object> parameters) throws SQLException {
		return select(parameters, Integer.MAX_VALUE);
	}
}
