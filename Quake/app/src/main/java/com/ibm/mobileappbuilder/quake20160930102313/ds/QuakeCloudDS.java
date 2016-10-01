

package com.ibm.mobileappbuilder.quake20160930102313.ds;

import android.content.Context;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.restds.AppNowDatasource;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.ds.restds.TypedByteArrayUtils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * "QuakeCloudDS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class QuakeCloudDS extends AppNowDatasource<QuakeCloudDSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private QuakeCloudDSService service;

    public static QuakeCloudDS getInstance(SearchOptions searchOptions){
        return new QuakeCloudDS(searchOptions);
    }

    private QuakeCloudDS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = QuakeCloudDSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<QuakeCloudDSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<QuakeCloudDSItem>>() {
                @Override
                public void onSuccess(List<QuakeCloudDSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new QuakeCloudDSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getQuakeCloudDSItemById(id, new Callback<QuakeCloudDSItem>() {
                @Override
                public void success(QuakeCloudDSItem result, Response response) {
                                        listener.onSuccess(result);
                }

                @Override
                public void failure(RetrofitError error) {
                                        listener.onFailure(error);
                }
            });
        }
    }

    @Override
    public void getItems(final Listener<List<QuakeCloudDSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<QuakeCloudDSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryQuakeCloudDSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<QuakeCloudDSItem>>() {
            @Override
            public void success(List<QuakeCloudDSItem> result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{null};
    }

    // Pagination

    @Override
    public int getPageSize(){
        return PAGE_SIZE;
    }

    @Override
    public void getUniqueValuesFor(String searchStr, final Listener<List<String>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
                service.getServiceProxy().distinct(searchStr, conditions, new Callback<List<String>>() {
             @Override
             public void success(List<String> result, Response response) {
                                  result.removeAll(Collections.<String>singleton(null));
                 listener.onSuccess(result);
             }

             @Override
             public void failure(RetrofitError error) {
                                  listener.onFailure(error);
             }
        });
    }

    @Override
    public URL getImageUrl(String path) {
        return service.getImageUrl(path);
    }

    @Override
    public void create(QuakeCloudDSItem item, Listener<QuakeCloudDSItem> listener) {
                          service.getServiceProxy().createQuakeCloudDSItem(item, callbackFor(listener));
          }

    private Callback<QuakeCloudDSItem> callbackFor(final Listener<QuakeCloudDSItem> listener) {
      return new Callback<QuakeCloudDSItem>() {
          @Override
          public void success(QuakeCloudDSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(QuakeCloudDSItem item, Listener<QuakeCloudDSItem> listener) {
                          service.getServiceProxy().updateQuakeCloudDSItem(item.getIdentifiableId(), item, callbackFor(listener));
          }

    @Override
    public void deleteItem(QuakeCloudDSItem item, final Listener<QuakeCloudDSItem> listener) {
                service.getServiceProxy().deleteQuakeCloudDSItemById(item.getIdentifiableId(), new Callback<QuakeCloudDSItem>() {
            @Override
            public void success(QuakeCloudDSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<QuakeCloudDSItem> items, final Listener<QuakeCloudDSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<QuakeCloudDSItem>>() {
            @Override
            public void success(List<QuakeCloudDSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<QuakeCloudDSItem> items){
        List<String> ids = new ArrayList<>();
        for(QuakeCloudDSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}

