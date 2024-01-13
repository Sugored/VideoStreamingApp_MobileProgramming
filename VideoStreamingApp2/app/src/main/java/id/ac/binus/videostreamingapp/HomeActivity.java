package id.ac.binus.videostreamingapp;

import id.ac.binus.videostreamingapp.DateConvert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class HomeActivity extends AppCompatActivity {

    RecyclerView listVideo;
    CardAdapter adapter;
    List<Video> allVids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        allVids = new ArrayList<>();

        listVideo = findViewById(R.id.listVideo);
        listVideo.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CardAdapter(this, allVids);
        listVideo.setAdapter(adapter);

        getJsonData();
    }

    private void getJsonData(){
        String URL = "https://raw.githubusercontent.com/Sugored/myvideos-android-app/Sugored-patch-2/data.json";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                Log.d("Tag", "onResponse: " + response);
                try {
                    JSONArray categories = response.getJSONArray("categories");
                    JSONObject categoriesData = categories.getJSONObject(0);
                    JSONArray videos = categoriesData.getJSONArray("videos");

                    for(int i = 0; i< videos.length(); i++){
                        JSONObject video = videos.getJSONObject(i);
                        Video vid = new Video();

                        vid.setTitle(video.getString("title"));
                        vid.setDesc(video.getString("description"));
                        vid.setAuthor(video.getString("author"));
                        vid.setImgURL(video.getString("thumb"));
                        JSONArray vidURL = video.getJSONArray("sources");
                        vid.setVidURL(vidURL.getString(0));
                        vid.setViews(video.getString("views"));
                        vid.setDate(DateConvert.getTimeAgo(video.getString("date")));

                        allVids.add(vid);
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", "onErrorResponse: " + error.getMessage());
            }
        });

        requestQueue.add(objectRequest);
    }
}