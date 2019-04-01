/*
 * 
 */
package com.simplenote.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.simplenote.dao.UserDao;
import com.simplenote.dao.UserNoteDao;
import com.simplenote.entities.User;
import com.simplenote.entities.UserNotes;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleNoteController.
 */
@Controller
public class SimpleNoteController {

	/** The user dao. */
	@Autowired
	private UserDao userDao;

	/** The user note dao. */
	@Autowired
	private UserNoteDao userNoteDao;

	/**
	 * Instantiates a new simple note controller.
	 *
	 * @param userDao the user dao
	 * @param userNoteDao the user note dao
	 */
	public SimpleNoteController(UserDao userDao, UserNoteDao userNoteDao) {
		this.userDao = userDao;
		this.userNoteDao = userNoteDao;
	}

	/**
	 * Show sign up form.
	 *
	 * @param user the user
	 * @return the string
	 */
	@GetMapping("/signup")
	public String showSignUpForm(User user) {
		return "signup";
	}

	/**
	 * Adds the user.
	 *
	 * @param user the user
	 * @param result the result
	 * @param model the model
	 * @return the string
	 */
	@PostMapping("/adduser")
	public String addUser(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "signup";
		}
		model.addAttribute("user", new User());
		userDao.save(user);
		return "success";
	}

	/**
	 * Gets the signin.
	 *
	 * @param user the user
	 * @return the signin
	 */
	@GetMapping("/signin")
	public String getSignin(User user) {
		return "signin";
	}

	/**
	 * Check user.
	 *
	 * @param user the user
	 * @param result the result
	 * @param model the model
	 * @return the string
	 */
	@PostMapping("/checkuser")
	public String checkUser(@Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "signin";
		}
		model.addAttribute("user", new User());
		model.addAttribute("usernotes", new UserNotes());
		User checkUserAvailability = userDao.checkUserAvailability(user.getUsername(), user.getPassword());
		if (checkUserAvailability != null) {
			model.addAttribute("userNote", new UserNotes());
			model.addAttribute("listUserNotes", userNoteDao.findAll());
			return "dashboard";
		} else {
			model.addAttribute("message", "User name and password wrong");
			return "signin";

		}
	}

	/**
	 * Gets the user note.
	 *
	 * @param model the model
	 * @return the user note
	 */
	@GetMapping("/addnote")
	public String getUserNote(Model model) {
		model.addAttribute("userNote", new UserNotes());
		return "addnote";
	}

	/**
	 * Save user note.
	 *
	 * @param userNote the user note
	 * @param result the result
	 * @param model the model
	 * @return the string
	 */
	@PostMapping("/saveusernote")
	public String saveUserNote(@Valid UserNotes userNote, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "addnote";
		}
		model.addAttribute("userNote", new UserNotes());
		userNote.setCreatedon(new Date());
		userNoteDao.save(userNote);
		model.addAttribute("listUserNotes", userNoteDao.findAll());
		return "dashboard";
	}

	/**
	 * Delete user note.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/delete/{id}")
	public String deleteUserNote(@PathVariable("id") long id, Model model) {
		UserNotes findById = userNoteDao.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user note Id:" + id));
		if (findById != null) {
			userNoteDao.delete(findById);
		}
		model.addAttribute("listUserNotes", userNoteDao.findAll());
		return "dashboard";
	}

	/**
	 * Show update form.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		UserNotes user = userNoteDao.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		model.addAttribute("user", user);
		return "updatenote";
	}

	/**
	 * Update user note.
	 *
	 * @param id the id
	 * @param user the user
	 * @param result the result
	 * @param model the model
	 * @return the string
	 */
	@PostMapping("/update/{id}")
	public String updateUserNote(@PathVariable("id") long id, @Valid UserNotes user, BindingResult result,
			Model model) {
		UserNotes userdetail = userNoteDao.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		if (userdetail != null) {
			user.setUpdateon(new Date());
			user.setCreatedon(userdetail.getCreatedon());
			userNoteDao.save(user);
			model.addAttribute("listUserNotes", userNoteDao.findAll());
			return "dashboard";
		}
		return "error";
	}

}
