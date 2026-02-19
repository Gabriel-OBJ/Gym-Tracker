package com.gabriel.gymtracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ExerciseRepository exerciseRepository) {
        return args -> {
            log.info("Preloading " + exerciseRepository.save(new Exercise("Bench Press", "Chest")));
            log.info("Preloading " + exerciseRepository.save(new Exercise("Leg Extensios", "Quadriceps")));
            log.info("Preloading " + exerciseRepository.save(new Exercise("Biceps Curl", "Biceps")));
            log.info("Preloading " + exerciseRepository.save(new Exercise("Triceps Pushdown", "Triceps")));

            exerciseRepository.findAll().forEach(exercise -> {
                log.info("Preloaded " + exercise.getName());
            });
        };
    }
}
