package com.example.asus.alumini;


        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText emailText;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        emailText=findViewById(R.id.forget_email);
        mAuth=FirebaseAuth.getInstance();
    }
    public void reset(View view)
    {
        String email=emailText.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email cant be blank", Toast.LENGTH_SHORT).show();
        }else {
            final ProgressDialog p = new ProgressDialog(this);
            p.setMessage("Please wait while we are logging you in");
            p.setTitle("Please wait");
            p.setCancelable(false);
            p.setCanceledOnTouchOutside(false);
            p.show();
            mAuth.sendPasswordResetEmail(email).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    p.dismiss();
                    Toast.makeText(ForgotPasswordActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    p.dismiss();
                    Toast.makeText(ForgotPasswordActivity.this, "Password Reset Sucessfuly", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }
}
