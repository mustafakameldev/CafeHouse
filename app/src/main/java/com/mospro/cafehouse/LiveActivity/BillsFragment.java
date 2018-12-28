package com.mospro.cafehouse.LiveActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mospro.cafehouse.MenuActivity.MenuAdapter;
import com.mospro.cafehouse.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class BillsFragment extends Fragment {
    ArrayList<Integer> numbers ;
    int number ;
    private TablesAdapter adapter;
    private RecyclerView recyclerView ;
    public BillsFragment() {
        // Required empty public constructor
        numbers = new ArrayList<>() ;
    }
    public static BillsFragment newInstance(String param1, String param2) {
        BillsFragment fragment = new BillsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    int getTablesNumber()
    {
        DatabaseReference tablesNuRef = FirebaseDatabase.getInstance().getReference().child("settings") ;
        tablesNuRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer tablesNo = dataSnapshot.child("tablesNo").getValue(Integer.class);
                number = tablesNo ;

                 ArrayList<Integer> nums = new ArrayList<>(tablesNo);
                 for (int i = 1 ; i <= tablesNo ;i++)
                 {
                     nums.add(i) ;
                     Log.i("MKA","onDataChange: " + numbers.toString());
                 }
                 numbers= nums ;
                Log.i("MKA", "onDataChange: "+numbers.toString());
                Toast.makeText(getActivity(), " "+ number, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        return number ;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bills, container, false);
        recyclerView =(RecyclerView)view.findViewById(R.id.recycler_tables_BillFragment) ;

        getTablesNumber();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                displayData();
            }
        }, 5000);

        return view ;
    }

    private void displayData() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TablesAdapter(numbers,getActivity());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }







}
