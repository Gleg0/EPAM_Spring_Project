package com.epam.conference.repository;
import com.epam.conference.entity.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
    @Query(value = "SELECT nextval(pg_get_serial_sequence('t_event', 'id'))", nativeQuery = true)
    Long getNextId();
    Page<Event> findAll(Pageable pageable);
    List<Event> findAllByDateAfter(Date date);
    List<Event> findAllByDateBefore(Date from);
}
