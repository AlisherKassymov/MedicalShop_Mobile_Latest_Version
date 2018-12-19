package com.example.alisher.medicalshopremastered.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.alisher.medicalshopremastered.enitity.Doctor;
import com.example.alisher.medicalshopremastered.enitity.Medical_Enterprise;
import com.example.alisher.medicalshopremastered.interfaces.ItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class EnterpriseAdapter extends RecyclerView.Adapter<EnterpriseAdapter.MyViewHolder> implements Filterable {
    private List<Medical_Enterprise> itemsData;
    private List<Medical_Enterprise> itemsFilteredData;
    private Context context;
    /*private static final String NAME="name";
    private static final String ADDRESS="address";
    private static final String DESCRIPTION="description";
    private static final String TIME="time";*/


    public EnterpriseAdapter(List<Medical_Enterprise> itemsData, Context context){
        this.itemsData=itemsData;
        this.itemsFilteredData=itemsData;
        this.context = context;
    }


    @Override
    public EnterpriseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.enterprise_card,parent,false);
        final MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position){
        final Medical_Enterprise enterprise=itemsFilteredData.get(position);
        holder.textView1.setText(enterprise.getMedName());
        holder.textView2.setText(enterprise.getTime_at());
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
                    List<Medical_Enterprise> filteredList = new ArrayList<>();
                    for (Medical_Enterprise row : itemsData) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getMedName().toLowerCase().contains(charString.toLowerCase()) || row.getTime_at().contains(charSequence)) {
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
                itemsFilteredData = (ArrayList<Medical_Enterprise>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public CardView cardView;
        public TextView textView1,textView2;
        public View mView;

        private ItemClickListener itemClickListener;
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), true);
            return true;
        }

        public MyViewHolder(View view){
            super(view);
            mView=view;
            cardView=(CardView) view.findViewById(R.id.enterprise_card_view);
            textView1=(TextView) view.findViewById(R.id.enterpriseName);
            textView2=(TextView) view.findViewById(R.id.enterpriseTime);

           /* view.setOnClickListener(this);
            view.setOnLongClickListener(this);*/
        }

       /* public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }*/
    }
}
