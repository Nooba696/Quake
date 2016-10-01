

package com.ibm.mobileappbuilder.quake20160930102313.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import ibmmobileappbuilder.ui.BaseDetailActivity;

/**
 * QuakeDataCloudDSSchemaItemFormActivity form activity
 */
public class QuakeDataCloudDSSchemaItemFormActivity extends BaseDetailActivity {
  	
  	@Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    
    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return QuakeDataCloudDSSchemaItemFormFragment.class;
    }
}


