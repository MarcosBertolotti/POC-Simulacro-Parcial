package com.example.SimulacroParcial.repository;

import com.example.SimulacroParcial.domain.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryCandidato extends JpaRepository<Candidato,Integer> {

}


