package com.labutin.barman.command.cocktail;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.PageEnum;
import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.service.CocktailService;
import com.labutin.barman.service.IngredientService;
import com.labutin.barman.service.UserService;
import com.labutin.barman.util.XssParser;

class CoctailUtil {
	private CocktailService receiverCocktail;
	private IngredientService receiverIngredient;
	private UserService receiverUser;
	private ImageUtil imageUtil = new ImageUtil();

	public CoctailUtil() {
		// TODO Auto-generated constructor stub
	}
	void addCocktailRating(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiverUser = new UserService();
			User user = (User) request.getSession().getAttribute("User");
			int cocktailId = Integer.parseInt(request.getParameter("cocktail_id"));
			int cocktailRating = Integer.parseInt(request.getParameter("cocktail_rating"));
			receiverCocktail.addCocktailRating(cocktailRating, cocktailId, user.getUserId());
		} catch (ServiceException | NumberFormatException e) {
			request.setAttribute("Errormessage", "Cannot update set rating to barman");
		}
	}
	boolean addCocktail(HttpServletRequest request, HttpServletResponse response) {
		String cocktailName = XssParser.parse(request.getParameter("cocktailname"));
		String cocktailDescription = XssParser.parse(request.getParameter("cocktaildesc"));
		User userSession = (User) request.getSession().getAttribute("User");
		int userId;
		boolean isPublished;
		if (userSession != null) {
			userId = userSession.getUserId();
			isPublished = userSession.getUserRole() != 2;
		} else {
			request.setAttribute("Errormessage", "Login pls");
			return false;
		}
		int cocktailVol = Integer.parseInt(request.getParameter("cocktailvol"));
		String[] selectedIngredients = request.getParameterValues("selected");
		try {
			receiverIngredient = new IngredientService();
			if (selectedIngredients == null) {
				Set<Ingredient> setIngredient = receiverIngredient.receiveIngredient();
				request.setAttribute("setIngredient", setIngredient);
				request.setAttribute("Errormessage", "Не выбрано ни 1 ингредиента");
				return false;
			}

			// TODO Auto-generated method stub]
			InputStream inputStream = imageUtil.getInputStream(request);
			if (inputStream == null) {
				return false;
			}
			receiverCocktail = new CocktailService();
			Cocktail cocktail = receiverCocktail.add(cocktailName, userId, cocktailDescription, cocktailVol,
					isPublished, inputStream);
			request.setAttribute("cocktailId", cocktail.getCocktailId());
			int cocktailId = cocktail.getCocktailId();
			for (String selected : selectedIngredients) {
				receiverIngredient.insertIngredientCocktail(Integer.parseInt(selected), cocktailId);
			}
		} catch (ServiceException | NumberFormatException e) {
			request.setAttribute("Errormessage", "Sorry try later");
		}
		return true;
	}

	public void showPublishedCocktail(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiverCocktail = new CocktailService();
			Set<Cocktail> setCocktail = receiverCocktail.receivePublishedCocktail();
			Set<User> setUser = null;
			if (setCocktail != null) {
				receiverUser = new UserService();
				setUser = receiverUser.receiveCocktailAuthorSet();
			}
			Map<Cocktail, User> userCocktailMap = new HashMap<>();
			if (setUser != null) {
				for (User user : setUser) {
					for (Cocktail cocktail : setCocktail) {
						if (user.getUserId() == cocktail.getUserId()) {
							userCocktailMap.put(cocktail, user);
						}
					}
				}
				request.setAttribute("userCocktailMap", userCocktailMap);
				request.setAttribute("setCocktail", setCocktail);
			} else {
				request.setAttribute("Errormessage", "Sorry try later");
			}
		} catch (ServiceException e) {
			request.setAttribute("Errormessage", "Sorry try later");
		}
	}

	public void showNotPublishedCocktail(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiverCocktail = new CocktailService();
			Set<Cocktail> setCocktail = receiverCocktail.receiveNotPublishedCocktail();
			Set<User> setUser = null;
			if (setCocktail != null) {
				receiverUser = new UserService();
				setUser = receiverUser.receiveCocktailAuthorSet();

			}
			Map<Cocktail, User> userCocktailMap = new HashMap<>();
			if (setUser != null) {
				for (User user : setUser) {
					for (Cocktail cocktail : setCocktail) {
						if (user.getUserId() == cocktail.getUserId()) {
							userCocktailMap.put(cocktail, user);
						}
					}
				}

				request.setAttribute("userCocktailMap", userCocktailMap);
				request.setAttribute("setCocktail", setCocktail);
			} else {
				request.setAttribute("Errormessage", "Sorry try later");
			}

		} catch (ServiceException e) {
			request.setAttribute("Errormessage", "Sorry try later");
		}
	}

	public void publishCocktail(HttpServletRequest request, HttpServletResponse response) {
		int cocktailId = Integer.parseInt(request.getParameter("cocktail_id"));
		try {
			// TODO Auto-generated method stub
			receiverCocktail = new CocktailService();
			receiverCocktail.publishCocktail(cocktailId);
		} catch (ServiceException e) {
			request.setAttribute("Errormessage", "Sorry try later");
		}
	}

	public void showCocktailInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			int cocktailId = Integer.parseInt(request.getParameter("cocktail_id"));
			int userId = Integer.parseInt(request.getParameter("user_id"));
			receiverCocktail = new CocktailService();
			Cocktail cocktail = receiverCocktail.receiveCocktailById(cocktailId);
			if (cocktail == null) {
				request.setAttribute("Errormessage", "Sorry try later");
				return;
			}
			receiverUser = new UserService();
			User user = receiverUser.receiveUserById(userId);
			receiverIngredient = new IngredientService();
			Set<Ingredient> setIngredient = receiverIngredient.receiveIngredientByCocktailId(cocktailId);
			request.setAttribute("setIngredient", setIngredient);
			request.setAttribute("user", user);
			request.setAttribute("cocktail", cocktail);
		} catch (ServiceException | NumberFormatException e) {
			request.setAttribute("Errormessage", "Sorry try later");
		}
	}

	public void findCocktailImage(HttpServletRequest request, HttpServletResponse response) {
		int cocktailId = Integer.parseInt(request.getParameter("imageId"));
		try {
			receiverCocktail = new CocktailService();
			Cocktail cocktail = receiverCocktail.receiveCocktailById(cocktailId);
			if (cocktail != null) {
				byte[] img = cocktail.getImage().readAllBytes();
				response.setContentType("image/jpeg");
				OutputStream os = response.getOutputStream();
				os.write(img);
				os.flush();
				os.close();
			}	
		} catch (ServiceException | IOException e) {
		}
	}
	public void deleteCocktail(HttpServletRequest request, HttpServletResponse response)
	{
		int cocktailId = Integer.parseInt(request.getParameter("cocktail_id"));
		try {
			// TODO Auto-generated method stub
			receiverIngredient = new IngredientService();
			receiverIngredient.removeIngredient(cocktailId);
			receiverCocktail = new CocktailService();
			receiverCocktail.removeCocktail(cocktailId);
		}catch (ServiceException e) {
			request.setAttribute("Errormessage", "Sorry try later");
		}
	}

}
