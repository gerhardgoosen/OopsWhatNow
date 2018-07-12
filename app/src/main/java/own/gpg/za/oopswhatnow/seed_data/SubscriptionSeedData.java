package own.gpg.za.oopswhatnow.seed_data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import own.gpg.za.oopswhatnow.data.structure.pojo.Subscription;
import own.gpg.za.oopswhatnow.data.utils.EntityManager;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class SubscriptionSeedData {

    /**
     * An array of sample (dummy) items.
     */
    public static List<Subscription> ITEMS = new ArrayList<Subscription>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Subscription> ITEM_MAP = new HashMap<String, Subscription>();
   public EntityManager entityManager;

    public static final String TRACK_AND_NOTIFY = "911 Track And Notify";
    public static final String TRACK_AND_NOTIFY_DESCR = "Share your location with your friends or family members.\nNotify them if you are in trouble by pressing the panic button,\n or let your friends track your location.";
    public static final String REPORT_MUNICIPAL_PROBLEM = "Report Municipal Problem";
    public static final String REPORT_MUNICIPAL_PROBLEM_DESCR = "Report any Municipal problems here, we will geotag your location, upload a photo and give us a short description of the problem.";


    public void doImport(EntityManager em) {
        // Add some sample items.
        addSubscriptionItem(createSubscriptionItem(em, TRACK_AND_NOTIFY, TRACK_AND_NOTIFY_DESCR, 50.0, "own.gpg.za.oopswhatnow.activity.subscriptions.map.TrackAndNotifyActivity",false));
        addSubscriptionItem(createSubscriptionItem(em, REPORT_MUNICIPAL_PROBLEM, REPORT_MUNICIPAL_PROBLEM_DESCR, 0.0, "own.gpg.za.oopswhatnow.activity.subscriptions.map.ReportMunicipalProblemActivity",false));

    }


    public static void addSubscriptionItem(Subscription item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Subscription createSubscriptionItem(EntityManager em, String name, String description , Double price , String activityClass, boolean active ) {

        return em.saveSubscription(name, makeDetails(description,price),price,activityClass,active);

    }



    private static String makeDetails(String description, Double price) {
        StringBuilder builder = new StringBuilder();

        builder.append(description);

        if(price > 0.0){
            builder.append("\n\n");
            builder.append("Price Per Year R " + price);
        }else{
            builder.append("\n\n");
            builder.append("Free Service");
        }



        return builder.toString();
    }



}
