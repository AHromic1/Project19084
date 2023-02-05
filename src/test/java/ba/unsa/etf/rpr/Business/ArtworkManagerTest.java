package ba.unsa.etf.rpr.Business;

import ba.unsa.etf.rpr.business.ArtworkManager;
import ba.unsa.etf.rpr.dao.ArtworkSQLImplementation;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Artists;
import ba.unsa.etf.rpr.domain.Artwork;
import ba.unsa.etf.rpr.domain.Exhibitions;
import ba.unsa.etf.rpr.exceptions.DBException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class ArtworkManagerTest {
    private ArtworkManager artworkManager;
    private Artwork a;
    private ArtworkSQLImplementation artworkSql;
    private List<Artwork> artwork;

    @BeforeEach
    public void initializeObj(){
        artworkManager= new ArtworkManager();
        Mockito.mock(ArtworkManager.class);
        a = new Artwork();
        a.setName("Water lilies");
       a.setEra("Impressionism");
       a.setPrice(1000);
       Exhibitions exhibition = new Exhibitions();
        a.setExhibition(exhibition);
        Artists artist = new Artists("Claud Monet");
        a.setArtist(artist);  //artists foreign key
        artworkSql = Mockito.mock(ArtworkSQLImplementation.class);
        artwork=new ArrayList<>();
        artwork.addAll(Arrays.asList(new Artwork(50,"Mona Lisa","Golden Age", 20000,new Exhibitions(),new Artists("Leonardo da Vinci")),
                new Artwork(51,"The Kiss","Futurism", 30000,new Exhibitions(),new Artists("Gustav Klimt"))));

    }
    @Test
    void validateEra() {

        String vname = "Golden Age";
        try {
            artworkManager.validateEra(vname);

        } catch (DBException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

        String incorrectName = " ";
        Assertions.assertThrows(DBException.class, () -> {
            artworkManager.validateEra(incorrectName);
        }, "Name of an era cannot be longer than 50 or shorter than 1 character!");

        String toolong = RandomStringUtils.randomAlphabetic(50);
        Assertions.assertThrows(DBException.class, () -> {
            artworkManager.validateEra(toolong);
        }, "Name of an era cannot be longer than 50 or shorter than 1 character!");

    }
    @Test
    void add() throws DBException{
        MockedStatic<DaoFactory> daoFactoryMockedStatic=Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::artworkDao).thenReturn(artworkSql);
        when(DaoFactory.artworkDao().getAll()).thenReturn(artwork);
        artworkManager.add(a);
        DBException emp=Assertions.assertThrows(DBException.class, ()->{
            artworkManager.add(a);}, "You cannot add a new artwork containing ID. ID is autogenerated");
        Assertions.assertEquals("You cannot add a new artwork containing ID. ID is autogenerated", emp.getMessage());
        daoFactoryMockedStatic.verify(DaoFactory::artworkDao);
        Mockito.verify(artworkManager).add(a);
        daoFactoryMockedStatic.close();
    }
    @Test
    void addArt() throws DBException{  ///ZAŠTO 2???
        Artwork a = new Artwork(50,"Mona Lisa","Golden Age", 20000,new Exhibitions(),new Artists("Leonardo da Vinci"));
        a.setId(0);

        MockedStatic<DaoFactory> daoFactoryMockedStatic=Mockito.mockStatic(DaoFactory.class);

       ArtworkSQLImplementation artworkDao = Mockito.mock(ArtworkSQLImplementation.class);
        daoFactoryMockedStatic.when(DaoFactory::artworkDao).thenReturn(artworkDao); //staviti dao!

        when(artworkDao.add(a)).thenReturn(a);

        artworkManager.add(a);
        Assertions.assertTrue(true);
    }

    @Test
    void update() throws  DBException{
        Artwork a = new Artwork(50,"Mona Lisa","Golden Age", 20000,new Exhibitions(),new Artists("Leonardo da Vinci"));

        a.setId(0);

        a.setEra("Post impressionism"); //PROVJWRI

        MockedStatic<DaoFactory> daoFactoryMockedStatic=Mockito.mockStatic(DaoFactory.class);
        ArtworkSQLImplementation artworkDao=Mockito.mock(ArtworkSQLImplementation.class);
        daoFactoryMockedStatic.when(DaoFactory::artworkDao).thenReturn(artworkDao);

        when(artworkDao.update(a)).thenReturn(a);

        artworkManager.update(a);
        Assertions.assertTrue(true);
    }
    @Test
    void delete() throws DBException{
        Artwork a = new Artwork(50,"Mona Lisa","Golden Age", 20000,new Exhibitions(),new Artists("Leonardo da Vinci"));

        a.setId(0);
        a.setEra("Post impressionism"); //PROVJWRI

        MockedStatic<DaoFactory> daoFactoryMockedStatic=Mockito.mockStatic(DaoFactory.class);
        ArtworkSQLImplementation artworkDao=Mockito.mock(ArtworkSQLImplementation.class);
        daoFactoryMockedStatic.when(DaoFactory::artworkDao).thenReturn(artworkDao);
        // when(employeeDao.delete(e.getId())).thenReturn(e);

        artworkManager.delete(a.getId());
        Assertions.assertTrue(true);
    }
}