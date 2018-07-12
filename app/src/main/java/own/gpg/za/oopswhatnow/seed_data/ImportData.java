package own.gpg.za.oopswhatnow.seed_data;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import own.gpg.za.oopswhatnow.data.structure.pojo.Subscription;
import own.gpg.za.oopswhatnow.data.utils.EntityManager;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ImportData {

    private final static String TAG = "ImportData";

    private Context mContext;

    private EntityManager entityManager;

    private SubscriptionSeedData subscriptionSeedData;


    public ImportData(Context context) {
        super();
        this.mContext = context;
        entityManager = new EntityManager(this,this.mContext);
        subscriptionSeedData = new SubscriptionSeedData();
    }


    public void doImport(){

        List<Subscription> subs = entityManager.findSubscriptions();

        if(subs.size()>0){
            Log.v(TAG,"Subscriptions Seeded");
            subscriptionSeedData.ITEMS = new ArrayList<>();
            for(Subscription s : subs){

                Log.v(TAG,"sub : " + s.content + " - "  + s.type) ;
                subscriptionSeedData.addSubscriptionItem(s);
            }
            Log.v(TAG,"Subscriptions Repopulated");
        }else{
            Log.v(TAG,"Seeding Subscriptions Data");
            subscriptionSeedData.doImport(entityManager);
            Log.v(TAG,"Subscriptions Imported");
        }

    }

}
