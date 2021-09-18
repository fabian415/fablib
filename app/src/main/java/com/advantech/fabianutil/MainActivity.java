package com.advantech.fabianutil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.advantech.fablib.CustomBottomSheetCallback;
import com.advantech.fablib.DatePickerCallback;
import com.advantech.fablib.FabianUtility;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        // 抓取元素
        Button btnDateTime = findViewById(R.id.btnDateTime);
        Button btnLoader = findViewById(R.id.btnLoader);
        Button btnBottomSelect = findViewById(R.id.btnBottomSelect);

        // 註冊事件
        btnDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FabianUtility.showDatePicker(context, new DatePickerCallback() {
                    @Override
                    public void onDateSet(int year, int month, int date) {
                        FabianUtility.showToast(context, String.format("%d/%d/%d", year, month, date));
                    }
                });
            }
        });

        btnLoader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打開 Loader
                FabianUtility.showLoader(context, "Loading ...");

                // 五秒後關閉 Loader
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 關閉 Loader
                        FabianUtility.closeLoader();
                    }
                }, 5000);
            }
        });

        btnBottomSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 準備要顯示的清單
                List<String> options = new ArrayList<>();
                options.add("哈士奇");
                options.add("牧羊犬");
                options.add("台灣黑狗");
                options.add("柯基");
                options.add("蝴蝶狗");
                options.add("吉娃娃");
                options.add("臘腸犬");

                // 顯示 BottomSheet
                FabianUtility.showBottomSheet(context, options, new CustomBottomSheetCallback() {
                    @Override
                    public void onOptionClicked(String option) {
                        FabianUtility.showToast(context, option);
                    }

                    @Override
                    public void onCanceled() {
                        FabianUtility.showToast(context, "Canceled");
                    }
                });
            }
        });
    }
}