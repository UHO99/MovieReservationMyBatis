package com.ureca.util;

import org.apache.ibatis.session.SqlSession;

public interface SqlSessionCallback<T>
{
	T apply(SqlSession session) throws Exception;
}
