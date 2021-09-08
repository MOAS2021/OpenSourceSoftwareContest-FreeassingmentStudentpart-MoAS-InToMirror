package robot.dong.swf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SmartMirrorActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private  Frag1 frag1;
    private  Frag2 frag2;
    private  Frag3 frag3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_mirror);






        bottomNavigationView = findViewById(R.id.bottomNavi);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_one:
                        setFrag(0);
                        break;
                    case R.id.action_two:
                        setFrag(1);
                        break;
                    case R.id.action_three:
                        setFrag(2);
                        break;

                }
                return true;
            }
        });
        frag1 = new Frag1();
        frag2 = new Frag2();
        frag3 = new Frag3();

        setFrag(0); // 첫 프래그 먼트 화면을 무엇으로 지정해줄 것인지 선택

    }
    // 프레그먼트 교체가 일어나는 실행문이다.
    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch(n){
            case 0 :
                ft.replace(R.id.main_frame, frag1);
                ft.commit();
                break;
            case 1 :
                ft.replace(R.id.main_frame, frag2);
                ft.commit();
                break;
            case 2 :
                ft.replace(R.id.main_frame, frag3);
                ft.commit();
                break;



        }




    }
}
