package com.jikexueyuan.jikexutils.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jikexueyuan.jikexutils.R;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.io.File;

/**
 * Created by houn.xu
 */
@ContentView(R.layout.http_view)
public class HttpFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    String url = "http://www.jikexueyuan.com";
    @Event(R.id.get)
    private void get(View v){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("请稍候...");
        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("username","houn.xu");
        params.addQueryStringParameter("password","123456");
        Callback.Cancelable cancelable = x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("houn.xu", "onSuccess result<<" + result);
                progressDialog.cancel();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                progressDialog.cancel();
            }
        });
        cancelable.cancel();
    }
    @Event(R.id.post)
    private void post(View view){
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("username","jikexueyuan");
        params.addParameter("password","123456");
        params.addHeader("head","jike");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    @Event(R.id.other)
    private void other(View v){
        RequestParams params = new RequestParams(url);
        params.addParameter("username","houn");
        x.http().request(HttpMethod.PUT, params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    @Event(R.id.upload)
    private void upload(View v){
        String path="/mnt/sdcard/Download/1.jpg";
        RequestParams params = new RequestParams(url);
        params.setMultipart(true);
        params.addBodyParameter("file",new File(path));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    @Event(R.id.download)
    private void download(View v){
        url = "http://124.193.230.157/dd.myapp.com/16891/28F3DE528CE9DAE149E2D39A26BB94CA.apk?mkey=5735c90b910ce983&f=8e5d&c=0&fsname=com.jikexueyuan.geekacademy_4.2.0-4f71632_421.apk&p=.apk";
        RequestParams params = new RequestParams(url);
        params.setSaveFilePath(Environment.getExternalStorageDirectory()+"/jikeapp/");
        params.setAutoRename(true);
        x.http().post(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File result) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(result),"application/vnd.android.package-archive");
                getActivity().startActivity(intent);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                Log.i("houn.xu","current<<"+current +" total<<"+total);
            }
        });
    }
    @Event(R.id.cache)
    private void cache(View v) {
        RequestParams params = new RequestParams(url);
        params.setCacheMaxAge(1000*60);
        Callback.Cancelable cancelable = x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("xxhong","onSuccess<<"+result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                //false 不相信本地缓存
                //true 相信本地缓存
                Log.i("houn.xu","cache<<"+result);
                return true;
            }
        });
    }
}
