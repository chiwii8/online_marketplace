package app.service.interfaces;


import java.util.List;

public interface ICommonService<T,ID> {
    /**
     * creates a new entity
     * @return new Entity blank
     */
    T create();

    /**
     * Use to search an entity by is identifier
     * @param id - must not be null
     * @return the entity search
     */
    T findById(Long id);

    /**
     * Use to search a list of entities
     * @return list of the entities, may be empty or not
     */
    List<T> findAll();

    /**
     * Deletes a given entity
     * @param entity - must not be null
     */
    void delete(T entity);

    /**
     * Deletes an entity by is identifier
     * @param idEntity - must not be null
     */
    void deleteById(ID idEntity);

    /**
     * Saves the given entity, may be a new one, or an update of the entity
     * @param entity - must not be null
     * @return The entity saved.
     */
    T save(T entity);
}
