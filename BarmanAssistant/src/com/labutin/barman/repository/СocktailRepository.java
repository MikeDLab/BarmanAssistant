package com.labutin.barman.repository;

import java.util.Set;

import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.exception.EntityException;
import com.labutin.barman.exception.UserException;
import com.labutin.barman.specification.Specification;

public interface СocktailRepository extends Repository {
	 void add(Cocktail item);
	 void remove(Cocktail item);
	 Set<Cocktail> query(Specification<Cocktail> specification) throws EntityException;
}
