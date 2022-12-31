package com.example.flipmeet.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flipmeet.CourseActivity;
import com.example.flipmeet.R;
import com.example.flipmeet.model.Course;


import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    public static final String COURSE_BG = "courseBg";
    public static final String COURSE_IMAGE = "courseImage";
    public static final String COURSE_TITLE = "courseTitle";
    public static final String COURSE_DATE = "courseDate";
    public static final String COURSE_LEVEL = "courseLevel";
    public static final String COURSE_TEXT = "courseText";

    Context context;
    List<Course> courses;

    public CourseAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseItems = LayoutInflater.from(context).inflate(R.layout.course_item, parent, false);
        return new CourseAdapter.CourseViewHolder(courseItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        int parseColor = Color.parseColor(courses.get(position).getColor());
        holder.courseBg.setCardBackgroundColor(parseColor);

        int imageId = context.getResources().getIdentifier("ic_" + courses.get(position).getImg(), "drawable", context.getPackageName());
        holder.courseImage.setImageResource(imageId);
        holder.courseTitle.setText(courses.get(position).getTitle());
        holder.courseDate.setText(courses.get(position).getDate());
        holder.courseLevel.setText(courses.get(position).getLevel());

        //button
        onClickCourse(holder, position, parseColor, imageId);
    }

    private void onClickCourse(@NonNull CourseViewHolder holder, int position, int parseColor, int imageId) {
        //button
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, CourseActivity.class);
            //transition
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                    (Activity) context,
                    new Pair<View, String>(holder.courseImage, COURSE_IMAGE));
            //set
            intent.putExtra(COURSE_BG, parseColor);
            intent.putExtra(COURSE_IMAGE, imageId);
            intent.putExtra(COURSE_TITLE, courses.get(position).getTitle());
            intent.putExtra(COURSE_DATE, courses.get(position).getDate());
            intent.putExtra(COURSE_LEVEL, courses.get(position).getLevel());
            intent.putExtra(COURSE_TEXT, courses.get(position).getText());

            context.startActivity(intent, options.toBundle());
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static final class CourseViewHolder extends RecyclerView.ViewHolder {
        CardView courseBg;
        ImageView courseImage;
        TextView courseTitle, courseDate, courseLevel;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseBg = itemView.findViewById(R.id.courseBg);
            courseImage = itemView.findViewById(R.id.courseImage);
            courseTitle = itemView.findViewById(R.id.courseTitle);
            courseDate = itemView.findViewById(R.id.courseDate);
            courseLevel = itemView.findViewById(R.id.courseLevel);
        }
    }
}
