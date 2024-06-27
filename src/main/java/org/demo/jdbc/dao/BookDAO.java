package org.demo.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.demo.dto.BookDTO;
import org.demo.jdbc.commons.IntegerMapper;
import org.demo.jdbc.commons.SqlMapper;
import org.demo.jdbc.commons.SqlSelect;
import org.demo.jdbc.commons.SqlSelectRequest;
import org.demo.jdbc.commons.StringMapper;

public class BookDAO extends SqlSelect {

	/**
	 * Select book by id 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
//	public BookDTO selectById(int id) throws SQLException {
//		return selectById.executeSelectOne(Arrays.asList(id));
//	}
//	private final SelectById selectById = new SelectById();
//	private class SelectById extends SqlSelectRequest<BookDTO> {
//		private static final String SQL = """
//				SELECT id, title
//				FROM books
//				WHERE id = ?
//				""";
//		protected SelectById() {
//			super(SQL, new BookMapper());
//		}
//	}	
	public BookDTO selectById(int id) throws SQLException {
		String SQL = 
				"""
				SELECT id, title
				FROM books
				WHERE id = ?
				""";		
		SqlSelectRequest<BookDTO> sql = new SqlSelectRequest<>(SQL, new BookMapper());
		return sql.executeSelectOne(Arrays.asList(id));
	}

	public String selectTitleById(int id) throws SQLException {
		String SQL = 
				"""
				SELECT title
				FROM books
				WHERE id = ?
				""";		
		SqlSelectRequest<String> sql = new SqlSelectRequest<>(SQL, new StringMapper());
		return sql.executeSelectOne(Arrays.asList(id));
	}
	// -------------------------------------------------------------------------------------------
	/**
	 * Count books
	 * @return
	 * @throws SQLException
	 */
//	public int selectCount() throws SQLException {
//		return selectCount.executeSelectOne();
//	}
//	private final SelectCount selectCount = new SelectCount();
//	private class SelectCount extends SqlSelectRequest<Integer> {
//		protected SelectCount() {
//			super("SELECT count(*) FROM books", new IntegerMapper());
//		}		
//	}
	public int selectCount() throws SQLException {
		String SQL = 
				"""
				SELECT count(*)
				FROM books
				""";		
		SqlSelectRequest<Integer> sql = new SqlSelectRequest<>(SQL, new IntegerMapper());
		return sql.executeSelectOne();
	}
//	private final SelectCount selectCount = new SelectCount();
//	private class SelectCount extends SqlSelectRequest<Integer> {
//		protected SelectCount() {
//			super("SELECT count(*) FROM books", new IntegerMapper());
//		}		
//	}

	// -------------------------------------------------------------------------------------------
	/**
	 * Select all books
	 * @return
	 * @throws SQLException
	 */
//	public List<BookDTO> selectAll() throws SQLException {
//		return selectAll.executeSelectMany();
//	}
//	private final SelectAll selectAll = new SelectAll();
//	private class SelectAll extends SqlSelectRequest<BookDTO> {
//		private static final String SQL = "SELECT id, title FROM books";
//		protected SelectAll() {
//			super(SQL, new BookMapper());
//		}	
//	}
	public List<BookDTO> selectAll() throws SQLException {
		String SQL = 
				"""
				SELECT id, title
				FROM books
				""";		
		SqlSelectRequest<BookDTO> sql = new SqlSelectRequest<>(SQL, new BookMapper());
		return sql.executeSelectMany();
	}
	// -------------------------------------------------------------------------------------------
	/**
	 * Mapper : ResultSet to BookDTO
	 */
	private class BookMapper implements SqlMapper<BookDTO> {
		@Override
		public BookDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BookDTO dto = new BookDTO();
			dto.setId(rs.getInt("id"));
			dto.setTitle(rs.getString("title"));
			return dto;
		}
	}

//	// -------------------------------------------------------------------------------------------
//	private class IntegerMapper implements SqlMapper<Integer> {
//		@Override
//		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
//			return Integer.valueOf(rs.getInt(1));
//		}
//	}
}
