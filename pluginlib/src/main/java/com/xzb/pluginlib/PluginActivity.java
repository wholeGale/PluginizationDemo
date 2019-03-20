package com.xzb.pluginlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * @Author: xiangzhenbiao
 * @Date: 2018-10-20 20:47
 * @Description:
 */

public class PluginActivity extends Activity implements ILifeCycle{

    private int mFrom;

    private Activity mActivity;

    @Override
    public void attach(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void setContentView(int layoutResID) {
        if(mFrom == FROM_INTERNAL){
            super.setContentView(layoutResID);
        }else{
            mActivity.setContentView(layoutResID);
        }


    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        if(saveInstanceState != null){
            mFrom = saveInstanceState.getInt("FROM");
        }
        if(mFrom == FROM_INTERNAL){
            super.onCreate(saveInstanceState);
            mActivity = this;
        }else{

        }
    }

    @Override
    public void onRestart() {
        if(mFrom == FROM_INTERNAL){
            super.onRestart();
        }else{

        }
    }

    @Override
    public void onStart() {
        if(mFrom == FROM_INTERNAL){
            super.onStart();
        }else{

        }
    }

    @Override
    public void onResume() {
        if(mFrom == FROM_INTERNAL){
            super.onResume();
        }else{

        }
    }

    @Override
    public void onPause() {
        if(mFrom == FROM_INTERNAL){
            super.onPause();
        }else{

        }
    }

    @Override
    public void onStop() {
        if(mFrom == FROM_INTERNAL){
            super.onStop();
        }else{

        }
    }

    @Override
    public void onDestroy() {
        if(mFrom == FROM_INTERNAL){
            super.onDestroy();
        }else{

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(mFrom == FROM_INTERNAL){
            super.onActivityResult(requestCode, resultCode,data);
        }else{

        }
    }
}
