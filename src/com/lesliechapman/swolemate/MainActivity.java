package com.lesliechapman.swolemate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.lesliechapman.R;
import com.lesliechapman.swolemate.db.Exercise;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Button button = (Button) findViewById(R.id.launchNewExerciseActivity);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Launch the add Exercise Activity
                Intent myIntent = new Intent(MainActivity.this, NewExercise.class);
                //myIntent.putExtra("key", value); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
}
