package com.app.framework.basic;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 适配器基类
 * Created by JayChen on 16/3/17.
 */
public abstract class BaseAppAdapter<T> extends BaseAdapter{

    private List<T> mDataList;

    public BaseAppAdapter(List<T> dataList){
        this.mDataList=dataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
