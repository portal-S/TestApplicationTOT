package com.totsystems.controller;

import com.totsystems.model.SumData;
import com.totsystems.service.SumDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sumdata")
@CrossOrigin
public class SumDataControllerV1 {

    private SumDataService service;

    @Autowired
    public SumDataControllerV1(SumDataService service) {
        this.service = service;
    }

    @GetMapping("/{secid}")
    public ResponseEntity<List<SumData>> getSumData(@PathVariable("secid") String secId){
        if(!(secId.isEmpty() && secId == null)){
            return new ResponseEntity<>(service.getData(secId), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
