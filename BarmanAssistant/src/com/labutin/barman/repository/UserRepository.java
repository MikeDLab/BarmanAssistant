package com.labutin.barman.repository;

import java.util.Set;

import com.labutin.barman.entity.User;
import com.labutin.barman.exception.EntityException;
import com.labutin.barman.exception.UserException;
import com.labutin.barman.specification.Specification;

public interface UserRepository extends Repository {
	void add(User item) throws UserException;

	void remove(User item) throws UserException;

	void update(User item) throws UserException;

	Set<User> query(Specification<User> specification) throws EntityException;
}
