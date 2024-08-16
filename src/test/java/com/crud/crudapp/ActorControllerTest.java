package com.crud.crudapp;


import com.crud.crudapp.controller.ApiController;
import com.crud.crudapp.entities.Actor;
import com.crud.crudapp.exeption.ResourceNotFoundException;
import com.crud.crudapp.repositories.ActorRepo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ActorControllerTest {

    ActorRepo repo = mock(ActorRepo.class);
    ApiController controller = new ApiController(repo);

    @Test
    void readAllReturnsAllTheActors(){
        Actor actor1 = new Actor((short)1, "Faizan", "Naseem", Calendar.getInstance());
        Actor actor2 = new Actor((short)2, "John", "Johns", Calendar.getInstance());
        List<Actor> mockActors = Arrays.asList(actor1, actor2);

        doReturn(mockActors).when(repo).findAll();
        List<Actor> actualActors = controller.readAll();
        assertEquals(mockActors, actualActors);
    }

    @Test
    void findActorReturnActorWhenValidIdGiven(){
        Short actorId = 1;
        Actor mockActor = new Actor(actorId, "Faizan", "Naseem", Calendar.getInstance());
        doReturn(Optional.of(mockActor)).when(repo).findById(actorId);

        Actor actualActor = controller.findActor(actorId);
        assertEquals(mockActor, actualActor);
    }
    @Test
    void findActorThrowsExceptionWhenInvalidIdGiven(){
        Short actorId = 3;
        doReturn(Optional.empty()).when(repo).findById(actorId);

        assertThrows(ResourceNotFoundException.class, ()-> {
            controller.findActor(actorId);
        });
    }
    @Test
    void saveActorCallsRepositorySave() {
        Short actorId = 201;
        Actor actor = new Actor(actorId, "Faizan", "Naseem", Calendar.getInstance());
        String actualResult = controller.saveActor(actor);

        verify(repo, times(1)).save(actor);
        assertEquals("Saved!!", actualResult);
    }
    @Test
    void saveActorThrowsExceptionWhenInvalidLastNameGiven() {
        Short actorId = 1;
        Actor invalidActor = new Actor(actorId, "Faizan", null, Calendar.getInstance());
        assertThrows(ResourceNotFoundException.class, () -> {
            controller.saveActor(invalidActor);
        });
    }
    @Test
    void saveActorThrowsExceptionWhenInvalidFirstNameGiven() {
        Short actorId = 1;
        Actor invalidActor = new Actor(actorId, null, "Naseem", Calendar.getInstance());
        assertThrows(ResourceNotFoundException.class, () -> {
            controller.saveActor(invalidActor);
        });
    }
    @Test
    void updateActorUpdatesActorWhenValidIdAndBodyGiven() {
        Short actorId = 1;
        Actor existingActor = new Actor(actorId, "OldFirstName", "OldLastName", Calendar.getInstance());
        Actor updatedActor = new Actor(actorId, "NewFirstName", "NewLastName", Calendar.getInstance());

        doReturn(Optional.of(existingActor)).when(repo).findById(actorId);

        String actualResult = controller.updateActor(actorId, updatedActor);

        verify(repo).findById(actorId);
        verify(repo).save(existingActor);
        assertEquals("Updated!!", actualResult);
        assertEquals("NewFirstName", existingActor.getFirstName());
        assertEquals("NewLastName", existingActor.getLastName());
    }
    @Test
    void updateActorThrowsExceptionWhenActorNotFound() {
        Short actorId = 1;
        Actor updatedActor = new Actor(actorId, "NewFirstName", "NewLastName", Calendar.getInstance());

        doReturn(Optional.empty()).when(repo).findById(actorId);

        assertThrows(ResourceNotFoundException.class, () -> {
            controller.updateActor(actorId, updatedActor);
        });
    }
    @Test
    void deleteActorDeletesTheActorWhenValidIdGiven(){
        Short actorId = 1;
        Actor actorToDelete = new Actor(actorId, "Faizan", "Naseem", Calendar.getInstance());

        doReturn(Optional.of(actorToDelete)).when(repo).findById(actorId);
        String deletedRecord = controller.deleteActor(actorId);

        verify(repo).delete(actorToDelete);
        assertEquals("Deleted!!!", deletedRecord);
    }
    @Test
    void deleteActorThrowsExceptionWhenInvalidIdGiven(){
        Short actorId = 1;
        doReturn(Optional.empty()).when(repo).findById(actorId);
        assertThrows(ResourceNotFoundException.class,()->{
            controller.deleteActor(actorId);
        } );
    }
}
