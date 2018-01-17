package fr.eseo.dis.begnaucle.ratemypostr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;


import fr.eseo.dis.begnaucle.ratemypostr.R;

public class MainActivity extends AppCompatActivity {


    List<String> ChildList;
    Map<String, List<String>> ParentListItems;
    ExpandableListView expandablelistView;

    // Assign Parent list items here.
    List<String> ParentList = new ArrayList<String>();

    {
        ParentList.add("Jury1");
        ParentList.add("Jury2");
    }

    // Assign children list element using string array.
    String[] AndroidName = {"Projet1", "Projet2"};
    String[] PhpName = {"Projet3", "Projet4"};
    String[] ByDefalutMessage = {"Items Loading"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ParentListItems = new LinkedHashMap<String, List<String>>();

        for (String HoldItem : ParentList) {
            if (HoldItem.equals("Jury1")) {
                loadChild(AndroidName);
            } else if (HoldItem.equals("Jury2"))
                loadChild(PhpName);

            else
                loadChild(ByDefalutMessage);

            ParentListItems.put(HoldItem, ChildList);
        }



        expandablelistView = (ExpandableListView) findViewById(R.id.expandableListView);
        final ExpandableListAdapter expListAdapter = new ListAdapter ( this, ParentList, ParentListItems);
        expandablelistView.setAdapter(expListAdapter);

        expandablelistView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub

                final String selected = (String) expListAdapter.getChild(
                        groupPosition, childPosition);

                // Switch case to open selected child element activity on child element selection.
                //TODO

                return true;
            }
        });
    }

    private void loadChild(String[] ParentElementsName) {
        ChildList = new ArrayList<String>();
        for (String model : ParentElementsName)
            ChildList.add(model);
    }



}
