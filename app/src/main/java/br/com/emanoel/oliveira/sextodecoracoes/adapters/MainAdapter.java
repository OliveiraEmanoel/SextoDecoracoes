package br.com.emanoel.oliveira.sextodecoracoes.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.emanoel.oliveira.sextodecoracoes.R;
import br.com.emanoel.oliveira.sextodecoracoes.modelos.MyCustonListener;

/**
 * Created by USUARIO on 11/01/2018.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private ArrayList<String> mDataset;
    private ArrayList<Integer> imageResource;
    private ArrayList<Integer> imageChair;
    private ImageView ivMain;
    MyCustonListener listener;


    public MainAdapter(ArrayList<String> mDataset, ArrayList<Integer> imageResource,ArrayList<Integer> imageChair, MyCustonListener listener ) {
        this.mDataset = mDataset;
        this.imageResource = imageResource;
        this.imageChair = imageChair;
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){



        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_fabrics, parent, false);

        // set the view's size, margins, paddings and layout parameters
        //...
        final ViewHolder vh = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LISTENER???","checking listener");
                listener.onItemClicked(v,vh.getAdapterPosition());

            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)  {

        holder.tvTitle.setText(mDataset.get(position));
        holder.imageView.setImageResource(imageResource.get(position));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        //Todo put here reference to all views you have on the cardView
        TextView tvTitle;
        ImageView imageView;
        CardView cv;

        public ViewHolder(View itemView) {
            //todo instantiate all viewa
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            imageView = itemView.findViewById(R.id.iView);
            cv = itemView.findViewById(R.id.cv);


        }
    }
}
