package com.epam.conference.repository;

import com.epam.conference.entity.event.Event;
import com.epam.conference.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event,Long> {
    @Query(value = "SELECT nextval(pg_get_serial_sequence('t_event', 'id'))", nativeQuery = true)
    Long getNextId();

}
