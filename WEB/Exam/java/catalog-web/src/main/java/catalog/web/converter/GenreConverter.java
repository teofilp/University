package catalog.web.converter;

import catalog.core.model.Genre;
import catalog.web.dto.GenreDto;
import org.springframework.stereotype.Component;

@Component
public class GenreConverter extends BaseConverter<Genre, GenreDto>{

    @Override
    public Genre convertDtoToModel(GenreDto dto) {
        var model = new Genre();
        model.setId(dto.getId());
        model.setName(dto.getName());
        return model;
    }

    @Override
    public GenreDto convertModelToDto(Genre genre) {
        GenreDto dto = new GenreDto(genre.getName());
        dto.setId(genre.getId());
        return dto;
    }
}
