package com.example.ngeluho;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.FindHolder> {
    private ArrayList<FindModel> listFind;
    private Context context;
    private static FragmentManager fragmentManager;

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
        final FindModel findModel = listFind.get(position) ;

        Glide.with(holder.itemView.getContext())
                .load(findModel.getPhoto())
                .into(holder.imgPhoto);

        holder.tvTitle.setText(findModel.getTitle());

        double hrg = Double.valueOf(findModel.getDescrib());
        NumberFormat formatter = new DecimalFormat("#,###,###");
        String harga = "Rp."+formatter.format(hrg);

        holder.tvDesc.setText(harga);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hrg = findModel.getDescrib();

                PresentCalcFragment presentFragment = new PresentCalcFragment();
                Bundle data = new Bundle();
                data.putString("harga", hrg);
                presentFragment.setArguments(data);

                //((Activity)context).finish();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, presentFragment, PresentCalcFragment.class.getSimpleName())
                        .addToBackStack(null)
                        .commit();

            }
        });


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
