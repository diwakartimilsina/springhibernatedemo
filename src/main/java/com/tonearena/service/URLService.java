package com.tonearena.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.enterprise.context.spi.Context;

import com.tonearena.model.MyURL;
import com.tonearena.repositories.UrlRepo;
import com.tonearena.threadpools.URLThreadPool;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


@Service
public class URLService {

	@Autowired
	UrlRepo urlRepo;
	
	@Autowired
	URLThreadPool urlThreadPool;

	@Autowired
    private WebApplicationContext context;
	
	Logger log = Logger.getLogger(URLService.class);

	public void save(MyURL url) {
		urlRepo.saveAndFlush(url);
	}

	public void update(MyURL url) {
		urlRepo.saveAndFlush(url);
	}

	public MyURL find(Long id) {
		return urlRepo.findOne(id);
	}

	public void delete(MyURL url) {
		urlRepo.delete(url);
	}
	
	public MyURL fetchURLContent(String url){
		MyURL myURL = new MyURL();
        try {
            URL urlToFetch = new URL(url);
            // read text returned by server
            BufferedReader in = new BufferedReader(new InputStreamReader(urlToFetch.openStream()));
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = in.readLine()) != null) {
                content.append(line);
            }
            in.close();
            myURL.setURLStr(url);
            myURL.setURLContent(content.toString());
        }
        catch (MalformedURLException e) {
            log.error("Malformed URL: " ,e);
        }
        catch (IOException e) {
            log.error("I/O Error: ",e);
        }
        return myURL;
	}
	
	public void addUrl(String url){
		long before = System.currentTimeMillis();
    	MyURL myURL=fetchURLContent(url);
    	save(myURL);	
    	long after = System.currentTimeMillis();
    	log.info("Add Url took: "+(after-before)+" ms");
	}

	public void addUrlBatchSimple(ArrayList<String> urls){
		Iterator<String> it = urls.iterator();
		long before=System.currentTimeMillis();
		while (it.hasNext()) {
	    	addUrl(it.next());
	    }
	    long after = System.currentTimeMillis();
	    log.info("addbatchsimple took "+(after-before)+" ms");
	}
	
	public void addUrlBatchThreaded(ArrayList<String> urls){
		List<String> urlList = Collections.synchronizedList(urls);
		
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        
        urlThreadPool = (URLThreadPool) context.getBean(URLThreadPool.class);

        Iterator<String> it = urlList.iterator();
        while (it.hasNext()) {
        	urlThreadPool.setUrl(it.next());
        	log.info("Currently working on thread with URL: "+urlThreadPool.url);
	    	executorService.execute(urlThreadPool);
        }

        executorService.shutdown();
	}
	
}
