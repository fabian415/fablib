package com.advantech.fablib;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class FabianUtility {
    private static PageLoader loader = new PageLoader();
    private static CustomBottomSheet customBottomSheet = new CustomBottomSheet();

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showDatePicker(Context context, DatePickerCallback datePickerCallback) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                datePickerCallback.onDateSet(year, month + 1, dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public static void showLoader(Context context, String message) {
        loader.show(context, message);
    }

    public static void closeLoader() {
        loader.getDialog().dismiss();
    }

    public static void showBottomSheet(Context context, List<String> options, CustomBottomSheetCallback customBottomSheetCallback) {
        customBottomSheet.show(context, options, customBottomSheetCallback);
    }

    public static void closeBottomSheet() {
        customBottomSheet.dismiss();
    }

}
