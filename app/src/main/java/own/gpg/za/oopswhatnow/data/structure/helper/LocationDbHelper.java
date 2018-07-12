package own.gpg.za.oopswhatnow.data.structure.helper;

import own.gpg.za.oopswhatnow.data.structure.LocationContract.LocationEntry;

public class LocationDbHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;


    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + LocationEntry.TABLE_NAME
            + " ( "
            +  LocationEntry._ID + " INTEGER PRIMARY KEY , "
            +  LocationEntry.COLUMN_NAME_latitude + "  TEXT , "
            +  LocationEntry.COLUMN_NAME_longitude + "  TEXT , "
            +  LocationEntry.COLUMN_NAME_altitude + "  TEXT , "
            +  LocationEntry.COLUMN_NAME_accuracy + "  TEXT , "
            +  LocationEntry.COLUMN_NAME_speed + "  TEXT , "
            +  LocationEntry.COLUMN_NAME_bearing + "  TEXT , "
            +  LocationEntry.COLUMN_NAME_provider + "  TEXT , "
            +  LocationEntry.COLUMN_NAME_time + "  TEXT , "
            +  LocationEntry.COLUMN_NAME_elapsedRealtimeNanos + "  TEXT , "
            +  LocationEntry.COLUMN_NAME_capturedDate + "  TEXT , "
            +  LocationEntry.COLUMN_NAME_routeId + "  INTEGER "
            + " )";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + LocationEntry.TABLE_NAME;

    public static final String SQL_DROP_ALL =
            "DELETE FROM " + LocationEntry.TABLE_NAME;


}
