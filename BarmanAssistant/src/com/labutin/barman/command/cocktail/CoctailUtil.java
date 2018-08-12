package com.labutin.barman.command.cocktail;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labutin.barman.command.JspParameter;
import com.labutin.barman.command.PageEnum;
import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.entity.Rating;
import com.labutin.barman.entity.User;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.service.CocktailService;
import com.labutin.barman.service.IngredientService;
import com.labutin.barman.service.RatingService;
import com.labutin.barman.service.UserService;
import com.labutin.barman.util.XssParser;

class CoctailUtil {
	private ImageUtil imageUtil = new ImageUtil();
	private CocktailService receiverCocktail;
	private IngredientService receiverIngredient;
	private UserService receiverUser;
	private RatingService receiverRating;

	public CoctailUtil() {
		// TODO Auto-generated constructor stub
	}

	boolean addCocktail(HttpServletRequest request, HttpServletResponse response) {
		String cocktailName = XssParser.parse(request.getParameter(JspParameter.COCKTAIL_NAME.getValue()));
		String cocktailDescription = XssParser
				.parse(request.getParameter(JspParameter.COCKTAIL_DESCRIPTION.getValue()));
		int cocktailVol = Integer.parseInt(request.getParameter(JspParameter.COCKTAIL_VOL.getValue()));
		User userSession = (User) request.getSession().getAttribute(JspParameter.USER.getValue());
		int userId;
		boolean isPublished;
		if (userSession != null) {
			userId = userSession.getUserId();
			isPublished = userSession.getUserRole() != 2;
		} else {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Login pls");
			return false;
		}

		String[] cocktailIngredientArray = request
				.getParameterValues(JspParameter.COCKTAIL_INGREDIENT_ARRAY.getValue());
		try {
			receiverIngredient = new IngredientService();
			if (cocktailIngredientArray == null) {
				Set<Ingredient> setIngredient = receiverIngredient.receiveIngredient();
				request.setAttribute(JspParameter.INGREDIENT_SET.getValue(), setIngredient);
				request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Не выбрано ни 1 ингредиента");
				return false;
			}

			InputStream inputStream = imageUtil.getInputStream(request);
			if (inputStream == null) {
				request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "No image selected");
				return false;
			}
			receiverCocktail = new CocktailService();
			Cocktail cocktail = receiverCocktail.add(cocktailName, userId, cocktailDescription, cocktailVol,
					isPublished, inputStream);
			int cocktailId = cocktail.getCocktailId();
			for (String selected : cocktailIngredientArray) {
				receiverIngredient.insertIngredientCocktail(Integer.parseInt(selected), cocktailId);
			}
		} catch (ServiceException | NumberFormatException e) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Sorry try later");
		}
		return true;
	}

	void addCocktailRating(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiverUser = new UserService();
			receiverRating = new RatingService();
			receiverCocktail = new CocktailService();
			User user = (User) request.getSession().getAttribute(JspParameter.USER.getValue());
			int cocktailId = Integer.parseInt(request.getParameter(JspParameter.COCKTAIL_ID.getValue()));
			int cocktailRating = Integer.parseInt(request.getParameter(JspParameter.COCKTAIL_RATING.getValue()));
			if (user != null) {
				receiverCocktail.addCocktailRating(cocktailRating, cocktailId, user.getUserId());
			}
		} catch (ServiceException | NumberFormatException e) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Cannot update set rating to barman");
		}
	}

	public void deleteCocktail(HttpServletRequest request, HttpServletResponse response) {
		int cocktailId = Integer.parseInt(request.getParameter(JspParameter.COCKTAIL_ID.getValue()));
		try {
			// TODO Auto-generated method stub
			receiverIngredient = new IngredientService();
			receiverIngredient.removeIngredient(cocktailId);
			receiverRating = new RatingService();
			receiverRating.removeCocktailRating(cocktailId);
			receiverCocktail = new CocktailService();
			receiverCocktail.removeCocktail(cocktailId);
		} catch (ServiceException e) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Sorry try later");
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

	public void publishCocktail(HttpServletRequest request, HttpServletResponse response) {
		int cocktailId = Integer.parseInt(request.getParameter(JspParameter.COCKTAIL_ID.getValue()));
		try {
			// TODO Auto-generated method stub
			receiverCocktail = new CocktailService();
			receiverCocktail.publishCocktail(cocktailId);
		} catch (ServiceException e) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Sorry try later");
		}
	}

	public void showCocktailInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			int cocktailId = Integer.parseInt(request.getParameter(JspParameter.COCKTAIL_ID.getValue()));
			int userId = Integer.parseInt(request.getParameter(JspParameter.USER_ID.getValue()));
			User currentUser = (User) request.getSession().getAttribute(JspParameter.USER.getValue());
			receiverRating = new RatingService();
			receiverCocktail = new CocktailService();
			Cocktail cocktail = receiverCocktail.receiveCocktailById(cocktailId);
			if (cocktail == null) {
				request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Sorry try later");
				return;
			}
			receiverUser = new UserService();
			User user = receiverUser.receiveUserById(userId);
			receiverIngredient = new IngredientService();
			Set<Ingredient> setIngredient = receiverIngredient.receiveIngredientByCocktailId(cocktailId);
			Rating rating = null;
			if (currentUser != null) {
				rating = receiverRating.receiveCocktailRatingSetByUserIdAndCocktailId(currentUser.getUserId(),
						cocktailId);
			}
			Map<Cocktail, Rating> cocktailRatingMap = new HashMap<>();
			cocktailRatingMap.put(cocktail, rating);
			request.setAttribute(JspParameter.COCKTAIL_RATING_MAP.getValue(), cocktailRatingMap);
			request.setAttribute(JspParameter.INGREDIENT_SET.getValue(), setIngredient);
			request.setAttribute(JspParameter.AUTHOR.getValue(), user);
			request.setAttribute(JspParameter.COCKTAIL.getValue(), cocktail);
		} catch (ServiceException | NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Sorry try later");
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

				request.setAttribute(JspParameter.COCKTAIL_USER_MAP.getValue(), userCocktailMap);
				// request.setAttribute(JspParameter.COCKTAIL_SET.getValue(), setCocktail);
			} else {
				request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Sorry try later");
			}

		} catch (ServiceException e) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Sorry try later");
		}
	}

	public void showPublishedCocktail(HttpServletRequest request, HttpServletResponse response) {
		try {
			receiverCocktail = new CocktailService();
			receiverRating = new RatingService();
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
				Map<Cocktail, Integer> cocktailAverageRatingMap = receiveAverageCockatilRating(setCocktail);
				request.setAttribute(JspParameter.COCKTAIL_AVERAGE_RATING_MAP.getValue(), cocktailAverageRatingMap);
				request.setAttribute(JspParameter.COCKTAIL_USER_MAP.getValue(), userCocktailMap);
				// request.setAttribute("setCocktail", setCocktail);
			} else {
				request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Sorry try later");
			}
		} catch (ServiceException e) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(), "Sorry try later");
		}
	}

	private Integer averageRating(Set<Rating> rating) {
		if (rating.isEmpty()) {
			return null;
		}
		Integer sum = 0;
		for (Rating r : rating) {
			sum += r.getRating();
		}
		return sum / rating.size();
	}

	private Map<Cocktail, Integer> receiveAverageCockatilRating(Set<Cocktail> cocktailSet) {
		try {
			receiverRating = new RatingService();
			Map<Cocktail, Integer> userAverageRatingMap = new HashMap<>();
			for (Cocktail cocktail : cocktailSet) {
				Set<Rating> averageRating = receiverRating
						.receiveCocktailRatingSetByCocktailId(cocktail.getCocktailId());
				userAverageRatingMap.put(cocktail, averageRating(averageRating));
			}
			return userAverageRatingMap;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

}
