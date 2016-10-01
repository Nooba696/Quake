
package com.ibm.mobileappbuilder.quake20160930102313.ds;
import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.POST;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Path;
import retrofit.http.PUT;

public interface QuakeCloudDSServiceRest{

	@GET("/app/57ee433d57acb00300063d6d/r/quakeCloudDS")
	void queryQuakeCloudDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<QuakeCloudDSItem>> cb);

	@GET("/app/57ee433d57acb00300063d6d/r/quakeCloudDS/{id}")
	void getQuakeCloudDSItemById(@Path("id") String id, Callback<QuakeCloudDSItem> cb);

	@DELETE("/app/57ee433d57acb00300063d6d/r/quakeCloudDS/{id}")
  void deleteQuakeCloudDSItemById(@Path("id") String id, Callback<QuakeCloudDSItem> cb);

  @POST("/app/57ee433d57acb00300063d6d/r/quakeCloudDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<QuakeCloudDSItem>> cb);

  @POST("/app/57ee433d57acb00300063d6d/r/quakeCloudDS")
  void createQuakeCloudDSItem(@Body QuakeCloudDSItem item, Callback<QuakeCloudDSItem> cb);

  @PUT("/app/57ee433d57acb00300063d6d/r/quakeCloudDS/{id}")
  void updateQuakeCloudDSItem(@Path("id") String id, @Body QuakeCloudDSItem item, Callback<QuakeCloudDSItem> cb);

  @GET("/app/57ee433d57acb00300063d6d/r/quakeCloudDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
}

