/*
 * @author - Rahul Sharma
 * */
package com.rs.contactlist.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rs.contactlist.dto.Contact;

import lombok.Data;
@Data
public class ContactRequest {
	
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String status;

	@JsonIgnore
	public Contact getContact() {
		Contact contact = new Contact();
		contact.setId(id);
		contact.setEmail(email);
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setPhoneNumber(phoneNumber);
		contact.setStatus(status);
		return contact;
	}
	

}
