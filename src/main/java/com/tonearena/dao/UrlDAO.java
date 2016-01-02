package com.tonearena.dao;

import com.tonearena.model.MyURL;


public interface UrlDAO extends BaseDAO<MyURL> {
	
	public void save(MyURL url);
	public void update(MyURL url);
	public void delete(MyURL url);
	public MyURL populate(int id);

}
