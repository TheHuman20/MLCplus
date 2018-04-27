package com.example.osads.mlcplus;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {

    Button mButtonCreate;
    RadioGroup mRadioGroupGender;
    EditText mPlayerName;
    ImageView mImageViewGender;
    Gender gender;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        mRadioGroupGender = findViewById(R.id.radio_group_gender);
        mButtonCreate = findViewById(R.id.create_player_button);
        mPlayerName = findViewById(R.id.edit_text_name);
        mImageViewGender = findViewById(R.id.image_view_gender);

        mRadioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_button_male:
                        gender = Gender.MALE;
                        mImageViewGender.setBackgroundResource(R.drawable.munch);
                        mImageViewGender.setImageResource(R.drawable.border);
                        break;
                    case R.id.radio_button_female:
                        gender = Gender.FEMALE;
                        mImageViewGender.setBackgroundResource(R.drawable.fem_munch);
                        mImageViewGender.setImageResource(R.drawable.border);
                        break;
                }
            }
        });
    }

    public void onClickCreatePlayer(View view) {
        Intent intent = new Intent(CreateActivity.this, GameActivity.class);
        name = mPlayerName.getText().toString();
        Player player = new Player(name, gender);
        //intent.putExtra("player", player);
        Game.Player = player;
        if (name.equals("") || gender == null) {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.create_activity_toast,
                    Toast.LENGTH_LONG);
            toast.show();
        } else {
            startActivity(intent);
        }
    }
    public void onClickClearName(View view) {
        mPlayerName.setText("");
    }
}
