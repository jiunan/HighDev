package com.kt.smcp.fw.comm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.event.RowHandler;


public class CommonDaoImpl extends SqlMapClientDaoSupport implements CommonDao  {

	@SuppressWarnings("unchecked")
	public <E> E queryForObject(String statementName) throws DataAccessException {
		return (E) getSqlMapClientTemplate().queryForObject(statementName);
	}

	@SuppressWarnings("unchecked")
	public <E> E queryForObject(String statementName, Object parameterObject) throws DataAccessException {
		return (E) getSqlMapClientTemplate().queryForObject(statementName, parameterObject);
	}

	@SuppressWarnings("unchecked")
	public <E> E queryForObject(String statementName, Object parameterObject, Object resultObject) throws DataAccessException {
		return (E) getSqlMapClientTemplate().queryForObject(statementName, parameterObject, resultObject);
	}

	public void queryWithRowHandler(String statementName, RowHandler rowHandler) throws DataAccessException {
		getSqlMapClientTemplate().queryWithRowHandler(statementName, rowHandler);
	}

	public void queryWithRowHandler(String statementName, Object parameterObject, RowHandler rowHandler) throws DataAccessException {
		getSqlMapClientTemplate().queryWithRowHandler(statementName, parameterObject, rowHandler);
	}

	@SuppressWarnings("unchecked")
	public Map<String, ?> queryForMap(String statementName, Object parameterObject, String keyProperty) throws DataAccessException {
		return getSqlMapClientTemplate().queryForMap(statementName, parameterObject, keyProperty);
	}

	@SuppressWarnings("unchecked")
	public Map<String, ?> queryForMap(String statementName, Object parameterObject, String keyProperty, String valueProperty) throws DataAccessException {
		return getSqlMapClientTemplate().queryForMap(statementName, parameterObject, keyProperty, valueProperty);
	}

	public int insert(String statementName) throws DataAccessException {
		return getSqlMapClientTemplate().update(statementName);
	}

	public int insert(String statementName, Object parameterObject)
	throws DataAccessException {
		return getSqlMapClientTemplate().update(statementName, parameterObject);
	}

	public int update(String statementName) throws DataAccessException {
		return getSqlMapClientTemplate().update(statementName);
	}

	public int update(String statementName, Object parameterObject) throws DataAccessException {
		return getSqlMapClientTemplate().update(statementName, parameterObject);
	}

	public void update(String statementName, Object parameterObject, int requiredRowsAffected) throws DataAccessException {
		getSqlMapClientTemplate().update(statementName, parameterObject, requiredRowsAffected);
	}

	public int delete(String statementName) throws DataAccessException {
		return getSqlMapClientTemplate().delete(statementName);
	}

	public int delete(String statementName, Object parameterObject)	throws DataAccessException {
		return getSqlMapClientTemplate().delete(statementName, parameterObject);
	}

	public void delete(String statementName, Object parameterObject, int requiredRowsAffected) throws DataAccessException {
		getSqlMapClientTemplate().delete(statementName, parameterObject, requiredRowsAffected);
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> queryForList(String statementName, Object parameterObject)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList(statementName, parameterObject);
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> queryForList(String statementName) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList(statementName);
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> queryForList(String statementName, Object parameterObject,
			int skip, int max) throws DataAccessException {
		return getSqlMapClientTemplate().queryForList(statementName, parameterObject, skip, max);
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> queryForList(String statementName, int skip, int max)
			throws DataAccessException {
		return getSqlMapClientTemplate().queryForList(statementName, skip, max);
	}


}
