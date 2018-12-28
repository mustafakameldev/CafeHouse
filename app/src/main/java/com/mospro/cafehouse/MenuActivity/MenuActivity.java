package com.mospro.cafehouse.MenuActivity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mospro.cafehouse.DataTypes.Item;
import com.mospro.cafehouse.R;

import java.util.ArrayList;
public class MenuActivity extends AppCompatActivity {
    private MenuAdapter adapter;
    private RecyclerView recyclerView ;
    private DatabaseReference mainRef  ;
    private  ArrayList<Item> items  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        items = new ArrayList<>() ;
        mainRef =FirebaseDatabase.getInstance().getReference().child("menu");
        getData();
        recyclerView =(RecyclerView)findViewById(R.id.recycler_MenuActivity) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MenuAdapter(this , items);
        recyclerView.setAdapter(adapter);
    }
    private void getData() {
        mainRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String name = dataSnapshot.child("name").getValue(String.class) ;
                Double price =dataSnapshot.child("price").getValue(Double.class);
                items.add(new Item(name, price));
                Log.d("GETDATA", " name " + name + "price" + price );
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }) ;

    }

}
