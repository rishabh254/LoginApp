package appsbyrishabh.loginsample;

/**
 * Created by Rishabh-PC on 4/4/2015.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationActivity extends Activity {
    RegistrationAdapter adapter;

    EditText professionEdit, dateEdit;
    Button submitBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        professionEdit = (EditText) findViewById(R.id.et_profession);
        dateEdit = (EditText) findViewById(R.id.et_date);
        submitBtn = (Button) findViewById(R.id.btn_submit);

        adapter = new RegistrationAdapter(this);

        submitBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String dateValue = professionEdit.getText().toString();
                String professionValue = dateEdit.getText().toString();
                long val = adapter.insertDetails(dateValue, professionValue);
                // Toast.makeText(getApplicationContext(), Long.toString(val),
                // 300).show();
                finish();
            }
        });


    }
}