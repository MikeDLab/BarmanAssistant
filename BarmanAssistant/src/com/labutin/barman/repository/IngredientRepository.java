package com.labutin.barman.repository;

import java.util.Set;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.exception.EntityException;
import com.labutin.barman.exception.ServiceException;
import com.labutin.barman.exception.UserException;
import com.labutin.barman.specification.Specification;
import com.labutin.barman.specification.ingredient.IngredientSpecification;

public interface IngredientRepository extends Repository {
	 void add(Ingredient item) throws ServiceException;
	 void remove(Ingredient item);
	 Set<Ingredient> query(IngredientSpecification specification) throws EntityException;
}
