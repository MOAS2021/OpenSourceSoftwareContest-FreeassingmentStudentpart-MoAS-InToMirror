package robot.dong.swf;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    String Login;
    ImageView usagemain;
    private AlertDialog dialog2;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usagemain = findViewById(R.id.usagemain);
        ImageButton login1 = findViewById(R.id.imageButton1M);
        ImageButton login2 = findViewById(R.id.imageButton2M);
        ImageButton login3 = findViewById(R.id.imageButton3M);
        ImageButton login4 = findViewById(R.id.imageButton4M);
        ImageView register = findViewById(R.id.imageView);

        Setusage();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(registerIntent);
            }
        });

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login = "PW1";

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef3 = database.getReference(Login);
                myRef3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        String num = value;
                        AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                        ad.setIcon(R.drawable.a5);
                        ad.setTitle("로그인");
                        ad.setMessage("비밀번호를 입력해주세요");

                        final EditText et = new EditText(MainActivity.this);
                        et.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                        et.setRawInputType(Configuration.KEYBOARD_12KEY);
                        et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        ad.setView(et);

                        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String PW12 = et.getText().toString();
                                if(num.equals(PW12)){

                                    Intent registerIntent6 = new Intent(MainActivity.this, SmartMirrorActivity.class);
                                    MainActivity.this.startActivity(registerIntent6);
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();

                                    DatabaseReference myRef123 = database.getReference("NOW");
                                    myRef123.setValue("ID1");
                                    DatabaseReference mosa = database.getReference("NOWPW");
                                    mosa.setValue("PW1");

                                }
                                else{

                                    FailLogin();
                                }
                            }
                        });
                        ad.show();


                    }

                    @Override
                    public void onCancelled(DatabaseError error) {


                    }

                });


            }
        });
        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login = "PW2";

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef3 = database.getReference(Login);
                myRef3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        String num = value;
                        AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                        ad.setIcon(R.drawable.a5);
                        ad.setTitle("로그인");
                        ad.setMessage("비밀번호를 입력해주세요");

                        final EditText et = new EditText(MainActivity.this);
                        et.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                        et.setRawInputType(Configuration.KEYBOARD_12KEY);
                        et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        ad.setView(et);

                        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String PW12 = et.getText().toString();



                                if(num.equals(PW12)){

                                    Intent registerIntent6 = new Intent(MainActivity.this, SmartMirrorActivity.class);
                                    MainActivity.this.startActivity(registerIntent6);
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();

                                    DatabaseReference myRef123 = database.getReference("NOW");
                                    myRef123.setValue("ID2");
                                    DatabaseReference mosa = database.getReference("NOWPW");
                                    mosa.setValue("PW2");
                                }
                                else{

                                    FailLogin();
                                }
                            }
                        });
                        ad.show();


                    }

                    @Override
                    public void onCancelled(DatabaseError error) {


                    }

                });

            }
        });
        login3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login = "PW3";
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef3 = database.getReference(Login);
                myRef3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        String num = value;
                        AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                        ad.setIcon(R.drawable.a5);
                        ad.setTitle("로그인");
                        ad.setMessage("비밀번호를 입력해주세요");

                        final EditText et = new EditText(MainActivity.this);
                        et.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                        et.setRawInputType(Configuration.KEYBOARD_12KEY);
                        et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        ad.setView(et);

                        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String PW12 = et.getText().toString();



                                if(num.equals(PW12)){

                                    Intent registerIntent6 = new Intent(MainActivity.this, SmartMirrorActivity.class);
                                    MainActivity.this.startActivity(registerIntent6);

                                    FirebaseDatabase database = FirebaseDatabase.getInstance();

                                    DatabaseReference myRef123 = database.getReference("NOW");
                                    myRef123.setValue("ID3");
                                    DatabaseReference mosa = database.getReference("NOWPW");
                                    mosa.setValue("PW3");
                                }
                                else{

                                    FailLogin();
                                }
                            }
                        });
                        ad.show();


                    }

                    @Override
                    public void onCancelled(DatabaseError error) {


                    }

                });

            }
        });
        login4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login = "PW4";
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef3 = database.getReference(Login);
                myRef3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        String num = value;
                        AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                        ad.setIcon(R.drawable.a5);
                        ad.setTitle("로그인");
                        ad.setMessage("비밀번호를 입력해주세요");

                        final EditText et = new EditText(MainActivity.this);
                        et.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                        et.setRawInputType(Configuration.KEYBOARD_12KEY);
                        et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        ad.setView(et);

                        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String PW12 = et.getText().toString();



                                if(num.equals(PW12)){

                                    Intent registerIntent6 = new Intent(MainActivity.this, SmartMirrorActivity.class);
                                    MainActivity.this.startActivity(registerIntent6);

                                    FirebaseDatabase database = FirebaseDatabase.getInstance();

                                    DatabaseReference myRef123 = database.getReference("NOW");
                                    myRef123.setValue("ID4");
                                    DatabaseReference mosa = database.getReference("NOWPW");
                                    mosa.setValue("PW4");
                                }
                                else{

                                    FailLogin();
                                }
                            }
                        });
                        ad.show();


                    }

                    @Override
                    public void onCancelled(DatabaseError error) {


                    }

                });

            }
        });

    }


    private void FailLogin() {
        LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout loginLayout1 = (LinearLayout) vi.inflate(R.layout.activity_fail, null);
        new AlertDialog.Builder(this).setTitle("message").setView(loginLayout1).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
    private void Setusage(){
        usagemain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                final View vew = inflater.inflate(R.layout.usage_dialog, null);



                back = (Button)vew.findViewById(R.id.back);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setView(vew);
                dialog2 = builder1.create();
                dialog2.show();
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2.dismiss();
                    }
                });


            }
        });
    }


}
