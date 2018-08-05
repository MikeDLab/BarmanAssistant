package com.labutin.barman.command.ingredient;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.service.IngredientService;



public class ShowIngredientCommand implements Command {
	private IngredientService receiver;

	public ShowIngredientCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiver = new IngredientService();		
			Set<Ingredient> setIngredient = receiver.receiveIngredient();
			request.setAttribute("setIngredient", setIngredient);
			System.out.println("good");
		} catch (NoJDBCDriverException | NoJDBCPropertiesFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return PageEnum.INGREDIENT_LIST;

	}

}
