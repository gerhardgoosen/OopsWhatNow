package own.gpg.za.oopswhatnow.fragment.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import own.gpg.za.oopswhatnow.data.structure.pojo.Subscription;
import own.gpg.za.oopswhatnow.data.utils.EntityManager;

public class SubscriptionDialogFragment extends DialogFragment {

    private Subscription subscription;
    private EntityManager entityManager;

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void subscribe() {
        if (this.entityManager != null && this.subscription != null) {
            try {
                this.subscription.active=true;
                entityManager.updateSubscription(this.subscription);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void open() {
        if (this.subscription != null) {
            try {
                Class act = Class.forName(this.subscription.type);
                startActivity(new Intent(getContext(), act));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sample() {
        if (this.subscription != null) {
            try {
                Class act = Class.forName(this.subscription.type);
                 Intent  actIntent = new Intent(getContext(), act);
                actIntent.putExtra("SAMPLE_TIMEOUT",20000L); // 60000L = 1 minute timeout//
                startActivity(actIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(this.subscription.content);
        builder.setMessage(this.subscription.details);


        if(this.subscription.price > 0.0){
            builder.setNeutralButton("Subscribe", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    subscribe();
                }
            });
            builder.setPositiveButton("Sample", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sample();
                }
            });

        }else{
            builder.setPositiveButton("Open", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    open();
                }
            });

        }

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return builder.create();
    }
}