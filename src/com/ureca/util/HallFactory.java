package com.ureca.util;

import com.ureca.hall.model.dao.HallDao;
import com.ureca.hall.model.dao.HallDaoMyBatisImp;

public class HallFactory
{
	private static final HallDao dao = new HallDaoMyBatisImp();

	public static HallDao getHall()
	{
		return dao;
	}
}
