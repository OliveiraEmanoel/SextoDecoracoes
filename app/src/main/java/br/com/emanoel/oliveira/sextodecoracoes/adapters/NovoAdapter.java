package br.com.emanoel.oliveira.sextodecoracoes.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.gms.common.data.DataBufferObserver;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.emanoel.oliveira.sextodecoracoes.R;
import br.com.emanoel.oliveira.sextodecoracoes.activities.BaseActivity;
import br.com.emanoel.oliveira.sextodecoracoes.activities.CheckoutActivity;
import br.com.emanoel.oliveira.sextodecoracoes.modelos.Produto_Tecido;

/**
 * Created by Emanoel de Oliveira on 22/01/2018.
 */

public class NovoAdapter extends BaseAdapter implements DataBufferObserver.Observable{

    private List<Produto_Tecido> mListProdutoTedido;
    private LayoutInflater layoutInflater;
    private boolean mShowCheckbox;
    private Myinterface listener;
    public ArrayList<Long> posArray = new ArrayList<>();
    DecimalFormat f = new DecimalFormat("R$ 0.00");
    long posItem;
    double total;
    String TAG = "NovoAdapter";
    Produto_Tecido curProduct;
    BaseActivity baseActivity;
    CheckoutActivity checkoutActivity;

    public NovoAdapter(List<Produto_Tecido> mListProdutoTedido, LayoutInflater layoutInflater, boolean showCheckbox, Myinterface listener) {
        this.mListProdutoTedido = mListProdutoTedido;
        this.layoutInflater = layoutInflater;
        this.mShowCheckbox = showCheckbox;
        this.listener = listener;
    }

    public interface Myinterface {

        void atualizaValorTotal(double valor);
    }


    @Override
    public int getCount() {

        return mListProdutoTedido.size();
    }

    @Override
    public Object getItem(int position) {
        return mListProdutoTedido.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewItem item;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_carrinho,
                    null);
            item = new ViewItem();

            item.nameEstampa = convertView.findViewById(R.id.tvNomeItem);

            item.qdade = convertView.findViewById(R.id.tvQdadeItem);

            item.valor = convertView.findViewById(R.id.tvValorItem);

            item.cbox = convertView.findViewById(R.id.cbExcluir);

            convertView.setTag(item);
        } else {
            item = (ViewItem) convertView.getTag();
        }

        curProduct = mListProdutoTedido.get(position);

        item.nameEstampa.setText(curProduct.getNomeEstampa());
        item.qdade.setText(String.valueOf(curProduct.getQdade()));
        total = curProduct.getQdade() * curProduct.getPrice();

        //totalzão igual totalzão + total(position)
        item.valor.setText(f.format(total));
        item.cbox.isChecked();

        item.cbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    item.cbox.setChecked(true);
                    Log.d("NOVO_ADAPTER", "getView: " + curProduct.getNomeEstampa() + "  position: " + getItemId(position));
                    mListProdutoTedido.remove(position);
                    Log.d("NOV0_Adapter", "onCheckedChanged: " + baseActivity.cart.size());
                    notifyDataSetChanged();//atualiza list

                    listener.atualizaValorTotal(atualizaTotal());

                    Log.d("NOV0_Adapter", "onCheckedChanged: totalcart = " + baseActivity.totalCart);
                }
            }
        });

        if (!mShowCheckbox) {
            item.cbox.setVisibility(View.GONE);
        } else {
            if (curProduct.selected == true) {
                item.cbox.setChecked(true);
                Log.d("NOVO_ADAPTER", "getView: " + position);

            }else{
            item.cbox.setChecked(false);}
        }

        return convertView;
        
       

    }

    @Override
    public void addObserver(DataBufferObserver dataBufferObserver) {

    }

    @Override
    public void removeObserver(DataBufferObserver dataBufferObserver) {

    }

    public double atualizaTotal(){

        int totalItens = getCount();//tamanho da lista
        double totalGeral = 0;
        Log.e(TAG, "atualizaTotal: "+ "total de itens = " + totalItens );

        for (int i = 0; i < totalItens ; i++) {

             totalGeral =+ mListProdutoTedido.get(i).getPrice()*mListProdutoTedido.get(i).getQdade();

            Log.e(TAG, "atualizaTotal: " + "total geral = "+ totalGeral );

        }

       return totalGeral;

    }


    private class ViewItem {
        TextView nameEstampa;
        TextView qdade;
        TextView valor;
        CheckBox cbox;
    }
}
