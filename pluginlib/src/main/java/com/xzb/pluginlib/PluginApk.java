package com.xzb.pluginlib;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

/**
 * @Author: xiangzhenbiao
 * @Date: 2018-10-20 20:02
 * @Description:
 */

public class PluginApk {

    //插件apk的实体对象
    public PackageInfo mPackageInfo;

    public Resources mResources;

    public AssetManager mAssetManager;

    public DexClassLoader mDexClassLoader;

    public PluginApk(PackageInfo packageInfo, Resources resources, DexClassLoader dexClassLoader) {
        this.mPackageInfo = packageInfo;
        this.mResources = resources;
        this.mDexClassLoader = dexClassLoader;

        //
        this.mAssetManager = resources.getAssets();
    }


}
