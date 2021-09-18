package com.advantech.fablib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class CustomBottomSheet {

    private BottomSheetDialog bottomSheetDialog;
    private List<String> options;
    private ButtonAdapter buttonAdapter;
    private CustomBottomSheetCallback customBottomSheetCallback;

    public void show(Context context, List<String> options, CustomBottomSheetCallback customBottomSheetCallback) {
        this.customBottomSheetCallback = customBottomSheetCallback;

        bottomSheetDialog = new BottomSheetDialog(context); // 初始化 BottomSheet
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet, null); // 連結的介面
        // Cancel Button
        Button btCancel = view.findViewById(R.id.button_cancel);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                customBottomSheetCallback.onCanceled();
            }
        });

        // RecyclerView
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvButtons);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        buttonAdapter = new ButtonAdapter(context, options);
        recyclerView.setAdapter(buttonAdapter);

        bottomSheetDialog.setContentView(view); // 將介面載入至BottomSheet內
        ViewGroup parent = (ViewGroup) view.getParent(); // 取得BottomSheet介面設定
        parent.setBackgroundResource(android.R.color.transparent); // 將背景設為透明，否則預設白底
        bottomSheetDialog.show();
    }

    public void dismiss() {
        bottomSheetDialog.dismiss();
    }

    private class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.MyViewHolder> {
        private Context context;
        private List<String> options;

        public ButtonAdapter(Context context, List<String> options) {
            this.context = context;
            this.options = options;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.itemview_button, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            String option = this.options.get(position);
            holder.btn.setText(option);
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customBottomSheetCallback.onOptionClicked(option);
                    dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return this.options.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            Button btn;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                btn = (Button) itemView.findViewById(R.id.btn);
            }
        }
    }

}
