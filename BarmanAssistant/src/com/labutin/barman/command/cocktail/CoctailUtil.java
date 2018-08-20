package com.labutin.barman.command.cocktail;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.labutin.barman.command.ImageUtil;
import com.labutin.barman.command.JspParameter;
import com.labutin.barman.command.LocaleKey;
import com.labutin.barman.command.UtilCommand;
import com.labutin.barman.command.ingredient.IngredientUtil;
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

class CoctailUtil extends UtilCommand {
	private static Logger logger = LogManager.getLogger();
	private CocktailService cocktailService;
	private ImageUtil imageUtil = new ImageUtil();
	private IngredientUtil ingredientUtil = new IngredientUtil();
	private IngredientService ingredientService;
	private RatingService ratingService;
	private UserService userService;
	private static final String IMAGE_CONTENT_TYPE = "image/jpeg";

	boolean addCocktail(HttpServletRequest request, HttpServletResponse response) {
		String cocktailName = request.getParameter(JspParameter.COCKTAIL_NAME.getValue());
		String cocktailDescription = request.getParameter(JspParameter.COCKTAIL_DESCRIPTION.getValue());
		String cocktailVolParameter = request.getParameter(JspParameter.COCKTAIL_VOL.getValue());
		InputStream inputStream = imageUtil.getInputStream(request);
		User userSession = (User) request.getSession().getAttribute(JspParameter.USER.getValue());
		int cocktailVol = Integer.parseInt(cocktailVolParameter);
		int userId;
		boolean isPublished;
		if (!checkRequestParameterSetOnNull(request, response) || cocktailName.isEmpty() || inputStream == null) {
			ingredientUtil.showIngredientSet(request, response);
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.EMPTY_FIELD.getValue()));
			return false;
		}
		cocktailName = XssParser.parse(cocktailName);
		cocktailDescription = XssParser.parse(cocktailDescription);
		if (userSession != null) {
			userId = userSession.getUserId();
			isPublished = userSession.getUserRole() != 2;
		} else {
			ingredientUtil.showIngredientSet(request, response);
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.USER_NO_SESSION.getValue()));
			return false;
		}

		String[] cocktailIngredientArray = request
				.getParameterValues(JspParameter.COCKTAIL_INGREDIENT_ARRAY.getValue());
		try {
			ingredientService = new IngredientService();
			if (cocktailIngredientArray == null) {
				Set<Ingredient> setIngredient = ingredientService.receiveIngredienSet();
				request.setAttribute(JspParameter.INGREDIENT_SET.getValue(), setIngredient);
				request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
						resourceBundle.getString(LocaleKey.COCKTAIL_WITHOUT_INGREDIENT.getValue()));
				return false;
			}
			cocktailService = CocktailService.getInstance();
			Cocktail cocktail = cocktailService.add(cocktailName, userId, cocktailDescription, cocktailVol, isPublished,
					inputStream);
			int cocktailId = cocktail.getCocktailId();
			for (String selectedIngredient : cocktailIngredientArray) {
				ingredientService.insertIngredientCocktail(Integer.parseInt(selectedIngredient), cocktailId);
			}
		} catch (ServiceException | NumberFormatException e) {
			logger.warn("Add cocktail exception", e);
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
		}
		return true;
	}

	void addCocktailRating(HttpServletRequest request, HttpServletResponse response) {
		if (!checkRequestParameterSetOnNull(request, response)) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
			return;
		}
		try {
			userService = UserService.getInstance();
			ratingService = RatingService.getInstance();
			cocktailService = CocktailService.getInstance();
			User user = (User) request.getSession().getAttribute(JspParameter.USER.getValue());
			int cocktailId = Integer.parseInt(request.getParameter(JspParameter.COCKTAIL_ID.getValue()));
			int cocktailRating = Integer.parseInt(request.getParameter(JspParameter.COCKTAIL_RATING.getValue()));
			if (user != null) {
				cocktailService.addCocktailRating(cocktailRating, cocktailId, user.getUserId());
			}
		} catch (ServiceException | NumberFormatException e) {
			logger.warn("Add cocktail rating exception", e);
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
		}
	}

	private Integer countAverageRating(Set<Rating> setRating) {
		if (setRating.isEmpty()) {
			return null;
		}
		Integer sum = 0;
		for (Rating rating : setRating) {
			sum += rating.getRating();
		}
		return sum / setRating.size();
	}

	void deleteCocktail(HttpServletRequest request, HttpServletResponse response) {
		if (!checkRequestParameterSetOnNull(request, response)) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
			return;
		}
		int cocktailId = Integer.parseInt(request.getParameter(JspParameter.COCKTAIL_ID.getValue()));
		try {
			ingredientService = new IngredientService();
			ingredientService.removeIngredient(cocktailId);
			ratingService = RatingService.getInstance();
			ratingService.removeCocktailRating(cocktailId);
			cocktailService = CocktailService.getInstance();
			cocktailService.removeCocktail(cocktailId);
		} catch (ServiceException e) {
			logger.warn("Delete cocktail exception", e);
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
		}
	}

	void findCocktailImage(HttpServletRequest request, HttpServletResponse response) {
		if (!checkRequestParameterSetOnNull(request, response)) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
			return;
		}
		int cocktailId = Integer.parseInt(request.getParameter(JspParameter.IMAGE_ID.getValue()));
		try {
			cocktailService = CocktailService.getInstance();
			Cocktail cocktail = cocktailService.receiveCocktailById(cocktailId);
			if (cocktail != null) {
				byte[] img = cocktail.getImage().readAllBytes();
				response.setContentType(IMAGE_CONTENT_TYPE);
				OutputStream outputStream = response.getOutputStream();
				outputStream.write(img);
				outputStream.flush();
				outputStream.close();
			}
		} catch (ServiceException | IOException e) {
			logger.info("Image not found", e);
		}
	}

	void publishCocktail(HttpServletRequest request, HttpServletResponse response) {
		if (!checkRequestParameterSetOnNull(request, response)) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
			return;
		}
		int cocktailId = Integer.parseInt(request.getParameter(JspParameter.COCKTAIL_ID.getValue()));
		try {
			cocktailService = CocktailService.getInstance();
			cocktailService.publishCocktail(cocktailId);
		} catch (ServiceException e) {
			logger.warn("Publish cocktail exception", e);
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
		}
	}

	private Map<Cocktail, Integer> receiveAverageCockatilRating(Set<Cocktail> cocktailSet) {
		try {
			ratingService = RatingService.getInstance();
			Map<Cocktail, Integer> userAverageRatingMap = new HashMap<>();
			for (Cocktail cocktail : cocktailSet) {
				Set<Rating> averageRating = ratingService
						.receiveCocktailRatingSetByCocktailId(cocktail.getCocktailId());
				userAverageRatingMap.put(cocktail, countAverageRating(averageRating));
			}
			return userAverageRatingMap;
		} catch (ServiceException e) {
			logger.warn("Average cocktail rating exception", e);
			return null;
		}
	}

	private Map<Cocktail, User> receiveUserCocktailMap(Set<User> setUser, Set<Cocktail> setCocktail) {
		Map<Cocktail, User> userCocktailMap = new HashMap<>();
		for (User user : setUser) {
			for (Cocktail cocktail : setCocktail) {
				if (user.getUserId() == cocktail.getUserId()) {
					userCocktailMap.put(cocktail, user);
				}
			}
		}
		return userCocktailMap;

	}

	void showCocktailInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (!checkRequestParameterSetOnNull(request, response)) {
				request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
						resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
				return;
			}
			int cocktailId = Integer.parseInt(request.getParameter(JspParameter.COCKTAIL_ID.getValue()));
			int userId = Integer.parseInt(request.getParameter(JspParameter.USER_ID.getValue()));
			User currentUser = (User) request.getSession().getAttribute(JspParameter.USER.getValue());
			ratingService = RatingService.getInstance();
			cocktailService = CocktailService.getInstance();
			Cocktail cocktail = cocktailService.receiveCocktailById(cocktailId);
			if (cocktail == null) {
				request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
						resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
				return;
			}
			userService = UserService.getInstance();
			User user = userService.receiveUserById(userId);
			ingredientService = new IngredientService();
			Set<Ingredient> setIngredient = ingredientService.receiveIngredientByCocktailId(cocktailId);
			Rating rating = null;
			if (currentUser != null) {
				rating = ratingService.receiveCocktailRatingSetByUserIdAndCocktailId(currentUser.getUserId(),
						cocktailId);
			}
			Map<Cocktail, Rating> cocktailRatingMap = new HashMap<>();
			cocktailRatingMap.put(cocktail, rating);
			request.setAttribute(JspParameter.COCKTAIL_RATING_MAP.getValue(), cocktailRatingMap);
			request.setAttribute(JspParameter.INGREDIENT_SET.getValue(), setIngredient);
			request.setAttribute(JspParameter.AUTHOR.getValue(), user);
			request.setAttribute(JspParameter.COCKTAIL.getValue(), cocktail);
		} catch (ServiceException | NumberFormatException e) {
			logger.warn("Show cocktail info exception", e);
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
		}
	}

	void showNotPublishedCocktail(HttpServletRequest request, HttpServletResponse response) {
		if (!checkRequestParameterSetOnNull(request, response)) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
			return;
		}
		try {
			cocktailService = CocktailService.getInstance();
			Set<Cocktail> setCocktail = cocktailService.receiveNotPublishedCocktail();
			Set<User> setUser = null;
			if (setCocktail != null) {
				userService = UserService.getInstance();
				setUser = userService.receiveCocktailAuthorSet();

			}
			Map<Cocktail, User> userCocktailMap;
			if (setUser != null) {
				userCocktailMap = receiveUserCocktailMap(setUser, setCocktail);
				request.setAttribute(JspParameter.COCKTAIL_USER_MAP.getValue(), userCocktailMap);
			} else {
				request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
						resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
			}

		} catch (ServiceException e) {
			logger.warn("Show not published cocktail exception", e);
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
		}
	}

	void showPublishedCocktail(HttpServletRequest request, HttpServletResponse response) {
		if (!checkRequestParameterSetOnNull(request, response)) {
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
			return;
		}
		try {
			cocktailService = CocktailService.getInstance();
			ratingService = RatingService.getInstance();
			Set<Cocktail> setCocktail = cocktailService.receivePublishedCocktail();
			Set<User> setUser = null;
			if (setCocktail != null) {
				userService = UserService.getInstance();
				setUser = userService.receiveCocktailAuthorSet();
			}
			Map<Cocktail, User> userCocktailMap;
			if (setUser != null) {
				userCocktailMap = receiveUserCocktailMap(setUser, setCocktail);
				Map<Cocktail, Integer> cocktailAverageRatingMap = receiveAverageCockatilRating(setCocktail);
				request.setAttribute(JspParameter.COCKTAIL_AVERAGE_RATING_MAP.getValue(), cocktailAverageRatingMap);
				request.setAttribute(JspParameter.COCKTAIL_USER_MAP.getValue(), userCocktailMap);
			} else {
				request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
						resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
			}
		} catch (ServiceException e) {
			logger.warn("Show published cocktail exception", e);
			request.setAttribute(JspParameter.ERROR_MESSAGE.getValue(),
					resourceBundle.getString(LocaleKey.GENERAL_EXCEPTION.getValue()));
		}
	}

}
