package com.tonearena.threadpools;

import com.tonearena.beans.MyURL;
import com.tonearena.service.URLService;

public class URLThreadPool implements Runnable{
	
	public String url;
	public URLService urlSvc;
	
	public URLThreadPool(String url, URLService urlSvc){
		this.url=url;
		this.urlSvc=urlSvc;
	}
	
	public void run(){
    	MyURL myURL=urlSvc.fetchURLContent(url);
    	urlSvc.addURL(myURL);		
	}

}
