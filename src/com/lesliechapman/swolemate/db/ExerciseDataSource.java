package com.lesliechapman.swolemate.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LChapm001 on 6/12/2015.
 */
public class ExerciseDataSource {

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = { SQLiteHelper.COLUMN_ID,
            SQLiteHelper.COLUMN_EXERCISE_DISPLAY_NAME, SQLiteHelper.COLUMN_EXERCISE_MUSCLE_GROUP };

    public ExerciseDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Exercise createExercise(String displayName, String muscleGroup) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_EXERCISE_DISPLAY_NAME, displayName);
        values.put(SQLiteHelper.COLUMN_EXERCISE_MUSCLE_GROUP, muscleGroup);
        long insertId = database.insert(SQLiteHelper.TABLE_EXERCISES, null,
                values);
        Cursor cursor = database.query(SQLiteHelper.TABLE_EXERCISES,
                allColumns, SQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Exercise newExercise = cursorToExercise(cursor);
        cursor.close();
        return newExercise;
    }

    public void deleteExercise(Exercise exercise) {
        long id = exercise.getId();
        System.out.println("Exercise deleted with id: " + id);
        database.delete(SQLiteHelper.TABLE_EXERCISES, SQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Exercise> getAllExercises() {
        List<Exercise> exercises = new ArrayList<Exercise>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_EXERCISES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Exercise exercise = cursorToExercise(cursor);
            exercises.add(exercise);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return exercises;
    }

    private Exercise cursorToExercise(Cursor cursor) {
        Exercise exercise = new Exercise();
        exercise.setId(cursor.getLong(0));
        exercise.setDisplayName(cursor.getString(1));
        exercise.setMuscleGroup(cursor.getString(2));
        return exercise;
    }
}

