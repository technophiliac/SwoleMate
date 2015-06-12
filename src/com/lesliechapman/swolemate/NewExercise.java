package com.lesliechapman.swolemate;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.lesliechapman.R;
import com.lesliechapman.swolemate.db.Exercise;
import com.lesliechapman.swolemate.db.ExerciseDataSource;

import java.util.List;

/**
 * Created by LChapm001 on 6/7/2015.
 */
public class NewExercise extends ListActivity {

    private ExerciseDataSource exerciseDataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_exercise);
        exerciseDataSource = new ExerciseDataSource(this);
        exerciseDataSource.open();

        List<Exercise> values = exerciseDataSource.getAllExercises();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        final ArrayAdapter<Exercise> adapter = new ArrayAdapter<Exercise>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

        final Button button = (Button) findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // save the new exercise to the database
                EditText displayNameEditText   = (EditText)findViewById(R.id.exerciseNameEditText);
                EditText muscleGroupEditText = (EditText)findViewById(R.id.muscleGroupEditText);
                if(displayNameEditText.getText() != null && displayNameEditText.getText().toString() != null && !displayNameEditText.getText().toString().isEmpty()
                        && muscleGroupEditText.getText() != null && muscleGroupEditText.getText().toString() != null && !muscleGroupEditText.getText().toString().isEmpty()){
                    Exercise exercise = exerciseDataSource.createExercise(displayNameEditText.getText().toString(), muscleGroupEditText.getText().toString());
                    adapter.add(exercise);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        final ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Exercise exercise = adapter.getItem(i);
                System.out.println("You clicked " + exercise.toString());
            }
        });
    }
}