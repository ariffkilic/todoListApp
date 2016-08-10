package com.demo.springboot.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="USER")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID",nullable=false,updatable=false)
	private Long id;
	
	@Column(name="NAME", nullable=false,updatable=false,unique=true)
	private String name;
	
	@Column(name="PASSWORD", nullable=false,updatable=true)
	private String password;
	
	@Column(name="DEPARTMENT")
	private String department;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY, orphanRemoval=true)
	private List<Todo> todoList;
	
	public User() {
	}

	public User(String name, String password, String department) {
		super();
		this.name = name;
		this.password = password;
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	public Long getId() {
		return id;
	}

	@Transient
	public List<Todo> getTodoList() {
		return todoList;
	}

	public void setTodoList(List<Todo> todoList) {
		this.todoList = todoList;
	}
}
