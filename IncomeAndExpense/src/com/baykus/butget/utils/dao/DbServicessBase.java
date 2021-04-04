package com.baykus.butget.utils.dao;

import java.lang.reflect.Field;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.baykus.butget.utils.MyHBUtil;



public class DbServicessBase<T> implements IDbServicess<T>{

	
	private Session ss ;
	private Transaction tt;
	
	public DbServicessBase() {
		getSession();
	}
	
	
	private void getSession() {
		ss=MyHBUtil.getSessionFactory().openSession();
		tt = ss.beginTransaction();
	}
	
	
	private void closeSessionAndCommit() {
		tt.commit();
		ss.close();
	}
	
	private void closeSessionAndRollback() {
		tt.rollback();
		ss.close();
	}
	@Override
	public Boolean save(T temp) {
		try {
			ss.save(temp);
			closeSessionAndCommit();
			return true;
		} catch (Exception e) {
			closeSessionAndRollback();
			return false;
		}
	}

	@Override
	public Boolean update(T temp) {
		try {
			ss.update(temp);
			closeSessionAndCommit();
			return true;
		} catch (Exception e) {
			closeSessionAndRollback();
			return false;
		}
	}

	@Override
	public List<T> getAllRows(T temp) {
		List<T> results ;
		try {
			Criteria cr = ss.createCriteria(temp.getClass());
			results = (List<T>)cr.list();
			closeSessionAndCommit();
			return results;	
		} catch (Exception e) {
			closeSessionAndRollback();
			return null;
		}
	}


	@Override
	public List<T> search(T temp) {
		List<T> results ;
		try {
			Criteria cr = ss.createCriteria(temp.getClass());
			Field[] field = temp.getClass().getDeclaredFields();
			for (int i = 0; i < field.length; i++) {
				field[i].setAccessible(true);
				if(field[i].get(temp) != null &&!field[i].get(temp).equals("") ) {
					//System.out.println(field[i].getType().getName().substring(field[i].getType().getName().lastIndexOf('.') + 1,field[i].getType().getName().length()));
					if(field[i].getType().getName().substring(field[i].getType()
							.getName().lastIndexOf('.') + 1,field[i].getType().getName().length())
					.equals("Integer")) {
						cr.add(Restrictions.eq(field[i].getName(),field[i].get(temp) ));
					}
				}
				if(field[i].get(temp) != null &&!field[i].get(temp).equals("") ) {
					if(field[i].getType().getName().substring(field[i].getType().getName().lastIndexOf('.') + 1)
					.equals("String")) {
						cr.add(Restrictions.ilike(field[i].getName(),"%"+field[i].get(temp)+"%" ));
					}
				}
				
			}
			results = (List<T>)cr.list();
			System.out.println(results);
			closeSessionAndCommit();
			return results;	
		} catch (Exception e) {
			closeSessionAndRollback();
			return null;
		}
	}


	@Override
	public Boolean delete(T temp) {
		try {
			ss.delete(temp);
			closeSessionAndCommit();
			return true;
		} catch (Exception e) {
			closeSessionAndRollback();
			return false;
		}
	}
}
