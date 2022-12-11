package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Customers;

import java.util.List;

public interface CustomersDao extends Dao<Customers> {

        @Override  //da li je override potreban ?
        Customers getById(int id) ;
        //public is redundant
        @Override
        Customers add(Customers item);

        @Override
        Customers update(Customers item);

        @Override
        void delete(int id);
        @Override
        List<Customers> getAll() ;



}
