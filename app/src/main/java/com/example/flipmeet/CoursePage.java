package com.example.flipmeet;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.flipmeet.model.Order;

import static com.example.flipmeet.adapter.CourseAdapter.*;

public class CoursePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);

        ConstraintLayout courseBg = findViewById(R.id.coursePageBg);
        courseBg.setBackgroundColor(getIntent().getIntExtra(COURSE_BG, 0));

        ImageView courseImage = findViewById(R.id.coursePageImage);
        courseImage.setImageResource(getIntent().getIntExtra(COURSE_IMAGE, 0));

        TextView courseTitle = findViewById(R.id.coursePageTitle);
        courseTitle.setText(getIntent().getStringExtra(COURSE_TITLE));

        TextView courseDate = findViewById(R.id.coursePageDate);
        courseDate.setText(getIntent().getStringExtra(COURSE_DATE));

        TextView courseLevel = findViewById(R.id.coursePageLevel);
        courseLevel.setText(getIntent().getStringExtra(COURSE_LEVEL));

        TextView courseText = findViewById(R.id.coursePageText);
        courseText.setText(getIntent().getStringExtra(COURSE_TEXT));
    }

    public void addToCart(View view) {
        int item_id = getIntent().getIntExtra(COURSE_ID, 0);
        Order.items_id.add(item_id);
        Toast.makeText(this, "Added. It is okay!!!", Toast.LENGTH_LONG).show();
    }
}
