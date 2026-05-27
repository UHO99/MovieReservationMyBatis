package com.ureca.reservation.model.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ureca.dto.Reservation;
import static com.ureca.util.MyBatisUtil.executeInSession;

public class ReservationDaoMyBatisImp implements ReservationDao
{
	private static final String NS = "com.ureca.reservation.model.dao.ReservationDao.";

	@Override
	public void add(Reservation res)
	{
		try
		{
			executeInSession(session ->
			{
				session.getMapper(ReservationDao.class).add(res);
				return null;
			});
		}
		catch (SQLException e)
		{
			throw new RuntimeException("예약 등록 중 오류 발생", e);
		}
	}

	@Override
	public void update(Reservation res)
	{
		try
		{
			executeInSession(session ->
			{
				session.getMapper(ReservationDao.class).update(res);
				return null;
			});
		}
		catch (SQLException e)
		{
			throw new RuntimeException("예약 수정 중 오류 발생", e);
		}
	}

	@Override
	public void remove(int reservationId)
	{
		try
		{
			executeInSession(session ->
			{
				session.getMapper(ReservationDao.class).remove(reservationId);
				return null;
			});
		}
		catch (SQLException e)
		{
			throw new RuntimeException("예약 삭제 중 오류 발생", e);
		}
	}

	@Override
	public Reservation search(int reservationId)
	{
		try
		{
			return executeInSession(session ->
				session.getMapper(ReservationDao.class).search(reservationId));
		}
		catch (SQLException e)
		{
			throw new RuntimeException("예약 조회 중 오류 발생", e);
		}
	}

	@Override
	public List<Reservation> searchAll()
	{
		try
		{
			return executeInSession(session ->
				session.getMapper(ReservationDao.class).searchAll());
		}
		catch (SQLException e)
		{
			throw new RuntimeException("예약 전체 조회 중 오류 발생", e);
		}
	}

	@Override
	public List<Reservation> searchByMovie(int movieId)
	{
		try
		{
			return executeInSession(session ->
				session.getMapper(ReservationDao.class).searchByMovie(movieId));
		}
		catch (SQLException e)
		{
			throw new RuntimeException("영화별 예약 조회 중 오류 발생", e);
		}
	}

	@Override
	public List<Reservation> searchByUserId(int userId)
	{
		try
		{
			return executeInSession(session ->
				session.getMapper(ReservationDao.class).searchByUserId(userId));
		}
		catch (SQLException e)
		{
			throw new RuntimeException("회원별 예약 조회 중 오류 발생", e);
		}
	}

	@Override
	public boolean isSeatTaken(int movieId, int seatId)
	{
		try
		{
			// 2-파라미터 버전: XML id "isSeatTaken" (param1=movieId, param2=seatId)
			return executeInSession(session ->
			{
				Map<String, Object> params = new HashMap<>();
				params.put("param1", movieId);
				params.put("param2", seatId);
				Boolean result = session.selectOne(NS + "isSeatTaken", params);
				return result != null && result;
			});
		}
		catch (SQLException e)
		{
			throw new RuntimeException("좌석 조회 중 오류 발생", e);
		}
	}

	@Override
	public boolean isSeatTaken(int movieId, int hallId, Timestamp startDatetime, int seatId)
	{
		try
		{
			// 4-파라미터 버전: 인터페이스 오버로딩으로 mapper binding 불가 → XML id "isSeatTakenBySchedule" 직접 호출
			return executeInSession(session ->
			{
				Map<String, Object> params = new HashMap<>();
				params.put("param1", movieId);
				params.put("param2", hallId);
				params.put("param3", startDatetime);
				params.put("param4", seatId);
				Boolean result = session.selectOne(NS + "isSeatTakenBySchedule", params);
				return result != null && result;
			});
		}
		catch (SQLException e)
		{
			throw new RuntimeException("좌석 조회 중 오류 발생", e);
		}
	}

	@Override
	public int nextReservationId()
	{
		try
		{
			return executeInSession(session ->
				session.getMapper(ReservationDao.class).nextReservationId());
		}
		catch (SQLException e)
		{
			throw new RuntimeException("예약번호 생성 중 오류 발생", e);
		}
	}
}
