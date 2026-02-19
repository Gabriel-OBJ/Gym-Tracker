package com.gabriel.gymtracker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GymSessionRepository extends JpaRepository<GymSession, Long> {
}
