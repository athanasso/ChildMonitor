package com.example.finalproject.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.finalproject.Adapters.ContactListArrayAdapter;
import com.example.finalproject.Adapters.MessagesArrayAdapter;
import com.example.finalproject.Models.ContactListModel;
import com.example.finalproject.Models.MessagesModel;
import com.example.finalproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Contact_List_Fragment extends Fragment {

    DatabaseReference databaseContactList;
    ListView listView ;
    List<ContactListModel> contactList = new ArrayList<ContactListModel>();
    public Contact_List_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact__list_, container, false);
        // Inflate the layout for this fragment
        listView = view.findViewById(R.id.listViewContact);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        databaseContactList = FirebaseDatabase.getInstance().getReference().child("ContactList").child(firebaseUser.getUid());

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        databaseContactList.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                contactList.clear();

                for (DataSnapshot callSnap : dataSnapshot.getChildren()) {
                    ContactListModel object = callSnap.getValue(ContactListModel.class);

                    ///Toast.makeText(getActivity(),object.getDomain()+object.getUrl(),Toast.LENGTH_SHORT).show();
                    contactList.add(object);
                }
                ContactListArrayAdapter adapter = new ContactListArrayAdapter(getActivity(), (ArrayList<ContactListModel>) contactList);

                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}
