package robot.dong.swf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImageButton register1 = findViewById(R.id.imageButton1R);
        ImageButton register2 = findViewById(R.id.imageButton2R);
        ImageButton register3 = findViewById(R.id.imageButton3R);
        ImageButton register4 = findViewById(R.id.imageButton4R);




        register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent1 = new Intent(RegisterActivity.this, RegisterActivity2.class);
                registerIntent1.putExtra("ID","ID1");
                registerIntent1.putExtra("PW","PW1");
                RegisterActivity.this.startActivity(registerIntent1);


            }
        });
        register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent2 = new Intent(RegisterActivity.this, RegisterActivity2.class);
                registerIntent2.putExtra("ID","ID2");
                registerIntent2.putExtra("PW","PW2");
                RegisterActivity.this.startActivity(registerIntent2);

            }
        });
        register3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent3 = new Intent(RegisterActivity.this, RegisterActivity2.class);
                registerIntent3.putExtra("ID","ID3");
                registerIntent3.putExtra("PW","PW3");
                RegisterActivity.this.startActivity(registerIntent3);

            }
        });
        register4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent4 = new Intent(RegisterActivity.this, RegisterActivity2.class);
                registerIntent4.putExtra("ID","ID4");
                registerIntent4.putExtra("PW","PW4");
                RegisterActivity.this.startActivity(registerIntent4);

            }
        });

    }
}