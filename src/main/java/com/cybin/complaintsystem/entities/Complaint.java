package com.cybin.complaintsystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="complaints")
public class Complaint {

	@Id
	@Column(name="id")
	@GenericGenerator(name="inc", strategy="increment")
	@GeneratedValue(generator="inc")
	private int id;
	
	@Column(name="message")
	private String message;
	
	@Column(name="sender_email")
	private String senderEmail;
	
	@Column(name="sender_Name")
	private String senderName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public Complaint() {
		super();
	}

	public Complaint(String message, String senderEmail, String senderName) {
		super();
		this.message = message;
		this.senderEmail = senderEmail;
		this.senderName = senderName;
	}
	
	
	
	
}
