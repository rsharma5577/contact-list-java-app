/*
 * @author - Rahul Sharma
 */

package com.rs.contactlist.daoImpl;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rs.contactlist.dao.ContactListDAO;
import com.rs.contactlist.dto.Contact;
import com.rs.contactlist.request.ContactRequest;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class ContactListDAOImpl implements ContactListDAO {

	@Value("${file.path}")
	private String contactListFilePath;
	
	final ObjectMapper objectMapper = new ObjectMapper();

	public List<Contact> getContactList() throws JsonParseException, JsonMappingException, IOException {
		
		File fileObj = new File(contactListFilePath);
		List<Contact> contactList = new ArrayList<Contact>();
		if (fileObj.exists() && 0 < fileObj.length()) {
			contactList = objectMapper.readValue(fileObj, new TypeReference<List<Contact>>() {});
			if (contactList.size() == 1) {
				log.debug("Zero records in file");
			}
		} else {
			log.debug("file does not exist or is empty");
		}
		return contactList;
	}

	public Contact addContact(ContactRequest contactRequest) throws JsonParseException, JsonMappingException, IOException {
		File fileObj = new File(contactListFilePath);
		List<Contact> contactList = getContactList();
		Contact contact =null;
		if (null == contactList || contactList.isEmpty()) {
			if (!fileObj.exists()) {
				log.debug("Creating File and putting Entry");
			}
		}
		if (null != contactRequest) {
			 contact = contactRequest.getContact();
			 UUID uuid= UUID.randomUUID();
			 contact.setId(uuid.toString());
			 contactList.add(contact);
			 objectMapper.writeValue(fileObj, contactList);

		} else {
			log.debug("RequestBody for Add Contact is NULL");
		}
		return contact;
	}

	public Boolean deleteContact(String id) throws JsonParseException, JsonMappingException, IOException {
		File fileObj = new File(contactListFilePath);
		List<Contact> contactList = null;
			Contact contact =null;
			Boolean success = false;
			contactList = getContactList();
		if (null != contactList && !contactList.isEmpty()) {
			Optional<Contact> contactOpts = contactList.stream().filter(r -> r.getId().equals(id)).findFirst();
			if (contactOpts.isPresent()) {
				contact = contactOpts.get();
				contactList.remove(contact);
				objectMapper.writeValue(fileObj, contactList);
				success = true;
				if (0 == contactList.size()) {
					log.debug("zero record left in contact List file");
				}

			} else {
				log.debug("Contact does not exist");
				success = false;
			}
		} else {
			log.debug("file does not exist or is empty");
			success = false;
		}
		return success;
	}

	public Contact updateContact(ContactRequest contactRequest, String id) throws JsonParseException, JsonMappingException, IOException {
		File fileObj = new File(contactListFilePath);
		Contact oldContact =null;
		Contact newContact = new Contact();
		List<Contact> contactList = null;
		contactList = getContactList();
		if (null != contactList && !contactList.isEmpty()) {
			if (null != contactRequest && StringUtils.isNoneBlank(id)) {
				Optional<Contact> contactOpts = contactList.stream().filter(r -> r.getId().equals(id)).findFirst();
				if (contactOpts.isPresent()) {
					 oldContact = contactOpts.get();
					contactList.remove(oldContact);
					newContact = new Contact();
					newContact.setId(id);
			        newContact.setFirstName(
			                StringUtils.isNoneBlank(contactRequest.getFirstName()) ? contactRequest.getFirstName()
			                        : oldContact.getFirstName());
			        newContact.setLastName(
			                StringUtils.isNoneBlank(contactRequest.getLastName()) ? contactRequest.getLastName() : oldContact.getLastName());
			        newContact.setEmail(StringUtils.isNoneBlank(contactRequest.getEmail()) ? contactRequest.getEmail()
			                : oldContact.getEmail());

			        newContact.setPhoneNumber(StringUtils.isNoneBlank(contactRequest.getPhoneNumber()) ? contactRequest.getPhoneNumber()
			                : oldContact.getPhoneNumber());
			        newContact.setStatus(StringUtils.isNoneBlank(contactRequest.getStatus()) ? contactRequest.getStatus()
			                : oldContact.getStatus());
			        
			        contactList.add(newContact);
					objectMapper.writeValue(fileObj, contactList);

				} else {
					log.debug("Contact does not exist");
				}
			} else {
				log.error("Bad Request");
				
			}
		} 
		
		return newContact;
	}
	
	
	
}
