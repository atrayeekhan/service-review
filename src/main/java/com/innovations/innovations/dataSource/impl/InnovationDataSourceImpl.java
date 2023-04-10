package com.innovations.innovations.dataSource.impl;

import com.innovations.innovations.dataSource.InnovationDataSource;
import com.innovations.innovations.innovations.GroupReplyDao;
import com.innovations.innovations.model.GroupReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

@Service
public class InnovationDataSourceImpl implements InnovationDataSource {

    @Autowired
    private GroupReplyDao groupReplyDao;

    @Override
    public Response fetch(String teamId, String versionNumber) {
        try {
            if (teamId != null) {
                if (versionNumber != null) {
                    List<GroupReply> groupReplyList = groupReplyDao.findByTeamIdAndVersion(teamId, versionNumber);
                    if (groupReplyList.isEmpty())
                        return Response.status(Response.Status.NOT_FOUND).entity(groupReplyList).build();
                    else {
                        return Response.status(Response.Status.OK).entity(groupReplyList.get(0)).build();
                    }
                } else {
                    List<GroupReply> groupReplyList = groupReplyDao.findAllByTeamIdOrderByVersion(teamId);
                    if (groupReplyList.isEmpty())
                        return Response.status(Response.Status.NOT_FOUND).entity(groupReplyList).build();
                    else {
                        return Response.status(Response.Status.OK).entity(groupReplyList.get(groupReplyList.size() - 1)).build();
                    }
                }
            } else {
                List<GroupReply> groupReplyFetched = groupReplyDao.findAllWithHighestVersion();
                if (groupReplyFetched.isEmpty())
                    return Response.status(Response.Status.NOT_FOUND).entity(groupReplyFetched).build();
                else {
                    return Response.status(Response.Status.OK).entity(groupReplyFetched).build();
                }
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @Transactional
    public Response save(GroupReply groupReply) {
        try {
            GroupReply groupReplyNew = groupReplyDao.save(groupReply);
            groupReply.getTeamResponseList().forEach(teamResponse -> teamResponse.setQuestionAnswerId(String.valueOf(groupReplyNew.getQuestionNumberId())));
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public Response update(GroupReply groupReply) {
        try {
                GroupReply groupReplyFetched = groupReplyDao.findByQuestionNumberId(groupReply.getQuestionNumberId());
                if (groupReplyFetched != null) {
                    groupReplyFetched.setTeamId(groupReply.getTeamId());
                    groupReplyFetched.setVersion(groupReply.getVersion());
                    groupReplyFetched.setTeamResponseList(groupReply.getTeamResponseList());
                    groupReplyFetched.getTeamResponseList().forEach(teamResponse -> teamResponse.setQuestionAnswerId(String.valueOf(groupReplyFetched.getQuestionNumberId())));
                    groupReplyDao.save(groupReplyFetched);
                } else {
                    groupReplyDao.save(groupReply);
                }
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
