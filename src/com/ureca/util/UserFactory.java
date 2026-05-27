package com.ureca.util;

import com.ureca.user.model.dao.UserDao;
import com.ureca.user.model.dao.UserDaoMyBatisImp;

public class UserFactory
{
	private static final UserDao dao = new UserDaoMyBatisImp();

	public static UserDao getUser()
	{
		return dao;
	}
}
