package com.ifreeshare.persistence.elasticsearch;

import io.vertx.core.json.JsonObject;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.ifreeshare.persistence.IDataSearch;

public class ElasticSearchSearch implements IDataSearch<JsonObject> {
	TransportClient searchClient;

	public ElasticSearchSearch() {
		try {
//			Settings settings = Settings.builder().put("cluster.name","elasticsearch").put("client.transport.sniff", true).build();
			searchClient = new PreBuiltTransportClient(Settings.EMPTY)
			.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		} catch (UnknownHostException e) {
			e.printStackTrace();
			
		}
	}
	@Override
	public JsonObject fullTextSearch(String index, String type, Map<String, String> search) {
		return null;
	}
	@Override
	public JsonObject getValueById(String index, String type, String id) {
		GetResponse response = searchClient.prepareGet(index, type, id).get();
	 	Map<String, Object> source =  response.getSource();
	 	if(source == null){
	 		return null;
	 	}else{
	 		return new JsonObject(source).put("id", id);
	 	}
	}
	
	
	public TransportClient getSearchClient() {
		return searchClient;
	}
	public void setSearchClient(TransportClient searchClient) {
		this.searchClient = searchClient;
	}
	
	
	
	@Override
	public JsonObject pages(String index, String type, int pageIndex, int pageSize, QueryBuilder queryBuilder) {
		SearchRequestBuilder srb = searchClient.prepareSearch(index).setTypes(type);
		int pageFrom =  pageIndex*pageSize;
		SearchResponse searchResponse = srb.setQuery(queryBuilder).setFrom(pageFrom).setSize(pageSize).get();
		SearchHits sh = searchResponse.getHits();
		long totalCount = sh.getTotalHits();
		
		return null;
	}
	
	
	

}
