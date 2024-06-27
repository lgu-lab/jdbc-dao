package org.demo.app;

import java.sql.Connection;
import java.sql.SQLException;

import org.demo.dto.BookDTO;
import org.demo.jdbc.commons.ConnectionProvider;
import org.demo.jdbc.commons.SqlCommand;
import org.demo.jdbc.dao.BookDAO;

public class App {

	public static void main(String[] args) throws SQLException {

		Connection connection = ConnectionProvider.getConnection();
		SqlCommand.execute(connection, """
				CREATE TABLE BOOKS (
				  ID    INTEGER NOT NULL,
				  TITLE VARCHAR(25)
				);
				""");
		SqlCommand.execute(connection, "INSERT INTO BOOKS (ID, TITLE) VALUES ( 1, 'My Book') ;");
		SqlCommand.execute(connection, "INSERT INTO BOOKS (ID, TITLE) VALUES ( 2, 'My other Book') ;");
		SqlCommand.execute(connection, "INSERT INTO BOOKS (ID, TITLE) VALUES ( 3, 'Germinal') ;");

		BookDAO bookDao = new BookDAO();
		
		System.out.println("Book #1: " + bookDao.selectById(1));
		System.out.println("Book #3: " + bookDao.selectById(3));

		System.out.println("Book #999: " + bookDao.selectById(999));
		
		System.out.println("Books count : " + bookDao.selectCount());
		System.out.println("Books title #1 : " + bookDao.selectTitleById(1));
		System.out.println("Books title #2 : " + bookDao.selectTitleById(2));

		System.out.println("All books: ");
		for ( BookDTO book : bookDao.selectAll() ) {
			System.out.println(" . " + book);
		} 

	}

}
