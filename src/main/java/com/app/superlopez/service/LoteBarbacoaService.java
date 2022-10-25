package com.app.superlopez.service;

import com.app.superlopez.model.LoteBarbacoa;
import com.app.superlopez.repository.LoteBarbacoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class LoteBarbacoaService {

    @Autowired
    private LoteBarbacoaRepository loteBarbacoaRepository;


    public List<LoteBarbacoa> findAll(){
        return loteBarbacoaRepository.findAll();
    }

    public LoteBarbacoa findById(Integer id){
        return loteBarbacoaRepository.findById(id).orElse(null);
    }
}
