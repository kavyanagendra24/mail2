package com.example.springapp.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.Type;

@Entity
public class Employee {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private String name;
    private String designation;
   
    private LocalDateTime datetimeField;
    
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(columnDefinition = "LONGBLOB")
    private byte[] content;
    
//	public Employee(int id, String name, String designation) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.designation = designation;
//	}

    
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public Employee(int id, String name, String designation, byte[] content ) {
//	super();
//	this.id = id;
//	this.name = name;
//	this.designation = designation;
//	
//	this.content = content;
//}
	
	

	

	

	public int getId() {
		return id;
	}

	public Employee(int id, String name, String designation, LocalDateTime datetimeField, byte[] content) {
	super();
	this.id = id;
	this.name = name;
	this.designation = designation;
	this.datetimeField = datetimeField;
	this.content = content;
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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public LocalDateTime getDatetimeField() {
		return datetimeField;
	}

	public void setDatetimeField(LocalDateTime datetimeField) {
		this.datetimeField = datetimeField;
	}
    
	
    

}
