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
import com.example.alisher.medicalshopremastered.activity.MedicineItemActivity;
import com.example.alisher.medicalshopremastered.enitity.Medicine;
import com.example.alisher.medicalshopremastered.interfaces.ItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MyViewHolder> implements Filterable {
    private List<Medicine> itemsData;
    private List<Medicine> itemsFilteredData;
    private Context context;
    private static final String NAME="name";
    private static final String DESC="description";
    private static final String CONTRADICTION="contradiction";
    private static final String USEINCASE="useInCase";
    private static final String PRICE="price";

    public MedicineAdapter(List<Medicine> itemsData, Context context){
        this.itemsData=itemsData;
        this.itemsFilteredData=itemsData;
        this.context = context;
    }


    @Override
    public MedicineAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_card,parent,false);
        final MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position){
        final Medicine medicine=itemsFilteredData.get(position);
        holder.textView1.setText(medicine.getName());
        holder.textView2.setText(medicine.getPrice());


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){
                    Toast.makeText(context, "Operation: "+"Added to cart", Toast.LENGTH_SHORT).show();
                }else{
                    Intent detailIntent=new Intent(context,MedicineItemActivity.class);
                    Medicine medicineItem=itemsData.get(position);

                    detailIntent.putExtra(NAME,medicineItem.getName());
                    detailIntent.putExtra(DESC, medicineItem.getDescription());
                    detailIntent.putExtra(PRICE,medicineItem.getPrice());
                    detailIntent.putExtra(CONTRADICTION,medicineItem.getContradiction());
                    detailIntent.putExtra(USEINCASE,medicineItem.getUse_in_case());
                    context.startActivity(detailIntent);
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
                    List<Medicine> filteredList = new ArrayList<>();
                    for (Medicine row : itemsData) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getPrice().contains(charSequence)) {
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
                itemsFilteredData = (ArrayList<Medicine>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public CardView cardView;
        public TextView textView1,textView2, description;
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
            cardView=(CardView) view.findViewById(R.id.medicine_card_view);
            textView1=(TextView) view.findViewById(R.id.medicineName);
            textView2=(TextView) view.findViewById(R.id.medicinePrice);

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);

        }
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }
    }
}
