package com.innovations.innovations.innovations;

import com.innovations.innovations.model.GroupReply;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupReplyDao extends CrudRepository<GroupReply , Long> {
//    TeamResponse findByTeamIdAndQuestionNumberAndVersion(String teamId, String questionNumber);
    List<GroupReply> findAllByTeamIdOrderByVersion(String teamName);
    List<GroupReply> findByTeamIdAndVersion(String teamName , String version);

    GroupReply findByQuestionNumberId (Integer questionNumberId);

    @Query("SELECT qa FROM GroupReply qa WHERE qa.version = (SELECT MAX(qa2.version) FROM GroupReply qa2 WHERE qa2.teamId = qa.teamId)")
    List<GroupReply> findAllWithHighestVersion();

}


