package org.example.list;

public interface List<T> {


	void insert(T element);


	void update(int index, T element);


	void delete(T element);


	T search(T element);


}
