package ba.unsa.etf.rpr.Business;

import ba.unsa.etf.rpr.business.ExhibitionManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.ExhibitionsSQLImplementation;
import ba.unsa.etf.rpr.domain.Exhibitions;
import ba.unsa.etf.rpr.exceptions.DBException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ExhibitionsManagerTest {
    private ExhibitionManager exhibitionsManager;
    private Exhibitions e;
    private ExhibitionsSQLImplementation exhibitionsSql;
    private List<Exhibitions> exhibitions;


    @BeforeEach
    public void initialize(){
       exhibitionsManager= new ExhibitionManager();
        Mockito.mock(ExhibitionManager.class);
        e = new Exhibitions();

        e.setExhibition_name("Early work of da Vinci");
        e.setStart_date(Date.valueOf(LocalDate.now()));
       // e.setEnd_date(21.4.2023.);
        e.setLocation("London");

        exhibitionsSql = Mockito.mock(ExhibitionsSQLImplementation.class);
        exhibitions = new ArrayList<>();
      //  exhibitions.addAll(Arrays.asList(new Exhibitions(50,"Painter's Golden Age", Date.valueOf(LocalDate.now()),"5.6.2023","Paris"));

    }

    @Test void validateName(){
        String name = "";
        assertThrows( DBException.class, ()->exhibitionsManager.validateExhibition_name(name), "Exhibition name cannot be longer than 50 or shorter than 1 character!");
    }

    @Test void validateLocation(){
        String loc = "";
        assertThrows( DBException.class, ()->exhibitionsManager.validateLocation(loc), "Location name cannot be longer than 50 or shorter than 1 character!");
    }

    @Test void validateStart_date(){
        LocalDate date = LocalDate.of(-2025, 1, 1);
        assertThrows( DBException.class, ()->exhibitionsManager.validateEnd_date(date), "Invalid date!");
    }
    @Test void validateEnd_date(){
        LocalDate date = LocalDate.of(2020, 1, 1);
        assertThrows( DBException.class, ()->exhibitionsManager.validateEnd_date(date), "Invalid date!");
    }

    @Test
    void add() throws DBException {
        Exhibitions e = new Exhibitions();
        e.setExhibition_name("Early work of da Vinci");
        LocalDate s_date = LocalDate.of(2023, 1, 1);
        e.setStart_date(Date.valueOf(s_date));
        LocalDate e_date = LocalDate.of(2023, 3, 1);
        e.setEnd_date(Date.valueOf(e_date));
        e.setLocation("London");

        Exhibitions result = exhibitionsManager.add(e);
        assertEquals(e,result);
    }







}

