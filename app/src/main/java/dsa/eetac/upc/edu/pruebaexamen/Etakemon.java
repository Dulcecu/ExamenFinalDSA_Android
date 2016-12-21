package dsa.eetac.upc.edu.pruebaexamen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
/**
 * Created by Turpitude on 21/12/2016.
 */
public class Etakemon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etakemon);
        Bundle extra=getIntent().getExtras();
        String name=extra.getString("name");
        String description=extra.getString("description");
        TextView text1=(TextView) findViewById(R.id.name);
        TextView text2=(TextView) findViewById(R.id.description);
        text1.setText(name);
        text2.setText(description);
    }
}
