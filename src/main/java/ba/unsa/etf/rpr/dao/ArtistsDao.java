package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artists;

import java.util.List;

/**
 * an interface for artists which extends Dao with methods specific for artists table
 */
public interface ArtistsDao extends Dao<Artists> {
    @Override  //da li je override potreban ?
     Artists getById(int id) ;

    @Override
     Artists add(Artists item);

    @Override
     Artists update(Artists item);

    @Override
     void delete(int id);
    @Override
     List<Artists> getAll() ;


}
