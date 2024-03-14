package com.itnation.mynote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itnation.mynote.Activity.InsertNoteActivity;
import com.itnation.mynote.Adapter.NotesAdapter;
import com.itnation.mynote.ModelClass.Notes;
import com.itnation.mynote.ViewModel.NotesViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    NotesViewModel notesViewModel;
    FloatingActionButton addNotesBtn;
    RecyclerView notesRecyclerView;
    NotesAdapter notesAdapter;

    TextView noFilter, highToLowFilter, lowToHighFilter;

    List<Notes>filterNotesAllList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNotesBtn = findViewById(R.id.addNotesBtn);
        notesRecyclerView = findViewById(R.id.notesRecyclerView);
        noFilter = findViewById(R.id.noFilter);
        highToLowFilter = findViewById(R.id.highToLowFilter);
        lowToHighFilter = findViewById(R.id.LowToHighFilter);



        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);


        noFilter.setBackgroundResource(R.drawable.filter_select_txt_bg);


        noFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadData(0);

                lowToHighFilter.setBackgroundResource(R.drawable.filter_txt_bg);
                highToLowFilter.setBackgroundResource(R.drawable.filter_txt_bg);
                noFilter.setBackgroundResource(R.drawable.filter_select_txt_bg);

            }
        });

         highToLowFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadData(1);

                lowToHighFilter.setBackgroundResource(R.drawable.filter_txt_bg);
                highToLowFilter.setBackgroundResource(R.drawable.filter_select_txt_bg);
                noFilter.setBackgroundResource(R.drawable.filter_txt_bg);


            }
        });

         lowToHighFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadData(2);

                lowToHighFilter.setBackgroundResource(R.drawable.filter_select_txt_bg);
                highToLowFilter.setBackgroundResource(R.drawable.filter_txt_bg);
                noFilter.setBackgroundResource(R.drawable.filter_txt_bg);


            }
        });





        addNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, InsertNoteActivity.class));
            }
        });

        notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {

                setAdapter(notes);
                filterNotesAllList=notes;

            }
        });



    }//close onCreate==================================

    private void loadData(int i) {

        if (i==0){

            notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {

                    setAdapter(notes);
                    filterNotesAllList=notes;

                }
            });

        }else if (i==1){

            notesViewModel.highToLow.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {

                    setAdapter(notes);
                    filterNotesAllList=notes;

                }
            });

        } else if (i == 2) {


            notesViewModel.lowToHigh.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {

                    setAdapter(notes);
                    filterNotesAllList=notes;

                }
            });

        }


    }



    //--------------forAdapter-------------------->>>
    public void setAdapter(List<Notes> notes){

        notesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        notesAdapter = new NotesAdapter(MainActivity.this, notes);
        notesRecyclerView.setAdapter(notesAdapter);
    }


    //--------------forSearch-------------------->>>
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem menuItem = menu.findItem(R.id.search_icon);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setSearchableInfo( searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search Here...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                notesFilter(newText);
                return false;
            }
        });


        return true;
    }

 */

    private void notesFilter(String newText) {

        ArrayList<Notes>filterNames= new ArrayList<>();

        for (Notes notes : this.filterNotesAllList){

            if (notes.notesTitle.contains(newText) || notes.notesSubTitle.contains(newText)){

                filterNames.add(notes);

            }
        }
        this.notesAdapter.searchNotes(filterNames);



    }
}