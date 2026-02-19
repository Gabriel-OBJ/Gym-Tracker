package com.gabriel.gymtracker;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GymSessionModelAssembler {


    EntityModel<GymSession> toModel(GymSession gymSession) {

        return EntityModel.of(gymSession,
                linkTo(methodOn(GymSessionController.class).one(gymSession.getId())).withSelfRel(),
                linkTo(methodOn(GymSessionController.class).all()).withRel("gym-sessions"));
    }
}
