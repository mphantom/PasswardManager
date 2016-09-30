package mphantom.com.passwardmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class CheckActivity extends AppCompatActivity {

    EditText etPasswrod;
    private String password = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        etPasswrod = (EditText) findViewById(R.id.et_password_input);
        etPasswrod.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (password.equals(etPasswrod.getText().toString().trim())) {
                        startActivity(new Intent(CheckActivity.this, MainActivity.class));
                    } else {
                        etPasswrod.setError(getString(R.string.input_error));
                    }
                }
                return false;
            }
        });
    }
}
