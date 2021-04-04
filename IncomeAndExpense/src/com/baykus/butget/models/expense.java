 package com.baykus.butget.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "Gider")
public class expense {
	
	private int giderId;
	
	private Categori categori;
	
	private Date date;
	
	private float sum; //tutar
	
	private Users users;

	@Id
	@SequenceGenerator(name = "seq_gider",sequenceName = "seq_gider_id",allocationSize = 1,initialValue = 1)
	@GeneratedValue(generator = "seq_gider", strategy = GenerationType.SEQUENCE)
	public int getGiderId() {
		return giderId;
	}

	public void setGiderId(int giderId) {
		this.giderId = giderId;
	}

	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getSum() {
		return sum;
	}

	public void setSum(float sum) {
		this.sum = sum;
	}
	
	
	@Enumerated
	@Column(name = "Gider_Categori")
	public Categori getCategori() {
		return categori;
	}

	public void setCategori(Categori categori) {
		this.categori = categori;
	}
	@Override
	public String toString() {
		return giderId+ "-" +categori ;
	}
	
	
	@ManyToOne()
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
}
