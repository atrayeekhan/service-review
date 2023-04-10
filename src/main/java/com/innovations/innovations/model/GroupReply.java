package com.innovations.innovations.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "question_answer")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class GroupReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_answer_id")
    private Integer questionNumberId;

    @Column(name = "team_id")
    private String teamId ;

    @Column(name = "version")
    private String version ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionAnswerId")
    @ToString.Exclude
    private List<TeamResponse> teamResponseList ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GroupReply that = (GroupReply) o;
        return getQuestionNumberId() != null && Objects.equals(getQuestionNumberId(), that.getQuestionNumberId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
