package com.baykus.butget.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Kullanicilar")
public class Users {
	private int id;
	private String userName;
	private String password;
	private String email;
	private Role userRole;
	
	
	private List<income> incomes;
	
	
	private List<expense> expenses;
	
	@SequenceGenerator(name = "seq_users", sequenceName = "seq_users_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "seq_users", strategy = GenerationType.SEQUENCE)
	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "kullaniciadi", length = 250, nullable = false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Enumerated
	@Column(name = "user_role_id")
	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}
	@OneToMany(mappedBy = "user")
	public List<income> getIncomes() {
		return incomes;
	}

	public void setIncomes(List<income> incomes) {
		this.incomes = incomes;
	}
	@OneToMany(mappedBy = "users")
	public List<expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<expense> expenses) {
		this.expenses = expenses;
	}

	
	
}
