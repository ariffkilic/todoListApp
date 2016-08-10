package com.demo.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.springboot.domain.Status;
import com.demo.springboot.domain.Todo;
import com.demo.springboot.domain.User;


public interface TodoRepository extends CrudRepository<Todo, Long> {
	
	@Query("SELECT t FROM Todo t where t.user = :user") 
	public List<Todo> listTaskByUser(@Param("user") User user);
	
	@Modifying
	@Query("update Todo set status=:status where id = :id") 
	public int updateTaskStatus(@Param("status") Status status ,@Param("id") Long id);

}
