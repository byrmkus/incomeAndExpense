package com.baykus.butget.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "Gelir")
public class income {

	private int gelirId;

	private Date date;

	private String explanation; // --->açýklama

	private float sum; // --> tutar

	private Users user;
	
	
	
	@Id
	@SequenceGenerator(name = "seq_gelir", sequenceName = "seq_gelir_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_gelir", strategy = GenerationType.SEQUENCE)

	public int getGelirId() {
		return gelirId;
	}

	public void setGelirId(int gelirId) {
		this.gelirId = gelirId;
	}

	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "Aciklama", length = 250 ,nullable = false)
	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public float getSum() {
		return sum;
	}

	public void setSum(float sum) {
		this.sum = sum;
	}
	@ManyToOne()
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "income [gelirId=" + gelirId + ","
				+ ", user=" + user + "]";
	}

}
