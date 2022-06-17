package catalog.core.repository;

import catalog.core.model.SoftwareDeveloper;

import java.util.List;
import java.util.Optional;

public interface SoftwareDeveloperRepository extends BaseRepository<SoftwareDeveloper, Long> {
    Optional<SoftwareDeveloper> findByName(String name);
}
