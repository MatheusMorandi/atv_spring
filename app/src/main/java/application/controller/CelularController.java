package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.server.ResponseStatusException;

import application.model.Celular;

import application.repository.CelularRepository;

@RestController
@RequestMapping("/celular")
public class CelularController {

    @Autowired
    private CelularRepository celularRepo;

    @GetMapping
    public Iterable<Celular> getAll() {        

        return celularRepo.findAll();

    }

    @PostMapping
    public Celular post(@RequestBody Celular celular) {

        return celularRepo.save(celular);

    }

    @GetMapping("/{id}")
    public Celular getOne(@PathVariable long id) {

        Optional<Celular> resultado = celularRepo.findById(id);

        if (resultado.isEmpty()){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Celular Não Encontrado");

        }

        return resultado.get();

    }

    @PutMapping("/{id}")
    public Celular put(@PathVariable long id, @RequestBody Celular novosDados){

        Optional<Celular> resultado = celularRepo.findById(id);

        if (resultado.isEmpty()){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Celular Não Encontrado");

        }

        resultado.get().setMarca(novosDados.getMarca());
        
        resultado.get().setModelo(novosDados.getModelo());

        return celularRepo.save(resultado.get());

    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable long id){

        if (!celularRepo.existsById(id)){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Celular Não Encontrado");

        }

        celularRepo.deleteById(id);

    }

}