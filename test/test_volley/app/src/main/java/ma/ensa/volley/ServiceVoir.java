package ma.ensa.volley;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ma.ensa.volley.adapter.ServiceAdapter;
import ma.ensa.volley.beans.Service;

public class ServiceVoir  extends AppCompatActivity {
    private ListView idserviceListView;
    private ServiceAdapter serviceAdapter;
    private RequestQueue requestQueue;
    Button backButtonServiceAdd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sevice_lists);
        backButtonServiceAdd = findViewById(R.id.backButtonServiceAdd);
        backButtonServiceAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceVoir.this, ServiceAdd.class);
                startActivity(intent);
            }
        });





        idserviceListView = findViewById(R.id.idServiceListView);

        fetchServicesFromServer();



    }

    private void fetchServicesFromServer() {
        String fetchUrl = "http://10.0.2.2:8088/api/v1/services";
        requestQueue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, fetchUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("oki", response);
                List<Service> services = parseServiceData(response);

                serviceAdapter = new ServiceAdapter(ServiceVoir.this, services);
                idserviceListView.setAdapter(serviceAdapter);

                idserviceListView.setOnItemClickListener((parent, view, position, id) -> {
                    showActionDialog(services.get(position));
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error here
            }
        });

        requestQueue.add(request);
    }
    private List<Service> parseServiceData(String response) {
        List<Service> services = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");

                String name = jsonObject.getString("nom");


                Service service = new Service((long) id, name);
                services.add(service);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return services;
    }

    private void showActionDialog(final Service service) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustomStyle);
        builder.setTitle("Actions")
                .setItems(new CharSequence[]{"Update", "Delete"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            showUpdateDialog(service);
                        } else {
                           showDeleteConfirmationDialog(service.getId());
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void showDeleteConfirmationDialog(final Long serviceId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Confirm the delete")
                .setPositiveButton("Delete", (dialog, which) -> {
                    deleteService(serviceId);
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    dialog.dismiss();
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteService(Long serviceId) {
        String deleteUrl = "http://10.0.2.2:8088/api/v1/services/"+serviceId;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.DELETE, deleteUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ServiceVoir.this, "ROLE deleted successfully", Toast.LENGTH_LONG).show();

                fetchServicesFromServer();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ServiceVoir.this, "Error deleting ROLE", Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(request);
    }

    private void showUpdateDialog(final Service service) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update Service Information");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);



        final EditText nameInput = new EditText(this);
        nameInput.setHint("Name");
        nameInput.setText(service.getNom());
        layout.addView(nameInput);


        builder.setView(layout);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String newName = nameInput.getText().toString();


                service.setNom(newName);


                sendUpdateRequest(service);

                serviceAdapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void sendUpdateRequest(final Service service) {
        String updateUrl = "http://10.0.2.2:8088/api/v1/services/" + service.getId();
        JSONObject jsonbody = new JSONObject();
        try {

            jsonbody.put("name", service.getNom());  } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, updateUrl, jsonbody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(ServiceVoir.this, "service updated successfully", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ServiceVoir.this, "Error updating service", Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(request);
    }



}