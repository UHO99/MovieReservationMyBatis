package com.ureca.movie.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ureca.dto.Movie;
import static com.ureca.util.MyBatisUtil.executeInSession;

public class MovieDaoMyBatisImp implements MovieDao
{
	@Override
	public void add(Movie mov) throws SQLException
	{
		if (mov.getEnd_date() == null)   mov.setEnd_date(mov.getAir_date());
		if (mov.getMovie_time() <= 0)    mov.setMovie_time(120);
		executeInSession(session ->
		{
			session.getMapper(MovieDao.class).add(mov);
			return null;
		});
	}

	@Override
	public void update(Movie mov) throws SQLException
	{
		if (mov.getEnd_date() == null)   mov.setEnd_date(mov.getAir_date());
		if (mov.getMovie_time() <= 0)    mov.setMovie_time(120);
		executeInSession(session ->
		{
			session.getMapper(MovieDao.class).update(mov);
			return null;
		});
	}

	@Override
	public void remove(int id) throws SQLException
	{
		executeInSession(session ->
		{
			// hall 테이블의 movie_id 참조를 먼저 NULL로 처리 후 movie 행 삭제
			session.update("com.ureca.movie.model.dao.MovieDao.clearHallMovieRef", id);
			session.getMapper(MovieDao.class).remove(id);
			return null;
		});
	}

	@Override
	public void close() throws SQLException
	{
	}

	@Override
	public Movie search(int id) throws SQLException
	{
		return executeInSession(session -> session.getMapper(MovieDao.class).search(id));
	}

	@Override
	public List<Movie> searchAll() throws SQLException
	{
		return executeInSession(session -> session.getMapper(MovieDao.class).searchAll());
	}
}
