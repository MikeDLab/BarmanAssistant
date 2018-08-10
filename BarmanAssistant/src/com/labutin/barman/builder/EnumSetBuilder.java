package com.labutin.barman.builder;

import java.util.EnumSet;

import com.labutin.barman.command.TypeCommand;
import com.labutin.barman.controller.UserType;

public class EnumSetBuilder extends AbstractEnumSetBuilder {
	private UserType userType;

	public EnumSetBuilder(UserType userType) {
		// TODO Auto-generated constructor stub
		this.userType = userType;
	}

	@Override
	public void buildEnumTypeCommandSet() {
		switch (userType) {
		case GUEST:
			typeCommand = EnumSet.of(TypeCommand.HOME, TypeCommand.SIGN_IN, TypeCommand.LOGIN, TypeCommand.REGISTER,
					TypeCommand.REGISTRATION, TypeCommand.SHOW_INGREDIENT, TypeCommand.COCKTAIL_LIST,
					TypeCommand.SHOW_COCKTAIL_IMAGE, TypeCommand.COCKTAIL_INFO);
			break;
		case USER:
			typeCommand = EnumSet.of(TypeCommand.HOME, TypeCommand.LOG_OUT, TypeCommand.SHOW_INGREDIENT,
					TypeCommand.ADD_COCKTAIL, TypeCommand.ADD_COCKTAIL_RATING, TypeCommand.COCKTAIL_LIST,
					TypeCommand.SHOW_COCKTAIL_IMAGE, TypeCommand.COCKTAIL_INFO, TypeCommand.ADD_BARMAN_RATING,
					TypeCommand.SHOW_BARMAN);
			break;
		case BARMAN:
			typeCommand = EnumSet.of(TypeCommand.HOME, TypeCommand.LOG_OUT, TypeCommand.SHOW_INGREDIENT,
					TypeCommand.ADD_COCKTAIL, TypeCommand.ADD_COCKTAIL_RATING, TypeCommand.COCKTAIL_LIST,
					TypeCommand.SHOW_COCKTAIL_IMAGE, TypeCommand.COCKTAIL_INFO, TypeCommand.ADD_BARMAN_RATING,
					TypeCommand.CHECK_COCKTAIL_LIST, TypeCommand.BARMAN_PANEL, TypeCommand.SHOW_BARMAN,
					TypeCommand.PUBLISH_COCKTAIL, TypeCommand.PUSH_COCKTAIL);
			break;
		case ADMIN:
			typeCommand = EnumSet.of(TypeCommand.HOME, TypeCommand.LOG_OUT, TypeCommand.SHOW_INGREDIENT,
					TypeCommand.EDIT_INGREDIENT_PAGE, TypeCommand.EDIT_INGREDIENT, TypeCommand.ADD_COCKTAIL,
					TypeCommand.ADD_COCKTAIL_RATING, TypeCommand.COCKTAIL_LIST, TypeCommand.SHOW_COCKTAIL_IMAGE,
					TypeCommand.COCKTAIL_INFO, TypeCommand.DELETE_COCKTAIL, TypeCommand.ADD_BARMAN_RATING,
					TypeCommand.CHECK_COCKTAIL_LIST, TypeCommand.BARMAN_PANEL, TypeCommand.SHOW_BARMAN,
					TypeCommand.PUBLISH_COCKTAIL, TypeCommand.PUSH_COCKTAIL, TypeCommand.ADMIN_PANEL,
					TypeCommand.SHOW_USER_LIST, TypeCommand.DELETE_USER, TypeCommand.UPDATE_TO_BARMAN,
					TypeCommand.DOWNGRADE_TO_USER);
			break;

		}

	}

}
