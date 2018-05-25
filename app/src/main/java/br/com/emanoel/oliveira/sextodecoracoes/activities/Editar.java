package br.com.emanoel.oliveira.sextodecoracoes.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;

import br.com.emanoel.oliveira.sextodecoracoes.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Editar extends BaseActivity {

    /**todo
     * obter chav/codigo do arquivo
     * atualizar os dados
     * deletar se for o caso
     * atualizar imagem do produto se necessário, clicando na imagem
     * o codigo de referencia mudara se deletarmos arquivos...usar o key?
     * setar isActive to false if delete button is clicked and check if isNew is/remains true/false
     * */

    @BindView(R.id.ivFotoProdutoEditar)
    ImageView ivFotoProdutoEditar;

    @BindView(R.id.etNomeEditar)
    EditText etNomeEditar;
    @BindView(R.id.etValorEditar)
    EditText etValorEditar;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.etTamanhoEditar)
    EditText etTamanhoEditar;
    @BindView(R.id.etTecidoEditar)
    EditText etTecidoEditar;
    @BindView(R.id.etDetalhesEditar)
    EditText etDetalhesEditar;
    @BindView(R.id.btLimparDescriptionEditar)
    Button btLimparDescriptionEditar;
    @BindView(R.id.llDescription)
    LinearLayout llDescription;
    @BindView(R.id.btSalvarProdutoEditar)
    Button btSalvarProdutoEditar;
    @BindView(R.id.btDeletarProdutoEditar)
    Button btDeletarProdutoEditar;
    @BindView(R.id.tiDetalhesEditar)
    TextInputLayout tiDetalhesEditar;

    private String imageURI,imageFileName;
    private StorageReference myStorageRef;
    private FirebaseAuth mAuth;
    private long itemCount;
    private String codigoRef;
    private String photoUrl,description,dataEntrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        ButterKnife.bind(this);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        final int position = bundle.getInt("Position");
        final String nameFabric = bundle.getString("Name");
        final String imagePath = bundle.getString("Image");
        final String codigo = bundle.getString("Codigo");
        final double price = bundle.getDouble("Price");
        final String tamanhoProduto = bundle.getString("Tamanho");
        final String tecido = bundle.getString("Tecido");
        final String description = bundle.getString("Description");


        Picasso.with(Editar.this)
                //.load("https://firebasestorage.googleapis.com/v0/b/sextodecoraes.appspot.com/o/sextoDir%2Falmofadas%2F12321527_1582971455347134_2369561376305251974_n.jpg?alt=media&token=12fb63a0-ba1b-4924-9357-fa246ad93763")
                //.placeholder(R.drawable.progress_animation)
                .load(imagePath)
                .error(R.drawable.logo)
                .into(ivFotoProdutoEditar, new Callback() {
                    @Override
                    public void onSuccess() {

                        hideProgressDialog();
                    }

                    @Override
                    public void onError() {
                        hideProgressDialog();
                        Toast.makeText(Editar.this, "Erro caregando a imagem", Toast.LENGTH_SHORT).show();
                    }
                });
        //carrega os valores do registro
        etNomeEditar.setText(nameFabric);
        etTamanhoEditar.setText(tamanhoProduto);
        etTecidoEditar.setText(tecido);
        etValorEditar.setText(f.format(price));
        etDetalhesEditar.setText(description);




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
                ivFotoProdutoEditar.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.btLimparDescriptionEditar)
    public void onBtLimparDescriptionEditarClicked() {
    }

    @OnClick(R.id.btSalvarProdutoEditar)
    public void onBtSalvarProdutoEditarClicked() {

        //todo fazer um upload ou update do arquivo atual
    }

    @OnClick(R.id.btDeletarProdutoEditar)
    public void onBtDeletarProdutoEditarClicked() {

        //todo sinalizar o arquivo atual como não ativo: isActive = false
    }

    @OnClick(R.id.ivFotoProdutoEditar)
    public void onViewClicked() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 0);
    }

    static class ViewHolder {
        @BindView(R.id.ivFotoProdutoEditar)
        ImageView ivFotoProdutoEditar;
        @BindView(R.id.etNomeEditar)
        EditText etNomeEditar;
        @BindView(R.id.etValorEditar)
        EditText etValorEditar;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.etTamanhoEditar)
        EditText etTamanhoEditar;
        @BindView(R.id.etTecidoEditar)
        EditText etTecidoEditar;
        @BindView(R.id.etDetalhesEditar)
        EditText etDetalhesEditar;
        @BindView(R.id.llDescription)
        LinearLayout llDescription;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);


        }
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
                        Toast.makeText(Editar.this,"Erro salvando foto! " + exception.toString(),Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
