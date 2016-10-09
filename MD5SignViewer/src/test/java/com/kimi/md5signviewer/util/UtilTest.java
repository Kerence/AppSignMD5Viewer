package com.kimi.md5signviewer.util;

import android.content.DialogInterface;

import com.kimi.md5signviewer.MainActivity;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kimi on 2016/10/5.
 */
public class UtilTest {
    @Test
    public void searchEmpty() {
        Util util = new Util();
        List<MainActivity.Model> res = util.search("abc");
        Assert.assertEquals(0, res.size());
        res = util.search(null);
        Assert.assertEquals(0, res.size());
        res = util.search("");
        Assert.assertEquals(0, res.size());
    }

    @Test
    public void search() {
        Util util = new Util();
        List<MainActivity.Model> lm = new ArrayList<MainActivity.Model>();
        lm.add(new MainActivity.Model("packageName", "md5"));
        util.setDataSets(lm);
        List<MainActivity.Model> res = util.search("abc");
        Assert.assertEquals(0, res.size());
        DialogInterface.OnClickListener
    }

    @Test
    public void searchWithNoneEmptyResult() {
        Util util = new Util();
        List<MainActivity.Model> lm = new ArrayList<MainActivity.Model>();
        lm.add(new MainActivity.Model("packageName", "md5"));
        util.setDataSets(lm);
        List<MainActivity.Model> res = util.search("md5");
        Assert.assertEquals(1, res.size());
        res = util.search("p");
        Assert.assertEquals(1, res.size());


        lm.add(new MainActivity.Model("packageName1", "md51"));
        res = util.search("md5");
        Assert.assertEquals(2, res.size());

        res = util.search("1");
        Assert.assertEquals(1, res.size());
    }


}
