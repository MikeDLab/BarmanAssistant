package com.labutin.barman.specification;

import java.util.Set;

import com.labutin.barman.entity.User;


public interface Specification {
	Set<User> querry();
}
