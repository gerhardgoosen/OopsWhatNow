package own.gpg.za.oopswhatnow.data.structure;

import android.provider.BaseColumns;

/**
 * Created by Gerhard on 2016/10/03.
 */

public final class SubscriptionContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private SubscriptionContract() {
    }

    /* Inner class that defines the table contents */
    public static class SubscriptionEntry implements BaseColumns {
        public static final String TABLE_NAME = "subscriptions";
        public static final String COLUMN_NAME_name = "name";
        public static final String COLUMN_NAME_details = "details";
        public static final String COLUMN_NAME_type = "type";
        public static final String COLUMN_NAME_price = "price";
        public static final String COLUMN_NAME_active = "active";
    }
}