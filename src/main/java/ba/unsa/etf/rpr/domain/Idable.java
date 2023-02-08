package ba.unsa.etf.rpr.domain;

/**
 * @author Amina Hromic
 * interface used to ensure that every table has a uniform name for id ("id")
 */
public interface Idable {
     void setId(int id);
        int getId();
}
