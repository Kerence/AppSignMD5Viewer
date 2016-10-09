package com.kimi.md5signviewer;

import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;

/**
 * Created by kimi on 2016/10/5.
 */
public class MainActivityTest {
    @Test
    public void showAllPackagesAtStartup() {
        ActivityController<MainActivity> ac = Robolectric.buildActivity(MainActivity.class);
        ac.create();
//        search();
    }
}
