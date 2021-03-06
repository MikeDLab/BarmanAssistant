package com.labutin.barman.repository;

import java.util.Set;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.specification.ingredient.IngredientSpecification;

public interface IngredientRepository extends Repository {
	 void add(Ingredient item) throws RepositoryException;
	 void remove(Ingredient item) throws RepositoryException;
	 Set<Ingredient> query(IngredientSpecification specification) throws RepositoryException;
}
