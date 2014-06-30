package com.tonearena.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import com.tonearena.beans.MyURL;
import com.tonearena.dao.impl.UrlDAOImpl;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Service
public class URLService {

	@Autowired
	UrlDAOImpl urlDAOImpl;
	
	@Transactional
	public void addURL(MyURL url){
		urlDAOImpl.save(url);
	}
	@Transactional
	public void updateURL(MyURL url){
		urlDAOImpl.update(url);
	}
	@Transactional
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
}
