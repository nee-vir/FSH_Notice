package com.example.fsh_notice;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterationFragment extends Fragment {
    private EditText regEmail;
    private EditText regPassword;
    private EditText regPassword2;
    private Button regButton;
    FirebaseAuth fAuth;
    private EditText regCourse;
    private EditText regPhone;
    private TextView logInLink;
    private EditText regUserName;
    private FirebaseFirestore fStore;
    String uId;


    public RegisterationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registeration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        regEmail = view.findViewById(R.id.reg_email);
        regPassword = view.findViewById(R.id.reg_password);
        regButton = view.findViewById(R.id.register_button);
        regPassword2 = view.findViewById(R.id.reg_password_2);
        logInLink = view.findViewById(R.id.log_in_link);
        regCourse = view.findViewById(R.id.course_edit_text);
        regPhone = view.findViewById(R.id.phone_edit_text);
        regUserName = view.findViewById(R.id.reg_user_name);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        final NavController navController = Navigation.findNavController(view);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regButton.setEnabled(false);
                final String email = regEmail.getText().toString().trim();
                String password = regPassword.getText().toString().trim();
                String password2 = regPassword2.getText().toString().trim();
                final String course = regCourse.getText().toString().trim();
                final String phone = regPhone.getText().toString().trim();
                final String name = regUserName.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    regUserName.setError("Please enter your name");
                    regButton.setEnabled(true);
                } else if (TextUtils.isEmpty(course)) {
                    regCourse.setError("Please enter course");
                    regButton.setEnabled(true);
                } else if (TextUtils.isEmpty(phone)) {
                    regPhone.setError("Please enter your phone number");
                    regButton.setEnabled(true);
                } else if (TextUtils.isEmpty(email)) {
                    regEmail.setError("Please enter your email address");
                    regButton.setEnabled(true);
                } else if (TextUtils.isEmpty(password)) {
                    regPassword.setError("Please enter your password");
                    regButton.setEnabled(true);
                } else if (password.length() < 6) {
                    regPassword.setError("Password must be more than 6 characters long");
                    regButton.setEnabled(true);
                } else if (TextUtils.isEmpty(password2)) {
                    regPassword2.setError("Please confirm your password");
                    regButton.setEnabled(true);
                } else if (!password.matches(password2)) {
                    regPassword2.setError("Passwords do not match");
                    regButton.setEnabled(true);
                } else {
                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                uId = fAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fStore.collection("Students").document(uId);
                                Map<String, Object> user = new HashMap<>();
                                user.put("Username", name);
                                user.put("Course", course);
                                user.put("Phone", phone);
                                user.put("Email", email);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("Result:", "Profile Created for " + uId);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("Result:", "Failed:" + e.toString());
                                    }
                                });
                                Toast.makeText(getActivity(), "Register Successful", Toast.LENGTH_SHORT).show();
                                navController.navigate(R.id.action_registerationFragment_to_menuActivity);
                                getActivity().finish();
                            } else {
                                Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                regButton.setEnabled(true);
                            }
                        }
                    });
                }
            }
        });
        logInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //NavOptions navOptions=new NavOptions.Builder().setPopUpTo(R.id.login_Fragment,true).build();
                //navController.navigate(R.id.action_register_Fragment_to_login_Fragment);
                navController.navigate(R.id.action_registerationFragment_to_loginFragment);
            }
        });
    }
}
