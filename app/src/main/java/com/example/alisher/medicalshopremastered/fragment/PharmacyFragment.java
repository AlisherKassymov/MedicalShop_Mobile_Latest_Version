package com.example.alisher.medicalshopremastered.fragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.alisher.medicalshopremastered.R;
import com.example.alisher.medicalshopremastered.adapter.MedicineAdapter;
import com.example.alisher.medicalshopremastered.decorator.SimpleDividerItemDecoration;
import com.example.alisher.medicalshopremastered.enitity.Medicine;
import com.example.alisher.medicalshopremastered.enitity.Pharmacy;
import com.example.alisher.medicalshopremastered.adapter.PharmacyAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PharmacyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PharmacyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PharmacyFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private PharmacyAdapter mAdapter;
    List<Pharmacy> pharmacies=new ArrayList<>();
    private String JSONUrl="https://3e196824.ngrok.io/api/pharmacie";
    private SearchView searchView;
    private SwipeRefreshLayout swipeRefreshLayout;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PharmacyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PharmacyFragment newInstance(String param1, String param2) {
        PharmacyFragment fragment = new PharmacyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_pharmacy, container, false);



        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.pharmacySwipe);
        swipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView=(RecyclerView) view.findViewById(R.id.pharmacyRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        mAdapter=new PharmacyAdapter(pharmacies,view.getContext());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(view.getContext(),DividerItemDecoration.VERTICAL,36));
        mRecyclerView.setAdapter(mAdapter);
        // Inflate the layout for this fragment

        jsonParser();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        pharmacies.clear();
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.detach(this).attach(this).commit();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void jsonParser(){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(JSONUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Pharmacy pharmacy= new Pharmacy();
                        pharmacy.setPharmId(jsonObject.getInt("pharmID"));
                        pharmacy.setPharmName(jsonObject.getString("pharmName"));
                        pharmacy.setTime_at(jsonObject.getString("time_at"));
                        pharmacy.setPharmAddress(jsonObject.getString("pharmAddress"));
                        pharmacy.setPharmPhone(jsonObject.getString("pharmPhone"));

                        pharmacies.add(pharmacy);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley",error.toString());
            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem=menu.findItem(R.id.action_search);
        searchView=(SearchView)menuItem.getActionView();
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
