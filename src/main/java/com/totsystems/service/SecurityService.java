package com.totsystems.service;

import com.totsystems.model.Security;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SecurityService {

    public List<Security> save(MultipartFile inputFile);

    public Security saveOne(Security security);

    public Security get(int id);

    public List<Security> getAll();

    public void delete(int id);

    public void update(Security security);

    public boolean exist(String secId);


}
