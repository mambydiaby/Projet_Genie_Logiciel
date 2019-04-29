package com.flight_sharing.dao;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;

public interface Dao {

	/**
	 * 
	 * @return return all the contents of the table
	 * @throws Exception
	 */
	public List<String> getAll() throws Exception;

	/**
	 * select * from ? where id=id
	 * 
	 * @param id the Id of the object we're looking for
	 * @return return the object corresponding to the id
	 * @throws Exception
	 */
	public String getById(String id) throws Exception;

	/**
	 * delete from ? where id=id
	 * 
	 * @param id object's id
	 * @return success or error message
	 * @throws Exception
	 */
	public String delete(String id) throws Exception;

	/**
	 * Add an object in the database
	 * 
	 * @param json
	 * @param id object's id
	 * @return success or error message
	 * @throws Exception
	 */
	public String add(byte[] json, String id) throws Exception;

	/**
	 * update set ?=? where id=id
	 * 
	 * @param id        object's id
	 * @param attribute
	 * @param valeur
	 * @throws Exception
	 */
	public void update(String id, String attribute, String value) throws Exception;

	/**
	 * select * from
	 * 
	 * @param queryBuilder
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<String> search(QueryBuilder queryBuilder) throws Exception;
}
