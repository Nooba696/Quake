package com.ibm.mobileappbuilder.quake20160930102313.ui;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import ibmmobileappbuilder.ui.DrawerActivity;

import com.ibm.mobileappbuilder.quake20160930102313.R;

public class QuakeMain extends DrawerActivity {

    private final SparseArray<Class<? extends Fragment>> sectionFragments = new SparseArray<>();
    {
                sectionFragments.append(R.id.entry0, QuakeMainFragment.class);
    }

    @Override
    public SparseArray<Class<? extends Fragment>> getSectionFragmentClasses() {
      return sectionFragments;
    }

}

