package catalog.core.repository;

import catalog.core.model.Genre;

import java.util.List;

public interface GenreRepository extends BaseRepository<Genre, Long> {
    public List<Genre> findGenreByNameContains(String name);
}
