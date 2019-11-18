package br.com.emanoel.oliveira.sextodecoracoes.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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
import com.google.firebase.auth.FirebaseUser;

import br.com.emanoel.oliveira.sextodecoracoes.R;
import br.com.emanoel.oliveira.sextodecoracoes.modelos.GlobalUserID;

public class LoginActivity extends BaseActivity {


    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    private String TAG = "AUTH_FIREBASE";
    private String email;
    private String password;
    private EditText etEmail;
    private EditText etSenha;
    private TextView tvClick;
    private TextView tvTitle;
    private GlobalUserID globalUserID;
    private boolean internet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        internet = false;
        internet = isConnected();
        if (!internet) {

            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_tamanho);
            dialog.show();
            Button sair = (Button) dialog.findViewById(R.id.btDialog);
            sair.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.exit(0);
                }
            });
        }

        //setting buttons

        tvClick = findViewById(R.id.tV_cadastro_login);
        tvTitle = findViewById(R.id.tv_title_login);
        final Button btEntrar = findViewById(R.id.bt_entrar_login);

        //setting edittext
        etEmail = findViewById(R.id.eT_email_login);
        etSenha = findViewById(R.id.eT_senha_login);


        //setting up firebese auth
        try {
            mAuth = FirebaseAuth.getInstance();
        } catch (Exception error) {
            Log.e(TAG, "Setting instance: " + error);
        }
        globalUserID = (GlobalUserID) getApplication(); //getApplicationContext();


        //checking if user exists
        if (mAuth.getCurrentUser() != null) {

            globalUserID.setUsuarioId(mAuth.getCurrentUser().getUid());
            btEntrar.setText("Entrar");
            tvTitle.setText(R.string.confirme_sua_senha);
            etEmail.setText(mAuth.getCurrentUser().getEmail());
            etEmail.setKeyListener(null);

            tvClick.setText(R.string.esqueci_minha_senha_click_aqui);


        }


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        //listener to buttons

        tvClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setting email and password
                email = etEmail.getText().toString();
                password = etSenha.getText().toString();
                if (mAuth.getCurrentUser() != null) {

                    mAuth.sendPasswordResetEmail(mAuth.getCurrentUser().getEmail());
                    Toast.makeText(LoginActivity.this, "Senha enviada para seu email!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btEntrar.getText().toString().equals("Cadastrar")) {
                    //setting email and password
                    email = etEmail.getText().toString();
                    password = etSenha.getText().toString();

                    cadastrar(email, password);
                } else {
                    entrar();
                }


            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }


    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void cadastrar(String email, String password) {
        Log.d(TAG, "createUser:" + email);

        if (!validateForm()) {

            return;

        }

        showProgressDialog();

        //Create account

        try {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());


                            if (!task.isSuccessful()) {
                               Toast.makeText(LoginActivity.this, R.string.auth_failed + task.getException().toString(),
                                        Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "createUserWithEmail:onComplete:" + task.getException());
                            } else {

                                Toast.makeText(LoginActivity.this, R.string.auth_success,
                                        Toast.LENGTH_SHORT).show();

                                entrar();

                            }
                            hideProgressDialog();
                            // ...
                        }
                    });
        } catch (Exception error) {

            myToastCurto("Usuário/Senha invalido(a)");
        }
    }

    private void entrar() {

        showProgressDialog();

        Log.d(TAG, "signIn:" + email);

        if (!validateForm()) {

            return;

        }


        try {
            mAuth.signInWithEmailAndPassword(etEmail.getText().toString(), etSenha.getText().toString()).

                    addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());


                            if (!task.isSuccessful()) {
                                Log.w(TAG, "signInWithEmail:failed", task.getException());
                                etSenha.setText("");//clear etsenha

                                Toast.makeText(LoginActivity.this, R.string.auth_failed,
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                //habilitar cadastro?
                                isUserAdmin(etEmail.getText().toString());

                                startActivity(new Intent(LoginActivity.this, Produtos.class));
                                finish();
                            }

                            hideProgressDialog();
                            // ...
                        }
                    });
        }catch (Exception e){

            myToastCurto("Erro de login" + TAG );
        }
    }

    //validate inputs from email and password
    private boolean validateForm() {

        boolean valid = true;


        String email = etEmail.getText().toString();

        if (TextUtils.isEmpty(email)) {

            etEmail.setError(getString(R.string.obrigatorio));

            valid = false;

        } else {

            etEmail.setError(null);

        }


        String password = etSenha.getText().toString();

        if (TextUtils.isEmpty(password)) {

            etSenha.setError(getString(R.string.obrigatorio));

            valid = false;

        } else {

            etSenha.setError(null);

        }


        return valid;

    }

}
