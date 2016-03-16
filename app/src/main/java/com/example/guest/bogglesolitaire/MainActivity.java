package com.example.guest.bogglesolitaire;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.addButton) Button mAddButton;
    @Bind(R.id.gridView) GridView mGridView;
    @Bind(R.id.listView) ListView mListView;
    @Bind(R.id.userInput) TextView mUserInput;
    ArrayAdapter mWordAdapter;
    ArrayAdapter mPickedWordsAdapter;


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

        final ArrayList<String> randomLetters = new ArrayList<>();
        final ArrayList<String> randomLettersForAdapter = new ArrayList<>();
        final ArrayList<String> pickedWords = new ArrayList<>();

        for(int i = 0; i < 6; i++) {
            randomLetters.add(consonantsArrayList.get(i));
            randomLettersForAdapter.add(consonantsArrayList.get(i));
        }
        for(int i = 0; i < 2; i++) {
            randomLetters.add(vowelsArrayList.get(i));
            randomLettersForAdapter.add(vowelsArrayList.get(i));
        }


        mWordAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, randomLettersForAdapter);
        mGridView.setAdapter(mWordAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String letter = new String (parent.getItemAtPosition(position).toString());
                mWordAdapter.remove(letter);
                mWordAdapter.insert("", position);
                mWordAdapter.notifyDataSetChanged();
                mUserInput.append(letter);
                mAddButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pickedWords.add(mUserInput.getText().toString());
                        mPickedWordsAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, pickedWords);
                        mListView.setAdapter(mPickedWordsAdapter);
                        mUserInput.setText("");
                        mWordAdapter.clear();
                        mWordAdapter.addAll(randomLetters);
                        mWordAdapter.notifyDataSetChanged();
                    }
                });
            }


        });
    }
}
