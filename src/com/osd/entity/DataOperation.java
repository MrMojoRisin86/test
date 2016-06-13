package com.osd.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.osd.util.DatabaseConnection;
import com.osd.util.Korisnici;


public class DataOperation {
	

	public void addUser(com.osd.entity.Korisnici k) {

		DatabaseConnection.getEntityManager().getTransaction().begin();

		DatabaseConnection.getEntityManager().persist(k);
		DatabaseConnection.getEntityManager().getTransaction().commit();

	}

	public void findUser(int id) {

		com.osd.entity.Korisnici k = DatabaseConnection.getEntityManager().find(com.osd.entity.Korisnici.class, id);

		if (k != null)
			System.out.println(k.toString());
		else
			System.out.println("Ne postoji korisnik sa ovim ID-om!");

	}
	
	public com.osd.entity.Korisnici getUser (int id){
		
		//DatabaseConnection.getEntityManager().getTransaction().begin();
		
		com.osd.entity.Korisnici k = DatabaseConnection.getEntityManager().find(com.osd.entity.Korisnici.class, id);

		return k;
		
	}

	public void deleteUser(int id) {

		DatabaseConnection.getEntityManager().getTransaction().begin();
		com.osd.entity.Korisnici k = DatabaseConnection.getEntityManager().find(com.osd.entity.Korisnici.class, id);

		if (k != null) {
			DatabaseConnection.getEntityManager().remove(k);
			DatabaseConnection.getEntityManager().getTransaction().commit();
		} else {

			System.out.println("Ne postoji korisnik sa ovim ID-om.Brisanje nije moguce!");
		}
		// entitymanager.close();
		// emfactory.close();

	}

	public void updateUser(int id, String drzava) {

		DatabaseConnection.getEntityManager().getTransaction().begin();
		com.osd.entity.Korisnici k = DatabaseConnection.getEntityManager().find(com.osd.entity.Korisnici.class, id);

		// before update
		System.out.println(k.toString());
		k.setDrzava(drzava);
		DatabaseConnection.getEntityManager().getTransaction().commit();

		// after updateaaa
		System.out.println(k);
		// entitymanager.close();
		// emfactory.close();

	}

	public static Timestamp vratiTimeStamp() {//vraca Timestamp

		Timestamp t = new Timestamp(new Date().getTime());
		return t;
	}
	
	
	
	public int duzina(List<com.osd.entity.Korisnici> lista){
		
		return lista.size();
	}

}
