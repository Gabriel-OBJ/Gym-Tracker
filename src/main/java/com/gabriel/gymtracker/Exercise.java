package com.gabriel.gymtracker;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class Exercise {

    @Id
    @GeneratedValue
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private String name;
    private String targetedMuscle;

    public Exercise() {
    }

    public Exercise(String name, String targetedMuscle) {
        this.name = name;
        this.targetedMuscle = targetedMuscle;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTargetedMuscle() {
        return targetedMuscle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTargetedMuscle(String targetedMuscle) {
        this.targetedMuscle = targetedMuscle;
    }
}
