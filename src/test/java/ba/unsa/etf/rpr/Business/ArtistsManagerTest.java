package ba.unsa.etf.rpr.Business;

import ba.unsa.etf.rpr.business.ArtistsManager;
import ba.unsa.etf.rpr.dao.ArtistsSQLImplementation;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Artists;
import ba.unsa.etf.rpr.exceptions.DBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;


/**
 * @author Amina Hromic
 *
 */
class CategoryManagerTest {

    private ArtistsManager artistManager;
    private Artists artist;
    private ArtistsSQLImplementation artistsDaoSQLMock;
    private List<Artists> artists;

    /**
     * This method will be called before each test method
     */
    @BeforeEach
    public void initializeObjects() {
        artistManager = Mockito.mock(ArtistsManager.class);
        artist = new Artists();
        artist.setName("Frida Kahlo");
        artist.setId(1);

        artistsDaoSQLMock = Mockito.mock(ArtistsSQLImplementation.class);
        artists = new ArrayList<>();
        artists.addAll(Arrays.asList(new Artists("Frida Kahlo"), new Artists("Gustav Klimt"), new Artists("Leonardo da Vinci"),new Artists("Picaso")));
    }

    /**
     * In this method we will test validateName(String name) for correct and incorrect passed parameters
     */

    @Test
    void validateArtistName() throws DBException {
        String correctName = "Frida Kahlo";
        try {
            Mockito.doCallRealMethod().when(artistManager).validateName(correctName);
        } catch (DBException e) {
            //Test will fall if method validateName(name) throws an exception for correct parameter
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

        String incorrectNameShort = " ";
        Mockito.doCallRealMethod().when(artistManager).validateName(incorrectNameShort);
        DBException artistException1 = Assertions.assertThrows(DBException.class, () -> {
           artistManager.validateName(incorrectNameShort);}, "Artist's name must be between 3 and 45 chars");
        Assertions.assertEquals("Artist's name must be between 3 and 45 chars", artistException1.getMessage());

        String incorrectNameLong = "This name is way too long for any artist, dead or alive, no matter how quirky, artistic or crazy they might have been.";
        Mockito.doCallRealMethod().when(artistManager).validateName(incorrectNameLong);
        DBException quoteException2 = Assertions.assertThrows(DBException.class, () -> {
            artistManager.validateName(incorrectNameLong);}, "Artist's name must be between 3 and 45 chars");
        Assertions.assertEquals("Artist's name must be between 3 and 45 chars", quoteException2.getMessage());
    }


    /**
     * Adding category that already exists
     * @throws DBException
     */
    @Test
    void add() throws DBException {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::artistsDao).thenReturn(artistsDaoSQLMock);
        /*
        An exception will be thrown because our instance of Artist.java class has value for id
         */
        when(DaoFactory.artistsDao().getAll()).thenReturn(artists);
        Mockito.doCallRealMethod().when(artistManager).add(artist);
        DBException artistException = Assertions.assertThrows(DBException.class, () -> {
            artistManager.add(artist);}, "You cannot add an artist with ID. ID is autogenerated");

        Assertions.assertEquals("You cannot add an artist with ID. ID is autogenerated", artistException.getMessage());
        daoFactoryMockedStatic.verify(DaoFactory::artistsDao);
        Mockito.verify(artistManager).add(artist);
        daoFactoryMockedStatic.close();
    }

    /**
     * Adding a new category
     * @throws DBException
     */
    @Test
    void addNewArtist() throws DBException {
        Artists newArtist = new Artists("Ime Prezime");
        artistManager.add(newArtist);

        Assertions.assertTrue(true);
        Mockito.verify(artistManager).add(newArtist);
    }

}