package br.com.emanoel.oliveira.sextodecoracoes.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.emanoel.oliveira.sextodecoracoes.R;
import br.com.emanoel.oliveira.sextodecoracoes.modelos.ConfigSeller;
import br.com.emanoel.oliveira.sextodecoracoes.modelos.GlobalUserID;

public class ConfigActivity extends BaseActivity {

    private EditText etEmailSeller;
    private EditText etTokenSeller;
    private Button btSalvarConfig;
    String email;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);


      etEmailSeller = findViewById(R.id.etEmailVendedor);
      etTokenSeller = findViewById(R.id.etToken);
      btSalvarConfig = findViewById(R.id.btSalvarConfig);

      globalUserID = (GlobalUserID) getApplication(); //getApplicationContext();



      btSalvarConfig.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              if (validate()){

                       ConfigSeller configSeller = new ConfigSeller(email,token,globalUserID.getUsuarioId());
                      myRef.child("config").push().setValue(configSeller);

              }else return;
          }
      });


    }

    private boolean validate() {

        boolean valid = true;


            email = etEmailSeller.getText().toString();
            if (TextUtils.isEmpty(email)) {
                etEmailSeller.setError(getString(R.string.obrigatorio));
                valid = false;
            } else {
                etEmailSeller.setError(null);
            }
            token = etTokenSeller.getText().toString();
            if (TextUtils.isEmpty(token)) {
                etTokenSeller.setError(getString(R.string.obrigatorio));
                valid = false;
            } else {
                etTokenSeller.setError(null);
            }


        return valid;

    }
}
