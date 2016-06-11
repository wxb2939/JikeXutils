package com.jikexueyuan.jikexutils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jikexueyuan.jikexutils.R;
import com.jikexueyuan.jikexutils.domain.Child;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by houn.xu
 */
public class ChildAdapter extends BaseAdapter {
    private List<Child> children;
    private LayoutInflater inflater;
    public ChildAdapter(Context context,List<Child> children) {
        this.children = children;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return children.size();
    }

    @Override
    public Object getItem(int position) {
        return children.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView ==null){
            convertView = inflater.inflate(R.layout.child_item,null);
            viewHolder = new ViewHolder();
            x.view().inject(viewHolder,convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Child child = children.get(position);
        viewHolder.childName.setText(child.getName());
        return convertView;
    }
    class ViewHolder{
        @ViewInject(R.id.tv_child_name)
        TextView childName;
    }
}
