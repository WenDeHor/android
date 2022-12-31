package com.example.flipmeet;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.flipmeet.adapter.CategoryAdapter;
import com.example.flipmeet.adapter.CourseAdapter;
import com.example.flipmeet.model.Category;
import com.example.flipmeet.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity {
    RecyclerView categoryRecycler, courseRecycler;
    CategoryAdapter categoryAdapter;
    CourseAdapter courseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Games"));
        categoryList.add(new Category(2, "Sites"));
        categoryList.add(new Category(3, "Lang"));
        categoryList.add(new Category(4, "Position"));
        setCategoryRecycler(categoryList);

        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course(1, "java", "This is super\n course in java", "23 march", "first", "#d9bb8b", "Test text"));
        courseList.add(new Course(2, "python", "This is super\n course in python", "30 may", "top", "#9FA52D","Test text"));
        courseList.add(new Course(3, "unity", "This is super\n course in unity", "23 march", "easy", "#e07e41","Test text"));
        courseList.add(new Course(4, "node", "This is super\n course in node js", "30 may", "middle", "#e7ebae","Test text"));

        setCourseRecycler(courseList);
    }
    private void setCourseRecycler(List<Course> courseList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        courseRecycler = findViewById(R.id.courseRecycler);
        courseRecycler.setLayoutManager(layoutManager);

        courseAdapter = new CourseAdapter(this, courseList);
        courseRecycler.setAdapter(courseAdapter);
    }

    private void setCategoryRecycler(List<Category> categoryList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }
}
