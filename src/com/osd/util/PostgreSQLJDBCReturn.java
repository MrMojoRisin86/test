package com.osd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class PostgreSQLJDBCReturn {
   public static void main( String args[] )
     {
       Connection c = null;
       Statement stmt = null;
       try {
       Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/postgres",
            "postgres", "postgres");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM korisnici" );
         while ( rs.next() ) {
            int id = rs.getInt("id");
            String firstname = rs.getString("firstname");
            String lastname=rs.getString("lastname"); 
            String  email = rs.getString("email");
            String drzava=rs.getString("drzava");
            System.out.println( "ID = " + id );
            System.out.println( "First Name = " + firstname );
            System.out.println( "Last Name = " + lastname);
            System.out.println( "Email = " + email );
            System.out.println( "Drzava = " + drzava);
         }
         rs.close();
         stmt.close();
         c.close();
       } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
       }
       System.out.println("Operation done successfully");
     }
}
