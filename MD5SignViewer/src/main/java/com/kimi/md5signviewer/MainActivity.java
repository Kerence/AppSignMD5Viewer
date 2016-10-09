package com.kimi.md5signviewer;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.kimi.md5signviewer.util.Util;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kimi on 2016/5/2.
 */
public class MainActivity extends Activity {
    public static class Model {
        public String pkgName;
        public String appName;
        public String md5;

        public Model(String pkgName, String md5) {
            this.pkgName = pkgName;
            this.md5 = md5;
        }

        public Model() {
        }
    }

    @ViewInject(R.id.et_search)
    EditText et_search;

    @ViewInject(R.id.lv)
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.setData(util.search(s + ""));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        PackageManager pkgMgr = getPackageManager();
        List<ApplicationInfo> lai = pkgMgr.getInstalledApplications(0);
        for (ApplicationInfo ai : lai) {
            Model m = new Model();
            m.pkgName = ai.packageName;
            m.md5 = util.getSign(this, ai.packageName);
            dataSet.add(m);
        }
        util.setDataSets(dataSet);
        adapter = new Md5Adapter(this);
        lv.setAdapter(adapter);
        adapter.setData(util.search(null));
        adapter.notifyDataSetChanged();
    }

    Util util = new Util();
    Md5Adapter adapter;
    List<Model> dataSet = new ArrayList<Model>();

}
