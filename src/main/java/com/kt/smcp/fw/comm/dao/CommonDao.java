package com.kt.smcp.fw.comm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ibatis.sqlmap.client.event.RowHandler;

public interface CommonDao {
	
	
	/**
	 * @see com.ibatis.sqlmap.client.SqlMapExecutor#queryForObject(String)
	 * @throws org.springframework.dao.DataAccessException in case of errors
	 */
	<E> E queryForObject(String statementName) throws DataAccessException;

	/**
	 * @see com.ibatis.sqlmap.client.SqlMapExecutor#queryForObject(String, Object)
	 * @throws org.springframework.dao.DataAccessException in case of errors
	 */
	<E> E queryForObject(String statementName, Object parameterObject)
			throws DataAccessException;

	/**
	 * @see com.ibatis.sqlmap.client.SqlMapExecutor#queryForObject(String, Object, Object)
	 * @throws org.springframework.dao.DataAccessException in case of errors
	 */
	<E> E queryForObject(String statementName, Object parameterObject,	Object resultObject)
			throws DataAccessException;
	
	/**
	 * Executes a mapped SQL SELECT statement that returns data to populate a
	 * number of result objects.
	 * <p/>
	 * The parameter object is generally used to supply the input data for the
	 * WHERE clause parameter(s) of the SELECT statement.
	 * 
	 * @param statementName
	 *            The name of the statement to execute.
	 * @param parameterObject
	 *            The parameter object (e.g. JavaBean, Map, XML etc.).
	 * @return A List of result objects.
	 * @throws org.springframework.dao.DataAccessException
	 *             If an error occurs.
	 */
	<E> List<E> queryForList(String statementName, Object parameterObject) throws DataAccessException;

	/**
	 * Executes a mapped SQL SELECT statement that returns data to populate a
	 * number of result objects.
	 * <p/>
	 * This overload assumes no parameter is needed.
	 * 
	 * @param statementName
	 *            The name of the statement to execute.
	 * @return A List of result objects.
	 * @throws org.springframework.dao.DataAccessException
	 *             If an error occurs.
	 */
	<E> List<E> queryForList(String statementName) throws DataAccessException;

	/**
	 * Executes a mapped SQL SELECT statement that returns data to populate a
	 * number of result objects within a certain range.
	 * <p/>
	 * The parameter object is generally used to supply the input data for the
	 * WHERE clause parameter(s) of the SELECT statement.
	 * 
	 * @param statementName
	 *            The name of the statement to execute.
	 * @param parameterObject
	 *            The parameter object (e.g. JavaBean, Map, XML etc.).
	 * @param skip
	 *            The number of results to ignore.
	 * @param max
	 *            The maximum number of results to return.
	 * @return A List of result objects.
	 * @throws org.springframework.dao.DataAccessException
	 *             If an error occurs.
	 */
	<E> List<E> queryForList(String statementName, Object parameterObject, int skip, int max)
			throws DataAccessException;

	/**
	 * Executes a mapped SQL SELECT statement that returns data to populate a
	 * number of result objects within a certain range.
	 * <p/>
	 * This overload assumes no parameter is needed.
	 * 
	 * @param statementName
	 *            The name of the statement to execute.
	 * @param skip
	 *            The number of results to ignore.
	 * @param max
	 *            The maximum number of results to return.
	 * @return A List of result objects.
	 * @throws org.springframework.dao.DataAccessException
	 *             If an error occurs.
	 */
	<E> List<E> queryForList(String statementName, int skip, int max) throws DataAccessException;
	  
	/**
	 * @see com.ibatis.sqlmap.client.SqlMapExecutor#queryWithRowHandler(String, RowHandler)
	 * @throws org.springframework.dao.DataAccessException in case of errors
	 */
	void queryWithRowHandler(String statementName, RowHandler rowHandler)
			throws DataAccessException;

	/**
	 * @see com.ibatis.sqlmap.client.SqlMapExecutor#queryWithRowHandler(String, Object, RowHandler)
	 * @throws org.springframework.dao.DataAccessException in case of errors
	 */
	void queryWithRowHandler(String statementName, Object parameterObject, RowHandler rowHandler)
			throws DataAccessException;

	/**
	 * @see com.ibatis.sqlmap.client.SqlMapExecutor#queryForMap(String, Object, String)
	 * @throws org.springframework.dao.DataAccessException in case of errors
	 */
	Map<String, ?> queryForMap(String statementName, Object parameterObject, String keyProperty)
			throws DataAccessException;

	/**
	 * @see com.ibatis.sqlmap.client.SqlMapExecutor#queryForMap(String, Object, String, String)
	 * @throws org.springframework.dao.DataAccessException in case of errors
	 */
	Map<String, ?> queryForMap(String statementName, Object parameterObject, String keyProperty, String valueProperty)
			throws DataAccessException;

	/**
	 * @see com.ibatis.sqlmap.client.SqlMapExecutor#insert(String)
	 * @throws org.springframework.dao.DataAccessException in case of errors
	 */
	//Object insert(String statementName) throws DataAccessException;

	/**
	 * @see com.ibatis.sqlmap.client.SqlMapExecutor#insert(String)
	 * @throws org.springframework.dao.DataAccessException in case of errors
	 */
	int insert(String statementName) throws DataAccessException;
	
	/**
	 * @see com.ibatis.sqlmap.client.SqlMapExecutor#insert(String, Object)
	 * @throws org.springframework.dao.DataAccessException in case of errors
	 */
	//Object insert(String statementName, Object parameterObject) throws DataAccessException;

	/**
	 * @see com.ibatis.sqlmap.client.SqlMapExecutor#insert(String, Object)
	 * @throws org.springframework.dao.DataAccessException in case of errors
	 */
	int insert(String statementName, Object parameterObject) throws DataAccessException;
	
	/**
	 * @see com.ibatis.sqlmap.client.SqlMapExecutor#update(String)
	 * @throws org.springframework.dao.DataAccessException in case of errors
	 */
	int update(String statementName) throws DataAccessException;

	/**
	 * @see com.ibatis.sqlmap.client.SqlMapExecutor#update(String, Object)
	 * @throws org.springframework.dao.DataAccessException in case of errors
	 */
	int update(String statementName, Object parameterObject) throws DataAccessException;

	/**
	 * Convenience method provided by Spring: execute an update operation
	 * with an automatic check that the update affected the given required
	 * number of rows.
	 * @param statementName the name of the mapped statement
	 * @param parameterObject the parameter object
	 * @param requiredRowsAffected the number of rows that the update is
	 * required to affect
	 * @throws org.springframework.dao.DataAccessException in case of errors
	 */
	void update(String statementName, Object parameterObject, int requiredRowsAffected)
			throws DataAccessException;

	/**
	 * @see com.ibatis.sqlmap.client.SqlMapExecutor#delete(String)
	 * @throws org.springframework.dao.DataAccessException in case of errors
	 */
	int delete(String statementName) throws DataAccessException;

	/**
	 * @see com.ibatis.sqlmap.client.SqlMapExecutor#delete(String, Object)
	 * @throws org.springframework.dao.DataAccessException in case of errors
	 */
	int delete(String statementName, Object parameterObject) throws DataAccessException;

	/**
	 * Convenience method provided by Spring: execute a delete operation
	 * with an automatic check that the delete affected the given required
	 * number of rows.
	 * @param statementName the name of the mapped statement
	 * @param parameterObject the parameter object
	 * @param requiredRowsAffected the number of rows that the delete is
	 * required to affect
	 * @throws org.springframework.dao.DataAccessException in case of errors
	 */
	void delete(String statementName, Object parameterObject, int requiredRowsAffected)
			throws DataAccessException;
}
