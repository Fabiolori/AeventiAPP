package com.unicam.it.AEventi.Repo;

import com.unicam.it.AEventi.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {}
