package ba.unsa.etf.rpr;
import java.util.List;

    /**
     * Root interface for all DAO classes
     *
     * @author Amina Hromic
     * @version 1.0
     */

    public interface Dao<T> {

        /**
         * Read - get entity from database base on ID
         * @param id primary key of entity
         * @return Entity from database
         */
        T getById(int id);

        /**
         * Update - Saves entity into database
         * @param item bean for saving to database
         * @return saved item with id field populated
         */
        T add(T item);

        /**
         * Update - Fully updates entity in database based on id (primary) match.
         * @param item - bean to be updated. id must be populated
         * @return updated version of bean
         */
        T update(T item);

        /**
         * Delete - Hard delete of item from database with given id
         * @param id - primary key of entity
         */
        void delete(int id);

        /**
         * Read - Lists all entities from database. WARNING: Very slow operation because it reads all records.
         * @return List of entities from database
         */
        List<T> getAll();
    }
