package com.labutin.barman.repository;

import java.util.Set;

import com.labutin.barman.entity.User;
import com.labutin.barman.specification.Specification;

public interface Repository<T> {
 void add(T item);
 void remove(T item);
 void update(T item);
 Set<User> query(Specification specification);
}
