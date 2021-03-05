package com.example.tomcrawfordapp.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecipeRepository {

    private RecipeDAO mRecipeDao;
//    3 live data for allBreak, allLunch etc
    private LiveData<List<Recipe>> mAllBreakfast;
    private LiveData<List<Recipe>> mAllLunch;
    private LiveData<List<Recipe>> mAllDinner;
    private LiveData<List<Recipe>> mAllSnacks;
    private LiveData<List<Recipe>> mAllDessert;

    RecipeRepository(Application application) {
        RecipeDatabase db = RecipeDatabase.getDatabase(application);
        mRecipeDao = db.recipeDAO();
//        initiliase each b/r/d
        mAllBreakfast = mRecipeDao.getAllBreakfast();
        mAllLunch = mRecipeDao.getAllLunch();
        mAllDinner = mRecipeDao.getAllDinner();
        mAllSnacks = mRecipeDao.getAllSnacks();
        mAllDessert = mRecipeDao.getAllDessert();
    }
// 3 of these - b/l/d
    public LiveData<List<Recipe>> getAllBreakfast() {
        return mAllBreakfast;
    }
    public LiveData<List<Recipe>> getAllLunch() {
        return mAllLunch;
    }
    public LiveData<List<Recipe>> getAllDinner() {
        return mAllDinner;
    }
    public LiveData<List<Recipe>> getAllSnacks() {
        return mAllSnacks;
    }
    public LiveData<List<Recipe>> getAllDessert() {
        return mAllDessert;
    }

    public void insert (Recipe recipe) {
        new insertAsyncTask(mRecipeDao).execute(recipe);
    }

    private static class insertAsyncTask extends AsyncTask<Recipe, Void, Void> {

        private RecipeDAO mAsyncTaskDao;

        insertAsyncTask(RecipeDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Recipe... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}