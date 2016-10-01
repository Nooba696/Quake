

package com.ibm.mobileappbuilder.quake20160930102313.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ibm.mobileappbuilder.quake20160930102313.R;

import ibmmobileappbuilder.ui.BaseListingActivity;
/**
 * QuakeMainActivity list activity
 */
public class QuakeMainActivity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.quakeMainActivity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return QuakeMainFragment.class;
    }

}

