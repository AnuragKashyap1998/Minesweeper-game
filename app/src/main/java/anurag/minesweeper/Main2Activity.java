package anurag.minesweeper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    String name; // null
    TextView myTextView;
    EditText myEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button myButton = (Button) findViewById(R.id.mybutton);
        myTextView=(TextView)findViewById(R.id.textView);
        final SharedPreferences sharedPreferences = getSharedPreferences("tic_tac_toe", MODE_PRIVATE);
        name = sharedPreferences.getString("username", null);
        if (name == null) {
            myTextView.setText("Welcome User");
        } else {
            myTextView.setText("Welcome" + name);
        }
        myEditText = (EditText) findViewById(R.id.myEditText);

        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                name = myEditText.getText().toString();
                if (name.isEmpty()) {
                    Toast.makeText(Main2Activity.this, "Enter user name", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", name);
                editor.commit();
                Intent i = new Intent(Main2Activity.this, MainActivity.class);
                i.putExtra("username", name);
                startActivity(i);
            }
        });

    }
}
