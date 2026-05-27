package com.ureca.user.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ureca.dto.User;
import static com.ureca.util.MyBatisUtil.executeInSession;

public class UserDaoMyBatisImp implements UserDao
{
	@Override
	public void add(User usr) throws SQLException
	{
		executeInSession(session ->
		{
			session.getMapper(UserDao.class).add(usr);
			return null;
		});
	}

	@Override
	public void update(User usr) throws SQLException
	{
		executeInSession(session ->
		{
			session.getMapper(UserDao.class).update(usr);
			return null;
		});
	}

	@Override
	public void remove(int id) throws SQLException
	{
		executeInSession(session ->
		{
			session.getMapper(UserDao.class).remove(id);
			return null;
		});
	}

	@Override
	public void close() throws SQLException
	{
	}

	@Override
	public User search(int id) throws SQLException
	{
		return executeInSession(session -> session.getMapper(UserDao.class).search(id));
	}

	@Override
	public List<User> searchAll() throws SQLException
	{
		return executeInSession(session -> session.getMapper(UserDao.class).searchAll());
	}
}
