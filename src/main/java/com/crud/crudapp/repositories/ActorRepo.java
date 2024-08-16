package com.crud.crudapp.repositories;

import com.crud.crudapp.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepo extends JpaRepository<Actor, Short> {

}
