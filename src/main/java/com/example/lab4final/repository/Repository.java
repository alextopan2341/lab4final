package com.example.lab4final.repository;


import com.example.lab4final.domain.User;
import com.example.lab4final.domain.Validator.ValidationException;

import java.util.List;
import java.util.Optional;

/**
 * CRUD operations repository interface
 * @param <ID> - type E must have an attribute of type ID
 * @param <E> -  type of entities saved in repository
 */

public interface Repository<E> {

    Optional<User> findOne(Integer integer);

    /**
     *
     * @param id -the id of the entity to be returned
     *           id must not be null
     * @return an {@code Optional} encapsulating the entity with the given id
     * @throws IllegalArgumentException
     *                  if id is null.
     */
    Optional<E> findOne(E e);

    Optional<User> findOne(int i);

    /**
     *
     * @return all entities
     */
    List<E> findAll();

    /**
     * @param entity entity must be not null
     * @throws ValidationException      if the entity is not valid
     * @throws IllegalArgumentException if the given entity is null.     *
     */
    public void save(E entity);


    /**
     *  removes the entity with the specified id
     * @param id
     *      id must be not null
     * @return an {@code Optional}
     *            - null if there is no entity with the given id,
     *            - the removed entity, otherwise
     * @throws IllegalArgumentException
     *                   if the given id is null.
     */
    public void delete(E e);

    /**
     *
     * @param entity
     *          entity must not be null
     * @return  an {@code Optional}
     *             - null if the entity was updated
     *             - otherwise (e.g. id does not exist) returns the entity.
     * @throws IllegalArgumentException
     *             if the given entity is null.
     * @throws ValidationException
     *             if the entity is not valid.
     */
    public void update(E entity, E newEntity);

}


