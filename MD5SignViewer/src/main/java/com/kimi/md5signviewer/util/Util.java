package com.kimi.md5signviewer.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import com.kimi.md5signviewer.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kimi on 2016/10/5.
 */
public class Util {
    List<MainActivity.Model> dataSets = new ArrayList<>();

    public List<MainActivity.Model> getDataSets() {
        return dataSets;
    }

    public void setDataSets(List<MainActivity.Model> dataSets) {
        this.dataSets = dataSets;
    }

    public List<MainActivity.Model> search(String kwd) {
        if (dataSets == null) {
            return Collections.emptyList();
        }
        if (kwd == null || "".equals(kwd.trim())) {
            return dataSets;
        }
        List<MainActivity.Model> results = new ArrayList<MainActivity.Model>();
        for (MainActivity.Model ai : dataSets) {
            if (ai.pkgName.contains(kwd) || ai.md5.contains(kwd)) {
                results.add(ai);
            }
        }
        return results;
    }

    protected static Signature[] getRawSignature(Context context,
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
