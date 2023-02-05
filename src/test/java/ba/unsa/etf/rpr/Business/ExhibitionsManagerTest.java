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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class ExhibitionsManagerTest {
    private ExhibitionManager exhibitionManager;
    private Exhibitions e;
    private ExhibitionsSQLImplementation employeeDAOSQL;
    private List<Exhibitions> employees;

    }