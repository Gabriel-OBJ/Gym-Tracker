package com.gabriel.gymtracker;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class GymSessionController {
    
    private GymSessionService service;
    private GymSessionModelAssembler assembler;

    public GymSessionController(GymSessionService service, GymSessionModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping("/gym-sessions")
    CollectionModel<EntityModel<GymSession>> all() {

        List<GymSession> gymSessions = service.findAll();

        List<EntityModel<GymSession>> gymSessionsModel =
                gymSessions.stream().map(assembler::toModel).toList();

        return CollectionModel.of(gymSessionsModel,
                linkTo(methodOn(GymSessionController.class).all()).withSelfRel());
    }

    @GetMapping("/gym-sessions/{id}")
    EntityModel<GymSession> one(@PathVariable Long id) {

        GymSession gymSession = service.findById(id);

        return assembler.toModel(gymSession);
    }

    @PostMapping("/gym-sessions")
    ResponseEntity<?> newSession(@RequestBody GymSession newSession) {

        EntityModel<GymSession> gymSessionModel = assembler.toModel(service.saveGymSession(newSession));

        return ResponseEntity
                .created(linkTo(methodOn(GymSessionController.class).one(gymSessionModel.getContent().getId())).withSelfRel().toUri())
                .body(gymSessionModel);
    }

    @PutMapping("/gym-sessions/{id}")
    ResponseEntity<?> updateSession(@RequestBody GymSession changingSession, @PathVariable Long id) {

        if (service.existsById(id)) {

            EntityModel<GymSession> gymSessionModel = assembler.toModel(service.updateSession(changingSession, id));

            return ResponseEntity.ok(gymSessionModel);
        }
        else {

            EntityModel<GymSession> gymSessionModel = assembler.toModel(service.saveGymSession(changingSession));

            return ResponseEntity
                    .created(linkTo(methodOn(GymSessionController.class).one(gymSessionModel.getContent().getId())).withSelfRel().toUri())
                    .body(gymSessionModel);
        }
    }

    @DeleteMapping("/gym-sessions/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.status(204).build();
    }
}
