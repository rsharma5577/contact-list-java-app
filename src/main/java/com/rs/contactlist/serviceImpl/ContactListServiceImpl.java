/*
 * @author - Rahul Sharma
 * */

package com.rs.contactlist.serviceImpl;


import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.rs.contactlist.dao.ContactListDAO;
import com.rs.contactlist.dto.Contact;
import com.rs.contactlist.request.ContactRequest;
import com.rs.contactlist.service.ContactListService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ContactListServiceImpl implements ContactListService {

	@Autowired
	private ContactListDAO contactListDAO;

	public ResponseEntity<List<Contact>> getContactList() {
		try {
			List<Contact> contactList  = contactListDAO.getContactList();
			return new ResponseEntity<>(contactList,HttpStatus.OK);
			
		}catch(Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	public ResponseEntity<Contact> addContact(ContactRequest contactRequest) { 
		try{
			HttpStatus httpStatus = null;
			Contact contactResponse = contactListDAO.addContact(contactRequest);
				if(null != contactResponse) {
					httpStatus = HttpStatus.CREATED;
				} 
			return new ResponseEntity<>(contactResponse,httpStatus);
		
		}catch(Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}

	@Override
	public ResponseEntity<Contact> deleteContact(String id) {
		try {
			Boolean status = contactListDAO.deleteContact(id);
			HttpStatus httpStatus = null;
			if(status) {
				httpStatus = HttpStatus.OK;
			} else {
				httpStatus = HttpStatus.NOT_FOUND;
			}
			return new ResponseEntity<>(null,httpStatus);

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}

	@Override
	public ResponseEntity<Contact> updateContact(ContactRequest contactRequest, String id) {
		try {
			HttpStatus httpStatus = null;
			Contact contactResponse = contactListDAO.updateContact(contactRequest, id);
				if(null != contactResponse) {
					httpStatus = HttpStatus.OK;
				} else {
					httpStatus = HttpStatus.NOT_FOUND;
				}
			
			return new ResponseEntity<>(contactResponse, httpStatus);

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}

}
