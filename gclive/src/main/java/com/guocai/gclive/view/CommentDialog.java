package com.guocai.gclive.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.guocai.gclive.R;
import com.guocai.gclive.utils.KeybordS;
import com.guocai.gclive.utils.ReleaseKeyBoard;

/**
 * author： mengjie on 2017/11/16.
 * email: weidadajie@163.com
 */

public class CommentDialog extends Dialog {
    private Context context;
    private OnclickCommentListener onclickCommentListener;
    private EditText commentEdit;
    private ImageView commentCommit;
    private String hintContent;

    public void setHintContent(String hintContent) {
        this.hintContent = hintContent;
    }

    public CommentDialog(@NonNull Context context, OnclickCommentListener onclickCommentListener) {
        super(context, R.style.comment_dialog);
        this.context = context;
        this.onclickCommentListener = onclickCommentListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_comment);
        initFindViewById();
        initView();

    }

    private void initFindViewById() {
        commentEdit = findViewById(R.id.comment_edit);
        commentCommit = findViewById(R.id.comment_commit);
        commentCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentContent = commentEdit.getText().toString().trim();
//                if (RegexSafeUtil.isCommentSafe(commentContent)) {
//                    Toast.makeText(context, "输入评论包含敏感信息，不能提交", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if (!TextUtils.isEmpty(commentContent)) {
                    onclickCommentListener.CommentSuccess(commentContent);
                }
                dismiss();
            }
        });
    }

    private void initView() {
        // 点击Dialog外部消失
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
            window.setWindowAnimations(R.style.bottom_menu_animation); // 添加动画
        }

        if (hintContent != null) {
            commentEdit.setHint("hintContent");
        }

        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth(); // 设置宽度
        getWindow().setAttributes(lp);
        KeybordS.openKeybord(commentEdit, context);
//        commentEdit.setBackListener(new www.zftc.snsmodule.view.component.MyEditText.BackListener() {
//            @Override
//            public void back(TextView textView) {
//                dismiss();
//            }
//        });
        commentEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (s.toString().trim().length() == 0) {
//                    commentCommit.setTextColor(context.getResources().getColor(R.color.text_error_color));
//                } else {
//                    commentCommit.setTextColor(context.getResources().getColor(R.color.room_table_text_color));
//                }
            }
        });
    }

    @Override
    public void dismiss() {
        View currentFocus = getCurrentFocus();
        if (currentFocus instanceof TextView) {
            if (KeybordS.isSoftInputShow((Activity) context)) {
                KeybordS.closeKeybord(commentEdit, context);
            }
        }
        ReleaseKeyBoard.fixInputMethodManagerLeak(context);
        onclickCommentListener.dialogDismiss();
        super.dismiss();
    }

    @Override
    public void onBackPressed() {
        onclickCommentListener.dialogDismiss();
        dismiss();
        super.onBackPressed();
    }

    public interface OnclickCommentListener {
        void CommentSuccess(String content);
        void dialogDismiss();
    }
}
