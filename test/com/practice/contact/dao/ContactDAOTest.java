package com.practice.contact.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.practice.contact.model.Contact;

class ContactDAOTest {
	private DriverManagerDataSource dataSource;
	private ContactDAO dao;
	
	
	@BeforeEach
	void SetupBeforeEach() {
		
		dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/contactdb?verifyServerCertificate=false&useSSL=true");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		
		dao=new ContactDAOImpl(dataSource);
		
	}

	@Test
	void testSave() {		
		
		Contact contact=new Contact("yosgesh","yogesh@mailinator.com","Chennai,TN","8248537855");
		int result =dao.save(contact);
		
		assertTrue(result >0);
	}

	@Test
	void testUpdate() {
		Contact contact=new Contact(6,"praveen","praveen@mailinator.com","Bangalore,KL","9597616892");
		int result=dao.update(contact);
		assertTrue(result >0);

	}

	@Test
	void testGet() {
		Integer id=5;
		Contact contact =dao.get(id);
		if(contact!=null) {
			System.out.println(contact);
		}
		
		assertNotNull(contact);
	}

	@Test
	void testDelete() {
		Integer id=3;
		int result=dao.delete(id);
		assertTrue(result>0);
		
	}


	@Test
	void testList() {
		List<Contact> listContacts=(List<Contact>) dao.list();
		
		if(listContacts!=null) {
			System.out.println(listContacts);
		
		}
		
		
		assertTrue(! listContacts.isEmpty());
	}

}
