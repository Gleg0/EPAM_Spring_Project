package com.epam.conference.entity.event;
import com.epam.conference.entity.user.User;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
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
    @Temporal(TemporalType.DATE)
    private Date date;
    private String description;
    @OneToMany
    @JoinColumn(name = "event_id")
    private List<Report> reports;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "t_users_events",
            joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<User> users;
    public int getUsersSize(){
        return users.size();
    }
    public int getReportsSize(){
        return reports.size();
    }
}
