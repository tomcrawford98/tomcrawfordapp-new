package com.example.tomcrawfordapp.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Recipe.class}, version = 1, exportSchema = false)

public abstract class RecipeDatabase extends RoomDatabase {
    private static RecipeDatabase INSTANCE;
    public abstract RecipeDAO recipeDAO();



    public static RecipeDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RecipeDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RecipeDatabase.class, "recipe_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final RecipeDAO mDao;
        Recipe[] foods = new Recipe[3];
// set meal types for each. meal type must be non null.
        PopulateDbAsync(RecipeDatabase db) {
            Recipe r1 = new Recipe();
            r1.setTitle("Fillet Steak");
            foods[0] = r1;
            Recipe r2 = new Recipe();
            r2.setTitle("Lasagna");
            foods[1] = r2;
            Recipe r3 = new Recipe();
            r3.setTitle("Mushroom Risotto");
            foods[2] = r3;
            mDao = db.recipeDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();

            for (int i = 0; i <= foods.length - 1; i++) {

                Recipe r = (foods[i]);
                mDao.insert(r);
            }
            return null;
        }
    }
}
