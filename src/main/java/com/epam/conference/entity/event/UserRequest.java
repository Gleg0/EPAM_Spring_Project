package com.epam.conference.entity.event;
import com.epam.conference.entity.user.User;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "t_user_request")
public class UserRequest {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    private RequestType type;
    @OneToOne
    @JoinColumn(name = "report_id")
    private Report report;
}
