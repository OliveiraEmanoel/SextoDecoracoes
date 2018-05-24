package br.com.emanoel.oliveira.sextodecoracoes.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.emanoel.oliveira.sextodecoracoes.R;
import br.com.emanoel.oliveira.sextodecoracoes.adapters.NovoAdapter;
import br.com.emanoel.oliveira.sextodecoracoes.modelos.Produto_Tecido;

public class CheckoutActivity extends BaseActivity {

    ListView list;
    TextView totalValorCheckout;
    NovoAdapter myAdapter;
    Button btExcluir;
    Button btAddItem;
    Produto_Tecido produtos = new Produto_Tecido();
    String nameEstampa;
    CheckBox checkBox;
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

        list = findViewById(R.id.lvCheckout);
        //checkBox = findViewById(R.id.cbExcluir);


        myAdapter = new NovoAdapter(mCart,getLayoutInflater(),true);

        list.setAdapter(myAdapter);

        // Log.e("Carrinho", "global Arraylist size = " + globalArray.size());
        //checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          //  @Override
          //  public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


        //todo
        //int item_position_on_list = list.getCheckedItemPosition();
           //     Log.d(TAG, "onCheckedChanged: " + item_position_on_list);
        //Log.d(TAG, "onCheckedChanged: "+ item_position_on_list);
        //get position from list adapter and create an array??
        //how to pass this position to btexcluir?
         // }
        //});

        btExcluir = findViewById(R.id.btExcluirItens);
        btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




    }
}
