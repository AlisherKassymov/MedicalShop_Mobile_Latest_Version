package com.example.alisher.medicalshopremastered.fragment;

import android.app.SearchManager;
import android.content.Context;
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
import com.example.alisher.medicalshopremastered.adapter.DoctorAdapter;
import com.example.alisher.medicalshopremastered.adapter.MedicineAdapter;
import com.example.alisher.medicalshopremastered.decorator.SimpleDividerItemDecoration;
import com.example.alisher.medicalshopremastered.enitity.Doctor;
import com.example.alisher.medicalshopremastered.enitity.Medical_Enterprise;
import com.example.alisher.medicalshopremastered.enitity.Medicine;
import com.example.alisher.medicalshopremastered.enitity.Specialization;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DoctorsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DoctorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoctorsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView mRecyclerView;
    private DoctorAdapter mAdapter;
    List<Doctor> doctors=new ArrayList<>();
    private String JSONUrl = "https://3e196824.ngrok.io/api/doctor";
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

    public DoctorsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoviesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DoctorsFragment newInstance(String param1, String param2) {
        DoctorsFragment fragment = new DoctorsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_doctors, container, false);

        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.doctorSwipe);
        swipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView=(RecyclerView) view.findViewById(R.id.doctorRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        mAdapter=new DoctorAdapter(doctors, view.getContext());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(view.getContext(),DividerItemDecoration.VERTICAL,36));
        mRecyclerView.setAdapter(mAdapter);

        jsonParser();
        // Inflate the layout for this fragment
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
        doctors.clear();
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

                        Doctor doctor= new Doctor() ;
                        doctor.setDoctorID(jsonObject.getInt("doctorID"));
                        doctor.setDoctorName(jsonObject.getString("doctorName"));
                        doctor.setPrice(jsonObject.getString("price"));
                        doctor.setDoctorDescription(jsonObject.getString("doctorDescription"));
                        doctor.setDoctorSurname(jsonObject.getString("doctorSurname"));
                        doctor.setDoctorPhone(jsonObject.getString("doctorPhone"));

                        Specialization doctorType=new Specialization();
                        doctorType.setSpecID(jsonObject.getJSONObject("doctorType").getInt("specID"));
                        doctorType.setSpecName(jsonObject.getJSONObject("doctorType").getString("specName"));
                        doctorType.setSpecDescription(jsonObject.getJSONObject("doctorType").getString("specDescription"));

                        doctor.setDoctorType(doctorType);

                        Medical_Enterprise doctorEnterprise=new Medical_Enterprise();
                        doctorEnterprise.setMedID(jsonObject.getJSONObject("doctorEnterprise").getInt("medID"));
                        doctorEnterprise.setMedName(jsonObject.getJSONObject("doctorEnterprise").getString("medName"));
                        doctorEnterprise.setMedDescription(jsonObject.getJSONObject("doctorEnterprise").getString(  "medDescription"));
                        doctorEnterprise.setTime_at(jsonObject.getJSONObject("doctorEnterprise").getString(  "time_at"));
                        doctorEnterprise.setMedAddress(jsonObject.getJSONObject("doctorEnterprise").getString(  "medAddress"));

                        doctor.setDoctorEnterprise(doctorEnterprise);


                        doctors.add(doctor);
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
