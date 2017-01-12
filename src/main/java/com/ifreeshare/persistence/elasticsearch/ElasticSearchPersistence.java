package com.ifreeshare.persistence.elasticsearch;

import io.vertx.core.json.JsonObject;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Set;

import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import co.paralleluniverse.fibers.Suspendable;

import com.ifreeshare.persistence.IDataPersistence;

/**
 * Save the data to ElasticSearch
 * Save only the data
 * @author zhuss
 */
public class ElasticSearchPersistence implements IDataPersistence<JsonObject> {
	
	TransportClient searchClient;
	
	public ElasticSearchPersistence() {
		try {
			searchClient = new PreBuiltTransportClient(Settings.EMPTY)
			.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	
	@Override
	public boolean insert(JsonObject t) {
		try {
			IndexResponse response = null;
			String index = t.getString(INDEX);
			String type = t.getString(TYPE);
			t.remove(INDEX);
			t.remove(TYPE);
			
			if(t.containsKey(UUID)){
				response = searchClient.prepareIndex(index,
						type,t.getString(UUID))
						.setSource(t.toString()).get();
			}else{
				response = searchClient.prepareIndex(index, 
						type)
						.setSource(t.toString()).get();
			}
			return  response.status() == RestStatus.CREATED;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(JsonObject t) {
		try {
			String index = t.getString(INDEX);
			String type = t.getString(TYPE);
			t.remove(INDEX);
			t.remove(TYPE);
			UpdateResponse updateResponse = searchClient.prepareUpdate(index, type, t.getString(UUID))
	        .setDoc(t.toString())
	        .get();
			Result result = updateResponse.getResult();
			return result == Result.UPDATED;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean remove(JsonObject t) {
		try {
			DeleteResponse response = searchClient.prepareDelete(t.getString(INDEX), 
					t.getString(TYPE),t.getString(UUID)).get();
			RestStatus rs = response.status();
			return rs  == RestStatus.NOT_FOUND || rs == RestStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Set<Object> insert(Collection<JsonObject> ts) {
		return null;
	}

	@Override
	public Set<Object> update(Collection<JsonObject> ts) {
		
		return null;
	}

	@Override
	public Set<Object> remove(Collection<JsonObject> ids) {
		return null;
	}

	public TransportClient getSearchClient() {
		return searchClient;
	}


	public void setSearchClient(TransportClient searchClient) {
		this.searchClient = searchClient;
	}

	
	
	
}
