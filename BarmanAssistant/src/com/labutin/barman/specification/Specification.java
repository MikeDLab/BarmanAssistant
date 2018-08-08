package com.labutin.barman.specification;

import java.util.Set;

import com.labutin.barman.exception.RepositoryException;

public interface Specification<T> {
	Set<T> querry() throws RepositoryException;
}
