package com.example.finalproject.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.finalproject.Activities.Parent;
import com.example.finalproject.Activities.TypeSelect;
import com.example.finalproject.CustomToast;
import com.example.finalproject.R;
import com.example.finalproject.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verify_Fragment extends AppCompatActivity implements View.OnClickListener {
    private View view;
    FirebaseAuth mAuth;
    private EditText verify_email,verify_password;
    private Button Login_verify;

    public Verify_Fragment() {

    }

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_layout);
        verify_email = (EditText) findViewById(R.id.verify_email);
        verify_password = (EditText) findViewById(R.id.verify_password);
        Login_verify = (Button) findViewById(R.id.Login_verify);
        mAuth = FirebaseAuth.getInstance();

        Login_verify.setOnClickListener(this);
        checkValidation();

    }

    public View onCreate(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_layout);
        view = inflater.inflate(R.layout.verify_layout, container, false);
        setListeners();
        return view;
    }

    private void setListeners() {
        Login_verify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Login_verify:
                checkValidation();
                break;
        }
    }

    private void checkValidation () {
        // Get email id and password
        String getEmailId = verify_email.getText().toString();
        String getPassword = verify_password.getText().toString();

        // Check patter for email id
        Pattern p = Pattern.compile(Utils.regEx);

        Matcher m = p.matcher(getEmailId);

        // Check for both field is empty or not
        if (getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {
        }
        // Check if email id is valid or not
        else if (!m.find()) {
        }

        // Else do login and do your stuff
        else {
            ///Toast.makeText(getActivity(), "Do Login.", Toast.LENGTH_SHORT)
            //.show();

            mAuth.signInWithEmailAndPassword(getEmailId, getPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(Verify_Fragment.this, Parent.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else {
                        ////Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        new CustomToast().Show_Toast(Verify_Fragment.this, view,
                                task.getException().getMessage());
                        ///progressBar.setVisibility(View.GONE);
                    }
                }
            });
        }
    }
}
