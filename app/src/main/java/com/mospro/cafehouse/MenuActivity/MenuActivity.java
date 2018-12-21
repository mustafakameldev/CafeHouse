package com.mospro.cafehouse.MenuActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mospro.cafehouse.DataTypes.Item;
import com.mospro.cafehouse.R;

import java.util.ArrayList;
public class MenuActivity extends AppCompatActivity {
    MenuAdapter adapter;
    RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ArrayList<Item> items = new ArrayList<>() ;
        items.add(new Item("tea" , 12.12)) ;
        items.add(new Item("ice tea" , 12.12)) ;
        items.add(new Item("coffee break" , 12.12)) ;
        recyclerView =(RecyclerView)findViewById(R.id.recycler_MenuActivity) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MenuAdapter(this , items);
        recyclerView.setAdapter(adapter);
    }
}
