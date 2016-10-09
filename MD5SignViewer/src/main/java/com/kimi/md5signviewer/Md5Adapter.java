package com.kimi.md5signviewer;

import android.content.Context;
import android.text.ClipboardManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kimi.md5signviewer.util.BasicAdapter;
import com.kimi.md5signviewer.util.ToastUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by kimi on 2016/10/5.
 */
public class Md5Adapter extends BasicAdapter<MainActivity.Model> {
    public Md5Adapter(Context c) {
        super(c);
    }

    //@ViewInject(R.id.tv_app_name)
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
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.item_layout, null);
        }
        final MainActivity.Model m = data.get(position);
        x.view().inject(this, convertView);
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