package de.albert.drinkordie;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button playButton;

    EditText et;
    Button bt;
    ListView lv;
    TextView title;

    Button clearButton;

    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    // public static int creationCounter = 0;



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et = (EditText) findViewById(R.id.editText3);
        bt = (Button) findViewById(R.id.Button_add);
        lv = (ListView) findViewById(R.id._dynamic_lv);
        title = (TextView) findViewById(R.id.headline1);

        clearButton = (Button) findViewById(R.id.button_clear);

        playButton = (Button) findViewById(R.id.button_play);

        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.my_list_item_1, arrayList);
        lv.setAdapter(adapter);



        onButtonClick();

        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(getColor(R.color.myColor));
        }
        else
        {
            window.setStatusBarColor(getResources().getColor(R.color.myColor));
        }

        startTitleAnimation();









    }

    public void onButtonClick()
    {
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String result = et.getText().toString();
                result = result.trim();

                if(result.equals("") || result.equals(" ") || result.equals("  ") || result.equals("   ") || result.equals("    ") || result.equals("     ") || result.equals("      "))
                {
                    dialogAddButton();
                }
                else
                {
                    arrayList.add(result);
                    adapter.notifyDataSetChanged();
                    et.setText("");
                    if(arrayList.toArray().length >2)
                    {
                        startButtonAnimation();
                    }
                }



            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {

                                               if(arrayList.isEmpty())
                                               {
                                                   adapter.notifyDataSetChanged();
                                               }
                                               else
                                               {
                                                   arrayList.remove( arrayList.size()- 1);
                                                   adapter.notifyDataSetChanged();
                                               }

                                           }
        }
        );

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayList.toArray().length < 3)
                {
                    dialog();
                }
                else
                {
                    openSelectScreen();
                }
            }
        });

    }

    public void openSelectScreen()
    {
        Intent intent = new Intent(this, SelectGameMode.class);
        intent.putExtra("playerArray", arrayList.toArray(new String[0]));
        startActivity(intent);
    }

    public void openActivity2()
    {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("playerArray", arrayList.toArray(new String[0]));
        startActivity(intent);
    }

    public void dialog()
    {
        new AlertDialog.Builder(this)
                .setMessage("Bitte gib mindestens drei Spieler ein!")
                .setNeutralButton("Ok", null)
                .create().show();

    }

    public void dialogAddButton()
    {
        new AlertDialog.Builder(this)
                .setMessage("Bitte gib einen Namen ein!")
                .setNeutralButton("Ok", null)
                .create().show();

    }

    public void startTitleAnimation() {

        final TextView headline = findViewById(R.id.headline1);
        headline.setVisibility(View.VISIBLE);


        Animation headZoom = AnimationUtils.loadAnimation(MainActivity.this, R.anim.title);
        headZoom.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation arg0) {
                Animation headZoom = AnimationUtils.loadAnimation(MainActivity.this, R.anim.title);
                headZoom.setAnimationListener(this);
                headline.startAnimation(headZoom);

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub

            }

        });


        headline.startAnimation(headZoom);
    }

    public void startButtonAnimation() {
        final Button playB = findViewById(R.id.button_play);
        playB.setVisibility(View.VISIBLE);

        Animation buttonZoom = AnimationUtils.loadAnimation(MainActivity.this, R.anim.playbuttonanim);
        buttonZoom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation buttonZoom = AnimationUtils.loadAnimation(MainActivity.this, R.anim.playbuttonanim);
                buttonZoom.setAnimationListener(this);
                playB.startAnimation(buttonZoom);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        playB.startAnimation(buttonZoom);

    }

}
