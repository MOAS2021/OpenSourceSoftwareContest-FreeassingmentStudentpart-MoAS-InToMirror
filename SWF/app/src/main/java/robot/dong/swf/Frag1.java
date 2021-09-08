package robot.dong.swf;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Frag1 extends Fragment {
    private View view;
    String value ;
    ImageView RT , LT , RM , LM , RB , LB;

    int[] image = {R.drawable.corona ,R.drawable.luck,R.drawable.sports,R.drawable.phrase,R.drawable.weather, R.drawable.stock, R.drawable.exchange};
    String[] text = {"코로나 확진자수","오늘의 운세", "오늘의 뉴스" , "오늘의 명언" , "오늘의 날씨", "주식","환율"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag1, container, false);
        RT = (ImageView) view.findViewById(R.id.RT);
        LT = (ImageView) view.findViewById(R.id.LT);
        RM = (ImageView) view.findViewById(R.id.RM);
        LM = (ImageView) view.findViewById(R.id.LM);
        RB = (ImageView) view.findViewById(R.id.RB);
        LB = (ImageView) view.findViewById(R.id.LB);





        return view;
    }
    private void callPW() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef3 = database.getReference("NOW");
        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue(String.class);


            }

            @Override
            public void onCancelled(DatabaseError error) {


            }

        });

    }
    private void callRT() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef3 = database.getReference(value+"RT");
        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String RTT = dataSnapshot.getValue(String.class);


            }

            @Override
            public void onCancelled(DatabaseError error) {


            }

        });

    }
}





