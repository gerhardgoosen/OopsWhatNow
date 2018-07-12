package own.gpg.za.oopswhatnow.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import own.gpg.za.oopswhatnow.R;

public class RegistrationActivity extends AppCompatActivity {

    private final static String TAG = "RegistrationActivity";

    private List<JSONObject> invites;
    private boolean addedFriendsTable = false;
    private Context mContext;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.mContext = this;


        invites = new ArrayList<>();


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Please wait... ", Snackbar.LENGTH_LONG).show();
                        //.setAction("Action", null).show();

                //startActivity(new Intent(getApplicationContext(),SubscriptionListActivity.class));
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        fab.setEnabled(false);

        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDark)));


        Button chooseContactButton = (Button) findViewById(R.id.choose_contacts_button);
        chooseContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!addedFriendsTable) {
                    buildFriendTableHeader((TableLayout) findViewById(R.id.reg_friend_table));
                }

                try {
                    JSONObject contactJSON = new JSONObject();
                    contactJSON.put("Name", "Friend " );

                    invites.add(contactJSON);

                    buildTableRow((TableLayout) findViewById(R.id.reg_friend_table), contactJSON);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (invites.size() >= 5) {
                    fab.setEnabled(true);
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));

                    Snackbar.make(view, "Click Register", Snackbar.LENGTH_LONG).show();
                } else {
                    fab.setEnabled(false);
                }


            }
        });


    }


    private void buildFriendTableHeader(TableLayout table) {
        Log.v(TAG, "buildFriendTableHeader :  " + addedFriendsTable);
        TableRow th = new TableRow(mContext);

        TextView friendNameHead = new TextView(mContext);
        friendNameHead.setText(" Name ");
        friendNameHead.setTextColor(Color.WHITE);
        th.addView(friendNameHead);

        TextView removeNameHead = new TextView(mContext);
        removeNameHead.setText(" Action ");
        removeNameHead.setTextColor(Color.WHITE);
        th.addView(removeNameHead);

        table.addView(th);
        addedFriendsTable = true;
        Log.v(TAG, "buildFriendTableHeader : done   " + addedFriendsTable);
    }


    private void buildTableRow(final TableLayout table, final JSONObject row) {
        Log.v(TAG, "buildTableRow : " + row.toString());

        final TableRow tr = new TableRow(mContext);

        TextView friendNameHead = new TextView(mContext);

        try {
            friendNameHead.setText(row.getString("Name"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        friendNameHead.setTextColor(Color.WHITE);
        tr.addView(friendNameHead);

        Button removeFriendButton = new Button(mContext);
        removeFriendButton.setText(" Remove ");
        removeFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.v(TAG, "remove : " + row.get("Name"));
                    invites.remove(row);
                    table.removeView(tr);

                    if (invites.size() >= 5) {
                        fab.setEnabled(true);
                        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));

                        Snackbar.make(v, "Click Send to Register", Snackbar.LENGTH_LONG).show();
                    } else {
                        fab.setEnabled(false);
                        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryDark)));
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        tr.addView(removeFriendButton);

        table.addView(tr);


    }
}
