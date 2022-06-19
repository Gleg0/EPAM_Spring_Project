package com.epam.conference.entity.event;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "t_event")
public class Event {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date date;
    private String description;
    @OneToMany
    @JoinColumn(name = "event_id")
    private Set<Report> reports;
}
