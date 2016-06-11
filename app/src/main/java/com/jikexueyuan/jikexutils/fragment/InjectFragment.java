package com.jikexueyuan.jikexutils.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.jikexueyuan.jikexutils.R;
import com.jikexueyuan.jikexutils.adapter.ChildAdapter;
import com.jikexueyuan.jikexutils.domain.Child;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by houn.xu
 */
@ContentView(R.layout.inject_view)
public class InjectFragment extends Fragment {
    @ViewInject(R.id.listview)
    ListView listView;
    List<Child> children = new ArrayList<>();
    @ViewInject(R.id.btn_inject)
    Button btnInject;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i=0;i<100;i++){
            children.add(new Child("张三"+i));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView.setAdapter(new ChildAdapter(getActivity(),children));
//        btnInject.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btnInject.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                return false;
//            }
//        });
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });

    }
    @Event(type = View.OnClickListener.class,value = R.id.btn_inject)
    private void testInjectOnClick(View view){
        Snackbar.make(view,"OnClickListener",Snackbar.LENGTH_SHORT).show();

    }
    @Event(type = View.OnLongClickListener.class,value = R.id.btn_inject)
    private boolean testOnLongClickListener(View view){
        Snackbar.make(view,"testOnLongClickListener",Snackbar.LENGTH_SHORT).show();
        return true;
    }
    @Event(type =AdapterView.OnItemClickListener.class,value = R.id.listview)
    private void testOnItemClick(AdapterView<?> parent, View view, int position, long id){
        Snackbar.make(view,children.get(position).getName(),Snackbar.LENGTH_SHORT).show();
    }

}
