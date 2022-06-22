package com.epam.conference.entity.event;
import com.epam.conference.entity.user.User;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
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
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "t_users_events",
            joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<User> users;
}
