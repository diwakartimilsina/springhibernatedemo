package com.tonearena.dao;

public interface BaseDAO <T> {

	public void save(T object);
	public void update(T object);
	public void delete(T object);
	public void populate(T object);
	
}
