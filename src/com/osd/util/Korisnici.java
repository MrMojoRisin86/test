package com.osd.util;

import java.sql.Timestamp;

public class Korisnici {

	private String firstName;
	private String lastName;
	private String email;
	private String drzava;
	private Timestamp time;

	public Korisnici firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public Korisnici lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public Korisnici email(String email) {
		this.email = email;
		return this;
	}

	public Korisnici drzava(String drzava) {
		this.drzava = drzava;
		return this;
	}

	public Korisnici time(Timestamp time) {
		this.time = time;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String toString() {
		return (firstName + " " + lastName + " " + email + " " + drzava);

	}

}
