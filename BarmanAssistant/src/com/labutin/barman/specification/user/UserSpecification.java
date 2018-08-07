package com.labutin.barman.specification.user;

import java.util.Set;

import com.labutin.barman.entity.User;
import com.labutin.barman.exception.UserException;
import com.labutin.barman.specification.Specification;

public interface UserSpecification extends Specification<User> {
	Set<User> querry() throws UserException;
}
