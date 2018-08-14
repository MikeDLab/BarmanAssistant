package com.labutin.barman.repository;

import java.util.Set;

import com.labutin.barman.entity.Cocktail;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.specification.cocktail.CocktailSpecification;

public interface СocktailRepository extends Repository {
	 void add(Cocktail item) throws RepositoryException;
	 void remove(int cocktailId) throws RepositoryException;
	 Set<Cocktail> query(CocktailSpecification specification) throws RepositoryException;
}
