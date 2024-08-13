package com.crud.crudApp.Controller;

import com.crud.crudApp.Entities.Actor;
import com.crud.crudApp.Repositories.ActorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private ActorRepo actorRepository;

    @GetMapping("/actors")
    public List<Actor> readAll(){
        return actorRepository.findAll();
    }
    @GetMapping("/actors/{id}")
    public Actor findActor(@PathVariable Short id){
        return actorRepository.findById(id).get();
    }
    @PostMapping("/actors/save")
    public String saveActor(@RequestBody Actor actor){
        actorRepository.save(actor);
        return "Saved!!";
    }
}
