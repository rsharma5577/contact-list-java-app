package com.rs.contactlist.dto;

import lombok.Data;

@Data
public class Contact {
	
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String status;
}
