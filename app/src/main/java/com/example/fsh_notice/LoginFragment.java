package com.example.fsh_notice;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private EditText logInEmail;
    private EditText logInPassword;
    private Button loginButton;
    private TextView regLink;
    private FirebaseAuth fAuth;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onViewCreated(view, savedInstanceState);
        logInEmail=view.findViewById(R.id.log_in_email);
        logInPassword=view.findViewById(R.id.log_in_password);
        loginButton=view.findViewById(R.id.login_button);
        regLink=view.findViewById(R.id.reg_page_link);
        fAuth=FirebaseAuth.getInstance();
        final NavController navController= Navigation.findNavController(view);
        if(fAuth.getCurrentUser()!=null){
            navController.navigate(R.id.action_loginFragment_to_menuActivity);
            getActivity().finish();
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setEnabled(false);
                String lemail=logInEmail.getText().toString().trim();
                String lpassword=logInPassword.getText().toString().trim();
                if(TextUtils.isEmpty(lemail)){
                    logInEmail.setError("Email is required");
                    loginButton.setEnabled(true);
                }
                else if(TextUtils.isEmpty(lpassword)){
                    logInPassword.setError("Password is required");
                    loginButton.setEnabled(true);
                }
                else{
                    fAuth.signInWithEmailAndPassword(lemail,lpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getActivity(),"Log in Successful",Toast.LENGTH_SHORT).show();
                                navController.navigate(R.id.action_loginFragment_to_menuActivity);
                                getActivity().finish();//to finish the fragment
                            }
                            else{
                                Toast.makeText(getActivity(),"Log in Failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                loginButton.setEnabled(true);
                            }
                        }
                    });
                }
            }
        });
        regLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_loginFragment_to_registerationFragment);
            }
        });

    }

}
