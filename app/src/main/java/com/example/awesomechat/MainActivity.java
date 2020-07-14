package com.example.awesomechat;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.awesomechat.adapter.AwesomeMessageAdapter;
import com.example.awesomechat.model.AwesomeMessage;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView messageListView;
    private AwesomeMessageAdapter adapter;
    private ProgressBar progressBar;
    private Button sendImageButtonn;
    private Button sendMessageButtonn;
    private EditText messageEditText;

    private String userName;

    FirebaseDatabase database;
    DatabaseReference messagesDatabaseReference;
    ChildEventListener messagesChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        messagesDatabaseReference = database.getReference().child("messages");

        progressBar = findViewById(R.id.progressBar);
        sendImageButtonn = findViewById(R.id.sendImageButton);
        sendMessageButtonn = findViewById(R.id.sendMessageButton);
        messageEditText = findViewById(R.id.messageEditText);

        userName = "Default User";

        messageListView = findViewById(R.id.messageListView);
        List<AwesomeMessage> awesomeMessages = new ArrayList<>();
        adapter = new AwesomeMessageAdapter(this, R.layout.messageitem,
                awesomeMessages);
        messageListView.setAdapter(adapter);

        progressBar.setVisibility(ProgressBar.INVISIBLE);

        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                if (s.toString().trim().length() > 0) {
                    sendMessageButtonn.setEnabled(true);
                } else {
                    sendMessageButtonn.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        messageEditText.setFilters(new InputFilter[]
                {new InputFilter.LengthFilter(500)});

        sendMessageButtonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AwesomeMessage message = new AwesomeMessage();
                message.setText(messageEditText.getText().toString());
                message.setName(userName);
                message.setImgUrl(null);

                messagesDatabaseReference.push().setValue(message);

                messageEditText.setText("");
            }
        });

        sendImageButtonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        messagesChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot,  String s) {
                AwesomeMessage message =
                        dataSnapshot.getValue(AwesomeMessage.class);

                adapter.add(message);
            }

            @Override
            public void onChildChanged( DataSnapshot dataSnapshot,  String s) {

            }

            @Override
            public void onChildRemoved( DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved( DataSnapshot dataSnapshot,  String s) {

            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        };

        messagesDatabaseReference.addChildEventListener(messagesChildEventListener);
    }
}