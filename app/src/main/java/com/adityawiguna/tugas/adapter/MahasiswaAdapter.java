package com.adityawiguna.tugas.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.adityawiguna.tugas.R;
import com.adityawiguna.tugas.model.Mahasiswa;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MahasiswaAdapter extends ArrayAdapter<Mahasiswa> {
    private static class ViewHolder {
        TextView tvName;
        TextView tvNim;
        ImageView imvPhoto;
    }

    public MahasiswaAdapter(@NonNull Context context, int resource, @NonNull List<Mahasiswa> mahasiswas) {
        super(context, resource, mahasiswas);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        ViewHolder viewHolder;

        Mahasiswa mahasiswa = getItem(position);

        if(itemView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            itemView = inflater.inflate(R.layout.item_mahasiswa, parent, false);

            viewHolder.tvNim = (TextView)itemView.findViewById(R.id.tv_nim);
            viewHolder.tvName = (TextView) itemView.findViewById(R.id.tv_name);
            viewHolder.imvPhoto = (ImageView) itemView.findViewById(R.id.imv_photo);
            // Cache the viewHolder object inside the fresh view
            itemView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) itemView.getTag();
        }

        viewHolder.tvName.setText(mahasiswa.getNama());
        viewHolder.tvNim.setText(mahasiswa.getNim());
        viewHolder.imvPhoto.setImageResource(mahasiswa.getFoto());

        return itemView;
    }
}
