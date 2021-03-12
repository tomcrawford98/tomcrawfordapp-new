package com.example.tomcrawfordapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface RecipeDAO {

//step 1 - replace getAll with getAllMeals(String mealName);
    //select all from recipe where type = :mealname
    @Insert
    void insertAllData(Recipe recipe);

    //Select All Data
    @Query("select * from  recipe where type = 'breakfast' ")
    LiveData<List<Recipe>> getAllBreakfast();
    //Select All Data
    @Query("select * from  recipe")
    LiveData<List<Recipe>> getAllLunch();
    //Select All Data
    @Query("select * from  recipe")
    LiveData<List<Recipe>> getAllDinner();
    //Select All Data
    @Query("select * from  recipe")
    LiveData<List<Recipe>> getAllSnacks();
    //Select All Data
    @Query("select * from  recipe")
    LiveData<List<Recipe>> getAllDessert();

    @Query("select * from  recipe where type = :mealName ")
    LiveData<List<Recipe>> getAllMeals(String mealName);
    //DELETE DATA
    @Query("delete from recipe")
    void deleteData();

    //Update Data

    @Query("update recipe SET type= :type, name= :name ,ingredients =:ingredients, notes =:notes where `key`= :key")
    void updateData(String type, String name, String ingredients, String notes, int key);
}
