package com.example.tomcrawfordapp.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecipeRepository {

    private RecipeDAO mRecipeDao;
//    3 live data for allBreak, allLunch etc
    private LiveData<List<Recipe>> mAllRecipes;

    RecipeRepository(Application application) {
        RecipeDatabase db = RecipeDatabase.getDatabase(application);
        mRecipeDao = db.recipeDAO();
//        initiliase each b/r/d
        mAllRecipes = mRecipeDao.getAllRecipe();
    }
// 3 of these - b/l/d
    public LiveData<List<Recipe>> getAllRecipes() {
        return mAllRecipes;
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