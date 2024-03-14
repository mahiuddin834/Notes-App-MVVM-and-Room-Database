package com.itnation.mynote.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.itnation.mynote.ModelClass.Notes;
import com.itnation.mynote.R;
import com.itnation.mynote.ViewModel.NotesViewModel;
import com.itnation.mynote.databinding.ActivityInsertNoteBinding;

import java.util.Date;

public class InsertNoteActivity extends AppCompatActivity {


    ActivityInsertNoteBinding binding;


    String title, subTitle, notesData;

    NotesViewModel notesViewModel;

    String priority = "1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityInsertNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);






        binding.doneNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title= binding.notesTitle.getText().toString();
                subTitle= binding.notesSubtitle.getText().toString();
                notesData = binding.notesData.getText().toString();


                createNotes(title, subTitle, notesData);



            }
        });




        binding.greenPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.greenPriority.setImageResource(R.drawable.done_ic);
                binding.yellowPriority.setImageResource(0);
                binding.redPriority.setImageResource(0);

                priority= "1";



            }
        });



        binding.yellowPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.greenPriority.setImageResource(0);
                binding.yellowPriority.setImageResource(R.drawable.done_ic);
                binding.redPriority.setImageResource(0);

                priority= "2";



            }
        });


        binding.redPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                binding.greenPriority.setImageResource(0);
                binding.yellowPriority.setImageResource(0);
                binding.redPriority.setImageResource(R.drawable.done_ic);

                priority= "3";




            }
        });






    }//=========================================onCreate closed==========================

    private void createNotes(String title, String subTitle, String notesData) {

        Date date= new Date();
        CharSequence charSequence= DateFormat.format("MMMM d, yyyy", date.getTime());


        Notes notes1 = new Notes();

        notes1.notesTitle=title;
        notes1.notesSubTitle=subTitle;
        notes1.notes=notesData;
        notes1.notesDate = charSequence.toString();
        notes1.notesPriority =priority;

        notesViewModel.insertNote(notes1);


        Toast.makeText(this, "Notes Created Successfully", Toast.LENGTH_SHORT).show();


        finish();



    }


}