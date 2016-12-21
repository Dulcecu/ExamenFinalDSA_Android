package dsa.eetac.upc.edu.pruebaexamen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Turpitude on 19/12/2016.
 */

public class Menu extends AppCompatActivity {
    public String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        name=getIntent().getExtras().getString("user");
        Toast.makeText(getApplicationContext(), name,
                Toast.LENGTH_SHORT).show();

    }
    public void onClick1(View v){
        Intent intent = new Intent(getApplicationContext(), Lista.class);
        intent.putExtra("user", name);
        startActivity(intent);

    }
    public void onClick2(View v){

        Intent intent = new Intent(getApplicationContext(), Insertar.class);
        intent.putExtra("user", name);
        startActivity(intent);
    }
    public void onClick4(View v){

        final EditText editText =(EditText) findViewById(R.id.editText3);
        String name= editText.getText().toString();
        Intent intent = new Intent(getApplicationContext(), Lista.class);
        intent.putExtra("user", name);
        startActivity(intent);

    }

}
