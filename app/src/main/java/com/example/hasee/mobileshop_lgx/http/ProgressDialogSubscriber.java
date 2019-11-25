package com.example.hasee.mobileshop_lgx.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

public class ProgressDialogSubscriber<T> extends Subscriber<T> {
    private final Context mContext;
    private ProgressDialog mDialog;

    public ProgressDialogSubscriber(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException){
            Toast.makeText(mContext,"网络中断，请见检查您的网络状态",Toast.LENGTH_SHORT).show();
        }else if (e instanceof ConnectException){
            Toast.makeText(mContext,"网络中断，请见检查您的网络状态",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(mContext,"error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        dismissProgressDialog();
    }

    @Override
    public void onNext(T t) {

    }

    private void dismissProgressDialog(){
        if (mDialog!=null&&mDialog.isShowing()){
            mDialog.dismiss();
            mDialog=null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        showProgressDialog();
    }

    private void showProgressDialog() {
        if (mDialog==null){
            mDialog=new ProgressDialog(mContext);
            mDialog.setCancelable(true);
            mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    //取消订阅，取消请求
                    ProgressDialogSubscriber.this.unsubscribe();
                }
            });
        }
        if (mDialog!=null&&!mDialog.isShowing()){
            mDialog.show();
        }
    }
}
