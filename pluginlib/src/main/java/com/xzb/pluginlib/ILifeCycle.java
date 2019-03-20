package com.xzb.pluginlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * @Author: xiangzhenbiao
 * @Date: 2018-10-20 20:37
 * @Description: 管理生命周期接口
 */

public interface ILifeCycle {

    int FROM_INTERNAL = 0 ;
    int FROM_EXTERNAL = 1 ;

    /**
     * 拿到上下文来控制生命周期方法
     * @param activity
     */
    void attach(Activity activity);

    void onCreate(Bundle saveInstanceState);

    void onRestart();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onActivityResult(int requestCode, int resultCode, Intent data);

}
