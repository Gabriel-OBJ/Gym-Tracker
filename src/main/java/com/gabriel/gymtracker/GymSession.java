package com.gabriel.gymtracker;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class GymSession {

    @Id
    @GeneratedValue
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private LocalDateTime date;

    @OneToMany(mappedBy = "gymSession", cascade = CascadeType.ALL)
    private List<WorkoutSet> workoutSetList;
    private Double weighIn;

    public GymSession() {
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setWorkoutSetList(List<WorkoutSet> workoutSetList) {

        workoutSetList.forEach(workoutSet -> workoutSet.setGymSession(this));

        this.workoutSetList = workoutSetList;
    }

    public List<WorkoutSet> getWorkoutSetList() {
        return workoutSetList;
    }

    public Double getWeighIn() {
        return weighIn;
    }

    public void setWeighIn(Double weighIn) {
        this.weighIn = weighIn;
    }
}
