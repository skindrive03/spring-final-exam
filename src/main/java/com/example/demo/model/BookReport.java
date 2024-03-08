package com.example.demo.model;

import java.util.Date;

public class BookReport {
	
	public Long id;
	
	public String name;

	public String writer;
	
	public Date publicationDate;
	
	public String gender;

	public BookReport(Long id, String name, String writer, Date publicationDate, String gender) {
		this.id = id;
		this.name = name;
		this.writer = writer;
		this.publicationDate = publicationDate;
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
