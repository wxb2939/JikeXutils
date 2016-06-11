package com.jikexueyuan.jikexutils.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jikexueyuan.jikexutils.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

/**
 * Created by houn.xu
 */
@ContentView(R.layout.image_view)
public class ImageFragment extends Fragment {
    @ViewInject(R.id.image01)
    ImageView image01;
    @ViewInject(R.id.image02)
    ImageView image02;
    @ViewInject(R.id.image03)
    ImageView image03;
    @ViewInject(R.id.image04)
    ImageView image04;
    @ViewInject(R.id.image05)
    ImageView image05;
    @ViewInject(R.id.image06)
    ImageView image06;
    String[] urls={
            "http://img4.duitang.com/uploads/item/201209/03/20120903121445_nx5wk.jpeg",
            "http://i7.qhimg.com/t01755f436ab31acc3e.jpg",
            "http://www.dianziyan668.com/images/mnsg4lteovuxiylom4xgg33n/uploads/item/201203/11/20120311204006_GraP2.jpeg",
            "http://img4.imgtn.bdimg.com/it/u=2141450888,3610011306&fm=11&gp=0.jpg",
            "http://img3.duitang.com/uploads/item/201509/20/20150920214014_dYSRj.jpeg",
            "http://p2.gexing.com/G1/M00/85/40/rBACFFPoibzDeIc6AAC_zw6Zw1Y995_600x.jpg"
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setPic();
    }

    private void setPic() {
        ImageOptions options = new ImageOptions.Builder()
                .setCircular(true).
                build();
        ImageOptions options2 = new ImageOptions.Builder()
                .setSquare(true).
        build();
        ImageOptions options3 = new ImageOptions.Builder()
                .setCrop(true)
                .setSize(200,200).
                        build();
        x.image().bind(image01,urls[0],options);
        x.image().bind(image02,urls[1],options2);
        x.image().bind(image03, urls[2],options3, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {

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
        x.image().bind(image04, urls[3], options, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {

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
        Callback.Cancelable cancelable = x.image().loadDrawable(urls[0], options, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                image05.setImageDrawable(result);
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
//        cancelable.cancel();
        x.image().loadFile(urls[0], options, new Callback.CacheCallback<File>() {
            @Override
            public boolean onCache(File result) {
                //图片另存为 等操作
                Log.i("houn.xu","file<<"+result.getPath()+result.getName());
                return true;
            }

            @Override
            public void onSuccess(File result) {
                Log.i("houn.xu","file");
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



}
