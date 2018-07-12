package own.gpg.za.oopswhatnow.data.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.location.Location;
import android.provider.Settings;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import own.gpg.za.oopswhatnow.data.structure.LocationContract.LocationEntry;
import own.gpg.za.oopswhatnow.data.structure.SubscriptionContract.SubscriptionEntry;
import own.gpg.za.oopswhatnow.data.structure.pojo.Subscription;

/**
 * Created by Gerhard on 2016/10/05.
 */

public class EntityManager {

    protected static final String TAG = "EntitiyManager";

    private Object mCallerActivity;
    private Context mContext;
    /**
     * DB
     */
    private DBContentProvider dbContentProvider;


    public EntityManager(Object activity, Context context) {
        super();
        this.mContext = context;
        this.mCallerActivity = activity;
        dbContentProvider = new DBContentProvider(this.mContext);
        Log.v(TAG, "EntityManager");
    }


    //LOCATION
    public boolean saveLocation(Location loc) {

        try {
            // Gets the data repository in write mode
            // SQLiteDatabase db = mLocationDbHelper.getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(LocationEntry.COLUMN_NAME_latitude, loc.getLatitude());
            values.put(LocationEntry.COLUMN_NAME_longitude, loc.getLongitude());
            values.put(LocationEntry.COLUMN_NAME_altitude, loc.getAltitude());
            values.put(LocationEntry.COLUMN_NAME_accuracy, loc.getAccuracy());
            values.put(LocationEntry.COLUMN_NAME_speed, loc.getSpeed());
            values.put(LocationEntry.COLUMN_NAME_bearing, loc.getBearing());
            values.put(LocationEntry.COLUMN_NAME_provider, loc.getProvider());
            values.put(LocationEntry.COLUMN_NAME_time, loc.getTime());
            values.put(LocationEntry.COLUMN_NAME_elapsedRealtimeNanos, loc.getElapsedRealtimeNanos());
            values.put(LocationEntry.COLUMN_NAME_capturedDate, new Date().toString());


            this.dbContentProvider.insert(LocationEntry.TABLE_NAME, values);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    public JSONObject findAllLocations(String where, String[] whereArgs) {

        String[] projection = {
                LocationEntry._ID,
                LocationEntry.COLUMN_NAME_latitude,
                LocationEntry.COLUMN_NAME_longitude,
                LocationEntry.COLUMN_NAME_altitude,
                LocationEntry.COLUMN_NAME_accuracy,
                LocationEntry.COLUMN_NAME_speed,
                LocationEntry.COLUMN_NAME_bearing,
                LocationEntry.COLUMN_NAME_provider,
                LocationEntry.COLUMN_NAME_time,
                LocationEntry.COLUMN_NAME_elapsedRealtimeNanos,
                LocationEntry.COLUMN_NAME_capturedDate
        };
        String selection = "";
        String[] selectionArgs = null;

        if (where != null) {
            selection = where;
        } else {
            selection = LocationEntry.COLUMN_NAME_accuracy + " <= ?";
        }
        if (whereArgs != null) {
            selectionArgs = whereArgs;
        } else {
            selectionArgs = new String[]{"100"};
        }

        // How you want the results sorted in the resulting Cursor
        String sortOrder = LocationEntry.COLUMN_NAME_time + " ASC";


        Cursor c = this.dbContentProvider.query(LocationEntry.TABLE_NAME, projection, selection, selectionArgs, sortOrder);


        return CursorToJSON(c);

    }



    //SUBSCRIPTIONS
    public Subscription saveSubscription(String name, String details, Double price, String type, boolean active) {

        try {

            ContentValues sub = new ContentValues();
            sub.put(SubscriptionEntry.COLUMN_NAME_name, name);
            sub.put(SubscriptionEntry.COLUMN_NAME_details, details);
            sub.put(SubscriptionEntry.COLUMN_NAME_price, price);
            sub.put(SubscriptionEntry.COLUMN_NAME_type, type);
            sub.put(SubscriptionEntry.COLUMN_NAME_active, Boolean.valueOf(active).toString());

            Long subId = this.dbContentProvider.insert(SubscriptionEntry.TABLE_NAME, sub);

            Subscription retSub = new Subscription(subId.toString(), name, details, price, type, active);

            return retSub;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateSubscription(Subscription subscription){
        Log.v(TAG,"updateSubscription");
        ContentValues sub = new ContentValues();
        sub.put(SubscriptionEntry.COLUMN_NAME_name, subscription.content );
        sub.put(SubscriptionEntry.COLUMN_NAME_details, subscription.details);
        sub.put(SubscriptionEntry.COLUMN_NAME_price, subscription.price);
        sub.put(SubscriptionEntry.COLUMN_NAME_type, subscription.type);
        sub.put(SubscriptionEntry.COLUMN_NAME_active, Boolean.valueOf(subscription.active).toString());

        String selection = LocationEntry._ID + " = ?";
        String[] selectionArgs = new String[]{subscription.id};

        int currId = Integer.parseInt(subscription.id);
        int subId = this.dbContentProvider.update(SubscriptionEntry.TABLE_NAME, sub,selection,selectionArgs);

        if(subId == currId){
            return  true;
        }else {
            return false;
        }


    }

    public List<Subscription> findSubscriptions() {

        String[] projection = {
                SubscriptionEntry._ID,
                SubscriptionEntry.COLUMN_NAME_name,
                SubscriptionEntry.COLUMN_NAME_details,
                SubscriptionEntry.COLUMN_NAME_type,
                SubscriptionEntry.COLUMN_NAME_price,
                SubscriptionEntry.COLUMN_NAME_active};

        // Filter results WHERE "title" = 'My Title'
        String selection = null;
        String[] selectionArgs = null;
        String groupByArgs = null;
        String filterArgs = null;

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                SubscriptionEntry.COLUMN_NAME_name + " DESC";


        Cursor c = this.dbContentProvider.query(SubscriptionEntry.TABLE_NAME, projection, selection, selectionArgs, sortOrder);

        return CursorToSubscriptionList(c);

    }

    private List<Subscription> CursorToSubscriptionList(Cursor c) {
        Log.v(TAG, "CursorToSubscriptionList " + c.getCount());
        ArrayList<Subscription> list = new ArrayList<>();
        c.moveToFirst();
        for (int row = 0; row < c.getCount(); row++) {
            String _id = "", name = "", details = "", type = "";
            Double price = 0.0;
            Boolean active = false;

            Log.v(TAG, "row " + row);

            for (int col = 0; col < c.getColumnCount(); col++) {

                Log.v(TAG, " col name : " + c.getColumnName(col));
                Log.v(TAG, " col val :" + c.getString(col));

                if (c.getColumnName(col).equalsIgnoreCase(SubscriptionEntry._ID)) {
                    _id = c.getString(col);
                }
                if (c.getColumnName(col).equalsIgnoreCase(SubscriptionEntry.COLUMN_NAME_name)) {
                    name = c.getString(col);
                }
                if (c.getColumnName(col).equalsIgnoreCase(SubscriptionEntry.COLUMN_NAME_details)) {
                    details = c.getString(col);
                }
                if (c.getColumnName(col).equalsIgnoreCase(SubscriptionEntry.COLUMN_NAME_type)) {
                    type = c.getString(col);
                }
                if (c.getColumnName(col).equalsIgnoreCase(SubscriptionEntry.COLUMN_NAME_price)) {
                    price = c.getDouble(col);
                }
                if (c.getColumnName(col).equalsIgnoreCase(SubscriptionEntry.COLUMN_NAME_active)) {
                    active = Boolean.parseBoolean(c.getString(col));
                }
            }

            Subscription sub = new Subscription(_id, name, details, price, type, active);
            list.add(sub);

            c.moveToNext();
        }

        return list;
    }


    private JSONObject CursorToJSON(Cursor c) {
        Log.v(TAG, "CursorToJSON");
        JSONObject return_data = new JSONObject();
        try {
            JSONObject data = new JSONObject();
            c.moveToFirst();

            for (int row = 0; row < c.getCount(); row++) {
                JSONObject row_item = new JSONObject();

                for (int col = 0; col < c.getColumnCount(); col++) {
                    String colName = c.getColumnName(col);
                    try {
                        row_item.put(colName, c.getString(col));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    JSONObject rowdata = new JSONObject();
                    rowdata.put("index", row);
                    rowdata.put("row", row_item);
                    data.put("row_" + row, rowdata);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                c.moveToNext();
            }


            try {
                return_data.put("data", data);
            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return return_data;
    }


}
