/*
 * 
 */
package com.simplenote.dao;

import org.springframework.data.repository.CrudRepository;

import com.simplenote.entities.UserNotes;


/**
 * The Interface UserNoteDao.
 */
public interface UserNoteDao extends CrudRepository<UserNotes, Long> {

}
