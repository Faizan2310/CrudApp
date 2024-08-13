package com.crud.crudApp.Repositories;

import com.crud.crudApp.Entities.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ActorRepo extends JpaRepository<Actor, Short> {

}
