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
import br.com.emanoel.oliveira.sextodecoracoes.modelos.Produto_Tecido;

/**
 * Created by Emanoel de Oliveira on 22/01/2018.
 */

public class NovoAdapter extends BaseAdapter implements DataBufferObserver.Observable{

    private List<Produto_Tecido> mListProdutoTedido;
    private LayoutInflater layoutInflater;
    private boolean mShowCheckbox;
    public ArrayList<Long> posArray = new ArrayList<>();
    DecimalFormat f = new DecimalFormat("R$ 0.00");
    long posItem;
    double total;
    Produto_Tecido curProduct;
    BaseActivity baseActivity;

    public NovoAdapter(List<Produto_Tecido> mListProdutoTedido, LayoutInflater layoutInflater, boolean showCheckbox) {
        this.mListProdutoTedido = mListProdutoTedido;
        this.layoutInflater = layoutInflater;
        this.mShowCheckbox = showCheckbox;
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
                    baseActivity.cart.notifyAll();
                    //atualiza preço

                   baseActivity.totalCart = atualizaTotal();
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

            }else
            item.cbox.setChecked(false);
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

        total = curProduct.getQdade() * curProduct.getPrice();

        baseActivity.totalCart = baseActivity.totalCart - total;
        //item.valor.setText(f.format(total));

        return baseActivity.totalCart;
    }


    private class ViewItem {
        TextView nameEstampa;
        TextView qdade;
        TextView valor;
        CheckBox cbox;
    }
}
