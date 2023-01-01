package com.example.flipmeet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
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

    static CourseAdapter courseAdapter;
    private static List<Category> categoryList = new ArrayList<Category>() {{
        add(new Category(1, "Games"));
        add(new Category(2, "Sites"));
        add(new Category(3, "Lang"));
        add(new Category(4, "Position"));
    }};
    private static List<Course> courseList = new ArrayList<Course>() {{
        add(new Course(1, "java", "This is super\n course in java", "23 march", "first", "#d9bb8b", "Test text", 1));
        add(new Course(2, "python", "This is super\n course in python", "30 may", "top", "#9FA52D", "Test text", 2));
        add(new Course(3, "unity", "This is super\n course in unity", "23 march", "easy", "#e07e41", "Test text", 3));
        add(new Course(4, "node", "This is super\n course in node js", "30 may", "middle", "#e7ebae", "Test text", 4));

    }};
    static List<Course> fullCoursesList = new ArrayList<>(courseList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        setCategoryRecycler(categoryList);
        setCourseRecycler(courseList);
    }

    public void openShoppingCart(View view) {
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);
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

    public static void showCoursesByCategory(int category) {

        courseList.clear();
        courseList.addAll(fullCoursesList);
        List<Course> filterCourses = new ArrayList<>();
        for (Course c : courseList) {
            if (c.getCategory() == category) {
                filterCourses.add(c);
            }
        }
        courseList.clear();
        courseList.addAll(filterCourses);
        courseAdapter.notifyDataSetChanged();
    }
}
