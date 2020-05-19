package com.adityawiguna.tugas.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adityawiguna.tugas.FormActivity;
import com.adityawiguna.tugas.R;
import com.adityawiguna.tugas.model.Mahasiswa;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MyViewHolder> {

    private Context context;
    private List<Mahasiswa> mahasiswaList;

    public MahasiswaAdapter(Context context, List<Mahasiswa> mahasiswaList) {
        this.context = context;
        this.mahasiswaList = mahasiswaList;
    }

    @NonNull
    @Override
    public MahasiswaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mahasiswa, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Mahasiswa mahasiswa = mahasiswaList.get(position);
        holder.tvName.setText(mahasiswa.getName());
        holder.tvNim.setText(mahasiswa.getNim());

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inSampleSize = 16;
        Bitmap img = BitmapFactory.decodeFile(mahasiswa.getPath(), options);

        ImageProcess ip = new ImageProcess();
        img = ip.automaticRotateImage(img, mahasiswa.getPath());
        holder.imvMahasiswa.setImageBitmap(img);
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_nim)
        TextView tvNim;

        @BindView(R.id.imv_mahasiswa)
        ImageView imvMahasiswa;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, FormActivity.class);
                    i.putExtra("mode", "detail");
                    i.putExtra("nim", mahasiswaList.get(getAdapterPosition()).getNim());
                    context.startActivity(i);
                }
            });
        }
    }
}
