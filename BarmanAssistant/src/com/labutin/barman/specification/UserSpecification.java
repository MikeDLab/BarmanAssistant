package com.labutin.barman.specification;

import java.util.Set;

import com.labutin.barman.entity.User;



public interface UserSpecification extends Specification {
	Set<User> querry(Set<User> items);
}
