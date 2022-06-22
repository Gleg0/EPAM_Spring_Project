package com.epam.conference.repository;
import com.epam.conference.entity.event.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface RequestForReportRepository extends JpaRepository<Report,Long> {
    @Query(value = "SELECT nextval(pg_get_serial_sequence('t_request_for_report', 'id'))", nativeQuery = true)
    Long getNextId();
}
