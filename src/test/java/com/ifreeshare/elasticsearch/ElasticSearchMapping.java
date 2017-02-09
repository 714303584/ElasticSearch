package com.ifreeshare.elasticsearch;

import io.vertx.core.json.JsonObject;

public class ElasticSearchMapping {
	
	public static final String keyword = "keyword";
	
	public static final String text = "text";
	
	public static final String Longs = "long";
	
//	public static final String 
	
	
	public static void main(String[] args) {
//		
//		long current = System.currentTimeMillis();
//		
//		User user = new User();
//		
//		user.setName("zhushunshan");
//		user.setPassword("zhushunshan1234567");
//		for (long i = 0; i < 1000000L; i++) {
//			user.setName("zhushunshan"+i);
//			String name = user.getName();
//		}
//		
//		System.err.println(System.currentTimeMillis()  - current);
//		
//		
//		
//		current = System.currentTimeMillis();
//		
//		JsonObject jsonUser = new JsonObject().put(name, "zhushunshan").put(password, "zhushunshan1234567");
//		
//		for (long i = 0; i < 1000000L; i++) {
//			jsonUser.put(name,"zhushunshan"+i);
//			String name2 = jsonUser.getString(name);
//		}
//		
//		System.err.println(System.currentTimeMillis()  - current);
		
//		getHTMLMapping();
//		getImageResouceMapping();
		getImageMapping();
		
	}
	
	
	
	public static void getImageResouceMapping(){
		
		JsonObject imageMapJson = new JsonObject();
		JsonObject _all = new JsonObject();
		_all.put("analyzer", "ik_max_word");
		_all.put("search_analyzer", "ik_max_word");
		JsonObject properties = new JsonObject();
		imageMapJson.put("_all", _all);
		imageMapJson.put("properties", properties);
		
		properties.put("keywords", getField("keywords", text, true).put("analyzer", "ik_max_word").put("search_analyzer", "ik_max_word"));
		properties.put("description", getField("description", text, true).put("analyzer", "ik_max_word").put("search_analyzer", "ik_max_word"));
		properties.put("title", getField("title", text, true).put("analyzer", "ik_max_word").put("search_analyzer", "ik_max_word"));
		properties.put("path", getField("path", keyword, true));
		properties.put("create_date", getField("create_date", Longs, true));
		System.out.println(imageMapJson.encodePrettily());
		
	}
	
	
	public static void getHTMLMapping(){
		
		JsonObject imageMapJson = new JsonObject();
		
		JsonObject _all = new JsonObject();
		_all.put("analyzer", "ik_max_word");
		_all.put("search_analyzer", "ik_max_word");
		
		JsonObject properties = new JsonObject();
		
		imageMapJson.put("_all", _all);
		
		imageMapJson.put("properties", properties);
		
		properties.put("charset", getField("charset", keyword, true));
		
		properties.put("Content-Type", getField("Content-Type", keyword, true));
		
		properties.put("create_date", getField("create_date", Longs, true));
		
		properties.put("keywords", getField("keywords", text, true).put("analyzer", "ik_max_word").put("search_analyzer", "ik_max_word"));
		
		System.out.println(imageMapJson.encodePrettily());
		
	}
	
	
	
	

public static void getImageMapping(){
	
	JsonObject imageMapJson = new JsonObject();
	
	JsonObject _all = new JsonObject();
	_all.put("analyzer", "ik_max_word");
	_all.put("search_analyzer", "ik_max_word");
	
	JsonObject properties = new JsonObject();
	
	imageMapJson.put("_all", _all);
	
	imageMapJson.put("properties", properties);
	
	properties.put("thumbnail", getField("thumbnail", keyword, true));
	properties.put("resolution", getField("resolution", keyword, true));
	properties.put("uuid", getField("uuid", keyword, true));
	
	
	properties.put("sha1", getField("sha1", keyword, true));
	properties.put("md5", getField("md5", keyword, true));
	properties.put("sha512", getField("sha512", keyword, true));
	properties.put("Content-Type", getField("Content-Type", keyword, true));
	
	properties.put("file_url_path", getField("file_url_path", keyword, true));
	properties.put("url", getField("url", keyword, true));
	properties.put("filename", getField("filename", keyword, true));
	properties.put("fileSize", getField("fileSize", Longs, true));
	properties.put("love_count", getField("love_count", Longs, true));
	properties.put("collect_count", getField("love_count", Longs, true));
	properties.put("create_date", getField("create_date", Longs, true));
	
	properties.put("keywords", getField("keywords", text, true).put("analyzer", "ik_max_word").put("search_analyzer", "ik_max_word"));
	
	System.out.println(imageMapJson.encodePrettily());
}



public static void imageLogMapping(){
	
	JsonObject imageMapJson = new JsonObject();
	
	JsonObject _all = new JsonObject();
	_all.put("analyzer", "ik_max_word");
	_all.put("search_analyzer", "ik_max_word");
	
	JsonObject properties = new JsonObject();
	
	imageMapJson.put("_all", _all);
	
	imageMapJson.put("properties", properties);
	
//	properties.put("thumbnail", getField("thumbnail", keyword, true));
//	properties.put("resolution", getField("resolution", keyword, true));
	
	properties.put("user_id", getField("user_id", keyword, true));
	properties.put("uuid", getField("uuid", keyword, true));
	properties.put("type", getField("type", "integer", true));
	properties.put("remote_host", getField("remote_host", keyword, true));
	System.out.println(imageMapJson.encodePrettily());
}



public static void imageCommentsMapping(){
	
	JsonObject imageMapJson = new JsonObject();
	
	JsonObject _all = new JsonObject();
	_all.put("analyzer", "ik_max_word");
	_all.put("search_analyzer", "ik_max_word");
	
	JsonObject properties = new JsonObject();
	
	imageMapJson.put("_all", _all);
	
	imageMapJson.put("properties", properties);
	
	properties.put("user_id", getField("user_id", keyword, true));
	properties.put("parent_id", getField("parent_id", keyword, true));
	properties.put("create_date", getField("create_date", keyword, true));
	properties.put("type", getField("type", "integer", true));
	properties.put("remote_host", getField("remote_host", keyword, true));
	properties.put("content", getField("content", text, true).put("analyzer", "ik_max_word").put("search_analyzer", "ik_max_word"));
	
	System.out.println(imageMapJson.encodePrettily());
}




public static JsonObject getField(String name, String type, boolean index){
	JsonObject fieldInfo = new JsonObject();
	fieldInfo.put("type", type);
	fieldInfo.put("index", index);
	return fieldInfo;
	
}


	

}





 class User{
	
	private String name;
	
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}