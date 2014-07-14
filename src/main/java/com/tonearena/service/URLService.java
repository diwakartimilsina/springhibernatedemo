package com.tonearena.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.tonearena.beans.MyURL;
import com.tonearena.dao.impl.UrlDAOImpl;
import com.tonearena.threadpools.URLThreadPool;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


@Service
public class URLService {

	@Autowired
	UrlDAOImpl urlDAOImpl;
	
	@Autowired
	URLThreadPool urlThreadPool;

	Logger log = Logger.getLogger(URLService.class);
	
	public void addUrlToDb(MyURL url){
		urlDAOImpl.save(url);
	}
	
	public void updateURL(MyURL url){
		urlDAOImpl.update(url);
	}

	public MyURL populateURL(Long id){
		return urlDAOImpl.populate(id);
	}
	
	public void deleteURL(MyURL url){
		urlDAOImpl.delete(url);
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
            System.out.println("Malformed URL: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
        return myURL;
	}
	
	public void addUrl(String url){
		long before = System.currentTimeMillis();
    	MyURL myURL=fetchURLContent(url);
    	addUrlToDb(myURL);	
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

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Iterator<String> it = urls.iterator();
        while (it.hasNext()) {
        	urlThreadPool.setUrl(it.next());
	    	executorService.execute(urlThreadPool);
        }

        executorService.shutdown();
	}
	
}
