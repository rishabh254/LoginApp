package appsbyrishabh.loginsample;

/**
 * Created by Rishabh-PC on 4/4/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    RegistrationAdapter adapter_ob;
    RegisterationOpenHelper helper_ob;
    ListView nameList;
    Button registerBtn;
    Cursor cursor;
    Button btnLogout;
    TextView header;
    SessionManager session;
    static String name;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        nameList = (ListView) findViewById(R.id.lv_name);
        registerBtn = (Button) findViewById(R.id.btn_register);
        btnLogout = (Button) findViewById(R.id.btnLogout);



        session = new SessionManager(getApplicationContext());
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

        session.checkLogin();


        header = (TextView)findViewById(R.id.lblName);
        header.setText(name);

        adapter_ob = new RegistrationAdapter(this);


        String[] from = { helper_ob.FNAME, helper_ob.LNAME };
        int[] to = { R.id.tv_fname, R.id.tv_lname };
        cursor = adapter_ob.queryName();
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                R.layout.row, cursor, from, to);
        nameList.setAdapter(cursorAdapter);


        // Button logout




        /**
         * Logout button click event
         * */
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Clear the session data
                // This will clear all session data and
                // redirect user to LoginActivity
                session.logoutUser();
            }
        });


        registerBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent registerIntent = new Intent(MainActivity.this,
                        RegistrationActivity.class);
                startActivity(registerIntent);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        cursor.requery();

    }

}