package br.com.emanoel.oliveira.sextodecoracoes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.emanoel.oliveira.sextodecoracoes.R;
import br.com.emanoel.oliveira.sextodecoracoes.adapters.NovoAdapter;
import br.com.emanoel.oliveira.sextodecoracoes.modelos.Produto_Tecido;

public class CheckoutActivity extends BaseActivity implements NovoAdapter.Myinterface {

    ListView list;
    TextView totalValorCheckout;
    NovoAdapter myAdapter;
    NovoAdapter.Myinterface listener;
    CheckBox checkBox;
    Button btEfetuarPagamento;
    Produto_Tecido produtos = new Produto_Tecido();
    String nameEstampa;

    String TAG="CHECKOUT_ACTIVITY";
    int qdade;
    double valorTotal;
    private List<Produto_Tecido> mCart = getCart();//carrego List com os dados já salvos em cart

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);

        totalValorCheckout = findViewById(R.id.tvTotalCheckout);
        totalValorCheckout.setText(f.format(totalCart));

        valorTotal = totalCart;//se não for deletado itens...

        btEfetuarPagamento = findViewById(R.id.btEfetuarPagamento);



        //myAdapter.atualizaTotal();

        list = findViewById(R.id.lvCheckout);


        myAdapter = new NovoAdapter(mCart, getLayoutInflater(), true, new NovoAdapter.Myinterface() {
            @Override
            public void atualizaValorTotal(double valor) {
                totalValorCheckout.setText(f.format(valor));
                valorTotal = valor;//se for deletado algum item o valorTotal será atualizado
            }
        });

        list.setAdapter(myAdapter);

        btEfetuarPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valorTotal>0){
                    /* chamar dados pagamento acticity
                    * passar dados para efetuar o pedido
                    * salvar pedido
                    * */


                    startActivity(new Intent(getApplicationContext(),PagamentoActivity.class));

                }else Toast.makeText(getApplicationContext(),"Não há itens no pedido!!",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void atualizaValorTotal(double valor){

        //this.totalValorCheckout = findViewById(R.id.tvTotalCheckout);

        //totalValorCheckout.setText(f.format(valor));
    }
}
