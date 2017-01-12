package com.ifreeshare.persistence;

import java.util.Map;

import com.ifreeshare.persistence.elasticsearch.ElasticSearchSearch;

/**
 * data query
 * @author zhuss
 */
public interface IDataSearch<T> {
	
	static IDataSearch instance = new ElasticSearchSearch();
	
	public static IDataSearch instance(){
		return instance;
	}
	
	
	
	public T fullTextSearch(String index,String type, Map<String,String> search);
	
	public T getValueById(String index,String type, String id);
	
	
	
	
	
	
	
	enum Type{
		/**
		 * The user queries for equal data
		 */
		Equal,
		/**
		 * For full-text search
		 */
		Search,
		/**
		 * User query interval
		 */
		Between
		
	}

}
