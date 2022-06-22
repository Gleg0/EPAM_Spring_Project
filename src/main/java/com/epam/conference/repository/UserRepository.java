package com.epam.conference.repository;
import com.epam.conference.entity.user.Role;
import com.epam.conference.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT nextval(pg_get_serial_sequence('t_user', 'id'))", nativeQuery = true)
    Long getNextId();
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findByRole(Role role);
}
