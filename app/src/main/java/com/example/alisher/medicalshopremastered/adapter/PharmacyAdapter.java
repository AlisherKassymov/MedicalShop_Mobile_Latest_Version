package com.example.alisher.medicalshopremastered.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alisher.medicalshopremastered.R;
import com.example.alisher.medicalshopremastered.activity.PharmacyItemActivity;
import com.example.alisher.medicalshopremastered.enitity.Medical_Enterprise;
import com.example.alisher.medicalshopremastered.enitity.Pharmacy;
import com.example.alisher.medicalshopremastered.interfaces.ItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class PharmacyAdapter extends RecyclerView.Adapter<PharmacyAdapter.MyViewHolder> implements Filterable {
    private List<Pharmacy> itemsData;
    private List<Pharmacy> itemsFilteredData;
    private Context context;


    public PharmacyAdapter(List<Pharmacy> itemsData, Context context){
        this.itemsData=itemsData;
        this.itemsFilteredData=itemsData;
        this.context = context;
    }


    @Override
    public PharmacyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.pharmacy_card,parent,false);
        final MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position){
        final Pharmacy pharmacy=itemsFilteredData.get(position);
        holder.textView1.setText(pharmacy.getPharmName());
        holder.textView2.setText(pharmacy.getTime_at());


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, final int position, boolean isLongClick) {
                if(isLongClick){
                            Pharmacy pharmacy=itemsData.get(position);
                            Intent intent=new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:"+pharmacy.getPharmPhone()));
                            context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount(){
        return itemsFilteredData.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    itemsFilteredData = itemsData;
                } else {
                    List<Pharmacy> filteredList = new ArrayList<>();
                    for (Pharmacy row : itemsData) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getPharmName().toLowerCase().contains(charString.toLowerCase()) || row.getTime_at().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    itemsFilteredData = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = itemsFilteredData;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                itemsFilteredData = (ArrayList<Pharmacy>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        public CardView cardView;
        public TextView textView1,textView2;
        public View mView;

        private ItemClickListener itemClickListener;

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), true);
            return true;
        }

        public MyViewHolder(View view){
            super(view);
            mView=view;
            cardView=(CardView) view.findViewById(R.id.pharmacy_card_view);
            textView1=(TextView) view.findViewById(R.id.pharmacyName);
            textView2=(TextView) view.findViewById(R.id.pharmacyTime);

            view.setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }
    }
}
