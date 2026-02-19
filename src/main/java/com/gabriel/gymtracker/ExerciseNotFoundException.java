package com.gabriel.gymtracker;

public class ExerciseNotFoundException extends RuntimeException {
    public ExerciseNotFoundException(Long id) {

        super("Could not find exercise with ID " + id);
    }

    public ExerciseNotFoundException(String name) {
        super("Could not find exercise with name" + name);
    }

}
