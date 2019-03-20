package com.xzb.pluginlib;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @Author: xiangzhenbiao
 * @Date: 2018-10-20 20:58
 * @Description:
 */

public class ProxyActivity extends Activity {

    private String targetClassName;

    private PluginApk mPluginApk;

    private ILifeCycle iLifeCycle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        targetClassName = intent.getStringExtra("targetClassName");
        //拿到PluginApk对象
        mPluginApk = PluginManager.getInstance().getPluginApk();

        launchPluginTargetActivity();
    }

    /**
     * 启动插件中目标界面
     */
    private void launchPluginTargetActivity() {
        try {
            //拿到 pluginTargetActivity 对象
            Class<?> clazz = mPluginApk.mDexClassLoader.loadClass(targetClassName);
            //注：这里的 pluginTargetActivity 只是我们手动加载出来的一个对象，并没有经过系统加载，
            //并没有经过平时正常的activity启动过程，所以不是严格意义上的Activity对象，也就不具备生命周期/上下文
            Object pluginTargetActivity = clazz.newInstance();
            if(pluginTargetActivity instanceof ILifeCycle){
                iLifeCycle = (ILifeCycle) pluginTargetActivity;
                iLifeCycle.attach(this);
                Bundle bundle = new Bundle();
                bundle.putInt("FROM", ILifeCycle.FROM_EXTERNAL);
                iLifeCycle.onCreate(bundle);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        return mPluginApk != null? mPluginApk.mResources : super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return mPluginApk != null? mPluginApk.mAssetManager : super.getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        return mPluginApk != null? mPluginApk.mDexClassLoader : super.getClassLoader();
    }
}
