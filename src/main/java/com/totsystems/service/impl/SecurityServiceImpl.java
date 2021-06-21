package com.totsystems.service.impl;

import com.totsystems.model.Security;
import com.totsystems.repository.SecurityRepository;
import com.totsystems.service.SecurityService;
import com.totsystems.utils.XmlParseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.List;

@Service
public class SecurityServiceImpl implements SecurityService {


    private XmlParseUtil parseUtil;
    private SecurityRepository repository;

    @Autowired
    public SecurityServiceImpl(XmlParseUtil parseUtil, SecurityRepository repository) {
        this.parseUtil = parseUtil;
        this.repository = repository;
    }

    @Override
    public List<Security> save(MultipartFile inputFile) {
        try {
            return repository.saveAll(parseUtil.parseSecurity(inputFile));
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Security saveOne(Security security) {
        return repository.save(security);
    }

    @Override
    public Security get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Security> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Security security) {
        repository.save(security);
    }

    @Override
    public boolean exist(String secId) {
        return repository.findSecurityBySecId(secId).isPresent();
    }
}
