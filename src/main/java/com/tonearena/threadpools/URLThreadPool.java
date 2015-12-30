package com.tonearena.threadpools;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tonearena.service.URLService;

@Component
@Scope(value="prototype")
public class URLThreadPool implements Runnable{
	
	public String url;

	@Autowired
	public URLService urlSvc;
	
	Logger log = Logger.getLogger(URLThreadPool.class);
	
	public URLThreadPool(){

	}
	
	public void setUrl(String url){
		this.url=url;
	}
	
	public void run(){
		log.info("Running thread for URL: " +url);
    	urlSvc.addUrl(url);		
	}
}
