package br.com.emanoel.oliveira.sextodecoracoes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.emanoel.oliveira.sextodecoracoes.R;
import br.com.emanoel.oliveira.sextodecoracoes.modelos.Produto_Tecido;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalhesActivity extends BaseActivity {


    @BindView(R.id.tvNomeFabric_Details)
    TextView tvNomeFabricDetails;
    @BindView(R.id.iv_Details)
    ImageView ivDetails;
    @BindView(R.id.etCodigoProdutoDetalhes)
    EditText etCodigoProdutoDetalhes;
    @BindView(R.id.etTamanhoProdutoDetalhes)
    EditText etTamanhoProdutoDetalhes;
    @BindView(R.id.etTecidoProdutoDetalhes)
    EditText etTecidoProdutoDetalhes;
    @BindView(R.id.etDescricaoProdutoDetalhes)
    EditText etDescricaoProdutoDetalhes;
    @BindView(R.id.etOrigemProdutoDetalhes)
    EditText etOrigemProdutoDetalhes;
    @BindView(R.id.etValorProdutoDetalhes)
    EditText etValorProdutoDetalhes;
    @BindView(R.id.etQdadeDetalhes)
    EditText etQdadeDetalhes;
    private ImageView ivAddCart;
    final List<Produto_Tecido> cart = getCart();
    int position;
    String nameFabric;
    String imagePath;
    String codigo;
    double price;
    private String chaveProduto;
    String tamanhoProduto;
    String tecido;
    String description;
    int qdade = 1;

    Produto_Tecido produtoTedido;

    double valorTecido;

    boolean selected = false;
    boolean sample = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        ButterKnife.bind(this);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        position = bundle.getInt("Position");
        nameFabric = bundle.getString("Name");
        imagePath = bundle.getString("Image");
        codigo = bundle.getString("Codigo");
        price = bundle.getDouble("Price");
        tamanhoProduto = bundle.getString("Tamanho");
        tecido = bundle.getString("Tecido");
        description = bundle.getString("Description");
        chaveProduto = bundle.getString("ProdutoId");

        Log.e("Detalhes", "onCreate: " + "ChaveProduto= " + chaveProduto);

        tvNomeFabricDetails.setText(nameFabric);
        etCodigoProdutoDetalhes.setText(codigo);
        etCodigoProdutoDetalhes.setInputType(0);//disabling edit
        etValorProdutoDetalhes.setText(f.format(price));
        etValorProdutoDetalhes.setInputType(0);
        etTamanhoProdutoDetalhes.setText(tamanhoProduto);
        etTamanhoProdutoDetalhes.setInputType(0);
        etTecidoProdutoDetalhes.setText(tecido);
        etTecidoProdutoDetalhes.setInputType(0);
        etDescricaoProdutoDetalhes.setText(description);
        etDescricaoProdutoDetalhes.setInputType(0);

        etQdadeDetalhes.setSelectAllOnFocus(true);//evita que valores padroes se sobreponham

        actionBar = getSupportActionBar();
        actionBar.setTitle("Detalhes");
        actionBar.setHomeButtonEnabled(true);


        ivDetails.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                if (userIsAdmin) {

                    Intent intent = new Intent(DetalhesActivity.this, Editar.class);


                    intent.putExtra("Image", imagePath);
                    intent.putExtra("Name", nameFabric);
                    intent.putExtra("Codigo", codigo);
                    intent.putExtra("Price", price);
                    intent.putExtra("Tamanho", tamanhoProduto);
                    intent.putExtra("Tecido", tecido);
                    intent.putExtra("Description", description);
                    intent.putExtra("Position", position);
                    intent.putExtra("ProdutoId", chaveProduto);

                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });


        if (imagePath != null) {
            showProgressDialog();

            Picasso.with(this)
                    //.load("https://firebasestorage.googleapis.com/v0/b/sextodecoraes.appspot.com/o/sextoDir%2Falmofadas%2F12321527_1582971455347134_2369561376305251974_n.jpg?alt=media&token=12fb63a0-ba1b-4924-9357-fa246ad93763")
                    //.placeholder(R.drawable.progress_animation)
                    .load(imagePath)
                    .error(R.drawable.logo)
                    .into(ivDetails, new Callback() {
                        @Override
                        public void onSuccess() {
                            hideProgressDialog();
                        }

                        @Override
                        public void onError() {
                            hideProgressDialog();
                            Toast.makeText(DetalhesActivity.this, "Erro caregando a imagem", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {

            Toast.makeText(DetalhesActivity.this, "Erro caregando a imagem", Toast.LENGTH_SHORT).show();
            ivDetails.setImageResource(R.drawable.logo);

        }

        ivAddCart = findViewById(R.id.ivAddCart);
        //ivSample = findViewById(R.id.ivSample);

        ivAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CarrinhoActivity.class);
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra("nomeTecido", codigo);
                if (!sample) {
                    valorTecido = price;
                    intent.putExtra("valor", valorTecido);
                } else {
                    valorTecido = 0.00;
                    intent.putExtra("valor", valorTecido);
                }
                try {
                    qdade = Integer.parseInt(etQdadeDetalhes.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(DetalhesActivity.this, "É preciso digitar a qdade desejada!", Toast.LENGTH_SHORT).show();
                    etQdadeDetalhes.setText("2");
                }

                if (qdade < 1) {
                    qdade = 2;
                }
                intent.putExtra("qdade", qdade);

                cart.add(new Produto_Tecido(codigo, valorTecido, qdade, selected));
                nroItensCart++;
                qdadePecas = +qdade;
                totalCart = totalCart + valorTecido * qdade;//variavel que guarda a soma dos valores dos itens
                //intent.putExtra("image position", actualPosition);

                startActivity(intent);

            }
        });

    }


}
