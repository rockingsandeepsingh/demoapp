/*
 * 
 */
package com.simplenote.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Columns;

// TODO: Auto-generated Javadoc
/**
 * The Class UserNotes.
 */
@Entity
@Table(name = "usernotes")
public class UserNotes {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	/** The title. */
	@NotBlank(message = "Title is mandatory")
	private String title;

	/** The description. */
	@NotBlank(message = "Description is mandatory")
	private String description;

	/** The createdon. */
	@Column(name = "createdon")
	private Date createdon;
	
	/** The updateon. */
	@Column(name = "updateon")
	private Date updateon;

	/**
	 * Instantiates a new user notes.
	 */
	public UserNotes() {
	}

	/**
	 * Instantiates a new user notes.
	 *
	 * @param title the title
	 * @param description the description
	 * @param createdon the createdon
	 * @param updateon the updateon
	 */
	public UserNotes(String title, String description, Date createdon, Date updateon) {
		super();
		this.title = title;
		this.description = description;
		this.createdon = createdon;
		this.updateon = updateon;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the createdon.
	 *
	 * @return the createdon
	 */
	public Date getCreatedon() {
		return createdon;
	}

	/**
	 * Sets the createdon.
	 *
	 * @param createdon the new createdon
	 */
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	/**
	 * Gets the updateon.
	 *
	 * @return the updateon
	 */
	public Date getUpdateon() {
		return updateon;
	}

	/**
	 * Sets the updateon.
	 *
	 * @param updateon the new updateon
	 */
	public void setUpdateon(Date updateon) {
		this.updateon = updateon;
	}

}
