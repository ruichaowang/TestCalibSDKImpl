package com.lixiang.testcalibsdkimpl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lixiang.svmclientsdk.SvmCalibClientImpl;
import com.lixiang.svmclientsdk.SvmCalibClientImpl.OnServiceStateChangedListener;
import com.lixiang.svmclientsdk.SvmCalibClientImpl.OnCalibrationChangedListener;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {
    private SvmCalibClientImpl m_CalibService;
    private static final String TAG = "模拟诊断 app";
    private AtomicBoolean mSVMAvailable = new AtomicBoolean(false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_CalibService = new SvmCalibClientImpl(this);
        m_CalibService.setOnServiceStateChangedListener(new MyServiceStateListener());
        m_CalibService.setOnCalibrationChangedListener(new MyCalibrationListener());
    }


    /** SVM Service Connect listener */
    private class MyServiceStateListener implements OnServiceStateChangedListener  {
        @Override
        public void onServiceConnected( boolean connected )  {
            Log.i( TAG, "标定程序链接: " + connected );
            mSVMAvailable.set( connected );
        }
    }
    /** SVM calibration result listener */
    private class MyCalibrationListener implements OnCalibrationChangedListener  {
        /** calibration callback */
        @Override
        public void onCalibrationStateChanged( int state )
        {
            Log.i( TAG, "onCalibrationStateChanged, state: " + state);
            // try wake waiting thread of executing request
        }

        /** calibration data download callback */
        @Override
        public void onUploadStateChanged( int state )
        {
            Log.i( TAG, "onUploadStateChanged, state: " + state );
            // try wake waiting thread of executing request
        }

        /** calibration data download callback */
        @Override
        public void onDownloadStateChanged( int state )
        {
            Log.i( TAG, "onDownloadStateChanged, state: " + state );
            // try wake waiting thread of executing request
        }

        @Override
        public void onCalibrationButtonClicked( boolean isConfirm )  {
            Log.i( TAG, "点击了取消按钮: " + isConfirm );
        }
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
        Toast.makeText(getApplicationContext(), "点击悬架 2 标定按钮", Toast.LENGTH_LONG) .show();
        m_CalibService.openCalibrationView(2);
    }

    public void click_calib_3(View v) throws RemoteException {
        Toast.makeText(getApplicationContext(), "点击悬架 3 标定按钮", Toast.LENGTH_LONG) .show();

        if (!mSVMAvailable.get()) {
            Log.w( TAG, "return because SVM is unvailable" );
        } else {
            m_CalibService.openCalibrationView(3);
        }

    }

    public void click_calib_4(View v) throws RemoteException {
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