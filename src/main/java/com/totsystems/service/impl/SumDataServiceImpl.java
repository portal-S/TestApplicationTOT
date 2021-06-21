package com.totsystems.service.impl;

import com.totsystems.model.History;
import com.totsystems.model.Security;
import com.totsystems.model.SumData;
import com.totsystems.repository.SecurityRepository;
import com.totsystems.service.SecurityService;
import com.totsystems.service.SumDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SumDataServiceImpl implements SumDataService {

    private SecurityRepository repository;

    @Autowired
    public SumDataServiceImpl(SecurityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SumData> getData(String secId) {
        List <SumData> sumDataList = new LinkedList<>();
        Security security = repository.findSecurityBySecId(secId).get();
        List<History> histories = security.getHistoryList();
        for (History history : histories){
            sumDataList.add(new SumData(
                    security.getSecId(),
                    security.getRegNumber(),
                    security.getName(),
                    security.getEmitentTitle(),
                    history.getTradeDate(),
                    history.getNumTrades(),
                    history.getOpen(),
                    history.getClose()
            ));
        }
        return sumDataList;
    }
}
