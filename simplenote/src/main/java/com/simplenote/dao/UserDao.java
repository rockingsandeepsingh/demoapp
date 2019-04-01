/*
 * 
 */
package com.simplenote.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.simplenote.entities.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserDao.
 */
public interface UserDao extends CrudRepository<User, Long> {

	/**
	 * Check user availability.
	 *
	 * @param username the username
	 * @param pwd the pwd
	 * @return the user
	 */
	@Query("SELECT u FROM User u WHERE u.username = :uname AND u.password = :pwd ")
	User checkUserAvailability(@Param("uname") String username, @Param("pwd") String pwd);

	/**
	 * Find byusername.
	 *
	 * @param name the name
	 * @return the user
	 */
	User findByusername(String name);
}
