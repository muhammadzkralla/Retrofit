package com.dimits.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView id;
    TextView user_id;
    TextView title;
    TextView body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_id = (TextView)findViewById(R.id.user_id);
        id = (TextView)findViewById(R.id.id);
        title = (TextView)findViewById(R.id.title);
        body = (TextView)findViewById(R.id.body);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IapiInterface iapiInterface = retrofit.create(IapiInterface.class);

        Call<Post> call = iapiInterface.getPost();

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                title.setText(response.body().getTitle());
               String id_S = String.valueOf(response.body().getId());
                String user_id_S = String.valueOf(response.body().getUserId());
                id.setText(id_S);
                user_id.setText(user_id_S);
                body.setText(response.body().getBody());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                title.setText(t.getMessage());

            }
        });


    }
}
