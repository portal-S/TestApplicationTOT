package com.totsystems.controller;

import com.totsystems.model.History;
import com.totsystems.model.Security;
import com.totsystems.service.HistoryService;
import com.totsystems.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/histories")
@CrossOrigin
public class HistoryControllerV1 {

    private HistoryService service;

    @Autowired
    public HistoryControllerV1(HistoryService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<History> getHistory(@PathVariable("id") int id){
        History history = service.get(id);
        if(history != null){
            return new ResponseEntity<>(history, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping()
    public ResponseEntity<List<History>> getAllHistory(){
        List<History> histories = service.getAll();
        if(histories != null){
            return new ResponseEntity<>(histories, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<History> delHistory(@PathVariable("id") int id){
        History history = service.get(id);
        if(history != null){
            service.delete(id);
            return new ResponseEntity<>(history, HttpStatus.NO_CONTENT);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<List<History>> saveHistory(@RequestParam("file") MultipartFile inputFile){
        if(inputFile == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        List<History> histories = service.save(inputFile);
        if(histories != null){
            return new ResponseEntity<>(histories, HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<History> updateHistory(@RequestBody History history){
        if(history == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        History found = service.get(history.getId());
        if(found != null){
            service.update(history);
            return new ResponseEntity<>(history, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
