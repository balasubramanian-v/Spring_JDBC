package com.practice.contact.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import com.practice.contact.model.Contact;

public class ContactDAOImpl implements ContactDAO {

	
	private JdbcTemplate jdbcTemplate;
	
	
	public  ContactDAOImpl(DataSource dataSource) {
		
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	@Override
	public int save(Contact c) {
		String sql="INSERT INTO contact (name,email,address,phone)VALUES (?, ?, ?, ?)";
		return jdbcTemplate.update(sql,c.getName(),c.getEmail(),c.getAddress(),c.getPhone());// order of parameters must match values 
		
	}

	@Override
	public int update(Contact c) {
		String sql="UPDATE contact SET name=?, email=?, address=?, phone=? WHERE id=?";
		return jdbcTemplate.update(sql,c.getName(),c.getEmail(),c.getAddress(),c.getPhone(),c.getId());
		 
	}

	@Override
	public Contact get(Integer id) {
		String sql="SELECT * FROM contact WHERE id=" + id;
		
		ResultSetExtractor<Contact> extractor= new ResultSetExtractor<Contact>() {
			
			@Override
			public Contact extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					String name=rs.getString("name");
					String email=rs.getString("email");
					String address=rs.getString("address");
					String phone=rs.getString("phone");
					
					return new Contact(id,name,email,address,phone);
					
				}
				return null;
			}
		};
		
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(Integer id) {
		String sql="DELETE FROM contact WHERE id="+id;		
		return jdbcTemplate.update(sql);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> list() {
		String sql="SELECT * FROM contact";
		List<Contact> userList = new ArrayList<Contact>((Collection<? extends Contact>) jdbcTemplate.queryForList(sql));
		
		
		return userList;
		
			
	
	}

}
