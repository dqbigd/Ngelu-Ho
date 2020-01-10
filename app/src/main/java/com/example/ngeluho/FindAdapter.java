package com.example.ngeluho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.FindHolder> {
    private ArrayList<FindModel> listFind;
    private Context context;

    public FindAdapter(ArrayList<FindModel> list){

        this.listFind = list;
    }

    @NonNull
    @Override
    public FindHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_finds, parent, false);
        context = view.getContext();
        return new FindHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FindHolder holder, int position) {
        FindModel findModel = listFind.get(position) ;

        Glide.with(holder.itemView.getContext())
                .load(findModel.getPhoto())
                .into(holder.imgPhoto);

        holder.tvTitle.setText(findModel.getTitle());
        holder.tvDesc.setText("Rp. "+ findModel.getDescrib());
    }

    @Override
    public int getItemCount() {
        return listFind.size();
    }

    public class FindHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvTitle, tvDesc;
        public FindHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.imgPhoto);
            tvTitle = itemView.findViewById(R.id.txtJudul);
            tvDesc = itemView.findViewById(R.id.txtDesc);

        }
    }
}
