package com.tonearena.threadpools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tonearena.service.URLService;

@Component
public class URLThreadPool implements Runnable{
	
	public String url;

	@Autowired
	public URLService urlSvc;
	
	public URLThreadPool(){
		
	}
	
	public void setUrl(String url){
		this.url=url;
	}
	
	public void run(){
    	urlSvc.addUrl(url);		
	}
}
