package com.practice.bmi;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String baseURL = "https://jsonplaceholder.typicode.com/users";
    ArrayList<String> arryName = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        AndroidNetworking.initialize(this);

        /* For GET Method */
        AndroidNetworking.get(baseURL).setPriority(Priority.HIGH).build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("MainActivity Res:", response.toString());
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObjectRes = response.getJSONObject(i);
                                String name = jsonObjectRes.getString("name");
                                String userName = jsonObjectRes.getString("username");
                                arryName.add(name + ", " + userName);
                            }
                            ArrayAdapter<String> nameAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arryName);
                            listView.setAdapter(nameAdapter);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("MainActivity Error:", anError.toString());
                    }
                });

    }
}