package com.itnation.mynote.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.itnation.mynote.ModelClass.Notes;
import com.itnation.mynote.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository repository;

    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> lowToHigh;
    public LiveData<List<Notes>> highToLow;

    public NotesViewModel(@NonNull Application application) {
        super(application);

        repository = new NotesRepository(application);
        getAllNotes = repository.getAllNotes;
        highToLow = repository.highToLow;
        lowToHigh=repository.lowToHigh;


    }


    public void insertNote(Notes notes) {

        repository.insertNotes(notes);

    }

    public void deleteNote(int id) {

        repository.deletetNotes(id);

    }

    public void updateNote(Notes notes) {

        repository.updateNotes(notes);

    }


}
