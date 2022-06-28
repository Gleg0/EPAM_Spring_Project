package com.epam.conference.repository;
import com.epam.conference.entity.event.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestRepository extends JpaRepository<UserRequest,Long> {
    @Query(value = "SELECT nextval(pg_get_serial_sequence('t_user_request', 'id'))", nativeQuery = true)
    Long getNextId();
    List<UserRequest> getByUserId(Long id);
}
