package own.gpg.za.oopswhatnow.data.structure.helper;

import own.gpg.za.oopswhatnow.data.structure.SubscriptionContract.SubscriptionEntry;

/**
 * Created by Gerhard on 2016/10/05.
 */

public class SubscriptionHelper {
    protected static final String TAG = "SubscriptionHelper";

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + SubscriptionEntry.TABLE_NAME
            + " ("
            + SubscriptionEntry._ID + " INTEGER PRIMARY KEY,"
            + SubscriptionEntry.COLUMN_NAME_name + "  TEXT NOT NULL,"
            + SubscriptionEntry.COLUMN_NAME_details + "  TEXT NOT NULL,"
            + SubscriptionEntry.COLUMN_NAME_type + "  TEXT NOT NULL,"
            + SubscriptionEntry.COLUMN_NAME_price + "  DOUBLE NOT NULL, "
            + SubscriptionEntry.COLUMN_NAME_active + "  TEXT NOT NULL "
            + ")";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + SubscriptionEntry.TABLE_NAME;


    public static final String SQL_DELETE_ENTRIES =
            "DELETE FROM " + SubscriptionEntry.TABLE_NAME;


}
