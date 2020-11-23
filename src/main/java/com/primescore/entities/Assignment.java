package com.primescore.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Assignment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String filename;
	private String path;
	private Date uploadDate;
	
	@ManyToOne
	private Classes classassignment;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	
	public Classes getClassassignment() {
		return classassignment;
	}
	public void setClassassignment(Classes classassignment) {
		this.classassignment = classassignment;
	}
	public Assignment(int id, String name, String filename, String path, Date uploadDate) {
		super();
		this.id = id;
		this.name = name;
		this.filename = filename;
		this.path = path;
		this.uploadDate = uploadDate;
	}
	public Assignment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
