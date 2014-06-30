package com.tonearena.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="\"urls\"")
@XmlRootElement(name = "url")
public class MyURL {

	@Id
    @Column(name="url_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	int URLId;
	
	@Column(name="url_str")
	String URLStr;
	
	@Column(name="url_content")
	String URLContent;
	

	public MyURL(){
		
	}
	
	public int getURLId() {
		return URLId;
	}
	public void setURLId(int uRLId) {
		URLId = uRLId;
	}
	
	public String getURLStr() {
		return URLStr;
	}
	public void setURLStr(String URLStr) {
		this.URLStr = URLStr;
	}
	public String getURLContent() {
		return URLContent;
	}
	public void setURLContent(String URLContent) {
		this.URLContent=URLContent;
	}

}
