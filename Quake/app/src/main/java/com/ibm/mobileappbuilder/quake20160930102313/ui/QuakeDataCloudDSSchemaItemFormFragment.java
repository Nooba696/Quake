
package com.ibm.mobileappbuilder.quake20160930102313.ui;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import com.ibm.mobileappbuilder.quake20160930102313.presenters.QuakeMapListFormPresenter;
import com.ibm.mobileappbuilder.quake20160930102313.R;
import ibmmobileappbuilder.ds.CloudantDatasource;
import ibmmobileappbuilder.ui.FormFragment;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.views.TextWatcherAdapter;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.quake20160930102313.ds.QuakeDataCloudDSSchemaItem;
import ibmmobileappbuilder.ds.CloudantDatasource;
import ibmmobileappbuilder.cloudant.factory.CloudantDatastoresFactory;
import java.net.URI;

public class QuakeDataCloudDSSchemaItemFormFragment extends FormFragment<QuakeDataCloudDSSchemaItem> {

    private CrudDatasource<QuakeDataCloudDSSchemaItem> datasource;

    public static QuakeDataCloudDSSchemaItemFormFragment newInstance(Bundle args){
        QuakeDataCloudDSSchemaItemFormFragment fr = new QuakeDataCloudDSSchemaItemFormFragment();
        fr.setArguments(args);

        return fr;
    }

    public QuakeDataCloudDSSchemaItemFormFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        // the presenter for this view
        setPresenter(new QuakeMapListFormPresenter(
                (CrudDatasource) getDatasource(),
                this));

            }

    @Override
    protected QuakeDataCloudDSSchemaItem newItem() {
        return new QuakeDataCloudDSSchemaItem();
    }

    @Override
    protected int getLayout() {
        return R.layout.quakemaplist_form;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final QuakeDataCloudDSSchemaItem item, View view) {
        
        bindDouble(R.id.lat, item.lat, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.lat = StringUtils.parseDouble(s.toString());
            }
        });
        
        
        bindDouble(R.id.lng, item.lng, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.lng = StringUtils.parseDouble(s.toString());
            }
        });
        
        
        bindDouble(R.id.value, item.value, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.value = StringUtils.parseDouble(s.toString());
            }
        });
        
    }

    @Override
    public Datasource<QuakeDataCloudDSSchemaItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = CloudantDatasource.cloudantDatasource(
              CloudantDatastoresFactory.create("quake_database"),
              URI.create("https://26a5cf3a-1fb1-41d8-a978-c9f3cf929ae0-bluemix:f419f1e18446db8f2a7bef92d95ca66bcd50660e555f4854a70f9b08638f5772@26a5cf3a-1fb1-41d8-a978-c9f3cf929ae0-bluemix.cloudant.com/quake_database/"),
              QuakeDataCloudDSSchemaItem.class,
              new SearchOptions(),
              null
      );
        return datasource;
    }
}

