package com.example.flipmeet;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import java.util.*;
import java.util.stream.Collectors;

public class MapActivity extends AppCompatActivity {
    private static final String NAME = "NAME";

    private SimpleExpandableListAdapter mAdapter;
    ExpandableListView simpleExpandableListView;
    // string arrays for group and child items
    private List<String> groupItems = new ArrayList<String>() {{
        add("Animals");
        add("Birds");
    }};
    private String[][] childItems = {{"Dog", "Cat", "Tiger"}, {"Crow", "Sparrow"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        //  initiate the expandable list view
        simpleExpandableListView = (ExpandableListView) findViewById(R.id.simpleExpandableListView);
        // create lists for group and child items
        List<Map<String, String>> groupData = new ArrayList<>();
        List<List<Map<String, String>>> childData = new ArrayList<>();
        // add data in group and child list
        for (int i = 0; i < groupItems.size(); i++) {
            Map<String, String> curGroupMap = new HashMap<>();
            curGroupMap.put(NAME, groupItems.get(i));
            groupData.add(curGroupMap);

            List<Map<String, String>> children = new ArrayList<>();

            for (int j = 0; j < childItems[i].length; j++) {
                Map<String, String> curChildMap = new HashMap<>();
                children.add(curChildMap);
                curChildMap.put(NAME, childItems[i][j]);
            }
            childData.add(children);
        }
        // define arrays for displaying data in Expandable list view
        String[] groupFrom = {NAME};
        int[] groupTo = {R.id.heading};
        String[] childFrom = {NAME};
        int[] childTo = {R.id.childItem};


        // Set up the adapter
        mAdapter = new SimpleExpandableListAdapter(this, groupData,
                R.layout.group_items,
                groupFrom, groupTo,
                childData, R.layout.child_items,
                childFrom, childTo);
        simpleExpandableListView.setAdapter(mAdapter);

        // perform set on group click listener event
        simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                // display a toast with group name whenever a user clicks on a group item
                Toast.makeText(getApplicationContext(), "Group Name Is :" + groupItems.get(groupPosition), Toast.LENGTH_LONG).show();

                return false;
            }
        });
        // perform set on child click listener event
        simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                // display a toast with child name whenever a user clicks on a child item
                Toast.makeText(getApplicationContext(), "Child Name Is :" + childItems[groupPosition][childPosition], Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}
