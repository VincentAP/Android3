package com.example.airy_receipt;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataHolder> {
    private List<Recipe> products;
    private Context mContext;
    RequestOptions option;

    public RecyclerViewAdapter(List<Recipe> products, Context mContext) {
        this.products = products;
        this.mContext = mContext;

        //Request Option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.loading);
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.cardview, viewGroup, false);
        return new DataHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder dataHolder, int i) {
        //yg buat masuking data dr object javany k view yg nampung data utk d tampiling
        final Recipe curr = products.get(i);

        //Log.d("Value", "Data Masuk: " + i + curr.getName());

        if(curr.getRating() > 4.0) {
            dataHolder.rating.setText(String.valueOf(curr.getRating())); //ini viewholderny}
        }
        else {
            dataHolder.rating.setVisibility(View.GONE);
        }
        dataHolder.name.setText(curr.getName());
        dataHolder.author.setText("By: "+curr.getCreator());
        dataHolder.description.setText(curr.getReview());

        //load image dr url dn masukin k imageview dgn Glide
        Glide.with(mContext).load(products.get(i).getImage()).apply(option).into(dataHolder.img);

        //set onclicklistener
        dataHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DataProduct.class);
                intent.putExtra("Image", curr.getImage());
                intent.putExtra("Name", curr.getName());
                intent.putExtra("Description", curr.getReview());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class DataHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView rating;
        private TextView name;
        private TextView author;
        private TextView description;
        private CardView cardView;

        public DataHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            rating = itemView.findViewById(R.id.rate);
            name = itemView.findViewById(R.id.prod_name);
            author = itemView.findViewById(R.id.author);
            description = itemView.findViewById(R.id.description);
            cardView = itemView.findViewById(R.id.cv);
        }
    }
}
