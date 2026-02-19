package com.gabriel.gymtracker;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class GymSessionService {

    private final ExerciseRepository exerciseRepository;
    private final GymSessionRepository gymSessionRepository;

    GymSessionService(ExerciseRepository exerciseRepository, GymSessionRepository gymSessionRepository) {
        this.exerciseRepository = exerciseRepository;
        this.gymSessionRepository = gymSessionRepository;
    }

    List<GymSession> findAll() {

        return gymSessionRepository.findAll().stream().toList();
    }

    @Transactional
    GymSession saveGymSession(GymSession newGymSession) {

        for (WorkoutSet set : newGymSession.getWorkoutSetList()) {

            Exercise exercise = set.getExercise();

            if (exercise.getId() != null) {

                exerciseRepository.findById(exercise.getId())
                        .orElseThrow(() -> new ExerciseNotFoundException(exercise.getId()));
            }
            else if (exercise.getName() != null) {

                Exercise existing = exerciseRepository.findByName(exercise.getName())
                        .orElseGet(() -> exerciseRepository.save(exercise));

                set.setExercise(existing);
            }
        }

        return gymSessionRepository.save(newGymSession);
    }

    public GymSession updateSession(GymSession changingSession, Long id) {

        return gymSessionRepository.findById(id)
                .map(currentGymSession -> {
                    currentGymSession.setDate(changingSession.getDate());
                    currentGymSession.setWeighIn(changingSession.getWeighIn());
                    currentGymSession.setWorkoutSetList(changingSession.getWorkoutSetList());

                    return gymSessionRepository.save(currentGymSession);
                }).orElseThrow(() -> new RuntimeException("Gym session not found with ID " + id));
    }

    public GymSession findById(Long id) {

        return gymSessionRepository.findById(id).orElseThrow(() -> new ExerciseNotFoundException(id));
    }

    public boolean existsById(Long id) {

        return gymSessionRepository.existsById(id);
    }

    public void delete(Long id) {

        gymSessionRepository.deleteById(id);
    }
}
