package com.itnation.mynote.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.itnation.mynote.ModelClass.Notes;
import com.itnation.mynote.R;
import com.itnation.mynote.ViewModel.NotesViewModel;
import com.itnation.mynote.databinding.ActivityInsertNoteBinding;
import com.itnation.mynote.databinding.ActivityUpdateNoteBinding;

import java.util.Date;

public class UpdateNoteActivity extends AppCompatActivity {

    ActivityUpdateNoteBinding binding;

    String priority = "1";

    String sTitle, sSubtitle, sNotes, sPriority;
    int sId;

    String title, subTitle, notesData;

    NotesViewModel notesViewModel;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);


         sId = getIntent().getIntExtra("id",0);
        sTitle = getIntent().getStringExtra("title");
        sSubtitle = getIntent().getStringExtra("subTitle");
        sNotes = getIntent().getStringExtra("notes");
        sPriority = getIntent().getStringExtra("priority");

        binding.upTitle.setText(sTitle);
        binding.upSubtitle.setText(sSubtitle);
        binding.upNoteData.setText(sNotes);

        if (sPriority.equals("1")){
            binding.upGreenPriority.setImageResource(R.drawable.done_ic);

        } else if (sPriority.equals("2")) {

            binding.upYellowPriority.setImageResource(R.drawable.done_ic);

        } else if (sPriority.equals("3")) {

            binding.upRedPriority.setImageResource(R.drawable.done_ic);

        }


        binding.upGreenPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.upGreenPriority.setImageResource(R.drawable.done_ic);
                binding.upYellowPriority.setImageResource(0);
                binding.upRedPriority.setImageResource(0);

                priority= "1";



            }
        });



        binding.upYellowPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.upGreenPriority.setImageResource(0);
                binding.upYellowPriority.setImageResource(R.drawable.done_ic);
                binding.upRedPriority.setImageResource(0);

                priority= "2";



            }
        });


        binding.upRedPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                binding.upGreenPriority.setImageResource(0);
                binding.upYellowPriority.setImageResource(0);
                binding.upRedPriority.setImageResource(R.drawable.done_ic);

                priority= "3";




            }
        });


        binding.updateNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                title= binding.upTitle.getText().toString();
                subTitle= binding.upSubtitle.getText().toString();
                notesData = binding.upNoteData.getText().toString();


                createNotes(title, subTitle, notesData);


            }
        });





    }//==============onCreate Closed ==========


    private void createNotes(String title, String subTitle, String notesData) {

        Date date= new Date();
        CharSequence charSequence= DateFormat.format("MMMM d, yyyy", date.getTime());

        Notes updateNotes= new Notes();

        updateNotes.id = sId;
        updateNotes.notesTitle=title;
        updateNotes.notesSubTitle=subTitle;
        updateNotes.notes=notesData;
        updateNotes.notesDate = charSequence.toString();
        updateNotes.notesPriority =priority;
        notesViewModel.updateNote(updateNotes);



        Toast.makeText(this, "Notes Updated Successfully", Toast.LENGTH_SHORT).show();


        finish();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.delete){

            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(UpdateNoteActivity.this);

            View view= LayoutInflater.from(UpdateNoteActivity.this)
                    .inflate(R.layout.delete_bottom_sheed,(LinearLayout)findViewById(R.id.bottomShade));

            bottomSheetDialog.setContentView(view);


            MaterialButton deleteButton, cancleButton;
            deleteButton= view.findViewById(R.id.deleteButton);
            cancleButton= view.findViewById(R.id.cancleButton);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    notesViewModel.deleteNote(sId);
                    Toast.makeText(UpdateNoteActivity.this, "Note Deleted Successfully", Toast.LENGTH_SHORT).show();
                    finish();

                }
            });


            cancleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bottomSheetDialog.dismiss();
                }
            });

            bottomSheetDialog.show();



        }
        return true;
    }

}