package com.example.manikandan.wipro.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.manikandan.wipro.R;

import timber.log.Timber;

public class BaseActivity extends AppCompatActivity {

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);

        } catch (Exception e) {
            Timber.e(e, e.getMessage());
        }
    }


    public void showProcess(@Nullable String msg) {
        try {
            if (TextUtils.isEmpty(msg)) {
                msg = "Loading...";
            }
            mProgressDialog.setMessage(msg);
            mProgressDialog.show();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void hideProcess() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }

        } catch (Exception e) {
            Timber.e(e, e.getMessage());
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void noNetworkConnectionDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.no_internet)
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_ok_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
