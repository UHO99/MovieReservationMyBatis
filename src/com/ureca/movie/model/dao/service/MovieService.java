package com.ureca.movie.model.dao.service;

import java.util.List;

import com.ureca.dto.Movie;

public interface MovieService
{
	void add(Movie mov);

	void update(Movie mov);

	void remove(int movieId);

	Movie search(int movieId);

	List<Movie> searchAll();
}
