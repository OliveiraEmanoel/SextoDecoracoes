package br.com.emanoel.oliveira.sextodecoracoes.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import br.com.emanoel.oliveira.sextodecoracoes.R;
import br.com.emanoel.oliveira.sextodecoracoes.activities.BaseActivity;
import br.com.emanoel.oliveira.sextodecoracoes.activities.DetalhesActivity;
import br.com.emanoel.oliveira.sextodecoracoes.modelos.Almofada;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProdutoRecyclerViewAdapter extends RecyclerView.Adapter<ProdutoRecyclerViewAdapter.ViewHolder> {

    private List<Almofada> almofadas;
    private String chave;
    private Context context;
    String TAG = "RECYCLERVIEW_HOLDER";
    private ProgressDialog progressDialog;
    View v;
    private DecimalFormat f = new DecimalFormat("'R$' 0.00");
    private DecimalFormat value = new DecimalFormat("0.00");
    private double valor;


    public ProdutoRecyclerViewAdapter(Context context, List<Almofada> almofadas) {
        this.almofadas = almofadas;
        this.context = context;
    }

    private Context getContext() {

        return context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new ViewHolder(v);


    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


       Almofada almofada = almofadas.get(position);
        Log.d(TAG, "position = " + position + "title = " + almofada.getNome());
        //TextView tvNameItemRoupas = (TextView) v.findViewById(R.id.tv_name_item_roupas);

        holder.tvNameItemRoupas.setText(almofada.getNome());
        valor = almofada.getPrice();
        String valorFormatado = f.format(valor);
        holder.tvPriceItemRoupas.setText(valorFormatado);

        if(almofada.getFotoPath()!=null) {
            showProgressDialog();

            Picasso.with(getContext())
                    //.load("https://firebasestorage.googleapis.com/v0/b/sextodecoraes.appspot.com/o/sextoDir%2Falmofadas%2F12321527_1582971455347134_2369561376305251974_n.jpg?alt=media&token=12fb63a0-ba1b-4924-9357-fa246ad93763")
                    //.placeholder(R.drawable.progress_animation)
                    .load(almofada.getFotoPath())
                    .error(R.drawable.logo)
                    .into(holder.ivItemRoupas, new Callback() {
                        @Override
                        public void onSuccess() {
                            hideProgressDialog();
                        }

                        @Override
                        public void onError() {
                            hideProgressDialog();
                            Toast.makeText(getContext(), "Erro caregando a imagem", Toast.LENGTH_SHORT).show();
                                                   }
                    });
        }else{

            Toast.makeText(getContext(), "Erro caregando a imagem", Toast.LENGTH_SHORT).show();
            holder.tvNameItemRoupas.setText("");
            holder.tvPriceItemRoupas.setText("");
        }

    }

    @Override
    public int getItemCount() {
        return almofadas.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_item_almofada)
        ImageView ivItemRoupas;
        @BindView(R.id.tv_name_item_almofada)
        TextView tvNameItemRoupas;
        @BindView(R.id.tv_price_item_almofada)
        TextView tvPriceItemRoupas;
        @BindView(R.id.ll_item_almofadas)
        LinearLayout llItemRoupas;
        @BindView(R.id.cvToYou)
        CardView cvToYou;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            v.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            //get information from adapter position
            Almofada almofada = almofadas.get(getAdapterPosition());

            int position = getAdapterPosition();
            Intent intent = new Intent(getContext(), DetalhesActivity.class);
            intent.putExtra("Image", almofada.getFotoPath());
            intent.putExtra("Name", almofada.getNome());
            intent.putExtra("Codigo", almofada.getCodigo());
            intent.putExtra("Price", almofada.getPrice());
            intent.putExtra("Tamanho", almofada.getTamanho());
            intent.putExtra("Tecido", almofada.getTecido());
            intent.putExtra("Description", almofada.getDescription());
            intent.putExtra("Position",position);
            intent.putExtra("ProdutoId", BaseActivity.produtoKey.get(position));

            getContext().startActivity(intent);
        }


    }



    public void showProgressDialog() {

        if (progressDialog == null) {

            progressDialog = new ProgressDialog(getContext());

            progressDialog.setCancelable(false);

            progressDialog.setMessage("Carregando...");

        }
        progressDialog.show();
    }

    public void hideProgressDialog() {

        if (progressDialog != null && progressDialog.isShowing()) {

            progressDialog.dismiss();

        }

    }

}
