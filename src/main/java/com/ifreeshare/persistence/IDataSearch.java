package com.ifreeshare.persistence;

import java.util.Map;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;

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
	
	
	public T pages(String index, String type, int pageIndex, int pageSize, QueryBuilder queryBuilders);
	
	
	public TransportClient getSearchClient();
	
	
	
	
	
	enum Type{
		/**
		 * The user queries for equal data
		 */
		Term,
		/**
		 * For full-text search
		 */
		Match,
		/**
		 * User query interval
		 */
		Between
		
	}

}
