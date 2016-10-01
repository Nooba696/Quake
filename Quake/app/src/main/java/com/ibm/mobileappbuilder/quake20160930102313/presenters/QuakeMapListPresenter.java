
package com.ibm.mobileappbuilder.quake20160930102313.presenters;

import com.ibm.mobileappbuilder.quake20160930102313.R;
import com.ibm.mobileappbuilder.quake20160930102313.ds.QuakeDataCloudDSSchemaItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.ListCrudPresenter;
import ibmmobileappbuilder.mvp.view.CrudListView;

public class QuakeMapListPresenter extends BasePresenter implements ListCrudPresenter<QuakeDataCloudDSSchemaItem>,
      Datasource.Listener<QuakeDataCloudDSSchemaItem>{

    private final CrudDatasource<QuakeDataCloudDSSchemaItem> crudDatasource;
    private final CrudListView<QuakeDataCloudDSSchemaItem> view;

    public QuakeMapListPresenter(CrudDatasource<QuakeDataCloudDSSchemaItem> crudDatasource,
                                         CrudListView<QuakeDataCloudDSSchemaItem> view) {
       this.crudDatasource = crudDatasource;
       this.view = view;
    }

    @Override
    public void deleteItem(QuakeDataCloudDSSchemaItem item) {
        crudDatasource.deleteItem(item, this);
    }

    @Override
    public void deleteItems(List<QuakeDataCloudDSSchemaItem> items) {
        crudDatasource.deleteItems(items, this);
    }

    @Override
    public void addForm() {
        view.showAdd();
    }

    @Override
    public void editForm(QuakeDataCloudDSSchemaItem item, int position) {
        view.showEdit(item, position);
    }

    @Override
    public void detail(QuakeDataCloudDSSchemaItem item, int position) {
        view.showDetail(item, position);
    }

    @Override
    public void onSuccess(QuakeDataCloudDSSchemaItem item) {
                view.showMessage(R.string.items_deleted);
        view.refresh();
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic);
    }

}

