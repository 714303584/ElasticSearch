package com.ifreeshare.elasticsearch;

import io.vertx.core.json.JsonObject;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	// on startup
    	
		try {
			TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
//    	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
			        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
			
			JsonObject jsonObject = new JsonObject();
			jsonObject.put("user", "zhushunshan");
			jsonObject.put("postDate", "2013-01-30");
			jsonObject.put("message", "hello world!");
//			IndexResponse response = client.prepareIndex("twitter", "tweet").setSource(jsonObject.toString()).get();
//			String _index = response.getIndex();
//			System.out.println(_index);
//			String _type = response.getType();
//			System.out.println(_type);
//			// Document ID (generated or not)
//			String _id = response.getId();
//			System.out.println(_id);
//			// Version (if it's the first time you index this document, you will get: 1)
//			long _version = response.getVersion();
//			System.out.println(_version);
//			// status has stored current instance statement.
//			RestStatus status = response.status();
//			
//			System.out.println(status);
			
			
			GetResponse response = client.prepareGet("twitter", "tweet", "AVkp7he0r9Rt059kaLJM1").get();
		 	String string =  response.getSourceAsString();
		 	
		 	System.out.println(response.isExists());
		 	
		 	System.out.println(string);
			
			client.close();
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

    	// on shutdown

    	
    }
}
