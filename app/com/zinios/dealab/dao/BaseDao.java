package com.zinios.dealab.dao;

public interface BaseDao<T> {

	/**
	 * @param entityToAdd - the entity to be add
	 * @return - entity added
	 */
	T add(T entityToAdd);

	/**
	 * @param entityToUpdate - entity to be updated
	 * @return - updated entity
	 */
	T update(T entityToUpdate);

	/**
	 * @param entityToDelete - entity to be delete
	 * @return - deleted entity
	 */
	T delete(T entityToDelete);

	/**
	 * @param idToFind - entity id which is to find
	 * @return - the T (model) contains that id
	 */
	T find(Long idToFind);

}
