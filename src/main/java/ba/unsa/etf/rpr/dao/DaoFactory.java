package ba.unsa.etf.rpr.dao;


/**
 * Factory method for singleton implementation of DAOs
 * @author Amina Hromic (ahromic1)
 */

public class DaoFactory {  //gdje koristiti???
    //da pristupi i jednom i drugom
   private static final ArtistsDao artistsDao = ArtistsSQLImplementation.getInstance();
    private static final ArtworkDao artworkDao = ArtworkSQLImplementation.getInstance();
    private static final ExhibitionsDao exhibitionsDao = ExhibitionsSQLImplementation.getInstance();


    /**
     * constructor with no parameters
     */
    private DaoFactory(){
    }

    /**
     * @return artistsDao
     */

    public static ArtistsDao artistsDao(){
        return artistsDao;
    }

    /**
     * @return artworkDao
     */

    public static ArtworkDao artworkDao(){

        return artworkDao;
    }

    /**
     * @return exhibitionsDao
     */

    public static ExhibitionsDao exhibitionsDao(){
        return exhibitionsDao;
    }

}
