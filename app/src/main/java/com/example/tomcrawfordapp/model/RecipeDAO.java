package com.example.tomcrawfordapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface RecipeDAO {
    @Insert
    void insert(Recipe recipe);

    @Query("DELETE FROM recipe")
    void deleteAll();
//5 different selects. getAllBreakfast etc ->order by meal type.
    //how to find things via parameter
    @Query("SELECT * from recipe WHERE type = 'breakfast' ORDER BY title ASC")
    LiveData<List<Recipe>> getAllBreakfast();
    @Query("SELECT * from recipe WHERE type = 'lunch' ORDER BY title ASC")
    LiveData<List<Recipe>> getAllLunch();
    @Query("SELECT * from recipe WHERE type = 'dinner' ORDER BY title ASC")
    LiveData<List<Recipe>> getAllDinner();
    @Query("SELECT * from recipe ORDER BY title ASC")
    LiveData<List<Recipe>> getAllSnacks();
    @Query("SELECT * from recipe ORDER BY title ASC")
    LiveData<List<Recipe>> getAllDessert();
}
