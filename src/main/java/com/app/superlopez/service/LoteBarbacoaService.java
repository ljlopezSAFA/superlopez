package com.app.superlopez.service;

import com.app.superlopez.repository.LoteBarbacoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoteBarbacoaService {

    @Autowired
    private LoteBarbacoaRepository loteBarbacoaRepository;
}
