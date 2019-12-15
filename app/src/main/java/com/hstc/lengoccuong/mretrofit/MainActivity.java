package com.hstc.lengoccuong.mretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private HeroAdapter heroAdapter;
    private ArrayList<Hero> listHero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = findViewById(R.id.lv_hero);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<ArrayList<Hero>> call = api.getHeroes();

        call.enqueue(new Callback<ArrayList<Hero>>() {
            @Override
            public void onResponse(Call<ArrayList<Hero>> call, Response<ArrayList<Hero>> response) {

                listHero = response.body();
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        Toast.makeText(getApplicationContext(),"Position " + position,Toast.LENGTH_LONG).show();
                    }
                });
                heroAdapter = new HeroAdapter(getApplicationContext(),listHero);
                listView.setAdapter(heroAdapter);


                /*String[] heroesName = new String[listHero.size()];

                for (int i = 0;i<listHero.size();i++){
                    heroesName[i] = listHero.get(i).getName();
                }

                listView.setAdapter(new ArrayAdapter<String>(
                        getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        heroesName
                ));
*/
                /*for (Hero h: heroes){
                    Log.d("name: " , h.getName());
                    Log.d("realname: " , h.getRealname());
                    Log.d("imageurl: " , h.getImageurl());
                }*/

            }

            @Override
            public void onFailure(Call<ArrayList<Hero>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
