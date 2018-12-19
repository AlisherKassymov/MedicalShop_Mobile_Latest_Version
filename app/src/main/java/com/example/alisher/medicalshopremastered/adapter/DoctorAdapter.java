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
import com.example.alisher.medicalshopremastered.activity.DoctorItemActivity;
import com.example.alisher.medicalshopremastered.activity.MedicineItemActivity;
import com.example.alisher.medicalshopremastered.enitity.Doctor;
import com.example.alisher.medicalshopremastered.enitity.Medicine;
import com.example.alisher.medicalshopremastered.enitity.Pharmacy;
import com.example.alisher.medicalshopremastered.interfaces.ItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.MyViewHolder> implements Filterable {
    private List<Doctor> itemsData;
    private List<Doctor> itemsFilteredData;
    private Context context;
    private static final String NAME="name";
    private static final String DESC="description";
    private static final String PHONE="phone";
    private static final String SURNAME="surname";
    private static final String PRICE="price";
    private static final String TYPE="specialization";
    private static final String ENTERPRISE="enterprise";

    public DoctorAdapter(List<Doctor> itemsData, Context context){
        this.itemsData=itemsData;
        this.itemsFilteredData=itemsData;
        this.context = context;
    }


    @Override
    public DoctorAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_card,parent,false);
        final MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position){
        final Doctor doctor=itemsFilteredData.get(position);
        holder.textView1.setText(doctor.getDoctorName());
        holder.textView2.setText(doctor.getDoctorType().getSpecName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){
                    Doctor doctor=itemsData.get(position);
                    Intent intent=new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+doctor.getDoctorPhone()));
                    context.startActivity(intent);
                }else{
                    Intent detailIntent=new Intent(context,DoctorItemActivity.class);
                    Doctor doctorItem=itemsData.get(position);

                    detailIntent.putExtra(NAME,doctorItem.getDoctorName());
                    detailIntent.putExtra(DESC, doctorItem.getDoctorDescription());
                    detailIntent.putExtra(PRICE,doctorItem.getPrice());
                    detailIntent.putExtra(PHONE,doctorItem.getDoctorPhone());
                    detailIntent.putExtra(SURNAME,doctorItem.getDoctorSurname());
                    detailIntent.putExtra(ENTERPRISE,doctorItem.getDoctorEnterprise().getMedName());
                    detailIntent.putExtra(TYPE,doctorItem.getDoctorType().getSpecName());
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
                    List<Doctor> filteredList = new ArrayList<>();
                    for (Doctor row : itemsData) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getDoctorName().toLowerCase().contains(charString.toLowerCase()) || row.getDoctorType().getSpecName().contains(charSequence)) {
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
                itemsFilteredData = (ArrayList<Doctor>) filterResults.values;
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
            cardView=(CardView) view.findViewById(R.id.doctor_card_view);
            textView1=(TextView) view.findViewById(R.id.doctorName);
            textView2=(TextView) view.findViewById(R.id.doctorPrice);

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);

        }
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }
    }
}
