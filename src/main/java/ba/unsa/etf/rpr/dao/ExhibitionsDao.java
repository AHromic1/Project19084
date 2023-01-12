package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Exhibitions;
import ba.unsa.etf.rpr.exceptions.DBException;

import java.sql.Date;
import java.util.List;

public interface ExhibitionsDao extends Dao<Exhibitions>  {

    List<Exhibitions> SearchByDate(Date d) throws DBException;


}
