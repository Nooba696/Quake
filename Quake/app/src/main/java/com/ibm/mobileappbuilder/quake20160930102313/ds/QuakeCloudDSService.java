
package com.ibm.mobileappbuilder.quake20160930102313.ds;
import java.net.URL;
import com.ibm.mobileappbuilder.quake20160930102313.R;
import ibmmobileappbuilder.ds.RestService;
import ibmmobileappbuilder.util.StringUtils;

/**
 * "QuakeCloudDSService" REST Service implementation
 */
public class QuakeCloudDSService extends RestService<QuakeCloudDSServiceRest>{

    public static QuakeCloudDSService getInstance(){
          return new QuakeCloudDSService();
    }

    private QuakeCloudDSService() {
        super(QuakeCloudDSServiceRest.class);

    }

    @Override
    public String getServerUrl() {
        return "https://ibm-pods.buildup.io";
    }

    @Override
    protected String getApiKey() {
        return "J7OybnR7";
    }

    @Override
    public URL getImageUrl(String path){
        return StringUtils.parseUrl("https://ibm-pods.buildup.io/app/57ee433d57acb00300063d6d",
                path,
                "apikey=J7OybnR7");
    }

}

