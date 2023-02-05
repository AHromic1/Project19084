package ba.unsa.etf.rpr.Business;

import ba.unsa.etf.rpr.business.ExhibitionManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.ExhibitionsSQLImplementation;
import ba.unsa.etf.rpr.domain.Exhibitions;
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

import static org.mockito.Mockito.when;

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
        e.setEnd_date(21.4.2023.);
        e.setLocation("London");

        exhibitionsSql = Mockito.mock(ExhibitionsSQLImplementation.class);
        exhibitions = new ArrayList<>();
        exhibitions.addAll(Arrays.asList(new Exhibitions(50,"Painter's Golden Age", Date.valueOf(LocalDate.now()),"5.6.2023","Paris"));

    }

    }