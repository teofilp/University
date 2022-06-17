package catalog.web.controller;

import catalog.core.service.GenreService;
import catalog.web.converter.GenreConverter;
import catalog.web.dto.GenreDto;
import catalog.web.dto.GenresDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GenreController {
    private static final Logger logger = LoggerFactory.getLogger(GenreController.class);

    @Autowired
    private GenreService genreService;

    @Autowired
    private GenreConverter genreConverter;

    @RequestMapping(value = "/genres")
    GenresDto getAllGenres() {
        var genres = genreService.getAllGenres();
        System.out.println("GENRES: " + genres);
        GenresDto genresDto = new GenresDto(genreConverter.convertModelsToDtos(genres));
        logger.trace("GET genres: result -> {}", genresDto);

        return genresDto;
    }

    @RequestMapping(value = "/genres", method = RequestMethod.POST)
    GenreDto addGenre(@RequestBody GenreDto genreDto) {
        var genre = genreConverter.convertDtoToModel(genreDto);
        var result = genreService.addGenre(genre);
        var resultModel = genreConverter.convertModelToDto(genre);
        logger.trace("POST genres: result -> {}", resultModel);

        return resultModel;
    }

    @RequestMapping(value = "/genres/{id}", method = RequestMethod.PUT)
    GenreDto updateGenre(@PathVariable Long id, @RequestBody GenreDto genreDto) {
        logger.trace("updateGenre - method entered");
        var res = genreConverter.convertModelToDto(
          genreService.updateGenre(genreConverter.convertDtoToModel(genreDto)).get()
        );
        logger.trace("PUT genres: result -> {}", res);

        return res;
    }


    @RequestMapping(value = "/genres/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteGenre(@PathVariable Long id) {
        logger.trace("DELETE genres");
        genreService.deleteGenre(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/genres/{id}")
    GenreDto getGenreById(@PathVariable Long id) throws Exception {
        var genreToGet = genreService.getById(id);
        if(genreToGet.isPresent()){
            logger.trace("GET genres/id: result -> {}", genreToGet.get());
            return genreConverter.convertModelToDto(genreToGet.get());
        }
        else {
            logger.error("getGenreById : result -> ERROR NO GENRE");
            throw new Exception("No genre");
        }
    }

    @RequestMapping(value = "/genres/filter/{name}")
    GenresDto filterByName(@PathVariable String name) throws Exception {
        return new GenresDto(genreConverter.convertModelsToDtos(genreService.filterByName(name)));
    }
}
