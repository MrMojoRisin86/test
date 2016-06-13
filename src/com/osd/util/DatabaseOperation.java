package com.osd.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseOperation {

	private Statement stmt = null;
	private static Connection c = null;

	public void insert(String fn, String ln, String em, String drz, String time) {

		try {
			getConnection();
			stmt = c.createStatement();
			String sql = "INSERT INTO Korisnici (FirstName,LastName,Email,Drzava,Datum) VALUES ('" + fn + "', '" + ln
					+ "','" + em + "', '" + drz + "','" + time + "');";
			stmt.executeUpdate(sql);
			stmt.close();

			// c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");

	}

	public void insert(Korisnici k) {

		try {
			getConnection();
			stmt = c.createStatement();
			String sql = "INSERT INTO Korisnici (FirstName,LastName,Email,Drzava,Datum) VALUES ('" + k.getFirstName()
					+ "', '" + k.getLastName() + "','" + k.getEmail() + "', '" + k.getDrzava() + "','" + k.getTime()
					+ "');";
			stmt.executeUpdate(sql);
			stmt.close();

			// c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");

	}

	public Connection getConnection() {

		if (c == null) {
			c = DatabaseConnection.connect("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
			return c;

		}
		return c;
	}

	public static String vratiDatum() {

		Date date = new Date();
		return date.toString();
	}

	public static Timestamp vratiTimeStamp() {

		Timestamp t = new Timestamp(new Date().getTime());
		return t;
	}

	public ArrayList<Korisnici> getDatabase() {

		ArrayList<Korisnici> listaKorinika = new ArrayList<>();
		ResultSet rs = null;
		try {
			int i = 0;
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM korisnici");

			while (rs.next()) {

				int id = rs.getInt("id");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String email = rs.getString("email");
				String drzava = rs.getString("drzava");
				Timestamp datum = rs.getTimestamp("datum");
				Korisnici k = new Korisnici();
				k = k.firstName(firstname).lastName(lastname).drzava(drzava).email(email).time(datum);
				listaKorinika.add(k);
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (c != null)
					c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		System.out.println("Operation done successfully");
		return listaKorinika;
	}

	public void printDatabase(ArrayList<Korisnici> baza) {

		for (Korisnici k : baza) {

			System.out.println(k.toString());
		}
	}
	public int rowcount()throws SQLException{
		c=DatabaseConnection.connect("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Korisnici");

	    rs = stmt.executeQuery("SELECT COUNT(*) FROM Korisnici");
	    // get the number of rows from the result set
	    rs.next();
	    int rowCount = rs.getInt(1);
	    System.out.println(rowCount);

	    rs.close();
	    stmt.close();
	    c.close();
		return rowCount;
	}

}
