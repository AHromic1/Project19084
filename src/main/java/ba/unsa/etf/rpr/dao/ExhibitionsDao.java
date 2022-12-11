package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Exhibitions;

import java.util.List;

public interface ExhibitionsDao extends Dao<Exhibitions>  {

    Exhibitions getById(int id) ;
    //public is redundant

    Exhibitions add(Exhibitions item);


    Exhibitions update(Exhibitions item);
    void delete(int id);

    List<Exhibitions> getAll() ;


}
