package com.example.finalproject.Adapters;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.finalproject.Models.MessagesModel;
import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class MessagesArrayAdapter extends ArrayAdapter<MessagesModel> {

    private Activity context;
    List<MessagesModel> messages;

    public MessagesArrayAdapter(Activity context, ArrayList<MessagesModel> messages) {
        super(context, R.layout.fragment_messages_, messages);
        this.context = context;
        this.messages = messages;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        MessagesModel item = messages.get(position);
        View listViewItem = inflater.inflate(R.layout.custom_messages_list_view, null);

        TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = listViewItem.findViewById(R.id.textViewMessage);

        MessagesModel artist = messages.get(position);
        textViewName.setText(artist.getMobile());
        textViewGenre.setText(artist.getMessage());

        return listViewItem;
    }
}