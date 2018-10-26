package br.com.emanoel.oliveira.sextodecoracoes.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;

import br.com.emanoel.oliveira.sextodecoracoes.R;
import br.com.emanoel.oliveira.sextodecoracoes.modelos.Almofada;

public class CadastroProdutos extends BaseActivity {

    private Button btBrouse, btSalvar, btLimpar;
    private EditText etTamanho, etValor, etTecido, etNome, etDetalhes;
    private ImageView ivProduto;
    private String imageURI,imageFileName;
    private StorageReference myStorageRef;
    private FirebaseAuth mAuth;
    private long itemCount;
    private String codigoRef;
    private String photoUrl,description,dataEntrada;
    private boolean isActive = true;
    private CheckBox cbNewProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produtos);


        // FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mAuth = FirebaseAuth.getInstance();

        btBrouse = findViewById(R.id.btBrowse);
        btLimpar = findViewById(R.id.btLimparDescription);
        btSalvar = findViewById(R.id.btSalvarProduto);
        ivProduto = findViewById(R.id.ivFotoProduto);
        etTecido = findViewById(R.id.etTecido);
        etNome = findViewById(R.id.etNome);
        etTamanho = findViewById(R.id.etTamanho);
        etValor = findViewById(R.id.etValor);
        etDetalhes = findViewById(R.id.etDetalhes);
        cbNewProduct = findViewById(R.id.cb_NewProduct);

        btBrouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //browse for images stored in user device
//                Intent intent = new Intent(Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                checkItemCount();
                Log.d("CHECK_ITEM_COUNT", "onDataChange: " + itemCount);
                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);

            }
        });

        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etDetalhes.setText("");
            }
        });

        cbNewProduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isNovidade = true;
                }else {isNovidade = false;}
            }
        });




        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validate();

                if (!validate()){
                    return;
                }

                checkItemCount();

                codigoRef = "ALM-" + (itemCount+1);

                Log.d("SALVANDO_DADOS", "onClick: " + codigoRef);

                addRegistro(etNome.getText().toString().toUpperCase(),
                            Double.parseDouble(etValor.getText().toString()),
                            description,
                            photoUrl,
                            dataEntrada,
                            etTamanho.getText().toString(),
                            etTecido.getText().toString().toUpperCase(),
                            codigoRef,
                            isActive,isNovidade);


            }
        });
    }

    private void addRegistro(String nome, double price,String description,String fotoPath,String dataIn,
                             String tamanho,String tecido,String codigo,boolean isActive,boolean isNovidade) {

        Almofada almofada = new Almofada(nome,price,description,photoUrl,dataIn,tamanho,tecido,codigo,isActive,isNovidade);
        myRef.child("almofadas").push().setValue(almofada);
    }

    private boolean validate() {
        dataEntrada = String.valueOf(Calendar.getInstance().getTime());
        boolean valid = true;
        if (imageURI!=null) {


            String nome = etNome.getText().toString().toUpperCase();
            if (TextUtils.isEmpty(nome)) {
                etNome.setError(getString(R.string.obrigatorio));
                valid = false;
            } else {
                etNome.setError(null);
            }
            String valunit = etValor.getText().toString();
            if (TextUtils.isEmpty(valunit)) {
                etValor.setError(getString(R.string.obrigatorio));
                valid = false;
            } else {
                etValor.setError(null);
            }

            String valM = etTamanho.getText().toString();
            if (TextUtils.isEmpty(valM)) {
                etTamanho.setError(getString(R.string.obrigatorio));
                valid = false;
            } else {
                etTamanho.setError(null);
            }
            String valG = etTecido.getText().toString().toUpperCase();
            if (TextUtils.isEmpty(valG)) {
                etTecido.setError(getString(R.string.obrigatorio));
                valid = false;
            } else {
                etTecido.setError(null);
                //description = etTecido.getText().toString();
            }
            String valGG = etDetalhes.getText().toString().toUpperCase();
            if (TextUtils.isEmpty(valGG)) {
                etDetalhes.setError(getString(R.string.obrigatorio));
                valid = false;
            } else {
                etDetalhes.setError(null);
                description = etDetalhes.getText().toString().toUpperCase();
            }
        } else{

            Toast.makeText(this,"Clic no botão BROWSE para escolher uma foto.",Toast.LENGTH_LONG).show();
            valid = false;
        }
            return valid;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if result is ok shows the image in imageview
        super.onActivityResult(requestCode, resultCode, data);
        //ContentResolver c ;

        if (resultCode == RESULT_OK){
            Uri targetUri = data.getData();

            imageURI =  getRealPathFromURI(this,targetUri);
            imageFileName = getRealTitleFromURI(this,targetUri);

            salvarFotoOnCloud();


            //Toast.makeText(this,imageURI,Toast.LENGTH_LONG).show();
            Log.d("Cadastro",imageFileName);
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
               ivProduto.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

//    public String getRealPathFromURI(Context context, Uri contentUri) {
//        Cursor cursor = null;
//
//        try {
//            String[] proj = { MediaStore.Images.Media.DATA };
//
//            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
//            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            cursor.moveToFirst();
//            return cursor.getString(column_index);
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//        }
//    }
//
//    public String getRealTitleFromURI(Context context, Uri contentUri) {
//        Cursor cursor = null;
//
//        try {
//            String[] proj = { MediaStore.Images.Media.DISPLAY_NAME };
//            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
//
//            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);// getColumnIndexOrThrow(MediaStore.Images.Media.TITLE);
//            cursor.moveToFirst();
//            return cursor.getString(column_index);
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//        }
//    }

    public void checkItemCount(){

        myRef.child("almofadas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    itemCount = dataSnapshot.getChildrenCount();


                    Log.d("CHECK_ITEM_COUNT", "onDataChange: " + itemCount);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //todo

                Log.d("Cadastro.class", "onCancelled: " + databaseError);
            }
        });


    }

    public void salvarFotoOnCloud(){

        myStorageRef = FirebaseStorage.getInstance().getReference("sextoDir/almofadas");
        Uri file = Uri.fromFile(new File(imageURI));



        myStorageRef.child(imageFileName.toLowerCase()).putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();

                        photoUrl = downloadUrl.toString();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        Toast.makeText(CadastroProdutos.this,"Erro salvando foto! " + exception.toString(),Toast.LENGTH_SHORT).show();
                    }
                });


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        checkItemCount();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        //Toast.makeText(CadastroProdutos.this,currentUser.toString(),Toast.LENGTH_SHORT).show();
        //updateUI(currentUser);
    }


}
