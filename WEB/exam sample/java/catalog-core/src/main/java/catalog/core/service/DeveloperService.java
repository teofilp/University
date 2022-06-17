package catalog.core.service;

import catalog.core.model.SoftwareDeveloper;
import catalog.core.repository.SoftwareDeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperService {
    @Autowired
    private SoftwareDeveloperRepository repository;

    public boolean exists(String name) {
        return repository.findByName(name).isPresent();
    }

    public List<SoftwareDeveloper> findAll() {
        return repository.findAll();
    }
}
