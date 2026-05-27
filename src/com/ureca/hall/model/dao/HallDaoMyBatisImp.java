package com.ureca.hall.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ureca.dto.Hall;
import static com.ureca.util.MyBatisUtil.executeInSession;

public class HallDaoMyBatisImp implements HallDao
{
	@Override
	public void add(Hall hall) throws SQLException
	{
		executeInSession(session ->
		{
			session.getMapper(HallDao.class).add(hall);
			return null;
		});
	}

	@Override
	public void update(Hall hall) throws SQLException
	{
		executeInSession(session ->
		{
			session.getMapper(HallDao.class).update(hall);
			return null;
		});
	}

	@Override
	public void remove(int hallId, int seatId) throws SQLException
	{
		executeInSession(session ->
		{
			// movie 테이블의 hall_id 참조를 먼저 NULL로 처리 후 hall 행 삭제
			session.update("com.ureca.hall.model.dao.HallDao.clearMovieHallRef", hallId);
			session.getMapper(HallDao.class).remove(hallId, seatId);
			return null;
		});
	}

	@Override
	public Hall search(int hallId, int seatId) throws SQLException
	{
		return executeInSession(session -> session.getMapper(HallDao.class).search(hallId, seatId));
	}

	@Override
	public List<Hall> searchByHallId(int hallId) throws SQLException
	{
		return executeInSession(session -> session.getMapper(HallDao.class).searchByHallId(hallId));
	}

	@Override
	public List<Hall> searchByMovieAndHall(int movieId, int hallId) throws SQLException
	{
		return executeInSession(session -> session.getMapper(HallDao.class).searchByMovieAndHall(movieId, hallId));
	}

	@Override
	public List<Hall> searchAll() throws SQLException
	{
		return executeInSession(session -> session.getMapper(HallDao.class).searchAll());
	}
}
