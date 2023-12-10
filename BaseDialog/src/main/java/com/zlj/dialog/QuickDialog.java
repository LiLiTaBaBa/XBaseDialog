package com.zlj.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import android.view.View;
import android.view.Window;

/**
 * Created by zlj on 2021/4/22.
 * @Word：Thought is the foundation of understanding
 * @since 1.0.0
 * 快速构建Dialog
 */
public  class QuickDialog {
    private BaseDialog mDialog;
    private DialogViewHolder viewHolder;
    private Context mContext;
    /**
     * QuickDialog
     * @param context
     * @param style
     */
    private QuickDialog(Context context, int style) {
        this.mContext=context;
        mDialog = new BaseDialog(context, style) {
            @Override
            protected void bindView(DialogViewHolder dialogVh) {
            }
        };
    }

    /**
     * 创建QuickDialog
     * @param context
     * @return
     */
    public static QuickDialog create(Context context) {
        return  create(context,R.style.quick_dialog);
    }

    /**
     * 创建QuickDialog
     * @param context
     * @param style
     * @return
     */
    public static QuickDialog create(Context context,int style) {
        return  new QuickDialog(context,style);
    }

    /**
     * 设置内容View
     * @param layoutId
     * @return
     */
    public QuickDialog setContentView(int layoutId){
        return setContentView(View.inflate(mContext,layoutId,null));
    }

    /**
     * 设置内容View
     * @param view
     * @return
     */
    public QuickDialog setContentView(@NonNull View view){
        viewHolder = DialogViewHolder.get(view);
        mDialog.setContentView(viewHolder.getConvertView());
        return this;
    }

    /**
     * @since 1.0.2
     * 设置数据绑定的回调
     * @param bindViewListener
     * @return
     */
    public QuickDialog setOnBindViewListener(OnBindViewListener bindViewListener){
        mDialog.setOnBindViewListener(bindViewListener);
        return this;
    }


    /**
     * 显示dialog
     */
    public QuickDialog show() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
        return this;
    }

    /**
     *设置Window背景的透明色
     * @param light 弹出时背景亮度 值为0.0~1.0    1.0表示全黑  0.0表示全白
     * @return
     */
    public QuickDialog backgroundLight(double light) {
         mDialog.backgroundLight(light);
        return this;
    }

    /**
     * 设置窗口动画样色
     * @param animationStyle
     * @return
     */
    public QuickDialog setWindowAnimations(int animationStyle) {
        getWindow().setWindowAnimations(animationStyle);
        return this;
    }

    /**
     * 从底部一直弹到中间
     */
    public QuickDialog fromBottomToMiddle() {
        mDialog.fromBottomToMiddle();
        return this;
    }

    /**
     * 从底部弹出
     */
    public QuickDialog fromBottom() {
        mDialog.fromBottom();
        return this;
    }

    /**
     * 从左边一直弹到中间退出也是到左边
     */
    public QuickDialog fromLeftToMiddle() {
        mDialog.fromLeftToMiddle();
        return this;
    }

    /**
     *  从右边一直弹到中间退出也是到右边
     * @return
     */
    public QuickDialog fromRightToMiddle() {
        mDialog.fromRightToMiddle();
        return this;
    }

    /**
     * 从顶部弹出 从顶部弹出  保持在顶部
     * @return
     */
    public QuickDialog fromTop() {
        mDialog.fromTop();
        return this;
    }

    /**
     * 从顶部谈到中间  从顶部弹出  保持在中间
     * @return
     */
    public QuickDialog fromTopToMiddle() {
        mDialog.fromTopToMiddle();
        return this;
    }
    /**
     *显示
     * @param style 显示一个Dialog自定义一个弹出方式  具体怎么写 可以模仿上面的
     * @return
     */
    public QuickDialog show(@StyleRes int style) {
        getWindow().setWindowAnimations(style);
        mDialog.show();
        return this;
    }

    /**
     * 显示
     * @param isAnimation 如果为true 就显示默认的一个缩放动画
     * @return
     */
    public QuickDialog show(boolean isAnimation) {
        if(isAnimation){
            getWindow().setWindowAnimations(R.style.dialog_scale);
        }else{
            getWindow().setWindowAnimations(-1);
        }
        mDialog.show();
        return this;
    }

    /**
     * 全屏显示
     */
    public QuickDialog fullScreen() {
        mDialog.fullScreen();
        return this;
    }

    /**
     * 监听键盘
     * @param onKeyListener
     * @return
     */
    public QuickDialog setOnKeyListener(DialogInterface.OnKeyListener onKeyListener){
        mDialog.setOnKeyListener(onKeyListener);
        return this;
    }
    /**
     * 全屏宽度
     */
    public QuickDialog fullWidth() {
        mDialog.fullWidth();
        return this;
    }

    /**
     * 全屏高度
     */
    public QuickDialog fullHeight() {
        mDialog.fullHeight();
        return this;
    }

    /**
     *设置宽度和高度
     * @param width  自定义的宽度
     * @param height  自定义的高度
     * @return
     */
    public QuickDialog setWidthAndHeight(int width, int height) {
        mDialog.setWidthAndHeight(width,height);
        return this;
    }

    /**
     *设置宽度
     * @param width  自定义的宽度
     * @return
     */
    public QuickDialog setWidth(int width) {
        mDialog.setWidth(width);
        return this;
    }

    /**
     *设置高度
     * @param height  自定义的高度
     * @return
     */
    public QuickDialog setHeight(int height) {
        mDialog.setHeight(height);
        return this;
    }

    /**
     * 设置设置对齐方向
     * @param gravity
     * @return
     */
    public QuickDialog setGravity(int gravity){
        getWindow().setGravity(gravity);
        return this;
    }

    /**
     * 取消对话框
     */
    public void cancel() {
        if (mDialog != null && mDialog.isShowing())
            dismiss();
    }

    /**
     * 隐藏对话框
     */
    public void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
    /**
     * 设置消失监听
     */
    public QuickDialog setOnDismissListener(DialogInterface.OnDismissListener listener) {
        mDialog.setOnDismissListener(listener);
        return this;
    }

    /**
     * 设置取消监听
     */
    public QuickDialog setOnCancelListener(DialogInterface.OnCancelListener listener) {
        mDialog.setOnCancelListener(listener);
        return this;
    }

    /**
     * 设置是否能取消
     */
    public QuickDialog setCancelable(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }

    /**
     * 设置触摸其他地方是否能取消
     */
    public QuickDialog setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    /**
     * 获取Dialog对话框
     * @return
     */
    public Dialog getDialog() {
        return mDialog;
    }

    /**
     * 获取Window对象
     * @return
     */
    public Window getWindow() {
        return getDialog().getWindow();
    }

    /**
     * 获取ViewHolder
     * @return
     */
    public DialogViewHolder getViewHolder() {
        return viewHolder;
    }

    /**
     * 获取内容View
     * @return
     */
    public View getContentView() {
        return getViewHolder().getConvertView();
    }
}
