package com.innovations.innovations.dataSource;

import com.innovations.innovations.model.GroupReply;
import com.innovations.innovations.model.TeamResponse;

import javax.ws.rs.core.Response;
import java.util.List;

public interface InnovationDataSource {
    Response fetch(String teamId , String versionNumber);

    Response save(GroupReply groupReply);

    Response update(GroupReply groupReply);
}
