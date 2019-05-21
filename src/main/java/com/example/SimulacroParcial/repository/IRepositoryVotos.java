package com.example.SimulacroParcial.repository;

import com.example.SimulacroParcial.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryVotos extends JpaRepository<Voto,Integer> {

    @Query(value = "delete from votos where fecha < (now() - interval 5 minutes )", nativeQuery = true)
    void eliminarVotos();


}
