package com.innowisegroup.repository;

public interface Specification<T> {

    boolean specified(T o);
}
