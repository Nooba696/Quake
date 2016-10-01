

package com.ibm.mobileappbuilder.quake20160930102313.ui;

import android.os.Bundle;

import com.ibm.mobileappbuilder.quake20160930102313.R;

import java.util.ArrayList;
import java.util.List;

import ibmmobileappbuilder.MenuItem;

import ibmmobileappbuilder.actions.StartActivityAction;
import ibmmobileappbuilder.util.Constants;

/**
 * QuakeMainFragment menu fragment.
 */
public class QuakeMainFragment extends ibmmobileappbuilder.ui.MenuFragment {

    /**
     * Default constructor
     */
    public QuakeMainFragment(){
        super();
    }

    // Factory method
    public static QuakeMainFragment newInstance(Bundle args) {
        QuakeMainFragment fragment = new QuakeMainFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
      public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
                }

    // Menu Fragment interface
    @Override
    public List<MenuItem> getMenuItems() {
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        items.add(new MenuItem()
            .setLabel("QuakeMapList")
            .setIcon(R.drawable.png_defaultmenuicon)
            .setAction(new StartActivityAction(QuakeMapListActivity.class, Constants.DETAIL))
        );
        return items;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_grid;
    }

    @Override
    public int getItemLayout() {
        return R.layout.quakemain_item;
    }
}

