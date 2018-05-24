package br.com.emanoel.oliveira.sextodecoracoes.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import br.com.emanoel.oliveira.sextodecoracoes.R;
import br.com.emanoel.oliveira.sextodecoracoes.modelos.Produto_Tecido;

import static br.com.emanoel.oliveira.sextodecoracoes.activities.BaseActivity.cart;
import static br.com.emanoel.oliveira.sextodecoracoes.activities.BaseActivity.nroItensCart;

public class CustomAdapter extends ArrayAdapter<Produto_Tecido> {
 
    ArrayList<Produto_Tecido> pedido;//fiz mudança aqui ...dataset e private 18/01

    Context mContext;
    int indice;
    Produto_Tecido produtoTedido;
    DecimalFormat f = new DecimalFormat("R$ 0.00");
    RelativeLayout relativeLayout;
 
    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtQdade;
        TextView txtValor;
//        ImageView info;
    }
 

    public CustomAdapter(ArrayList<Produto_Tecido> pedido, Context context) {
        super(context, R.layout.list_carrinho, pedido);
        this.pedido = pedido;
        this.mContext=context;
       // this.grupoColor = grupoColor;

    }
 

 
    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
       Produto_Tecido dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag


 
        final View result;
 
        if (convertView == null) {
 
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_carrinho, parent, false);

            viewHolder.txtName = (TextView) convertView.findViewById(R.id.tvNomeItem);
            viewHolder.txtQdade = (TextView) convertView.findViewById(R.id.tvQdadeItem);
            viewHolder.txtValor = convertView.findViewById(R.id.tvValorItem);

 
            result=convertView;
 
            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;
        relativeLayout = convertView.findViewById(R.id.rlCarrinho);
        //relativeLayout.setBackgroundResource(grupoColor); // setBackground(0xFFA726);

        indice = nroItensCart;
        Log.e("Adapter","indice" + indice);
        double valorTot = (cart.get(indice).getQdade()) * (cart.get(indice).getPrice());
        String total = f.format(valorTot);

        viewHolder.txtName.setText(cart.get(indice).getNomeEstampa());
        viewHolder.txtQdade.setText(String.valueOf(cart.get(indice).getQdade()));
        viewHolder.txtValor.setText(total);

        // Return the completed view to render on screen
        return convertView;
    }
}