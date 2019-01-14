package com.fyp115494258.restreserveadmin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fyp115494258.restreserveadmin.Common.Common;
import com.fyp115494258.restreserveadmin.Model.User;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    EditText edtPhone,edtPassword;
    Button btnSignIn;










    //Referred to the following youtube video https://www.youtube.com/watch?v=Ad41Bh704ms&t=0s&list=PLaoF-xhnnrRW4lXuIhNLhgVuYkIlF852V&index=2
    //to aid me in setting up the Log in

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);



        edtPassword = (EditText)findViewById(R.id.edtPassword);
        edtPhone = (EditText)findViewById(R.id.edtPhone);

        btnSignIn = (Button)findViewById(R.id.btnSignIn);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();


                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        if(dataSnapshot.child(edtPhone.getText().toString()).exists()){



                            mDialog.dismiss();

                            User user =dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edtPassword.getText().toString())) {

                                Intent homeIntent= new Intent(SignIn.this,Home.class);





                                //creating variable to store current user
                                Common.currentUser=user;
                                startActivity(homeIntent);
                                finish();

                            }
                            else{
                                Toast.makeText(SignIn.this, "Sign In failed", Toast.LENGTH_SHORT).show();

                            }




                        }else{
                            Toast.makeText(SignIn.this, "User does not exist", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError){

                    }

                });
            }
        });


    }





}
