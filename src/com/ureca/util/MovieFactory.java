package com.ureca.util;

import com.ureca.movie.model.dao.MovieDao;
import com.ureca.movie.model.dao.MovieDaoMyBatisImp;

public class MovieFactory
{
	private static final MovieDao dao = new MovieDaoMyBatisImp();

	public static MovieDao getMovie()
	{
		return dao;
	}
}
