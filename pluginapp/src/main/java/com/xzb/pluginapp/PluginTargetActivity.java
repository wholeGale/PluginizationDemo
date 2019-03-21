package com.xzb.pluginapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.xzb.pluginlib.PluginActivity;

/**
 * @Author: xiangzhenbiao
 * @Date: 2018-10-20 20:58
 * @Description: 插件的目标界面，这里的PluginTargetActivity只是我们手动创建的一个Activity类，
 * 加载出来的对象pluginTargetActivity，没有经过系统加载，也就没有经过平时正常的activity启动过程，
 * 所以不是严格意义上的Activity对象，也就不具备生命周期/上下文
 */
public class PluginTargetActivity extends PluginActivity {

    private TextView tv_plugin_main_name_text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin_target);

//        tv_plugin_main_name_text = findViewById(R.id.tv_plugin_main_name_text);
        //注意pluginApp里面不能直接访问资源，findViewById,getResources()这些都不能直接获取对象，
        //要通过mActivity.getXXX来获取
        tv_plugin_main_name_text = mActivity.findViewById(R.id.tv_plugin_main_name_text);
        tv_plugin_main_name_text.setText(mActivity.getResources().getText(R.string.plugin_main_name_text));
    }


}
