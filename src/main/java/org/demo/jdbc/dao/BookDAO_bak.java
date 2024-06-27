package org.demo.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.demo.dto.BookDTO;
import org.demo.jdbc.commons.SqlMapper;
import org.demo.jdbc.commons.SqlSelect;

public class BookDAO_bak extends SqlSelect {

	private static final String SELECT_BY_ID = """
			SELECT id, title
			FROM books
			WHERE id = ?
			""";
	private static final String SELECT_COUNT = """
			SELECT count(*)
			FROM books
			""";
	private static final String SELECT_ALL = """
			SELECT id, title
			FROM books
			""";

	private final BookMapper bookMapper ;
	
	public BookDAO_bak() {
		super();
		this.bookMapper = new BookMapper();
	}

	public BookDTO selectOneById(int id) throws SQLException {
		return executeSelectOne(SELECT_BY_ID, Arrays.asList(id), bookMapper);
	}

	public int selectCount() throws SQLException {
		return executeSelectOne(SELECT_COUNT, new IntegerMapper());
	}

	public List<BookDTO> selectAll() throws SQLException {
		return executeSelectMany(SELECT_ALL, bookMapper);
	}

//	public void toDTO(ResultSet rs, Object object) throws SQLException {
//		BookDTO dto = (BookDTO) object;
//		dto.setId(rs.getInt("id"));
//		dto.setTitle(rs.getString("title"));
//	}

//	@Override
//	protected BookDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//		BookDTO dto = new BookDTO();
//		dto.setId(rs.getInt("id"));
//		dto.setTitle(rs.getString("title"));
//		return dto;
//	}

	private class BookMapper implements SqlMapper<BookDTO> {
		@Override
		public BookDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BookDTO dto = new BookDTO();
			dto.setId(rs.getInt("id"));
			dto.setTitle(rs.getString("title"));
			return dto;
		}
	}

	private class IntegerMapper implements SqlMapper<Integer> {
		@Override
		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return Integer.valueOf(rs.getInt(1));
		}
	}
}
