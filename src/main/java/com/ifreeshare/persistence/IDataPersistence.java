package com.ifreeshare.persistence;

import java.util.Collection;
import java.util.Set;

/**
 * Data persistence interface
 * Used for data creation, deletion, update
 * @author zhuss
 */
public interface IDataPersistence<T> {
	
	public static final String INDEX = "index";
	public static final String TYPE = "type";
	public static final String UUID = "uuid";
	
	/**
	 * Add a piece of data
	 * @param t --- The data that needs to be added
	 * @return --- The operation was successful. true is success, false is failed.
	 */
	public boolean insert(T t);
	
	
	/**
	 * update data
	 * @param t --- Need to update the data
	 * @return  --- Whether the update was successful, true is succes, false is failed.
	 */
	public boolean update(T t);
	
	
	/**
	 * Delete  data
	 * @param id --- The unique identifier for the data that needs to be deleted
	 * @return  --- The deletion is successful, true is success, false is failed.
	 */
	public boolean remove(T id);
	
	/**
	 * A collection of data that needs to be created in bulk
	 * @param ts ---  A collection of data 
	 * @return  --- Returns the dataset
	 */
	public Set<Object> insert(Collection<T> ts);
	
	
	/**
	 * A collection of data that needs to be updated in bulk
	 * @param ts ---  A collection of data 
	 * @return  --- Returns the dataset
	 */
	public Set<Object> update(Collection<T> ts);
	
	/**
	 * A collection of data that needs to be deleted in bulk
	 * @param ts ---  A collection of data 
	 * @return  --- Returns the dataset
	 */
	public Set<Object> remove(Collection<T> ids);
	

}
