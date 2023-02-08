package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Exhibitions;
import ba.unsa.etf.rpr.exceptions.DBException;

import java.sql.Date;
import java.util.List;

/**
 * @author Amina Hromic
 * an interface for artists which extends Dao with methods specific for artists table
 */
public interface ExhibitionsDao extends Dao<Exhibitions>  {

    /**
     * a method which searches for exhibitions by date
     * @param d date
     * @return list of exhibitions
     * @throws DBException when something is out of order
     */

    List<Exhibitions> SearchByDate(Date d) throws DBException;


}
