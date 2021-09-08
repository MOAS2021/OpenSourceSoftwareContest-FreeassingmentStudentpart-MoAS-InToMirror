package robot.dong.swf;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Frag2 extends Fragment {
    private View view;
    private static final String TAG_TEXT = "text";
    private static final String TAG_IMAGE = "image";

    TextView textview_result;
    ImageView RT , LT , RM , LM , RB , LB ;
    String value ;

    List<Map<String, Object>> dialogItemList;

    int[] image = {R.drawable.box , R.drawable.corona ,R.drawable.luck,R.drawable.sports,R.drawable.phrase,R.drawable.weather,R.drawable.time, R.drawable.stock, R.drawable.exchange};
    String[] text = {"빈칸","코로나 확진자수","오늘의 운세", "오늘의 뉴스" , "오늘의 명언" , "오늘의 날씨","시간", "주식","환율"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag2, container, false);
        RT = (ImageView) view.findViewById(R.id.RT);
        LT = (ImageView) view.findViewById(R.id.LT);
        RM = (ImageView) view.findViewById(R.id.RM);
        LM = (ImageView) view.findViewById(R.id.LM);
        RB = (ImageView) view.findViewById(R.id.RB);
        LB = (ImageView) view.findViewById(R.id.LB);


        textview_result = (TextView) view.findViewById(R.id.textview_result);


        int a = 1;

        if (a ==1){
           //callPW();
            RTcall();
            LTcall();
            RMcall();
            LMcall();
            RBcall();
            LBcall();
        }


        RT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RTshowAlertDialog();
            }
        });

        LT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LTshowAlertDialog();
            }
        });

        RM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RMshowAlertDialog();
            }
        });

        LM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LMshowAlertDialog();
            }
        });

        RB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RBshowAlertDialog();
            }
        });

        LB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LBshowAlertDialog();
            }
        });

        dialogItemList = new ArrayList<>();
        for(int i=0;i<image.length;i++)
        {
            Map<String, Object> itemMap = new HashMap<>();
            itemMap.put(TAG_IMAGE, image[i]);
            itemMap.put(TAG_TEXT, text[i]);

            dialogItemList.add(itemMap);
        }




        return view;
    }
    private void RTshowAlertDialog()
    {
        callPW();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_dialog, null);
        builder.setView(view);

        final ListView listview = (ListView)view.findViewById(R.id.listview_alterdialog_list);
        final AlertDialog dialog = builder.create();

        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), dialogItemList,
                R.layout.alert_dialog_row,
                new String[]{TAG_IMAGE, TAG_TEXT},
                new int[]{R.id.alertDialogItemImageView, R.id.alertDialogItemTextView});

        listview.setAdapter(simpleAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                textview_result.setText(text[position] + "를(을) 선택했습니다.");
                String sel = text[position];
                switch (sel){
                    case "빈칸":
                        dialog.dismiss();
                        break;
                    case "코로나 확진자수":
                        break;
                    case "오늘의 운세":
                        break;
                    case "오늘의 뉴스":
                        break;
                    case "오늘의 명언":
                        break;
                    case "시간":
                        break;
                    case "오늘의 날씨":
                        AlertDialog.Builder ads = new AlertDialog.Builder(getContext());
                        ads.setIcon(R.drawable.weather);
                        ads.setTitle("오늘의 날씨");
                        ads.setMessage("지역을 적어주세요");

                        final EditText et = new EditText(getContext());

                        ads.setView(et);

                        ads.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String IDNF = value + "LOCAL";
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String inp = et.getText().toString();
                                DatabaseReference myRef = database.getReference(IDNF);
                                myRef.setValue(inp);
                            }
                        });ads.show();
                        dialog.dismiss();
                        break;
                    case "주식":
                        AlertDialog.Builder zxc = new AlertDialog.Builder(getContext());
                        zxc.setIcon(R.drawable.stock);
                        zxc.setTitle("주식");
                        zxc.setMessage("주식코드를 적어주세요");

                        final EditText ry = new EditText(getContext());

                        zxc.setView(ry);

                        zxc.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String IDNF = value + "CODE";
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String inp = ry.getText().toString();
                                DatabaseReference myRef = database.getReference(IDNF);
                                myRef.setValue(inp);
                            }
                        });zxc.show();
                        dialog.dismiss();
                        break;
                    case "환율":
                        AlertDialog.Builder qwe = new AlertDialog.Builder(getContext());
                        qwe.setIcon(R.drawable.exchange);
                        qwe.setTitle("환율");
                        qwe.setMessage("확인하고 싶은 나라를 적어주세요");

                        final EditText ui = new EditText(getContext());

                        qwe.setView(ui);

                        qwe.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String IDNF = value  + "COUNTRY";
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String inp = ui.getText().toString();
                                DatabaseReference myRef = database.getReference(IDNF);
                                myRef.setValue(inp);
                            }
                        });qwe.show();
                        dialog.dismiss();
                        break;

                    default:
                        break;

                }
                RT.setImageResource(image[position]);
                String IDNF = value + "RT";
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference myRef = database.getReference(IDNF);
                myRef.setValue(text[position]);
                dialog.dismiss();
            }
        });

        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }



    private void LTshowAlertDialog()
    {
        callPW();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_dialog, null);
        builder.setView(view);

        final ListView listview = (ListView)view.findViewById(R.id.listview_alterdialog_list);
        final AlertDialog dialog = builder.create();

        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), dialogItemList,
                R.layout.alert_dialog_row,
                new String[]{TAG_IMAGE, TAG_TEXT},
                new int[]{R.id.alertDialogItemImageView, R.id.alertDialogItemTextView});

        listview.setAdapter(simpleAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                textview_result.setText(text[position] + "를(을) 선택했습니다.");
                String sel = text[position];
                switch (sel){
                    case "빈칸":
                        dialog.dismiss();
                        break;
                    case "코로나 확진자수":
                        break;
                    case "오늘의 운세":
                        break;
                    case "오늘의 뉴스":
                        break;
                    case "오늘의 명언":
                        break;
                    case "시간":
                        break;
                    case "오늘의 날씨":
                        AlertDialog.Builder ads = new AlertDialog.Builder(getContext());
                        ads.setIcon(R.drawable.weather);
                        ads.setTitle("오늘의 날씨");
                        ads.setMessage("지역을 적어주세요");

                        final EditText et = new EditText(getContext());

                        ads.setView(et);

                        ads.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String IDNF = value + "LOCAL";
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String inp = et.getText().toString();
                                DatabaseReference myRef = database.getReference(IDNF);
                                myRef.setValue(inp);
                            }
                        });ads.show();
                        dialog.dismiss();
                        break;
                    case "주식":
                        AlertDialog.Builder zxc = new AlertDialog.Builder(getContext());
                        zxc.setIcon(R.drawable.stock);
                        zxc.setTitle("주식");
                        zxc.setMessage("주식코드를 적어주세요");

                        final EditText ry = new EditText(getContext());

                        zxc.setView(ry);

                        zxc.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String IDNF = value  + "CODE";
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String inp = ry.getText().toString();
                                DatabaseReference myRef = database.getReference(IDNF);
                                myRef.setValue(inp);
                            }
                        });zxc.show();
                        dialog.dismiss();
                        break;
                    case "환율":
                        AlertDialog.Builder qwe = new AlertDialog.Builder(getContext());
                        qwe.setIcon(R.drawable.exchange);
                        qwe.setTitle("환율");
                        qwe.setMessage("확인하고 싶은 나라를 적어주세요");

                        final EditText ui = new EditText(getContext());

                        qwe.setView(ui);

                        qwe.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String IDNF = value + "LT" + "COUNTRY";
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String inp = ui.getText().toString();
                                DatabaseReference myRef = database.getReference(IDNF);
                                myRef.setValue(inp);
                            }
                        });qwe.show();
                        dialog.dismiss();
                        break;

                    default:
                        break;

                }
                LT.setImageResource(image[position]);
                String IDNF = value + "LT";
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference myRef = database.getReference(IDNF);
                myRef.setValue(text[position]);
                dialog.dismiss();
            }
        });

        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    private void RMshowAlertDialog()
    {
        callPW();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_dialog, null);
        builder.setView(view);

        final ListView listview = (ListView)view.findViewById(R.id.listview_alterdialog_list);
        final AlertDialog dialog = builder.create();

        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), dialogItemList,
                R.layout.alert_dialog_row,
                new String[]{TAG_IMAGE, TAG_TEXT},
                new int[]{R.id.alertDialogItemImageView, R.id.alertDialogItemTextView});

        listview.setAdapter(simpleAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                textview_result.setText(text[position] + "를(을) 선택했습니다.");
                String sel = text[position];
                switch (sel){
                    case "빈칸":
                        dialog.dismiss();
                        break;
                    case "코로나 확진자수":
                        break;
                    case "오늘의 운세":
                        break;
                    case "오늘의 뉴스":
                        break;
                    case "오늘의 명언":
                        break;
                    case "시간":
                        break;
                    case "오늘의 날씨":
                        AlertDialog.Builder ads = new AlertDialog.Builder(getContext());
                        ads.setIcon(R.drawable.weather);
                        ads.setTitle("오늘의 날씨");
                        ads.setMessage("지역을 적어주세요");

                        final EditText et = new EditText(getContext());

                        ads.setView(et);

                        ads.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String IDNF = value  + "LOCAL";
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String inp = et.getText().toString();
                                DatabaseReference myRef = database.getReference(IDNF);
                                myRef.setValue(inp);
                            }
                        });ads.show();
                        dialog.dismiss();
                        break;
                    case "주식":
                        AlertDialog.Builder zxc = new AlertDialog.Builder(getContext());
                        zxc.setIcon(R.drawable.stock);
                        zxc.setTitle("주식");
                        zxc.setMessage("주식코드를 적어주세요");

                        final EditText ry = new EditText(getContext());

                        zxc.setView(ry);

                        zxc.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String IDNF = value  + "CODE";
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String inp = ry.getText().toString();
                                DatabaseReference myRef = database.getReference(IDNF);
                                myRef.setValue(inp);
                            }
                        });zxc.show();
                        dialog.dismiss();
                        break;
                    case "환율":
                        AlertDialog.Builder qwe = new AlertDialog.Builder(getContext());
                        qwe.setIcon(R.drawable.exchange);
                        qwe.setTitle("환율");
                        qwe.setMessage("확인하고 싶은 나라를 적어주세요");

                        final EditText ui = new EditText(getContext());

                        qwe.setView(ui);

                        qwe.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String IDNF = value + "COUNTRY";
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String inp = ui.getText().toString();
                                DatabaseReference myRef = database.getReference(IDNF);
                                myRef.setValue(inp);
                            }
                        });qwe.show();
                        dialog.dismiss();
                        break;

                    default:
                        break;

                }
                RM.setImageResource(image[position]);
                String IDNF = value + "RM";
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference myRef = database.getReference(IDNF);
                myRef.setValue(text[position]);
                dialog.dismiss();
            }
        });

        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    private void LMshowAlertDialog()
    {
        callPW();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_dialog, null);
        builder.setView(view);

        final ListView listview = (ListView)view.findViewById(R.id.listview_alterdialog_list);
        final AlertDialog dialog = builder.create();

        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), dialogItemList,
                R.layout.alert_dialog_row,
                new String[]{TAG_IMAGE, TAG_TEXT},
                new int[]{R.id.alertDialogItemImageView, R.id.alertDialogItemTextView});

        listview.setAdapter(simpleAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                textview_result.setText(text[position] + "를(을) 선택했습니다.");
                String sel = text[position];
                switch (sel){
                    case "빈칸":
                        dialog.dismiss();
                        break;
                    case "코로나 확진자수":
                        break;
                    case "오늘의 운세":
                        break;
                    case "오늘의 뉴스":
                        break;
                    case "오늘의 명언":
                        break;
                    case "시간":
                        break;
                    case "오늘의 날씨":
                        AlertDialog.Builder ads = new AlertDialog.Builder(getContext());
                        ads.setIcon(R.drawable.weather);
                        ads.setTitle("오늘의 날씨");
                        ads.setMessage("지역을 적어주세요");

                        final EditText et = new EditText(getContext());

                        ads.setView(et);

                        ads.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String IDNF = value + "LOCAL";
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String inp = et.getText().toString();
                                DatabaseReference myRef = database.getReference(IDNF);
                                myRef.setValue(inp);
                            }
                        });ads.show();
                        dialog.dismiss();
                        break;
                    case "주식":
                        AlertDialog.Builder zxc = new AlertDialog.Builder(getContext());
                        zxc.setIcon(R.drawable.stock);
                        zxc.setTitle("주식");
                        zxc.setMessage("주식코드를 적어주세요");

                        final EditText ry = new EditText(getContext());

                        zxc.setView(ry);

                        zxc.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String IDNF = value  + "CODE";
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String inp = ry.getText().toString();
                                DatabaseReference myRef = database.getReference(IDNF);
                                myRef.setValue(inp);
                            }
                        });zxc.show();
                        dialog.dismiss();
                        break;
                    case "환율":
                        AlertDialog.Builder qwe = new AlertDialog.Builder(getContext());
                        qwe.setIcon(R.drawable.exchange);
                        qwe.setTitle("환율");
                        qwe.setMessage("확인하고 싶은 나라를 적어주세요");

                        final EditText ui = new EditText(getContext());

                        qwe.setView(ui);

                        qwe.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String IDNF = value  + "COUNTRY";
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String inp = ui.getText().toString();
                                DatabaseReference myRef = database.getReference(IDNF);
                                myRef.setValue(inp);
                            }
                        });qwe.show();
                        dialog.dismiss();
                        break;

                    default:
                        break;

                }
                LM.setImageResource(image[position]);
                String IDNF = value + "LM";
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference myRef = database.getReference(IDNF);
                myRef.setValue(text[position]);
                dialog.dismiss();
            }
        });

        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    private void RBshowAlertDialog()
    {
        callPW();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_dialog, null);
        builder.setView(view);

        final ListView listview = (ListView)view.findViewById(R.id.listview_alterdialog_list);
        final AlertDialog dialog = builder.create();

        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), dialogItemList,
                R.layout.alert_dialog_row,
                new String[]{TAG_IMAGE, TAG_TEXT},
                new int[]{R.id.alertDialogItemImageView, R.id.alertDialogItemTextView});

        listview.setAdapter(simpleAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                textview_result.setText(text[position] + "를(을) 선택했습니다.");
                String sel = text[position];
                switch (sel){
                    case "빈칸":
                        dialog.dismiss();
                        break;
                    case "코로나 확진자수":
                        break;
                    case "오늘의 운세":
                        break;
                    case "오늘의 뉴스":
                        break;
                    case "오늘의 명언":
                        break;
                    case "시간":
                        break;
                    case "오늘의 날씨":
                        AlertDialog.Builder ads = new AlertDialog.Builder(getContext());
                        ads.setIcon(R.drawable.weather);
                        ads.setTitle("오늘의 날씨");
                        ads.setMessage("지역을 적어주세요");

                        final EditText et = new EditText(getContext());

                        ads.setView(et);

                        ads.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String IDNF = value  + "LOCAL";
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String inp = et.getText().toString();
                                DatabaseReference myRef = database.getReference(IDNF);
                                myRef.setValue(inp);
                            }
                        });ads.show();
                        dialog.dismiss();
                        break;
                    case "주식":
                        AlertDialog.Builder zxc = new AlertDialog.Builder(getContext());
                        zxc.setIcon(R.drawable.stock);
                        zxc.setTitle("주식");
                        zxc.setMessage("주식코드를 적어주세요");

                        final EditText ry = new EditText(getContext());

                        zxc.setView(ry);

                        zxc.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String IDNF = value + "CODE";
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String inp = ry.getText().toString();
                                DatabaseReference myRef = database.getReference(IDNF);
                                myRef.setValue(inp);
                            }
                        });zxc.show();
                        dialog.dismiss();
                        break;
                    case "환율":
                        AlertDialog.Builder qwe = new AlertDialog.Builder(getContext());
                        qwe.setIcon(R.drawable.exchange);
                        qwe.setTitle("환율");
                        qwe.setMessage("확인하고 싶은 나라를 적어주세요");

                        final EditText ui = new EditText(getContext());

                        qwe.setView(ui);

                        qwe.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String IDNF = value  + "COUNTRY";
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String inp = ui.getText().toString();
                                DatabaseReference myRef = database.getReference(IDNF);
                                myRef.setValue(inp);
                            }
                        });qwe.show();
                        dialog.dismiss();
                        break;

                    default:
                        break;

                }
                RB.setImageResource(image[position]);
                String IDNF = value + "RB";
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference myRef = database.getReference(IDNF);
                myRef.setValue(text[position]);
                dialog.dismiss();
            }
        });

        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    private void LBshowAlertDialog()
    {
        callPW();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_dialog, null);
        builder.setView(view);

        final ListView listview = (ListView)view.findViewById(R.id.listview_alterdialog_list);
        final AlertDialog dialog = builder.create();

        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), dialogItemList,
                R.layout.alert_dialog_row,
                new String[]{TAG_IMAGE, TAG_TEXT},
                new int[]{R.id.alertDialogItemImageView, R.id.alertDialogItemTextView});

        listview.setAdapter(simpleAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                textview_result.setText(text[position] + "를(을) 선택했습니다.");
                String sel = text[position];
                switch (sel){
                    case "빈칸":
                        dialog.dismiss();
                        break;
                    case "코로나 확진자수":
                        break;
                    case "오늘의 운세":
                        break;
                    case "오늘의 뉴스":
                        break;
                    case "오늘의 명언":
                        break;
                    case "시간":
                        break;
                    case "오늘의 날씨":
                        AlertDialog.Builder ads = new AlertDialog.Builder(getContext());
                        ads.setIcon(R.drawable.weather);
                        ads.setTitle("오늘의 날씨");
                        ads.setMessage("지역을 적어주세요");

                        final EditText et = new EditText(getContext());

                        ads.setView(et);

                        ads.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String IDNF = value  + "LOCAL";
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String inp = et.getText().toString();
                                DatabaseReference myRef = database.getReference(IDNF);
                                myRef.setValue(inp);
                            }
                        });ads.show();
                        dialog.dismiss();
                        break;
                    case "주식":
                        AlertDialog.Builder zxc = new AlertDialog.Builder(getContext());
                        zxc.setIcon(R.drawable.stock);
                        zxc.setTitle("주식");
                        zxc.setMessage("주식코드를 적어주세요");

                        final EditText ry = new EditText(getContext());

                        zxc.setView(ry);

                        zxc.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String IDNF = value + "CODE";
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String inp = ry.getText().toString();
                                DatabaseReference myRef = database.getReference(IDNF);
                                myRef.setValue(inp);
                            }
                        });zxc.show();
                        dialog.dismiss();
                        break;
                    case "환율":
                        AlertDialog.Builder qwe = new AlertDialog.Builder(getContext());
                        qwe.setIcon(R.drawable.exchange);
                        qwe.setTitle("환율");
                        qwe.setMessage("확인하고 싶은 나라를 적어주세요");

                        final EditText ui = new EditText(getContext());

                        qwe.setView(ui);

                        qwe.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String IDNF = value  + "COUNTRY";
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String inp = ui.getText().toString();
                                DatabaseReference myRef = database.getReference(IDNF);
                                myRef.setValue(inp);
                            }
                        });qwe.show();
                        dialog.dismiss();
                        break;

                    default:
                        break;

                }
                LB.setImageResource(image[position]);
                String IDNF = value + "LB";
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference myRef = database.getReference(IDNF);
                myRef.setValue(text[position]);
                dialog.dismiss();
            }
        });

        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
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

    private void RTcall() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef3 = database.getReference("NOW");
        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue(String.class);
                String qwer = value+"RT";
                FirebaseDatabase DB = FirebaseDatabase.getInstance();
                DatabaseReference rf = DB.getReference(qwer);
                rf.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String asd = dataSnapshot.getValue(String.class);
                        switch(asd) {
                            case "빈칸":
                                RT.setImageResource(R.drawable.box);
                                break;
                            case "코로나 확진자수":
                                RT.setImageResource(R.drawable.corona);
                                break;
                            case "오늘의 운세":
                                RT.setImageResource(R.drawable.luck);
                                break;
                            case "오늘의 뉴스":
                                RT.setImageResource(R.drawable.sports);
                                break;
                            case "오늘의 명언":
                                RT.setImageResource(R.drawable.phrase);
                                break;
                            case "오늘의 날씨":
                                RT.setImageResource(R.drawable.weather);
                                break;
                            case "시간":
                                RT.setImageResource(R.drawable.time);
                                break;
                            case "주식":
                                RT.setImageResource(R.drawable.stock);
                                break;
                            case "환율":
                                RT.setImageResource(R.drawable.exchange);
                                break;
                            default:
                                break;
                        }


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
    private void LTcall() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef3 = database.getReference("NOW");
        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue(String.class);
                String qwer = value+"LT";
                FirebaseDatabase DB = FirebaseDatabase.getInstance();
                DatabaseReference rf = DB.getReference(qwer);
                rf.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String asd = dataSnapshot.getValue(String.class);
                        switch(asd) {
                            case "빈칸":
                                //LT.setImageResource(R.drawable.box);
                                break;
                            case "코로나 확진자수":
                                LT.setImageResource(R.drawable.corona);
                                break;
                            case "오늘의 운세":
                                LT.setImageResource(R.drawable.luck);
                                break;
                            case "오늘의 뉴스":
                                LT.setImageResource(R.drawable.sports);
                                break;
                            case "오늘의 명언":
                                LT.setImageResource(R.drawable.phrase);
                                break;
                            case "오늘의 날씨":
                                LT.setImageResource(R.drawable.weather);
                                break;
                            case "시간":
                                LT.setImageResource(R.drawable.time);
                                break;
                            case "주식":
                                LT.setImageResource(R.drawable.stock);
                                break;
                            case "환율":
                                LT.setImageResource(R.drawable.exchange);
                                break;
                            default:
                                break;
                        }


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
    private void RMcall() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef3 = database.getReference("NOW");
        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue(String.class);
                String qwer = value+"RM";
                FirebaseDatabase DB = FirebaseDatabase.getInstance();
                DatabaseReference rf = DB.getReference(qwer);
                rf.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String asd = dataSnapshot.getValue(String.class);
                        switch(asd) {
                            case "빈칸":
                                //RM.setImageResource(R.drawable.box);
                                break;
                            case "코로나 확진자수":
                                RM.setImageResource(R.drawable.corona);
                                break;
                            case "오늘의 운세":
                                RM.setImageResource(R.drawable.luck);
                                break;
                            case "오늘의 뉴스":
                                RM.setImageResource(R.drawable.sports);
                                break;
                            case "오늘의 명언":
                                RM.setImageResource(R.drawable.phrase);
                                break;
                            case "오늘의 날씨":
                                RM.setImageResource(R.drawable.weather);
                                break;
                            case "시간":
                                LT.setImageResource(R.drawable.time);
                                break;
                            case "주식":
                                RM.setImageResource(R.drawable.stock);
                                break;
                            case "환율":
                                RM.setImageResource(R.drawable.exchange);
                                break;
                            default:
                                break;
                        }


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
    private void LMcall() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef3 = database.getReference("NOW");
        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue(String.class);
                String qwer = value+"LM";
                FirebaseDatabase DB = FirebaseDatabase.getInstance();
                DatabaseReference rf = DB.getReference(qwer);
                rf.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String asd = dataSnapshot.getValue(String.class);
                        switch(asd) {
                            case "빈칸":
                               // LM.setImageResource(R.drawable.box);
                                break;
                            case "코로나 확진자수":
                                LM.setImageResource(R.drawable.corona);
                                break;
                            case "오늘의 운세":
                                LM.setImageResource(R.drawable.luck);
                                break;
                            case "오늘의 뉴스":
                                LM.setImageResource(R.drawable.sports);
                                break;
                            case "오늘의 명언":
                                LM.setImageResource(R.drawable.phrase);
                                break;
                            case "오늘의 날씨":
                                LM.setImageResource(R.drawable.weather);
                                break;
                            case "시간":
                                LM.setImageResource(R.drawable.time);
                                break;
                            case "주식":
                                LM.setImageResource(R.drawable.stock);
                                break;
                            case "환율":
                                LM.setImageResource(R.drawable.exchange);
                                break;
                            default:
                                break;
                        }


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
    private void RBcall() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef3 = database.getReference("NOW");
        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue(String.class);
                String qwer = value+"RB";
                FirebaseDatabase DB = FirebaseDatabase.getInstance();
                DatabaseReference rf = DB.getReference(qwer);
                rf.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String asd = dataSnapshot.getValue(String.class);
                        switch(asd) {
                            case "빈칸":
                                //RB.setImageResource(R.drawable.box);
                                break;
                            case "코로나 확진자수":
                                RB.setImageResource(R.drawable.corona);
                                break;
                            case "오늘의 운세":
                                RB.setImageResource(R.drawable.luck);
                                break;
                            case "오늘의 뉴스":
                                RB.setImageResource(R.drawable.sports);
                                break;
                            case "오늘의 명언":
                                RB.setImageResource(R.drawable.phrase);
                                break;
                            case "오늘의 날씨":
                                RB.setImageResource(R.drawable.weather);
                                break;
                            case "시간":
                                RB.setImageResource(R.drawable.time);
                                break;
                            case "주식":
                                RB.setImageResource(R.drawable.stock);
                                break;
                            case "환율":
                                RB.setImageResource(R.drawable.exchange);
                                break;
                            default:
                                break;
                        }

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
    private void LBcall() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef3 = database.getReference("NOW");
        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue(String.class);
                String qwer = value+"LB";
                FirebaseDatabase DB = FirebaseDatabase.getInstance();
                DatabaseReference rf = DB.getReference(qwer);
                rf.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String asd = dataSnapshot.getValue(String.class);
                        switch(asd) {
                            case "빈칸":
                                //LB.setImageResource(R.drawable.box);
                                break;
                            case "코로나 확진자수":
                                LB.setImageResource(R.drawable.corona);
                                break;
                            case "오늘의 운세":
                                LB.setImageResource(R.drawable.luck);
                                break;
                            case "오늘의 뉴스":
                                LB.setImageResource(R.drawable.sports);
                                break;
                            case "오늘의 명언":
                                LB.setImageResource(R.drawable.phrase);
                                break;
                            case "오늘의 날씨":
                                LB.setImageResource(R.drawable.weather);
                                break;
                            case "시간":
                                LB.setImageResource(R.drawable.time);
                                break;
                            case "주식":
                                LB.setImageResource(R.drawable.stock);
                                break;
                            case "환율":
                                LB.setImageResource(R.drawable.exchange);
                                break;
                            default:
                                break;
                        }

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

}
