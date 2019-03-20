package com.xzb.pluginizationdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xzb.pluginizationdemo.utils.FileHelpUtil;
import com.xzb.pluginlib.PluginManager;
import com.xzb.pluginlib.ProxyActivity;
/**
 * @Author: xiangzhenbiao
 * @Date: 2018-10-20 17:39
 * @Description:
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLoadPluginApk;
    private Button btnJumpToTargetActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoadPluginApk = findViewById(R.id.btn_load_plugin_apk);
        btnLoadPluginApk.setOnClickListener(this);
        btnJumpToTargetActivity = findViewById(R.id.btn_jump_to_target_activity);
        btnJumpToTargetActivity.setOnClickListener(this);

        PluginManager.getInstance().init(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_load_plugin_apk:
                loadPluginFile();
                break;
            case R.id.btn_jump_to_target_activity:
                jumpToTargetActivity();
                break;
            default:
                break;
        }
    }


    /**
     * 加载apk文件
     */
    private void loadPluginFile() {
        //这里直接将assets的apk文件拷贝至CacheDir目录，模拟从服务器下载插件apk文件
        String apkPath = FileHelpUtil.copyAssetFileToCacheDir(this, "pluginapp-debug.apk");
        PluginManager.getInstance().loadApk(apkPath);
    }

    /**
     * 跳转到插件的目标界面
     * 这里不能直接跳转过去，那么通过先跳转至baseLib的ProxyActivity类
     */
    private void jumpToTargetActivity() {
        Intent intent = new Intent(this, ProxyActivity.class);
        intent.putExtra("targetClassName", "com.xzb.pluginapp.PluginTargetActivity");
        startActivity(intent);
    }

}
