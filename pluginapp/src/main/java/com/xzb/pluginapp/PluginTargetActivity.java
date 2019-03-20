package com.xzb.pluginapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xzb.pluginlib.PluginActivity;

/**
 * @Author: xiangzhenbiao
 * @Date: 2018-10-20 20:58
 * @Description: 插件的目标界面，这里的PluginTargetActivity只是我们手动创建的一个Activity类，
 * 加载出来的对象pluginTargetActivity，没有经过系统加载，也就没有经过平时正常的activity启动过程，
 * 所以不是严格意义上的Activity对象，也就不具备生命周期/上下文
 */
public class PluginTargetActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin_target);
    }


}
