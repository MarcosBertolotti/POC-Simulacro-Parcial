package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.domain.Candidato;
import com.example.SimulacroParcial.domain.Voto;
import com.example.SimulacroParcial.repository.IRepositoryCandidato;
import com.example.SimulacroParcial.repository.IRepositoryVotos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/candidatos")
@RestController
public class RestControllerCandidato {

    @Autowired
    IRepositoryCandidato repoCandidato;

    @Autowired
    IRepositoryVotos repoVotos;

    // [POST] /candidatos/  Dar de alta 1 candidato.
    @PostMapping("")
    public void add(@RequestBody Candidato c){
        repoCandidato.save(c);
    }

    // [GET] /candidatos/ Devolver los candidatos.
    @GetMapping("")
    public List<Candidato> getAll(){
        return repoCandidato.findAll();
    }

    // [GET] /candidatos/{id} Devolver candidato id 1.
    @GetMapping("/{id}")
    public Candidato getById(@PathVariable("id") Integer id) throws Exception {
        return repoCandidato.findById(id).orElseThrow(
                () -> new Exception(
                        String.format("No se pudo encontrar el candidato")));
    }

    // [POST] /votes/{idCandidato} Votar al candidato N° 1.
    @PostMapping("/votos/{id}/voto")
    public void votar(@PathVariable("id") Integer id, @RequestBody Voto voto) throws Exception {
        Candidato  c = repoCandidato.findById(id).orElseThrow(
                () -> new Exception(
                        String.format("No se pudo encontrar el candidato")));

        c.getVotos().add(voto);
        voto.setCandidato(c);

        repoCandidato.save(c);
    }

    // Generar un Scheduler para eliminar los votos que fueron realizados hace mas de 5 minutos.
    @Scheduled (fixedRate = 10000)
    public void eliminarVotos(){
        repoVotos.eliminarVotos();
    }


}
