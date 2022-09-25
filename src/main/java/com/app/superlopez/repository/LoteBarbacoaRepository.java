package com.app.superlopez.repository;

import com.app.superlopez.model.LoteBarbacoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteBarbacoaRepository extends JpaRepository<LoteBarbacoa,Integer> {
}
