package dsa.eetac.upc.edu.pruebaexamen;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import cz.msebera.android.httpclient.Header;
/**
 * Created by Turpitude on 21/12/2016.
 */
public class Lista extends ListActivity {
    public String name;
    HashMap<String,String> hs=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name=getIntent().getExtras().getString("user");
        try {
            getEtakemons(name);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    protected void onListItemClick(ListView l, View view, int position, long id) {
        String selectedValue =(String) getListAdapter().getItem(position);
        Intent Activity2 = new Intent(getApplicationContext(), Etakemon.class);
        Bundle extras = new Bundle();
        extras.putString("name",selectedValue);
        extras.putString("description",hs.get(selectedValue));
        Activity2.putExtras(extras);
        startActivity(Activity2);
    }

    public void getEtakemons(String name) throws JSONException, UnsupportedEncodingException {
        final TextView res =(TextView) findViewById(R.id.label);
        MainActivity.RestClient.get("/myapp/json/get/"+name, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String i=response.get("name").toString();
                    res.setText(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCOde, Header[] headers, String s, Throwable i){
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // Pull out the first event on the public timeline
                try {
                    String[] arrayTrack =new String[response.length()];
                    for (int i=0;i<response.length();i++) {
                        String res=response.getJSONObject(i).get("name").toString();
                        String s=response.getJSONObject(i).get("description").toString();
                        hs.put(res,s);
                        arrayTrack[i]=res;
                    }
                    setListAdapter(new ElmeuArrayAdapter(getApplicationContext(), arrayTrack
                    ));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}
