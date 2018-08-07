package com.labutin.barman.specification;

import java.util.Set;

import com.labutin.barman.exception.EntityException;
import com.labutin.barman.exception.UserException;

public interface Specification<T> {
	Set<T> querry() throws EntityException;
}
