package com.labutin.barman.repository;

import java.util.Set;

import com.labutin.barman.entity.Ingredient;
import com.labutin.barman.specification.Specification;

public interface IIngredientRepository extends Repository {
	 void add(Ingredient item);
	 void remove(Ingredient item);
	 Set<Ingredient> query(Specification<Ingredient> specification);
}
