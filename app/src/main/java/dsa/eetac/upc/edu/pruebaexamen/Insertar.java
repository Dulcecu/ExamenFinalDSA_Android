package dsa.eetac.upc.edu.pruebaexamen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.entity.mime.Header;
/**
 * Created by Turpitude on 21/12/2016.
 */
public class Insertar extends AppCompatActivity {
    public String name2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name2=getIntent().getExtras().getString("user");
        setContentView(R.layout.activity_insertar);

    }

    public void post (View v) throws JSONException, UnsupportedEncodingException {
        final EditText name =(EditText) findViewById(R.id.name);
        final EditText description =(EditText) findViewById(R.id.description);
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("name", name.getText().toString());
        jsonParams.put("description",description.getText().toString());
        StringEntity entity = new StringEntity(jsonParams.toString());
        MainActivity.RestClient.post(getApplicationContext(), "/myapp/json/new/"+name2, entity, "application/json",
                new TextHttpResponseHandler(){

                    @Override
                    public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                        if(statusCode==500) {
                            Toast.makeText(getApplicationContext(), "Error al insertar",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String statCode) {
                        if(statCode.equals("200")) {
                            Toast.makeText(getApplicationContext(), "Etakemon insertado correctamente",
                                    Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Error al insertar",
                                    Toast.LENGTH_LONG).show();
                        }
                    }




                });


        }
    public void postOther (View v) throws JSONException, UnsupportedEncodingException {
        final EditText name =(EditText) findViewById(R.id.name);
        final EditText description =(EditText) findViewById(R.id.description);
        final EditText otherName =(EditText) findViewById(R.id.otheruser);
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("name", name.getText().toString());
        jsonParams.put("description",description.getText().toString());
        StringEntity entity = new StringEntity(jsonParams.toString());
        MainActivity.RestClient.post(getApplicationContext(), "/myapp/json/new/"+otherName.getText().toString(), entity, "application/json",
                new TextHttpResponseHandler(){

                    @Override
                    public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                        if(statusCode==500) {
                            Toast.makeText(getApplicationContext(), "Error al insertar",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String statCode) {
                        if(statCode.equals("200")) {
                            Toast.makeText(getApplicationContext(), "Etakemon insertado correctamente",
                                    Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Error al insertar",
                                    Toast.LENGTH_LONG).show();
                        }
                    }




                });


    }
    }


