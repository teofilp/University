package catalog.core.repository;

import catalog.core.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends BaseRepository<Project, Long> {
    List<Project> findByMembersContains(String search);
    Optional<Project> findByName(String name);
}
