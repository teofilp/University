package catalog.core.service;

import catalog.core.model.Project;
import catalog.core.model.SoftwareDeveloper;
import catalog.core.repository.ProjectRepository;
import catalog.core.repository.SoftwareDeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.event.AdjustmentEvent;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository repository;
    @Autowired
    SoftwareDeveloperRepository devsRepository;

    public List<Project> findAll() {
        return repository.findAll();
    }

    public List<Project> findByMember(String member) {
        return repository.findByMembersContains(member);
    }

    public void assignMember(String developerName, List<String> projects) {
        var developer = devsRepository.findByName(developerName);

        if (developer.isEmpty()) {
            return;
        }

        projects.forEach(p -> {
            var project = repository.findByName(p);

            if (project.isEmpty()) {
                repository.save(new Project(p, "", developerName, developer.get()));
            } else {
                var instance = project.get();
                instance.setMembers(instance.getMembers() + ", " + developerName);
                repository.save(instance);
            }
        });
    }
}
