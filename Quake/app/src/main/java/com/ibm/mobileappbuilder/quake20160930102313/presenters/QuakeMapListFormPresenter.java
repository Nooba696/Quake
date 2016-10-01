
package com.ibm.mobileappbuilder.quake20160930102313.presenters;

import com.ibm.mobileappbuilder.quake20160930102313.R;
import com.ibm.mobileappbuilder.quake20160930102313.ds.QuakeDataCloudDSSchemaItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BaseFormPresenter;
import ibmmobileappbuilder.mvp.view.FormView;

public class QuakeMapListFormPresenter extends BaseFormPresenter<QuakeDataCloudDSSchemaItem> {

    private final CrudDatasource<QuakeDataCloudDSSchemaItem> datasource;

    public QuakeMapListFormPresenter(CrudDatasource<QuakeDataCloudDSSchemaItem> datasource, FormView<QuakeDataCloudDSSchemaItem> view){
        super(view);
        this.datasource = datasource;
    }

    @Override
    public void deleteItem(QuakeDataCloudDSSchemaItem item) {
        datasource.deleteItem(item, new OnItemDeletedListener());
    }

    @Override
    public void save(QuakeDataCloudDSSchemaItem item) {
        // validate
        if (validate(item)){
            datasource.updateItem(item, new OnItemUpdatedListener());
        } else {
            view.showMessage(R.string.correct_errors, false);
        }
    }

    @Override
    public void create(QuakeDataCloudDSSchemaItem item) {
        // validate
        if (validate(item)){
            datasource.create(item, new OnItemCreatedListener());
        } else {
            view.showMessage(R.string.correct_errors, false);
        }
    }

    private class OnItemDeletedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(QuakeDataCloudDSSchemaItem  item) {
                        view.showMessage(R.string.item_deleted, true);
            view.close(true);
        }
    }

    private class OnItemUpdatedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(QuakeDataCloudDSSchemaItem item) {
                        view.setItem(item);
            view.showMessage(R.string.item_updated, true);
            view.close(true);
        }
    }

    private class OnItemCreatedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(QuakeDataCloudDSSchemaItem item) {
                        view.setItem(item);
            view.showMessage(R.string.item_created, true);
            view.close(true);
        }
    }

    private abstract class ShowingErrorOnFailureListener implements Datasource.Listener<QuakeDataCloudDSSchemaItem > {
        @Override
        public void onFailure(Exception e) {
            view.showMessage(R.string.error_data_generic, true);
        }
    }

}

