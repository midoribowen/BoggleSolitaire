package com.example.guest.bogglesolitaire;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.gridView) GridView mGridView;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        Resources res = getResources();

        String[] consonants = res.getStringArray(R.array.consonants);
        ArrayList<String> consonantsArrayList = new ArrayList<>(Arrays.asList(consonants));
        Collections.shuffle(consonantsArrayList);

        String[] vowels = res.getStringArray(R.array.vowels);
        ArrayList<String> vowelsArrayList = new ArrayList<>(Arrays.asList(vowels));
        Collections.shuffle(vowelsArrayList);

        ArrayList<String> randomLetters = new ArrayList<>();

        for(int i = 0; i < 6; i++) {
            randomLetters.add(consonantsArrayList.get(i));
        }
        for(int i = 0; i < 2; i++) {
            randomLetters.add(vowelsArrayList.get(i));
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, randomLetters);
        mGridView.setAdapter(adapter);
    }
}
