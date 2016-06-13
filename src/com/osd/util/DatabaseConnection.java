package com.osd.util;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseConnection {

	private static EntityManagerFactory emfactory;
	private static EntityManager entitymanager;

	public static Connection connect(String url, String user, String password) {
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return null;
		}

		System.out.println("Opened database successfully");
		return c;
	}

	public static EntityManager getEntityManager() {

		if (emfactory == null)
			emfactory = Persistence.createEntityManagerFactory("ExampleTom");
		if (entitymanager == null)
			entitymanager = emfactory.createEntityManager();

		return entitymanager;
	}

}
