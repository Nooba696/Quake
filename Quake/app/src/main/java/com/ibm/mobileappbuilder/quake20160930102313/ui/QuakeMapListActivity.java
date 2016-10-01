

package com.ibm.mobileappbuilder.quake20160930102313.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ibm.mobileappbuilder.quake20160930102313.R;

import ibmmobileappbuilder.ui.BaseListingActivity;
/**
 * QuakeMapListActivity list activity
 */
public class QuakeMapListActivity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.quakeMapListActivity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return QuakeMapListFragment.class;
    }

}

