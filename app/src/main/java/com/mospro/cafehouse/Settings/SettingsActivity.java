package com.mospro.cafehouse.Settings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mospro.cafehouse.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText nameEt , tablesEt ;
    private Button nextBtn ;
    private DatabaseReference mainRef ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        declare();
    }
    private void declare() {
        nameEt=(EditText)findViewById(R.id.editText_name_settingsActivity) ;
        tablesEt=(EditText)findViewById(R.id.editText_tableNo_SettingsActivity);
        mainRef =FirebaseDatabase.getInstance().getReference().child("settings");
        nextBtn =(Button)findViewById(R.id.btn_next_settingsActivity);
        nextBtn.setOnClickListener(this);
    }
    void setData()
    {
        Integer tablesNo = Integer.valueOf(tablesEt.getText().toString());
        String name =nameEt.getText().toString() ;
        if(tablesNo!= 0|| TextUtils.isEmpty(name))
            {
                mainRef.child("name").setValue(name) ;
                mainRef.child("tablesNo").setValue(tablesNo);
                Toast.makeText(this, "good", Toast.LENGTH_SHORT).show();
            }
    }

    @Override
    public void onClick(View v) {
        if(v==nextBtn)
        {
            setData();
        }
    }
}
