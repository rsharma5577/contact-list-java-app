/*
 * @author - Rahul Sharma
 */

package com.rs.contactlist.dao;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.rs.contactlist.dto.Contact;
import com.rs.contactlist.request.ContactRequest;

public interface ContactListDAO {

	List<Contact> getContactList() throws JsonParseException, JsonMappingException, IOException;

	Contact addContact(ContactRequest ContactRequest) throws JsonParseException, JsonMappingException, IOException;

	Boolean deleteContact(String name) throws JsonParseException, JsonMappingException, IOException;

	Contact updateContact(ContactRequest contactRequest, String id)
			throws JsonParseException, JsonMappingException, IOException;
}