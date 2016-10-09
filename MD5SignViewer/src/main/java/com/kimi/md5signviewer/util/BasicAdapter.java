package com.kimi.md5signviewer.util;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础适配器
 *
 * @param <T>
 * @author zhangjinming
 */
public abstract class BasicAdapter<T> extends BaseAdapter {
    protected Context context;

    public BasicAdapter(Context context) {
        this.context = context;
        selected = new SparseBooleanArray();
    }

    public T last() {
        if (data == null || data.size() == 0) {
            return null;
        }
        return data.get(data.size() - 1);
    }

    protected List<T> data = new ArrayList<T>();

    /**
     * 添加数据
     *
     * @param list
     */
    @Deprecated
    public void addData(List<T> list) {// 把list的内容加到data上
        if (list != null && list.size() > 0) {
            for (T t : list) {
                if (t != null) {
                    data.add(t);
                }
            }
        }
    }

    /**
     * 添加数据
     *
     * @param list
     */
    public void appendData(List<T> list) {// 把list的内容加到data上
        if (list != null && list.size() > 0) {
            for (T t : list) {
                if (t != null) {
                    data.add(t);
                }
            }
        }
    }

    /**
     * 添加数据
     *
     * @param list
     */
    public void appendDataExclusively(List<T> list) {// 把list的内容加到data上
        if (list != null && list.size() > 0) {
            for (T t : list) {
                if (t != null) {
                    appendDataExclusively(t);
                }
            }
        }
    }

    /**
     * 添加数据
     *
     * @param item
     */
    public void appendData(T item) {// 把list的内容加到data上
        data.add(item);
    }

    /**
     * 添加数据
     * 数据不存在则添加
     *
     * @param item
     */
    public void appendDataExclusively(T item) {// 把list的内容加到data上
        synchronized (data) {
            if (!data.contains(item)) {
                data.add(item);
            }
        }
    }

    /**
     * 添加数据
     *
     * @param list
     */
    public void prependData(List<T> list) {// 把list的内容加到data上
        if (list != null && list.size() > 0) {
            for (int i = list.size() - 1; i >= 0; i--) {
                T t = list.get(i);
                if (t != null) {
                    data.add(0, t);
                }
            }
        }
    }

    /**
     * 添加数据
     *
     * @param list
     */
    public void prependDataExclusively(List<T> list) {// 把list的内容加到data上
        if (list != null && list.size() > 0) {
            for (int i = list.size() - 1; i >= 0; i--) {
                T t = list.get(i);
                if (t != null) {
                    prependDataExclusively(t);
                }
            }
        }
    }

    public void prependData(T item) {// 把list的内容加到data上
        if (data.size() > 0) {
            data.add(0, item);
        } else {
            data.add(item);
        }
    }

    public void prependDataExclusively(T item) {// 把list的内容加到data上
        synchronized (data) {
            if (!data.contains(item)) {
                prependData(item);
            }
        }
    }

    /**
     * 替换数据
     *
     * @param list
     */
    public void setData(List<T> list) {
        data = list;
    }

    /**
     * 清空数据
     */
    public void clear() {
        if (data != null) {
            data.clear();
        }
    }

    public int getCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    public T getItem(int position) {
        return data.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    public List<T> getData() {
        return data;
    }

    public void notifyDataSetChanged(List<T> data) {
        synchronized (data) {
            if (data == null) {
                data = new ArrayList<T>();
            }
            this.data = data;
            this.notifyDataSetChanged();
        }

    }

    protected SparseBooleanArray selected;
    protected boolean isSingle = true;
    protected int old = -1;

    public void setSelectedItem(int selectedPosition) {
        if (isSingle = true && old != -1) {
            this.selected.put(old, false);
        }
        this.selected.put(selectedPosition, true);
        old = selectedPosition;
    }

    public int getFirstSelectedItemPosition() {
        for (int i = 0; i < this.selected.size(); i++) {
            if (this.selected.valueAt(i)) {
                return this.selected.keyAt(i);
            }
        }
        return -1;
    }
}
