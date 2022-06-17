package catalog.web.controller;

import catalog.core.model.Project;
import catalog.core.model.SoftwareDeveloper;
import catalog.core.repository.ProjectRepository;
import catalog.core.service.DeveloperService;
import catalog.core.service.ProjectService;
import catalog.web.dto.AssignDeveloperRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeveloperController {
    @Autowired
    private DeveloperService service;

    @RequestMapping(value = "/Developers/register", method = RequestMethod.POST)
    public boolean getAll(@RequestParam("username") String username) {
        return service.exists(username);
    }

    @RequestMapping(value = "/Developers", method = RequestMethod.GET)
    public List<SoftwareDeveloper> findAll() {
        return service.findAll();
    }
}
