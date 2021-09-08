package robot.dong.swf;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Image;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Frag3 extends Fragment {
    private View view;
    private AlertDialog dialog , dialog1;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button cancel, change , back;
    ImageView  usage , logout, IDprofile  ;
    TextView editprofile , IDname ;
    String value , IDNN , NOWPW , NOWID;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.frag3, container, false);

         editprofile = (TextView)view.findViewById(R.id.editprofile);
         usage =  (ImageView)view.findViewById(R.id.usage);
         logout =  (ImageView) view.findViewById(R.id.logout);
         IDname = (TextView) view.findViewById(R.id.IDname);
         IDprofile = (ImageView) view.findViewById(R.id.IDprofile);

         int a = 1 ;
         if (a == 1) {
             callID();
             callPW();
             callIDprofile();
             callIDname();
             EditProfile();
             Setusage();
         }
         logout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent registerIntent = new Intent(getContext(), MainActivity.class);
                 getContext().startActivity(registerIntent);
             }
         });



        return view;
    }

    private void callIDprofile() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef3 = database.getReference("NOW");
        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue(String.class);
                switch (value){
                    case "ID1":
                        IDprofile.setImageResource(R.drawable.a1);
                        break;
                    case "ID2":
                        IDprofile.setImageResource(R.drawable.a2);
                        break;
                    case "ID3":
                        IDprofile.setImageResource(R.drawable.a3);
                        break;
                    case "ID4":
                        IDprofile.setImageResource(R.drawable.a4);
                        break;
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {


            }

        });

    }
    private void callIDname() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef3 = database.getReference("NOW");
        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue(String.class);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef3 = database.getReference(value);
                myRef3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        IDNN = dataSnapshot.getValue(String.class);
                        IDname.setText("이름 : " + IDNN);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {


                    }

                });

            }

            @Override
            public void onCancelled(DatabaseError error) {


            }

        });

    }
    private void EditProfile(){
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                final View vew = inflater.inflate(R.layout.login_dialog, null);

                editTextEmail = (EditText) vew.findViewById(R.id.editTextEmail);
                editTextPassword = (EditText) vew.findViewById(R.id.editTextPassword);
                editTextPassword.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                editTextPassword.setRawInputType(Configuration.KEYBOARD_12KEY);
                change = (Button)vew.findViewById(R.id.change);
                cancel = (Button)vew.findViewById(R.id.cancel);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(vew);
                dialog = builder.create();
                dialog.show();
                change.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String email = editTextEmail.getText().toString();
                        String password = editTextPassword.getText().toString();
                        FirebaseDatabase database = FirebaseDatabase.getInstance();

                        DatabaseReference setID = database.getReference(NOWID);
                        DatabaseReference setPW = database.getReference(NOWPW);
                        setID.setValue(email);
                        setPW.setValue(password);
                        dialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
    private void Setusage(){
        usage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                final View vew = inflater.inflate(R.layout.usage_dialog, null);



                back = (Button)vew.findViewById(R.id.back);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setView(vew);
                dialog1 = builder1.create();
                dialog1.show();
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });


            }
        });
    }
    private void callID() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("NOW");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                NOWID = dataSnapshot.getValue(String.class);


            }

            @Override
            public void onCancelled(DatabaseError error) {


            }

        });

    }
    private void callPW() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef3 = database.getReference("NOWPW");
        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                NOWPW = dataSnapshot.getValue(String.class);


            }

            @Override
            public void onCancelled(DatabaseError error) {


            }

        });

    }



}
