package com.innovations.innovations.resource;

import com.innovations.innovations.dataSource.InnovationDataSource;
//import com.innovations.innovations.model.Criteria;
import com.innovations.innovations.model.GroupReply;
import com.innovations.innovations.model.TeamResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
public class InnovationResource {

    @Autowired
    private InnovationDataSource innovationDataSource;

    @GetMapping("/fetch")
    public Response fetch(@RequestParam(required = false) String teamId,
                          @RequestParam(required = false) String versionNumber) {
        return this.innovationDataSource.fetch(teamId, versionNumber);
    }

    @PostMapping("/save")
    public Response save(@RequestBody GroupReply groupReply) {
        return this.innovationDataSource.save(groupReply);
    }

    @PutMapping("/update")
    public Response update(@RequestBody GroupReply groupReply) {
        return this.innovationDataSource.update(groupReply);
    }

}
