package com.kimi.md5signviewer;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.kimi.md5signviewer.util.BasicAdapter;
import com.kimi.md5signviewer.util.MD5Util;
import com.kimi.md5signviewer.util.ToastUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kimi on 2016/5/2.
 */
public class MainActivity extends Activity {
    public class Model {
        public String pkgName;
        public String appName;
        public String md5;
    }

    @ViewInject(R.id.et_search)
    EditText et_search;


    @ViewInject(R.id.lv)
    ListView lv;

    public static class Md5Adapter extends BasicAdapter<Model> {
        public Md5Adapter(Context c) {
            super(c);
        }

        //        @ViewInject(R.id.tv_app_name)
        TextView tv_app_name;
        @ViewInject(R.id.tv_package_name)
        TextView tv_package_name;
        @ViewInject(R.id.tv_md5)
        TextView tv_md5;
        @ViewInject(R.id.btn_copy)
        Button btn_copy;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflator = (LayoutInflater)
                        parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflator.inflate(R.layout.item_layout, null);
            }
            final Model m = data.get(position);
            x.view().inject(this, convertView);
//            tv_app_name.setText(m.appName);
            tv_package_name.setText(m.pkgName);
            tv_md5.setText(m.md5);
            btn_copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    cm.setText(m.md5);
                    ToastUtils.showShort("MD5 Copied");
                }
            });
            return convertView;
        }
    }

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
                search(s + "");
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
            m.md5 = getSign(this, ai.packageName);
            lpi.add(m);
        }
        adapter = new Md5Adapter(this);
        adapter.setData(lpi);
        lv.setAdapter(adapter);
    }

    Md5Adapter adapter;
    List<Model> lpi = new ArrayList<Model>();

    void search(String t) {
        if (t == null || "".equals(t)) {
            adapter.setData(lpi);
        } else {
            List<Model> sr = new ArrayList<Model>();
            adapter.setData(sr);
            for (Model ai : lpi) {
                if (ai.pkgName.contains(t) || ai.md5.contains(t)) {
                    sr.add(ai);
                }
            }
        }
        //order by package name
        adapter.notifyDataSetChanged();
    }

    public static Signature[] getRawSignature(Context context,
                                              String packageName) {
        if ((packageName == null) || (packageName.length() == 0)) {
            return null;
        }
        PackageManager pkgMgr = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = pkgMgr.getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES | PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
        if (info == null) {
            return null;
        }
        return info.signatures;
    }

    public String getSign(Context context, String packageName) {
        Signature[] signs = getRawSignature(context, packageName);
        if ((signs == null) || (signs.length == 0)) {
            return null;
        } else {
            Signature sign = signs[0];
            String signMd5 = MD5Util.MD5(sign.toByteArray());
            return signMd5;
        }
    }
}
