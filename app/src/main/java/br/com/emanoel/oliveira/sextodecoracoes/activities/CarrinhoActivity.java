package br.com.emanoel.oliveira.sextodecoracoes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.emanoel.oliveira.sextodecoracoes.R;
import br.com.emanoel.oliveira.sextodecoracoes.adapters.NovoAdapter;
import br.com.emanoel.oliveira.sextodecoracoes.modelos.Produto_Tecido;

public class CarrinhoActivity extends BaseActivity {

    ListView list;
    TextView totalValorLista;
    TextView qdadeTotalLista;
    NovoAdapter myAdapter;
    Button btFinalizar;
    Button btAddItem;
    Button btExcluir;
    Produto_Tecido produtos = new Produto_Tecido();
    String nameEstampa;
    String TAG="CARRINHO_ACTIVITY";
    CheckBox checkBox;
    int qdade;
    double valor;
    private List<Produto_Tecido> mCart = getCart();//carrego List com os dados já salvos em cart

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        if (savedInstanceState != null) {
            nameEstampa = bundle.getString("nomeTecido");//codigo
            valor = bundle.getDouble("valor");
            qdade = bundle.getInt("qdade");
        }

        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Carrinho de compras");


        totalValorLista = findViewById(R.id.tvValorTotCarrinho);
        totalValorLista.setText(f.format(totalCart));

        qdadeTotalLista = findViewById(R.id.tvQdadeTotalCarrinho);
        qdadeTotalLista.setText(String.valueOf(qdadePecas));

        list = findViewById(R.id.lvCarrinho);
        checkBox = findViewById(R.id.cbExcluir);

        myAdapter = new NovoAdapter(mCart, getLayoutInflater(), false,null);

        list.setAdapter(myAdapter);



        btFinalizar = findViewById(R.id.btFinalizar);
        btFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkout
                startActivity(new Intent(getApplicationContext(), CheckoutActivity.class));
            }
        });
        btAddItem = findViewById(R.id.btAddItem);
        btAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //back to main
                startActivity(new Intent(getApplicationContext(), Produtos.class));

            }
        });
    }
}
