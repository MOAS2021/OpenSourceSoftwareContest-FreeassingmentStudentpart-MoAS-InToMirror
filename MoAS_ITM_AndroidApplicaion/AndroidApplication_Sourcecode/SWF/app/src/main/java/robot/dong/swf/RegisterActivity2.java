package robot.dong.swf;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        EditText RI = findViewById(R.id.registerID);
        EditText RP = findViewById(R.id.registerPW);
        Button RB = findViewById(R.id.registerB);
        Intent secondIntent = getIntent();
        String ID = secondIntent.getStringExtra("ID");
        String PW = secondIntent.getStringExtra("PW");
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference(ID);
        DatabaseReference myRef1 = database.getReference(PW);
        DatabaseReference my1 = database.getReference(ID+"RT");
        DatabaseReference my2 = database.getReference(ID+"LT");
        DatabaseReference my3 = database.getReference(ID+"RM");
        DatabaseReference my4 = database.getReference(ID+"LM");
        DatabaseReference my5 = database.getReference(ID+"RB");
        DatabaseReference my6 = database.getReference(ID+"LB");
        


        RB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = RI.getText().toString();
                String password = RP.getText().toString();
                myRef.setValue(name);
                myRef1.setValue(password);
                my1.setValue("빈칸");
                my2.setValue("빈칸");
                my3.setValue("빈칸");
                my4.setValue("빈칸");
                my5.setValue("빈칸");
                my6.setValue("빈칸");
                showFinish();


            }
        });


    }
    private void showFinish() {
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout loginLayout = (LinearLayout) vi.inflate(R.layout.activity_finish_register, null);
        new AlertDialog.Builder(this).setTitle("message").setView(loginLayout).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent registerIntent5 = new Intent(RegisterActivity2.this, MainActivity.class);
                RegisterActivity2.this.startActivity(registerIntent5);
            }
        }).show();
    }
}