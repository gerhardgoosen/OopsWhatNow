package own.gpg.za.oopswhatnow.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;

import own.gpg.za.oopswhatnow.R;
import own.gpg.za.oopswhatnow.data.structure.pojo.Subscription;
import own.gpg.za.oopswhatnow.data.utils.EntityManager;
import own.gpg.za.oopswhatnow.fragment.adapters.SubscriptionAdapter;
import own.gpg.za.oopswhatnow.fragment.dialogs.SubscriptionDialogFragment;
import own.gpg.za.oopswhatnow.fragment.interfaces.OnSubscriptionFragmentInteractionListener;

/**
 * A simple {@link  android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link  OnSubscriptionFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SubscriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubscriptionFragment extends ListFragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnSubscriptionFragmentInteractionListener mListener;


    private  EntityManager entityManager;
    private ArrayList<Subscription> subscriptionList;
    private Context mContext;

    public SubscriptionFragment() {

        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        entityManager = new EntityManager(this,getContext());
        subscriptionList = (ArrayList<Subscription>)entityManager.findSubscriptions();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subscriptions, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);


        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Planets, android.R.layout.simple_list_item_1);
        //setListAdapter(adapter);

        SubscriptionAdapter subscriptionAdapter = new SubscriptionAdapter(getContext(),android.R.layout.simple_list_item_1,subscriptionList);
        setListAdapter(subscriptionAdapter);
        getListView().setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        final Subscription item = subscriptionList.get(position);


        if(item.active) {
            //open subscription activity class
            try {
                Class act = Class.forName(item.type);
                startActivity(new Intent(getContext(), act));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            //show dialog
            FragmentManager fm = getFragmentManager();
            SubscriptionDialogFragment dialogFragment = new SubscriptionDialogFragment();
            dialogFragment.setSubscription(item);
            dialogFragment.setEntityManager(this.entityManager);
            dialogFragment.show(fm, "SubscriptionDialog");
        }
    }




    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubscriptionFragment newInstance(String param1, String param2) {
        SubscriptionFragment fragment = new SubscriptionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onDashboardFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        if (context instanceof OnSubscriptionFragmentInteractionListener) {
            mListener = (OnSubscriptionFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSubscriptionFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
