package com.epam.conference.entity.event;
import com.epam.conference.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_report")
public class Report {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "speaker_id")
    private User speaker;
    private String description;
}
