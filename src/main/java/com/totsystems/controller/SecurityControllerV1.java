package com.totsystems.controller;

import com.totsystems.model.Security;
import com.totsystems.service.HistoryService;
import com.totsystems.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("api/v1/securities")
@CrossOrigin
public class SecurityControllerV1 {

    private SecurityService service;

    @Autowired
    public SecurityControllerV1(SecurityService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Security> getSecurity(@PathVariable("id") int id){
        Security security = service.get(id);
        if(security != null){
            return new ResponseEntity<>(security, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping()
    public ResponseEntity<List<Security>> getAllSecurity(){
        List<Security> securities = service.getAll();
        if(securities != null){
            return new ResponseEntity<>(securities, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Security> delSecurity(@PathVariable("id") int id){
        Security security = service.get(id);
        if(security != null){
            service.delete(id);
            return new ResponseEntity<>(security, HttpStatus.NO_CONTENT);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<List<Security>> saveSecurity(@RequestParam("file") MultipartFile inputFile){
        System.out.println("F");
        if(inputFile == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        List<Security> securities = service.save(inputFile);
        if(securities != null){
            return new ResponseEntity<>(securities, HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<Security> updateSecurity(@RequestBody Security security){
        if(security == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Security found = service.get(security.getId());
        if(found != null){
            service.update(security);
            return new ResponseEntity<>(security, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
