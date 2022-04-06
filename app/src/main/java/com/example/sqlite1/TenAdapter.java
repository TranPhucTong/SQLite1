package com.example.sqlite1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TenAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private List<TenSV> tenSVList;

    public TenAdapter(Context context, int layout, List<TenSV> tenSVList) {
        this.context = context;
        this.layout = layout;
        this.tenSVList = tenSVList;
    }

    @Override
    public int getCount() {
        return tenSVList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        TextView tvTen;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.tvTen = (TextView) view.findViewById(R.id.tvTen);

            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        TenSV tenSV = tenSVList.get(i);
        holder.tvTen.setText(tenSV.getTenSV());
//        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "Xóa", Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;

    }
}
