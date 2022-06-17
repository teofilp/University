package catalog.web.controller;

import catalog.core.model.Project;
import catalog.core.repository.ProjectRepository;
import catalog.core.service.ProjectService;
import catalog.web.dto.AssignDeveloperRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    private ProjectService service;

    @RequestMapping(value = "/Projects", method = RequestMethod.GET)
    public List<Project> getAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/Projects/getByUsername/{username}", method = RequestMethod.GET)
    public List<Project> getByUsername(@PathVariable(value="username") String username) {
        return service.findByMember(username);
    }

    @RequestMapping(value = "/Projects/assign", method = RequestMethod.POST)
    public boolean AssignDeveloper(@RequestBody() AssignDeveloperRequest request) {
        service.assignMember(request.getUsername(), request.getProjects());
        return true;
    }
}
