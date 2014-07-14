package com.tonearena.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tonearena.beans.MyURL;
import com.tonearena.service.URLService;
	 
	@Controller
    @RequestMapping("/url")

	public class UrlController {
	 
		Logger log = Logger.getLogger(UrlController.class);
		
		@Autowired
		URLService urlSvc;
		
		MyURL url;

	    @RequestMapping(value="/fetch/{url}", method=RequestMethod.GET)
	    public String fetchURL(@PathVariable String url, ModelMap model) {
	    	MyURL myURL=urlSvc.fetchURLContent(url);
	    	model.addAttribute("model", myURL);
	        return "fetchURL";
	    }
		
	    @RequestMapping(value="/add", method=RequestMethod.POST)
	    public String addURL(@RequestParam String url, ModelMap model) {
	    	urlSvc.addUrl(url);
	    	model.addAttribute("model", url);
	        return "addURL";
	    }

	    @RequestMapping(value="/addbatchsimple", method=RequestMethod.POST)
	    public String addURLBatch(@RequestParam("urlfile") MultipartFile multipartFile, Model model) throws Exception {
	    	
	    	ArrayList<String> urls = new ArrayList<String>();
	    	
	    	InputStream is = multipartFile.getInputStream();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	        String line;
	        while ((line = reader.readLine()) != null) {
	        	urls.add(line);
	        }
	        urlSvc.addUrlBatchSimple(urls);
	        return "addURLBatchSimple";
	    }

	    @RequestMapping(value="/addbatchthreaded", method=RequestMethod.POST)
	    public String addURLBatchThreaded(@RequestParam("urlfile") MultipartFile multipartFile, Model model) throws Exception {
	    	ArrayList<String> urls = new ArrayList<String>();
	    	log.info(multipartFile.toString());
	    	InputStream is = multipartFile.getInputStream();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	        String line;
	        while ((line = reader.readLine()) != null) {
	        	urls.add(line);
	        }
	        urlSvc.addUrlBatchThreaded(urls);
	        return "addURLBatchThreaded";
	    }
	    
	    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	    public String deleteURL(@PathVariable Long id, ModelMap model) {
	    	MyURL url=urlSvc.populateURL(id);
	    	urlSvc.deleteURL(url);
	    	model.addAttribute("model", url);
	        return "deleteURL";
	    }
	}
