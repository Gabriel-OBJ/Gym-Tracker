package com.gabriel.gymtracker;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
public class WorkoutSet {

    @Id
    @GeneratedValue
    @Schema (accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name = "gym_session_id")
    @JsonIgnore
    private GymSession gymSession;

    private double weight;
    private int reps;

    public WorkoutSet() {
    }

    public WorkoutSet(Exercise exercise, double weight, int reps, GymSession gymSession) {
        this.exercise = exercise;
        this.weight = weight;
        this.reps = reps;
        this.gymSession = gymSession;
    }

    public Long getId() {
        return id;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public GymSession getGymSession() {
        return gymSession;
    }

    public void setGymSession(GymSession gymSession) {
        this.gymSession = gymSession;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}