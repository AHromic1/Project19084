package ba.unsa.etf.rpr.dao;


/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Amina Hromic (ahromic1)
 */

public class DaoFactory {  //gdje koristiti???
    //da pristupi i jednom i drugom
   private static final ArtistsDao artistsDao = ArtistsSQLImplementation.getInstance();
    private static final ArtworkDao artworkDao = ArtworkSQLImplementation.getInstance();
    private static final ExhibitionsDao exhibitionsDao = ExhibitionsSQLImplementation.getInstance();


    private DaoFactory(){
    }


    public static ArtistsDao artistsDao(){
        return artistsDao;
    }

    public static ArtworkDao artworkDao(){

        return artworkDao;
    }

    public static ExhibitionsDao exhibitionsDao(){
        return exhibitionsDao;
    }

}
