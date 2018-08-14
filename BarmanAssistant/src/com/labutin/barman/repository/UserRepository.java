package com.labutin.barman.repository;

import java.util.Set;

import com.labutin.barman.entity.User;
import com.labutin.barman.exception.RepositoryException;
import com.labutin.barman.specification.user.UserSpecification;

public interface UserRepository extends Repository {
	void add(User item) throws RepositoryException;

	void remove(int userId) throws RepositoryException;

	void update(User item) throws RepositoryException;

	Set<User> query(UserSpecification specification) throws RepositoryException;
}
