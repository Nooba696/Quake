package com.ibm.mobileappbuilder.quake20160930102313.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.ibm.mobileappbuilder.quake20160930102313.presenters.QuakeMapListPresenter;
import com.ibm.mobileappbuilder.quake20160930102313.R;
import ibmmobileappbuilder.behaviors.FabBehaviour;
import ibmmobileappbuilder.behaviors.SelectionBehavior;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ui.ListGridFragment;
import ibmmobileappbuilder.util.Constants;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.util.ViewHolder;
import java.util.List;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.quake20160930102313.ds.QuakeDataCloudDSSchemaItem;
import ibmmobileappbuilder.ds.CloudantDatasource;
import ibmmobileappbuilder.cloudant.factory.CloudantDatastoresFactory;
import java.net.URI;
import ibmmobileappbuilder.mvp.view.CrudListView;
import ibmmobileappbuilder.ds.CrudDatasource;

import static ibmmobileappbuilder.util.NavigationUtils.generateIntentToAddOrUpdateItem;

/**
 * "QuakeMapListFragment" listing
 */
public class QuakeMapListFragment extends ListGridFragment<QuakeDataCloudDSSchemaItem> implements CrudListView<QuakeDataCloudDSSchemaItem> {

    private CrudDatasource<QuakeDataCloudDSSchemaItem> datasource;

    // "Add" button
    private FabBehaviour fabBehavior;

    public static QuakeMapListFragment newInstance(Bundle args) {
        QuakeMapListFragment fr = new QuakeMapListFragment();

        fr.setArguments(args);
        return fr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new QuakeMapListPresenter(
            (CrudDatasource) getDatasource(),
            this
        ));
        // Multiple selection
        SelectionBehavior<QuakeDataCloudDSSchemaItem> selectionBehavior = new SelectionBehavior<>(
            this,
            R.string.remove_items,
            R.drawable.ic_delete_alpha);

        selectionBehavior.setCallback(new SelectionBehavior.Callback<QuakeDataCloudDSSchemaItem>() {
            @Override
            public void onSelected(List<QuakeDataCloudDSSchemaItem> selectedItems) {
                getPresenter().deleteItems(selectedItems);
            }
        });
        addBehavior(selectionBehavior);
        // FAB button
        fabBehavior = new FabBehaviour(this, R.drawable.ic_add_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().addForm();
            }
        });
        addBehavior(fabBehavior);
    }

    protected SearchOptions getSearchOptions() {
      SearchOptions.Builder searchOptionsBuilder = SearchOptions.Builder.searchOptions();
      return searchOptionsBuilder.build();
    }


    /**
    * Layout for the list itselft
    */
    @Override
    protected int getLayout() {
        return R.layout.fragment_list;
    }

    /**
    * Layout for each element in the list
    */
    @Override
    protected int getItemLayout() {
        return R.layout.quakemaplist_item;
    }

    @Override
    protected Datasource<QuakeDataCloudDSSchemaItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
      datasource = CloudantDatasource.cloudantDatasource(
              CloudantDatastoresFactory.create("quake_database"),
              URI.create("https://26a5cf3a-1fb1-41d8-a978-c9f3cf929ae0-bluemix:f419f1e18446db8f2a7bef92d95ca66bcd50660e555f4854a70f9b08638f5772@26a5cf3a-1fb1-41d8-a978-c9f3cf929ae0-bluemix.cloudant.com/quake_database/"),
              QuakeDataCloudDSSchemaItem.class,
              getSearchOptions(),
              null
      );
      return datasource;
    }

    @Override
    protected void bindView(QuakeDataCloudDSSchemaItem item, View view, int position) {
        
        TextView title = ViewHolder.get(view, R.id.title);
        
        if (item.lat != null && item.lng != null){
            title.setText("Location : " + StringUtils.doubleToString(item.lat, true) + " " + StringUtils.doubleToString(item.lng, true));
            
        }
        
        TextView subtitle = ViewHolder.get(view, R.id.subtitle);
        
        if (item.value != null){
            subtitle.setText("Magnitude : " + StringUtils.doubleToString(item.value, true));
            
        }
    }

    @Override
    protected void itemClicked(final QuakeDataCloudDSSchemaItem item, final int position) {
        fabBehavior.hide(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                getPresenter().detail(item, position);
            }
        });
    }
    @Override
    public void showAdd() {
        startActivityForResult(generateIntentToAddOrUpdateItem(null,
                        0,
                        getActivity(),
                        QuakeDataCloudDSSchemaItemFormActivity.class
                ), Constants.MODE_CREATE
        );
    }

    @Override
    public void showEdit(QuakeDataCloudDSSchemaItem item, int position) {
    startActivityForResult(
                generateIntentToAddOrUpdateItem(item,
                        position,
                        getActivity(),
                        QuakeDataCloudDSSchemaItemFormActivity.class
                ), Constants.MODE_EDIT
        );
    }
}

