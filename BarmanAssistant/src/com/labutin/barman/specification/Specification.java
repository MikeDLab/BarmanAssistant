package com.labutin.barman.specification;

import java.util.Set;

public interface Specification<T> {
	Set<T> querry();
}
