package com.ureca.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil
{

	private static final SqlSessionFactory SQL_SESSION_FACTORY;

	static
	{
		try (InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml"))
		{
			if (inputStream == null)
			{
				throw new IllegalStateException("mybatis-config.xml을 classpath에서 찾을 수 없습니다.");
			}
			SQL_SESSION_FACTORY = new SqlSessionFactoryBuilder().build(inputStream);
		}
		catch (IOException e)
		{
			throw new ExceptionInInitializerError("MyBatis SqlSessionFactory 초기화 실패: " + e.getMessage());
		}
	}

	private MyBatisUtil()
	{
	}

	public static SqlSessionFactory getSqlSessionFactory()
	{
		return SQL_SESSION_FACTORY;
	}

	public static <T> T executeInSession(SqlSessionCallback<T> callback) throws SQLException
	{
		SqlSession session = null;
		try
		{
			session = MyBatisUtil.getSqlSessionFactory().openSession();
			T result = callback.apply(session);
			session.commit();
			return result;
		}
		catch (Exception e)
		{
			if (session != null)
			{
				session.rollback();
			}
			if (e instanceof SQLException)
			{
				throw (SQLException) e;
			}
			throw new SQLException(e);
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
	}

}
