package com.totsystems.service;

import com.totsystems.model.History;
import com.totsystems.model.Security;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HistoryService {

    public List<History> save(MultipartFile inputFile);

    public History get(int id);

    public List<History> getAll();

    public void delete(int id);

    public void update(History history);
}
