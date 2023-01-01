package ba.unsa.etf.rpr.dao;


/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Amina Hromic (ahromic1)
 */

public class DaoFactory {  //gdje koristiti???
   private static final ArtistsDao artistsDao = new ArtistsSQLImplementation();
    private static final ArtworkDao artworkDao = new ArtworkSQLImplementation();
    private static final ExhibitionsDao exhibitionsDao = new ExhibitionsSQLImplementation();


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
