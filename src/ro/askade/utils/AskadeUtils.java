package ro.askade.utils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by AdrianIonita on 3/31/2017.
 */
public class AskadeUtils {
    public static final String CREATED_BY = "createdBy";
    public static final String CREATION_DATE = "creationDate";
    public static final String LAST_UPDATED_BY = "lastUpdatedBy";
    public static final String LAST_UPDATE_DATE = "lastUpdateDate";
    public static final String ENTITY_NAME = "entityName";
    public static Date getCurrentDate(){
        java.util.Date date = new java.util.Date();
        return date;
    }

    public static Timestamp getCurrentTimestamp(){
        java.util.Date date = new java.util.Date();
        return new Timestamp(date.getTime());
    }
}
