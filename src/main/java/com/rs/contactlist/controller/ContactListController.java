/*
 * @author - Rahul Sharma
 * */

package com.rs.contactlist.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rs.contactlist.dto.Contact;
import com.rs.contactlist.request.ContactRequest;
import com.rs.contactlist.service.ContactListService;



@RestController
@CrossOrigin()
@RequestMapping("/api")
public class ContactListController {

    @Autowired
    private ContactListService contactListService;

    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getRouters() {
        return contactListService.getContactList();
    }

    @PostMapping("/contacts")
    public ResponseEntity<Contact> addContact(@RequestBody ContactRequest contactRequest) {
        return contactListService.addContact(contactRequest);
    }
    

    @DeleteMapping("/contacts/{contactId}")
    public ResponseEntity<Contact> deleteRouter(@PathVariable("contactId") String contactId) {
    	 return contactListService.deleteContact(contactId);
    }

    @PutMapping("/contacts/{contactId}")
    public ResponseEntity<Contact> updateContact(@RequestBody ContactRequest contactRequest,@PathVariable("contactId") String contactId) {
        return contactListService.updateContact(contactRequest, contactId);
    }

}
