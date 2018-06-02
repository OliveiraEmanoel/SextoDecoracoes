package br.com.emanoel.oliveira.sextodecoracoes.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

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
    Button btAddItem;
    Produto_Tecido produtos = new Produto_Tecido();
    String nameEstampa;

    String TAG="CHECKOUT_ACTIVITY";
    int qdade;
    double valor;
    private List<Produto_Tecido> mCart = getCart();//carrego List com os dados já salvos em cart

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);

        totalValorCheckout = findViewById(R.id.tvTotalCheckout);
        totalValorCheckout.setText(f.format(totalCart));


        //myAdapter.atualizaTotal();

        list = findViewById(R.id.lvCheckout);


        myAdapter = new NovoAdapter(mCart, getLayoutInflater(), true, new NovoAdapter.Myinterface() {
            @Override
            public void atualizaValorTotal(double valor) {
                totalValorCheckout.setText(f.format(valor));
            }
        });

        list.setAdapter(myAdapter);



    }


    public void atualizaValorTotal(double valor){

        //this.totalValorCheckout = findViewById(R.id.tvTotalCheckout);

        //totalValorCheckout.setText(f.format(valor));
    }
}
