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

public class ExpenseDBDao extends DbServicessBase<expense> {

	Session ss = MyHBUtil.getSessionFactory().openSession();
	Transaction tt = ss.beginTransaction();

	public List<expense> searchforUsers(expense temp) {
		List<expense> results;
		try {
			Criteria cr = ss.createCriteria(temp.getClass());
			cr.add(Restrictions.eq("users", temp.getUsers()));
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
	
	public List<expense> searchBetween(expense temp,Date startDate,Date endDate) {
		List<expense> results;
		try {
		
			Criteria cr1 = ss.createCriteria(temp.getClass());
			cr1.add(Restrictions.between("date", startDate, endDate));
			results = cr1.list();
			tt.commit();
			ss.close();
			System.out.println("DAO" + startDate);

			return results;

		} catch (Exception e) {
			tt.rollback();
			ss.close();
			e.printStackTrace();
			return null;
		}

	}

}
