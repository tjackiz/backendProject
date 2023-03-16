package de.tjackiz.entertainmentService.service.impl;

import de.tjackiz.entertainmentService.model.AbstractMovie;
import de.tjackiz.entertainmentService.repository.MovieRepository;
import de.tjackiz.entertainmentService.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public AbstractMovie getMovieById(UUID id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public UUID createMovie(AbstractMovie movie) {
        movie = movieRepository.save(movie);
        return movie.getId();
    }

    @Override
    public AbstractMovie updateMovie(UUID id, AbstractMovie movie) {
        movie.setId(id);
        return movieRepository.save(movie);
    }

    @Override
    public Set<AbstractMovie> getMovieSet() {
        return StreamSupport.stream(movieRepository.findAll().spliterator(), false).collect(Collectors.toSet());
    }

    @Override
    public void deleteMovie(UUID id) {
        movieRepository.deleteById(id);
    }
}
