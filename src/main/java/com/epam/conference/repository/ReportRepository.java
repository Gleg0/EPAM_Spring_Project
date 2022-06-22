package com.epam.conference.repository;
import com.epam.conference.entity.event.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface ReportRepository extends JpaRepository<Report,Long> {
    @Query(value = "SELECT nextval(pg_get_serial_sequence('t_report', 'id'))", nativeQuery = true)
    Long getNextId();
}
