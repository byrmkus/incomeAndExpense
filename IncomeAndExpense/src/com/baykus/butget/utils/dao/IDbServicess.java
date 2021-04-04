package com.baykus.butget.utils.dao;

import java.util.List;

public interface IDbServicess<T> {
	
	
	public Boolean save(T temp);
	public Boolean update(T temp);
	public List<T> getAllRows(T temp);
	public List<T> search(T temp);
	public Boolean delete(T temp);
}
