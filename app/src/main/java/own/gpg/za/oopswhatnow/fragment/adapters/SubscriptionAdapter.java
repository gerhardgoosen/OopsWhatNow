package own.gpg.za.oopswhatnow.fragment.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import own.gpg.za.oopswhatnow.data.structure.pojo.Subscription;

public class SubscriptionAdapter extends ArrayAdapter<Subscription> {

    ViewHolder viewHolder;

    private static class ViewHolder {
        private TextView itemView;
    }

    public SubscriptionAdapter(Context context, int textViewResourceId, ArrayList<Subscription> items) {
        super(context, textViewResourceId, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
            .inflate(android.R.layout.simple_list_item_1, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.itemView = (TextView) convertView.findViewById(android.R.id.text1);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Subscription item = getItem(position);
        if (item!= null) {

            viewHolder.itemView.setText(  item.content );
        }

        return convertView;
    }
}