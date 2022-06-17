package catalog.core.service;

import catalog.core.model.Genre;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface GenreService {

    Optional<Genre> addGenre(Genre genre);

    List<Genre> getAllGenres();

    Optional<Genre> getById(Long id);

    Optional<Genre> deleteGenre(Long id);

    Optional<Genre> updateGenre(Genre genre);

    List<Genre> filterByName(String name);
}
