package dsa.eetac.upc.edu.pruebaexamen;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static class RestClient {
        private static final String BASE_URL = "http://10.193.170.131:8081";
        private static AsyncHttpClient client = new AsyncHttpClient();


        public static void get(String url, AsyncHttpResponseHandler responseHandler) {
            client.get(getAbsoluteUrl(url), responseHandler);

        }

        public static void post(Context context, String url, StringEntity entity, String c, AsyncHttpResponseHandler responseHandler) {

            client.post(context, getAbsoluteUrl(url), entity,c, responseHandler);
        }

        private static String getAbsoluteUrl(String relativeUrl) {
            return BASE_URL + relativeUrl;
        }
    }

    public void onClick3 (View v) throws JSONException, UnsupportedEncodingException {
        final EditText user =(EditText) findViewById(R.id.editText);
        final EditText password =(EditText) findViewById(R.id.editText2);
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("name", user.getText().toString());
        jsonParams.put("password",password.getText().toString());
        StringEntity entity = new StringEntity(jsonParams.toString());
        RestClient.post(getApplicationContext(), "/myapp/json/Login", entity, "application/json",
                new TextHttpResponseHandler(){

                    @Override
                    public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                        if(statusCode==500) {
                            Toast.makeText(getApplicationContext(),responseString,
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String statCode) {
                        if(statCode.equals("200")) {
                            Toast.makeText(getApplicationContext(), "Bienvenido",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Menu.class);
                            //Añade informacion extra el intent para pasarlo a la otra vista
                            intent.putExtra("user", user.getText().toString());
                            //Inicia la activity esperando un resultado( nueva vista)
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Usuario no existente",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }
    public void onClick4 (View v) throws JSONException, UnsupportedEncodingException {
        final EditText user =(EditText) findViewById(R.id.editText);
        final EditText password =(EditText) findViewById(R.id.editText2);
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("name", user.getText().toString());
        jsonParams.put("password",password.getText().toString());
        StringEntity entity = new StringEntity(jsonParams.toString());
        RestClient.post(getApplicationContext(), "/myapp/json/Register", entity, "application/json",
                new TextHttpResponseHandler(){

                    @Override
                    public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {

                            Toast.makeText(getApplicationContext(), "Usuario ya existente",
                                    Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String statCode) {
                        if(statCode.equals("200")) {
                            Toast.makeText(getApplicationContext(), "Usuario creado correctamente",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Menu.class);
                            //Añade informacion extra el intent para pasarlo a la otra vista
                            intent.putExtra("user", user.getText().toString());
                            //Inicia la activity esperando un resultado( nueva vista)
                            startActivity(intent);
                        }
                        else{ Toast.makeText(getApplicationContext(), "Usuario ya existente",
                                Toast.LENGTH_SHORT).show();}

                    }

                });

    }

}
