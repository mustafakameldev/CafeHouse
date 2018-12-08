package com.mospro.cafehouse;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText etPassword , etEmail ;
    private Button loginBtn ;
    private FirebaseAuth mAuth ;
    private TextView tvStatus ;
    private FirebaseAuth.AuthStateListener  mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        declare();


    }

    private void declare() {
        etEmail =(EditText) findViewById(R.id.tv_email_AuthActivity) ;
        etPassword=(EditText)findViewById(R.id.tv_password_AuthActivity);
        loginBtn =(Button)findViewById(R.id.btn_login_AuthActivity);
        loginBtn.setOnClickListener(this);
        tvStatus =(TextView)findViewById(R.id.tv_status_AuthActivity);
        mAuth =FirebaseAuth.getInstance() ;
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                 //   startActivity(new Intent(AuthActivity.this, LiveActivity.class));

                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        if(v== loginBtn)
        {
            signIn();
        }
    }

    public void signIn()
    {
        String email = etEmail.getText().toString() ;
        String password = etPassword.getText().toString() ;
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
        {
            tvStatus.setText(R.string.empty);
        }else
        {
            tvStatus.setText("");
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        // startActivity(new Intent(AuthActivity.this , MainActivity.class));
                    }else
                    {
                        Toast.makeText(AuthActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("Log in", "onComplete: ");
                    }
                }
            }) ;
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

}
