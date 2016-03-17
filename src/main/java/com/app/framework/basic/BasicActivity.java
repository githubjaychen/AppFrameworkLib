package com.app.framework.basic;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

/**
 * 此类为Activity的基类,含网络状态改变接收器，默认不启动，可手动打开/关闭
 * 参考文献 《设计模式之禅》 模板方法设计模式
 * Created by JayChen on 3/16/16.
 */
public abstract class BasicActivity extends Activity{

    //网络接收状态
    private ReceiverStatus receiverStatus=ReceiverStatus.DEFAULT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadActivity();
    }

    /**
     * 加载Activity
     */
    private void loadActivity() {
        initView();
        findViews();
        loadViewData();
        bindEvent();

        //判断是否接收广播
        boolean isReceiver=isReceiver();
        if(isReceiver){
            startNetworkStateReceiver();
        }
    }

    /**
     * 初始化视图(模板过程)
     */
    protected abstract void initView();

    /**
     * find视图控件(模板过程)
     */
    protected void findViews(){}

    /**
     * 加载界面数据(模板过程)
     */
    protected  void loadViewData(){}

    /**
     * 绑定事件(模板过程)
     */
    protected void bindEvent(){}


    /**
     * 网络发生了变化(模板过程)
     */
    protected void onNetworkChanged(NetworkInfo.State netWorkState){}


    /**
     * 是否接收广播(模板钩子函数)
     * @return 默认不接收广播
     */
    protected boolean isReceiver(){
        return false;
    }



    /**
     * 启动网络状态广播接收
     */
    protected void startNetworkStateReceiver(){
        if(receiverStatus!=ReceiverStatus.STARTRECEIVER) {//如果当前是未启动状态则启动广播接收
            //开始接收广播
            IntentFilter networkIntentFilter = new IntentFilter();
            networkIntentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(networkStateReceiver, networkIntentFilter);
            //设置广播接收状态为启动
            changeReceiverStatus(ReceiverStatus.STARTRECEIVER);
        }
    }

    /**
     * 停止网络广播接收
     */
    protected void stopNetworkStateReceiver(){
        //停止接收广播
        unregisterReceiver(networkStateReceiver);
        //设置接收状态为停止
        changeReceiverStatus(ReceiverStatus.STOPRECEIVER);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //生命周期结束，如果广播是启动状态的那么就停止广播接收
        if(receiverStatus==ReceiverStatus.STARTRECEIVER){
            stopNetworkStateReceiver();
        }
    }


    /**
     * 广播接收器实例
     */
    private BroadcastReceiver networkStateReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            switch (action){
                case ConnectivityManager.CONNECTIVITY_ACTION:
                    NetworkInfo networkInfo=intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK);
                    NetworkInfo.State networkState=networkInfo.getState();
                    onNetworkChanged(networkState);
                    break;
            }
        }
    };

    /**
     * 改变广播接收状态
     * @param receiverStatus 网络接收状态
     */
    private void changeReceiverStatus(ReceiverStatus receiverStatus){
        this.receiverStatus=receiverStatus;
    }

    /**
     * 网络接收状态枚举
     */
    private enum ReceiverStatus{
        DEFAULT,//默认状态
        STARTRECEIVER,//启动状态
        STOPRECEIVER//停止状态
    }


}
