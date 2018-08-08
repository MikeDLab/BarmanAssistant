package com.labutin.barman.command.cocktail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

public class AddCocktailCommand implements Command {
	private static Logger logger = LogManager.getLogger();
	private CocktailService receiver;
	private IngredientService receiverIngredient;
	private UserService receiverUser;
	private ImageUtil imageUtil = new ImageUtil();

	public AddCocktailCommand() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String cocktailName = request.getParameter("cocktailname");
		String cocktailDescription = request.getParameter("cocktaildesc");
		User userSession = (User) request.getSession().getAttribute("User");
		int userId = userSession.getUserId();
		boolean isPublished = userSession.getUserRole() != 2;
		int cocktailVol = Integer.parseInt(request.getParameter("cocktailvol"));
		String[] selectedIngredients = request.getParameterValues("selected");
		try {
			receiverIngredient = new IngredientService();
			if (selectedIngredients == null) {
				Set<Ingredient> setIngredient = receiverIngredient.receiveIngredient();
				request.setAttribute("setIngredient", setIngredient);
				request.setAttribute("Errormessage", "Не выбрано ни 1 ингредиента");
				return PageEnum.ADD_COCKTAIL;
			}

			// TODO Auto-generated method stub
			InputStream inputStream = imageUtil.getInputStream(request);
			receiver = new CocktailService();
			Cocktail cocktail = receiver.add(cocktailName, userId, cocktailDescription, cocktailVol, isPublished,
					inputStream);
			request.setAttribute("cocktailId", cocktail.getCocktailId());
			int cocktailId = cocktail.getCocktailId();
			for (String selected : selectedIngredients) {
				receiverIngredient.insertIngredientCocktail(Integer.parseInt(selected), cocktailId);
			}
			Set<Cocktail> setCocktail = receiver.receivePublishedCocktail();
			Set<User> setUser = null;
			if (setCocktail != null) {
				receiverUser = new UserService();
				setUser = receiverUser.receiveCocktailAuthorSet();

			}
			Map<Cocktail, User> userCocktailMap = new HashMap<>();
			for (User user : setUser) {
				for (Cocktail cocktail1 : setCocktail) {
					if (user.getUserId() == cocktail1.getUserId()) {
						userCocktailMap.put(cocktail1, user);
					}
				}
			}
			request.setAttribute("userCocktailMap", userCocktailMap);
			request.setAttribute("setCocktail", setCocktail);
		} catch (ServiceException e) {
			return PageEnum.ERROR_PAGE;
		}

		return PageEnum.COCKTAIL_LIST_PAGE;
	}

}
