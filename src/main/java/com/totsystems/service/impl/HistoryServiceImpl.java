package com.totsystems.service.impl;

import com.totsystems.model.History;
import com.totsystems.model.Security;
import com.totsystems.repository.HistoryRepository;
import com.totsystems.repository.SecurityRepository;
import com.totsystems.service.HistoryService;
import com.totsystems.service.SecurityService;
import com.totsystems.utils.RequestHandler;
import com.totsystems.utils.XmlParseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class HistoryServiceImpl implements HistoryService {

    private XmlParseUtil parseUtil;
    private HistoryRepository repository;
    private SecurityService securityService;
    private RequestHandler requestHandler;

    @Autowired
    public HistoryServiceImpl(XmlParseUtil parseUtil, HistoryRepository repository, SecurityService securityService, RequestHandler requestHandler) {
        this.parseUtil = parseUtil;
        this.repository = repository;
        this.securityService = securityService;
        this.requestHandler = requestHandler;
    }



    @Override
    public List<History> save(MultipartFile inputFile) {
        try {
            List<History> histories = parseUtil.parseHistory(inputFile);
            for (History history : histories){
                if(securityService.exist(history.getSecId())){
                    securityService.saveOne(parseUtil.parseSecurityByString(requestHandler.securityFoundRequest(history.getSecId())));
                    repository.save(history);
                }
            }
            return histories;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public History get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<History> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public void update(History history) {
        repository.save(history);
    }
}
