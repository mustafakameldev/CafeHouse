package com.mospro.cafehouse.LiveActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.mospro.cafehouse.MenuActivity.MenuActivity;
import com.mospro.cafehouse.R;

public class LiveActivity extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    OrdersFragment fragment = new OrdersFragment() ;
                    getSupportFragmentManager().beginTransaction().replace(R.id.container1 ,fragment).commit() ;
                    return true;
                case R.id.navigation_notifications:
                    BillsFragment fragment1 = new BillsFragment() ;
                    getSupportFragmentManager().beginTransaction().replace(R.id.container1 ,fragment1).commit() ;
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        OrdersFragment fragment = new OrdersFragment() ;
        getSupportFragmentManager().beginTransaction().replace(R.id.container1 ,fragment).commit() ;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu , menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_action)
        {
            startActivity(new Intent(LiveActivity.this , MenuActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
