package com.labutin.barman.repository;

import java.util.Set;

import com.labutin.barman.entity.User;
import com.labutin.barman.exception.AddUserException;
import com.labutin.barman.exception.RemoveUserException;
import com.labutin.barman.exception.UpdateUserException;
import com.labutin.barman.specification.Specification;

public interface IUserRepository extends Repository {
	 void add(User item) throws AddUserException;
	 void remove(User item) throws RemoveUserException;
	 void update(User item) throws UpdateUserException;
	 Set<User> query(Specification<User> specification);
}
