package com.example.airy_receipt;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RecyclerView.LayoutManager lm;
    private List<Recipe> recipes = new ArrayList<>();
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.recyclerview);
        //lm = new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        rv.setHasFixedSize(true);
        loadJson();

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.center_action_bar);
    }

    public void loadJson(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<DataJson> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<DataJson>() {
            @Override
            public void onResponse(Call<DataJson> call, Response<DataJson> response) {
                if(response.isSuccessful() && response.body().getRecipe() != null){
                    if(!recipes.isEmpty()){
                        recipes.clear();
                    }

                    recipes = response.body().getRecipe();

                    Collections.sort(recipes, Recipe.ByRating);

                    List<Recipe> obj = new ArrayList<>();

                    //Log.d("Value: ", "Tes : " + recipes.get(1));
                    for(int j=0; j<recipes.size();j++){
                        for (int y=0; y<recipes.get(j).getIngredients().size(); y++){
                            if(recipes.get(j).getIngredients().get(y).equals("Chicken")){
                                //Log.d("Value: ", "Tes : " + recipes.get(j).getIngredients().get(y) + recipes.get(j).getRating() + recipes.get(j).getName());
                                obj.add(recipes.get(j));
                                adapter = new RecyclerViewAdapter(obj, MainActivity.this);
                                rv.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }

//                    adapter = new RecyclerViewAdapter(recipes, MainActivity.this);
//                    rv.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<DataJson> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
