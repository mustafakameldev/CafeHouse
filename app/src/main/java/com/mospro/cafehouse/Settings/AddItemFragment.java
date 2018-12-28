package com.mospro.cafehouse.Settings;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mospro.cafehouse.R;
public class AddItemFragment extends Fragment implements View.OnClickListener {

    private Button addItemButton  ;
    private EditText nameText , priceText ;
    private DatabaseReference mainRef = FirebaseDatabase.getInstance().getReference().child("menu");
    public AddItemFragment() {
        // Required empty public constructor
    }
    public static AddItemFragment newInstance(String param1, String param2) {
        AddItemFragment fragment = new AddItemFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);
        addItemButton =(Button)view.findViewById(R.id.btn_addItem_AddItemFragment);
        nameText =(EditText)view.findViewById(R.id.editTxt_Name_AddItemFragment) ;
        priceText=(EditText)view.findViewById(R.id.editText_Price_AddFragment);
        addItemButton.setOnClickListener(this);
        return view  ;

    }

    @Override
    public void onClick(View v) {
        if(v== addItemButton)
        {

            String name = nameText.getText().toString() ;
            String price =  priceText.getText().toString();

            if(TextUtils.isEmpty(name)|| TextUtils.isEmpty(price))
            {
                Toast.makeText(getContext(), R.string.dataItemRequired, Toast.LENGTH_SHORT).show();
            }else {

                Float priceF = Float.parseFloat(price);
                DatabaseReference pushRef = mainRef.push() ;
                pushRef.child("name").setValue(name);
                pushRef.child("price").setValue(priceF);
                getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
            }
        }
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
