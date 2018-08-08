package com.labutin.barman.command.cocktail;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.Command;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.NoJDBCDriverException;
import com.labutin.barman.exception.NoJDBCPropertiesFileException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.service.CocktailService;
import com.labutin.barman.service.IngredientService;
import com.labutin.barman.service.UserService;
import com.mysql.jdbc.Blob;

public class ShowPublishedCocktailList implements Command {
	private CocktailService receiverCocktail;
	private UserService receiverUser;

	public ShowPublishedCocktailList() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiverCocktail = new CocktailService();
			Set<Cocktail> setCocktail = receiverCocktail.receivePublishedCocktail();
			Set<User> setUser = null;
			if (setCocktail != null) {
				receiverUser = new UserService();
				setUser = receiverUser.receiveCocktailAuthorSet();
			}
			Map<Cocktail, User> userCocktailMap = new HashMap<>();
			for (User user : setUser) {
				for (Cocktail cocktail : setCocktail) {
					if (user.getUserId() == cocktail.getUserId()) {
						userCocktailMap.put(cocktail, user);
					}
				}
			}
			request.setAttribute("userCocktailMap", userCocktailMap);
			request.setAttribute("setCocktail", setCocktail);
		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return PageEnum.COCKTAIL_LIST_PAGE;

	}

}
