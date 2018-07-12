package own.gpg.za.oopswhatnow.data.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import own.gpg.za.oopswhatnow.data.structure.helper.LocationDbHelper;
import own.gpg.za.oopswhatnow.data.structure.helper.SubscriptionHelper;


public class DatabaseManager extends SQLiteOpenHelper {
    private static final String LOG_TAG = "OopsWhatNowDBM";
    private static final String DB_NAME = "OopsWhatNow.db";
    private static final int DATABASE_VERSION = 1;
    private Context mContext;

    public DatabaseManager(Context context){
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LocationDbHelper.SQL_CREATE_TABLE);
        db.execSQL(SubscriptionHelper.SQL_CREATE_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Here you can perform updates when the database own.gpg.za.oopswhatnow.data.structure changes
        // Begin transaction
        db.beginTransaction();

        try {
            if(oldVersion<2){
                // Upgrade database own.gpg.za.oopswhatnow.data.structure from Version 1 to 2
                String alterTable = "ALTER ....";

                db.execSQL(alterTable);
                Log.i(LOG_TAG,"Successfully upgraded to Version 2");
            }
            // This allows you to upgrade from any version to the next most 
            // recent one in multiple steps as you don't know if the user has
            // skipped any of the previous updates
            if(oldVersion<3){
                // Upgrade database own.gpg.za.oopswhatnow.data.structure from Version 2 to 3
                String alterTable = "ALTER ....";

                db.execSQL(alterTable);
                Log.i(LOG_TAG,"Successfully upgraded to Version 3");
            }

            // Only when this code is executed, the changes will be applied 
            // to the database
            db.setTransactionSuccessful();
        } catch(Exception ex){
            ex.printStackTrace();
        } finally {
            // Ends transaction
            // If there was an error, the database won't be altered
            db.endTransaction();
        }
    }
}