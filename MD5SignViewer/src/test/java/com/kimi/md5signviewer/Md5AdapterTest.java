package com.kimi.md5signviewer;

import android.view.View;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, packageName = "com.kimi.md5signviewer")
public class Md5AdapterTest {
    @Test
    public void modelMapToView() {
        List<MainActivity.Model> list = new ArrayList<MainActivity.Model>();
        MainActivity.Model m1 = new MainActivity.Model();
        m1.md5 = "12345";
        m1.pkgName = "packageName";
        list.add(m1);
        Md5Adapter adapter = new Md5Adapter(RuntimeEnvironment.application);
        adapter.setData(list);
        View v = adapter.getView(0, null, null);
        Assert.assertEquals("12345", ((TextView) v.findViewById(R.id.tv_md5)).getText());
        Assert.assertEquals("packageName", ((TextView) v.findViewById(R.id.tv_package_name)).getText());
    }
}