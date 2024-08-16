package com.crud.crudapp.controller;

import com.crud.crudapp.entities.Actor;
import com.crud.crudapp.exeption.ResourceNotFoundException;
import com.crud.crudapp.repositories.ActorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    private final ActorRepo actorRepository;

    @Autowired
    public ApiController(ActorRepo actorRepository){
        this.actorRepository = actorRepository;
    }



    @GetMapping("/actors")
    public List<Actor> readAll(){
        return actorRepository.findAll();
    }
    @GetMapping("/actors/{id}")
    public Actor findActor(@PathVariable Short id){
        return actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Actor not found with id " + id));
    }

    @PostMapping("/actors/save")
    public String saveActor(@RequestBody Actor actor){
        if (actor.getFirstName() == null || actor.getLastName() == null) {
            throw new ResourceNotFoundException("Actor must have a first name and last name");
        }
        actorRepository.save(actor);
        return "Saved!!";
    }

    @PutMapping("actors/update/{id}")
    public String updateActor(@PathVariable Short id, @RequestBody Actor actor){
        Actor updatedActor = actorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id " + id));
        updatedActor.setFirstName(actor.getFirstName());
        updatedActor.setLastName(actor.getLastName());
        updatedActor.setLastUpdate(actor.getLastUpdate());
        actorRepository.save(updatedActor);
        return "Updated!!";
    }

    @DeleteMapping("actors/delete/{id}")
    public String deleteActor(@PathVariable Short id){
        Actor deletedActor = actorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id" + id));
        actorRepository.delete(deletedActor);
        return "Deleted!!!";
    }
}
