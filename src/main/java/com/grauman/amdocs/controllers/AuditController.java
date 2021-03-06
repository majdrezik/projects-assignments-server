package com.grauman.amdocs.controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grauman.amdocs.dao.AuditDAO;
import com.grauman.amdocs.models.Audit;
import com.grauman.amdocs.models.AuditEmployee;
import com.grauman.amdocs.models.WorkSite;

@RequestMapping("/audit")
@RestController
@CrossOrigin(origins = "*" , allowCredentials = "true")
public class AuditController {
	@Autowired
	private AuditDAO auditDAO;
	
	@GetMapping("")
	public ResponseEntity<List<AuditEmployee>> all(@RequestParam int page,@RequestParam int limit) throws SQLException{
		List<AuditEmployee> audit=auditDAO.findAll(page,limit);
 		return ResponseEntity.ok().body(audit);  
	}
	
    @GetMapping("/search")
    public ResponseEntity<List<AuditEmployee>> searchAuditByDateBetween(
    	@RequestParam(defaultValue = "0") Integer number,
    	@RequestParam String activity,
    	@RequestParam Date datefrom,
     	@RequestParam Date dateto,
     	@RequestParam int page,
     	@RequestParam int limit)throws SQLException{ 
         List<AuditEmployee> auditByDateFrom=auditDAO.searchAudit(number,activity, Optional.of(datefrom), Optional.of(dateto),page,limit);
  		return ResponseEntity.ok().body(auditByDateFrom);  

    }

 @GetMapping("/count")
 public Integer numberOfRoles() throws SQLException {
 	Integer Counter= auditDAO.countAudit();
 	return Counter;
 }
 @PostMapping("")
 public ResponseEntity<AuditEmployee> newAudit(@RequestBody AuditEmployee audit) throws SQLException{
	 AuditEmployee addedAudit=auditDAO.add(audit);
    return ResponseEntity.ok().body(addedAudit);
 }
 
}
