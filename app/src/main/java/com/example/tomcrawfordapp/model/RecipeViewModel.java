package com.example.tomcrawfordapp.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {

    private RecipeRepository mRepository;
// 5 live datas
    private LiveData<List<Recipe>> mAllBreakfast;
    private LiveData<List<Recipe>> mAllLunch;
    private LiveData<List<Recipe>> mAllDinner;
    private LiveData<List<Recipe>> mAllSnacks;
    private LiveData<List<Recipe>> mAllDessert;

    public RecipeViewModel(Application application) {
        super(application);
        mRepository = new RecipeRepository(application);
//        3 initialisations
        mAllBreakfast = mRepository.getAllBreakfast();
        mAllLunch = mRepository.getAllLunch();
        mAllDinner = mRepository.getAllDinner();
        mAllSnacks = mRepository.getAllSnacks();
        mAllDessert = mRepository.getAllDessert();
    }
// 5 types
    public LiveData<List<Recipe>> getAllBreakfast() { return mAllBreakfast; }
    public LiveData<List<Recipe>> getAllLunch() { return mAllLunch; }
    public LiveData<List<Recipe>> getAllDinner() { return mAllDinner; }
    public LiveData<List<Recipe>> getAllSnacks() { return mAllSnacks; }
    public LiveData<List<Recipe>> getAllDessert() { return mAllDessert; }

    public void insert(Recipe recipe) { mRepository.insert(recipe); }
}
//dropdown on createUI for meal type - spinner - valid strings