package com.simplenote;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.simplenote.controller.SimpleNoteController;
import com.simplenote.dao.UserDao;
import com.simplenote.dao.UserNoteDao;
import com.simplenote.entities.User;

public class SimpleNoteControllerTest {
	private static SimpleNoteController noteController;
	private static UserDao mockedUserDao;
	private static UserNoteDao mockedNoteDao;
	private static BindingResult mockedBindingResult;
	private static Model mockedModel;

	@BeforeClass
	public static void setUpUserControllerInstance() {
		mockedUserDao = mock(UserDao.class);
		mockedNoteDao = mock(UserNoteDao.class);
		mockedBindingResult = mock(BindingResult.class);
		mockedModel = mock(Model.class);
		noteController = new SimpleNoteController(mockedUserDao, mockedNoteDao);
	}

	@Test
	public void whenCalledshowSignUpForm_thenCorrect() {
		User user = new User("abc", "abc");
		assertThat(noteController.showSignUpForm(user)).isEqualTo("signup");
	}

	@Test
	public void whenCalledaddUserAndValidUser_thenCorrect() {
		User user = new User("John", "john@domain.com");

		when(mockedBindingResult.hasErrors()).thenReturn(false);

		assertThat(noteController.addUser(user, mockedBindingResult, mockedModel)).isEqualTo("success");
	}

	@Test
	public void whenCalledaddUserAndInValidUser_thenCorrect() {
		User user = new User("John", "john@domain.com");

		when(mockedBindingResult.hasErrors()).thenReturn(true);

		assertThat(noteController.addUser(user, mockedBindingResult, mockedModel)).isEqualTo("signup");
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenCalledshowUpdateNotesForm_thenIllegalArgumentException() {
		assertThat(noteController.showUpdateForm(0, mockedModel)).isEqualTo("updatenote");
	}

}
