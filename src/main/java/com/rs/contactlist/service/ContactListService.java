/*
 * @author - Rahul Sharma
 * */

package com.rs.contactlist.service;

import java.util.List;
import org.springframework.http.ResponseEntity;

import com.rs.contactlist.dto.Contact;
import com.rs.contactlist.request.ContactRequest;


public interface ContactListService {

	ResponseEntity<List<Contact>> getContactList();

	ResponseEntity<Contact> addContact(ContactRequest contactRequest);

	ResponseEntity<Contact> deleteContact(String contactId);

	ResponseEntity<Contact> updateContact(ContactRequest contactRequest, String contactId);

}