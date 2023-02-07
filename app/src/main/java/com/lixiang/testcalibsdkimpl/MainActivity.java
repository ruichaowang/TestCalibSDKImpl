package com.lixiang.testcalibsdkimpl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lixiang.svmclientsdk.SvmCalibClientImpl;

public class MainActivity extends AppCompatActivity {
    private SvmCalibClientImpl m_CalibService;
    private static final String TAG = "模拟诊断 app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_CalibService = new SvmCalibClientImpl(this);
        m_CalibService.setOnServiceStateChangedListener(new SvmCalibClientImpl.OnServiceStateChangedListener() {
            @Override
            public void onServiceConnected(boolean connected) {
                Log.i(TAG, "标定服务连接上!");
            }
        });
        m_CalibService.setOnCalibrationChangedListener(new SvmCalibClientImpl.OnCalibrationChangedListener() {
            @Override
            public void onCalibrationStateChanged(int i) {
                Log.i(TAG, "收到标定的结果为" + i );
            }

            @Override
            public void onCalibrationButtonClicked(boolean b) {
                //Do nothing
            }

            @Override
            public void onUploadStateChanged(int i) {
                Log.i(TAG, "标定上传结果" + i );
            }

            @Override
            public void onDownloadStateChanged(int i) {
                Log.i(TAG, "标定下载结果" + i );
            }
        });
    }

    /** 按钮的实现部分*/
    public void click_upload(View v) throws RemoteException {
        Log.i(TAG, "点击上传文件按钮");
        Toast.makeText(getApplicationContext(), "点击上传文件按钮", Toast.LENGTH_LONG) .show();
        m_CalibService.uploadCalibrationData();
    }

    public void click_download(View v) throws RemoteException {
        Log.i(TAG, "点击上传文件按钮");
        Toast.makeText(getApplicationContext(), "点击上传文件按钮", Toast.LENGTH_LONG) .show();
        m_CalibService.downloadCalibrationData();
    }

    public void click_calib_2(View v) throws RemoteException {
        Log.i(TAG, "点击悬架 2 标定 按钮");
        Toast.makeText(getApplicationContext(), "点击悬架 2 标定按钮", Toast.LENGTH_LONG) .show();
        m_CalibService.openCalibrationView(2);
    }

    public void click_calib_3(View v) throws RemoteException {
        Log.i(TAG, "点击悬架 3 标定 按钮");
        Toast.makeText(getApplicationContext(), "点击悬架 3 标定按钮", Toast.LENGTH_LONG) .show();
        m_CalibService.openCalibrationView(3);
    }

    public void click_calib_4(View v) throws RemoteException {
        Log.i(TAG, "点击悬架 4 标定 按钮");
        Toast.makeText(getApplicationContext(), "点击悬架 4 标定按钮", Toast.LENGTH_LONG) .show();
        m_CalibService.openCalibrationView(4);
    }

    public void click_calib_5(View v) throws RemoteException {
        Log.e(TAG, "停止标定标定显示");
        m_CalibService.stopCalibration();
    }

    public void click_calib_6(View v) throws RemoteException {
        Log.e(TAG, "停止标定标定显示");
        m_CalibService.unBindService();
    }

}