package com.xzb.pluginlib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * @Author: xiangzhenbiao
 * @Date: 2018-10-20 20:06
 * @Description:
 */

public class PluginManager {
    //私有化
    private PluginManager(){

    }

    //单例
    private static final PluginManager instance = new PluginManager();

    private Context mContext;
    private PluginApk mPluginApk;

    public static PluginManager getInstance(){
        return instance;
    }

    public void init(Context context){
        mContext = context.getApplicationContext();
    }


    public PluginApk getPluginApk() {
        return mPluginApk;
    }


    //加载插件apk
    public void loadApk(String apkPath){
        PackageInfo packageInfo = mContext.getPackageManager()
                .getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES
                        | PackageManager.GET_SERVICES);
        if(packageInfo == null){
            return;
        }

        DexClassLoader dexClassLoader = createDexClassLoader(apkPath);
        AssetManager assetManager = createAssertManager(apkPath);
        Resources resources = createResource(assetManager);
        mPluginApk = new PluginApk(packageInfo, resources, dexClassLoader);
    }

    /**
     * 创建Resources，用于加载资源文件
     * @param assetManager
     * @return
     */
    private Resources createResource(AssetManager assetManager) {
        Resources resources = mContext.getResources();
        return new Resources(assetManager,
                resources.getDisplayMetrics(),resources.getConfiguration());
    }

    /**
     * 创建AssetManager
     * @param apkPath
     * @return
     */
    private AssetManager createAssertManager(String apkPath) {
        AssetManager assetManager = null;
        try {
            assetManager = AssetManager.class.newInstance();
            //通过反射调用内部隐藏的方法
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            method.invoke(assetManager, apkPath);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return assetManager;
    }

    /**
     * 创建DexClassLoader对象
     * @param apkPath
     * @return
     */
    private DexClassLoader createDexClassLoader(String apkPath) {
        File file = mContext.getDir("dex", Context.MODE_PRIVATE);
        return new DexClassLoader(apkPath, file.getAbsolutePath(),
                null, mContext.getClassLoader());
    }


    //创建Resource


}
