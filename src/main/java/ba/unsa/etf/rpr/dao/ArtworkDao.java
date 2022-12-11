package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artwork;

import java.util.List;

public interface ArtworkDao extends Dao<Artwork>{
    @Override  //da li je override potreban ?
    Artwork getById(int id) ;
    //public is redundant
    @Override
    Artwork add(Artwork item);

    @Override
    Artwork update(Artwork item);

    @Override
    void delete(int id);
    @Override
    List<Artwork> getAll() ;

}
