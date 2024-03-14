package com.itnation.mynote.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itnation.mynote.Activity.UpdateNoteActivity;
import com.itnation.mynote.MainActivity;
import com.itnation.mynote.ModelClass.Notes;
import com.itnation.mynote.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {


    MainActivity mainActivity;
    List<Notes> allNotes;

    //--------------forSearch--------------------
    List<Notes> allNotesItem;

    public NotesAdapter(MainActivity mainActivity, List<Notes> allNotes) {
        this.mainActivity = mainActivity;
        this.allNotes = allNotes;

        //--------------forSearch--------------------
        allNotesItem= new ArrayList<>(allNotes);
    }


    //--------------forSearch--------------------
    public void searchNotes(List<Notes>filterredName){

        this.allNotes=filterredName;
        notifyDataSetChanged();

    }

    //--------------forSearch--------------------

    @NonNull
    @Override
    public NotesAdapter.NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mainActivity).inflate(R.layout.item_notes, parent,false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NotesViewHolder holder, int position) {

        Notes notes = allNotes.get(position);

        holder.title.setText(notes.notesTitle);
        holder.subTitle.setText(notes.notesSubTitle);
        holder.noteDate.setText(notes.notesDate);
        holder.notesData.setText(notes.notes);

        switch (notes.notesPriority) {
            case "1":
                holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
                break;
            case "2":
                holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
                break;
            case "3":
                holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
                break;
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(mainActivity, UpdateNoteActivity.class);
                intent.putExtra("id",notes.id);
                intent.putExtra("title",notes.notesTitle);
                intent.putExtra("subTitle",notes.notesSubTitle);
                intent.putExtra("notes",notes.notes);
                intent.putExtra("priority",notes.notesPriority);

                mainActivity.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    public static class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView title,subTitle, noteDate, notesData;
        View notesPriority;
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.notesTitle);
            subTitle=itemView.findViewById(R.id.notesSubtitle);
            noteDate=itemView.findViewById(R.id.notesDate);
            notesPriority=itemView.findViewById(R.id.notesPriority);
            notesData=itemView.findViewById(R.id.notesData);
        }
    }
}
