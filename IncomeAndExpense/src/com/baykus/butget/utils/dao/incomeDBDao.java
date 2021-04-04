package com.baykus.butget.utils.dao;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.baykus.butget.models.expense;
import com.baykus.butget.models.income;
import com.baykus.butget.utils.MyHBUtil;

public class incomeDBDao extends DbServicessBase<income> {

	
	
	Session ss = MyHBUtil.getSessionFactory().openSession();
	Transaction tt = ss.beginTransaction();
	
	
	public List<income> searchforUsers(income temp){
		List<income> results ;
		try {
			Criteria cr = ss.createCriteria(temp.getClass());
			cr.add(Restrictions.eq("user", temp.getUser()));
			results = cr.list();
			tt.commit();
			ss.close();
			return results;
			
			
		} catch (Exception e) {
			tt.rollback();
			ss.close();
			e.printStackTrace();
			 return null;
		}
	
	}
	
	public List<income> searchBetween(income temp,Date startDate,Date endDate) {
		List<income> results;
		try {
			Criteria cr = ss.createCriteria(temp.getClass());
			cr.add(Restrictions.between("date", startDate, endDate));
			results = cr.list();
			tt.commit();
			ss.close();
			return results;

		} catch (Exception e) {
			tt.rollback();
			ss.close();
			e.printStackTrace();
			return null;
		}

	}
}
