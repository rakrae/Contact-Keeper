package repository;

import java.util.List;
import java.util.Optional;

/**
 * A basic repository interface that provides CRUD (Create, Read, Update, Delete) operations for entity types.
 *
 * @param <T>  The entity type.
 * @param <ID> The ID type of the entity.
 */
public interface CrudRepository<T, ID> {

    /**
     * Retrieves an entity by its ID.
     *
     * @param id The ID of the entity to retrieve.
     * @return An {@link Optional} containing the entity with the given ID, or an empty Optional if none found.
     */
    Optional<T> findById(ID id);

    /**
     * Retrieves all entities of the repository's type.
     *
     * @return A {@link List} containing all entities in the repository.
     */
    List<T> findAll();

    /**
     * Retrieves all entities with the given IDs.
     *
     * @param ids The IDs of the entities to retrieve.
     * @return A {@link List} containing the entities with the given IDs.
     */
    List<T> findAllById(Iterable<ID> ids);

    /**
     * Counts the number of entities in the repository.
     *
     * @return The number of entities in the repository.
     */
    long count();

    /**
     * Deletes the entity with the given ID.
     *
     * @param id The ID of the entity to delete.
     */
    void deleteById(ID id);

    /**
     * Deletes the given entity.
     *
     * @param entity The entity to delete.
     */
    void delete(T entity);

    /**
     * Deletes all entities in the given iterable.
     *
     * @param entities The entities to delete.
     */
    void deleteAll(Iterable<? extends T> entities);

    /**
     * Checks if an entity with the given ID exists in the repository.
     *
     * @param id The ID of the entity to check.
     * @return True if an entity with the given ID exists in the repository, false otherwise.
     */
    boolean existsById(ID id);

    /**
     * Saves the given entity.
     *
     * @param entity The entity to save.
     * @return The saved entity.
     */
    <S extends T> S save(S entity);

    /**
     * Saves all entities in the given iterable.
     *
     * @param entities The entities to save.
     * @return A {@link List} containing the saved entities.
     */
    <S extends T> List<S> saveAll(Iterable<S> entities);
    
    /**
     * Updates the given entity.
     *
     * @param entity The entity to update.
     * @return The updated entity.
     */
    <S extends T> S update(S entity);

}
