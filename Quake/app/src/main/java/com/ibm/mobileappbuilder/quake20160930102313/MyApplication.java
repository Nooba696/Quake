

package com.ibm.mobileappbuilder.quake20160930102313;

import android.app.Application;
import ibmmobileappbuilder.injectors.ApplicationInjector;
import ibmmobileappbuilder.cloudant.factory.CloudantDatabaseSyncerFactory;
import java.net.URI;


/**
 * You can use this as a global place to keep application-level resources
 * such as singletons, services, etc.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationInjector.setApplicationContext(this);
        //Syncing cloudant ds
        CloudantDatabaseSyncerFactory.instanceFor(
            "quake_database",
            URI.create("https://26a5cf3a-1fb1-41d8-a978-c9f3cf929ae0-bluemix:f419f1e18446db8f2a7bef92d95ca66bcd50660e555f4854a70f9b08638f5772@26a5cf3a-1fb1-41d8-a978-c9f3cf929ae0-bluemix.cloudant.com/quake_database/")
        ).sync(null);
      }
}

