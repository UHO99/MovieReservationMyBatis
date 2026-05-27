package com.ureca.util;

import com.ureca.reservation.model.dao.ReservationDao;
import com.ureca.reservation.model.dao.ReservationDaoMyBatisImp;

public class ReservationFactory
{
	private static final ReservationDao dao = new ReservationDaoMyBatisImp();

	public static ReservationDao getReservation()
	{
		return dao;
	}
}
