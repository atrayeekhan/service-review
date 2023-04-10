package com.innovations.innovations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "team_response")
@Getter
@Setter
@ToString
@RequiredArgsConstructor

public class TeamResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_response_id")
    private String teamResponseId;

    @Column(name = "question_number")
    private String questionNumber;

    @Column(name = "response")
    private String response;

    @JoinColumn(name =  "questionNumberId" , referencedColumnName = "question_answer_id")
    @ToString.Exclude
    @JsonIgnore
    private String questionAnswerId;

}