package ma.ensa.volley.adapter;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ma.ensa.volley.R;
import ma.ensa.volley.beans.Service;

public class ServiceAdapter extends ArrayAdapter<Service> {
    private Context context;
    private List<Service> services;

    public ServiceAdapter(Context context, List<Service> services) {
        super(context, 0, services);
        this.context = context;
        this.services = services;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.service_item, parent, false);
        }


        Service role1 = services.get(position);

        TextView nameTextView = convertView.findViewById(R.id.nomTextservice);

        nameTextView.setText(role1.getNom());

        return convertView;
    }

}
