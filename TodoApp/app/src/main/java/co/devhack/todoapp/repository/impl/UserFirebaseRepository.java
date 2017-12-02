package co.devhack.todoapp.repository.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.devhack.todoapp.domain.model.User;
import co.devhack.todoapp.repository.UserRepository;

/**
 * Created by krlosf on 30/11/17.
 */

public class UserFirebaseRepository implements UserRepository {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    public UserFirebaseRepository() {
        this.mAuth = FirebaseAuth.getInstance();
        this.mDatabase = FirebaseDatabase.getInstance()
                .getReference("users");
    }

    @Override
    public void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (task.isSuccessful()) {
                        
                    }
                }
            });
    }

    @Override
    public void signUp(User user) {

    }
}
