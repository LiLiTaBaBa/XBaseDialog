package com.zlj.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by zlj on 2021/4/22 0022
 * @Word：Thought is the foundation of understanding
 * @since 1.0.0
 * Dialog
 */
public abstract class BaseDialog<T extends BaseDialog> extends Dialog {

    private DialogViewHolder dialogVh;

    public BaseDialog(@NonNull Context context) {
        this(context,R.style.quick_dialog);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }


    private OnBindViewListener bindViewListener;

    public void setOnBindViewListener(OnBindViewListener bindViewListener) {
        this.bindViewListener = bindViewListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //对话框创建的时候执行数据的绑定
        if(dialogVh!=null){
            bindView(dialogVh);
            if(bindViewListener!=null){
                bindViewListener.bindView(dialogVh);
            }
        }
    }

    @Override
    public void setContentView(@NonNull View view, @Nullable ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        dialogVh = DialogViewHolder.get(view);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        View view=View.inflate(getContext(),layoutResID,null);
        dialogVh = DialogViewHolder.get(view);
    }

    @Override
    public void setContentView(@NonNull View view) {
        super.setContentView(view);
        dialogVh = DialogViewHolder.get(view);
    }


    /**
     * convert
     * @param dialogVh
     */
    protected abstract void bindView(DialogViewHolder dialogVh);

    /**
     *backgroundLight
     * @param light 弹出时背景亮度 值为0.0~1.0    1.0表示全黑  0.0表示全白
     * @return
     */
    public T backgroundLight(double light) {
        if (light < 0.0 || light > 1.0)
            return (T) this;
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount = (float) light;
        getWindow().setAttributes(lp);
        return (T) this;
    }


    /**
     * 从底部一直弹到中间
     */
    public T fromBottomToMiddle() {
        getWindow().setWindowAnimations(R.style.window_bottom_in_bottom_out);
        return (T) this;
    }

    /**
     * 从底部弹出
     */
    public T fromBottom() {
        fromBottomToMiddle();
        getWindow().setGravity(Gravity.CENTER | Gravity.BOTTOM);
        return (T) this;
    }

    /**
     * 从左边一直弹到中间退出也是到左边
     */
    public T fromLeftToMiddle() {
        getWindow().setWindowAnimations(R.style.window_left_in_left_out);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setGravity(Gravity.CENTER | Gravity.LEFT);
        return (T) this;
    }

    /**
     *  从右边一直弹到中间退出也是到右边
     * @return
     */
    public T fromRightToMiddle() {
        getWindow().setWindowAnimations(R.style.window_right_in_right_out);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setGravity(Gravity.RIGHT);
        return (T) this;
    }

    /**
     * 从顶部弹出 从顶部弹出  保持在顶部
     * @return
     */
    public T fromTop() {
        fromTopToMiddle();
        getWindow().setGravity(Gravity.CENTER | Gravity.TOP);
        return (T) this;
    }

    /**
     * 从顶部谈到中间  从顶部弹出  保持在中间
     * @return
     */
    public T fromTopToMiddle() {
        getWindow().setWindowAnimations(R.style.window_top_in_top_out);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        return (T) this;
    }

    /**
     * 从正中间缩放动画
     * @return
     */
    public T fromCenterScale() {
        getWindow().setWindowAnimations(R.style.dialog_scale);
        return (T) this;
    }

    /**
     * 全屏显示T
     */
    public T fullScreen() {
        WindowManager.LayoutParams wl = getWindow().getAttributes();
        wl.height = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        onWindowAttributesChanged(wl);
        return (T) this;
    }

    /**
     * 全屏宽度
     */
    public T fullWidth() {
        WindowManager.LayoutParams wl = getWindow().getAttributes();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        onWindowAttributesChanged(wl);
        return (T) this;
    }

    /**
     * 全屏高度
     */
    public T fullHeight() {
        WindowManager.LayoutParams wl = getWindow().getAttributes();
        wl.height = ViewGroup.LayoutParams.MATCH_PARENT;
        onWindowAttributesChanged(wl);
        return (T) this;
    }

    /**
     *setWidthAndHeight
     * @param width  自定义的宽度
     * @param height  自定义的高度
     * @return
     */
    public T setWidthAndHeight(int width, int height) {
        WindowManager.LayoutParams wl = getWindow().getAttributes();
        wl.width = width;
        wl.height = height;
        onWindowAttributesChanged(wl);
        return (T) this;
    }

    /**
     *setWidth
     * @param width  自定义的宽度
     * @return
     */
    public T setWidth(int width) {
        WindowManager.LayoutParams wl = getWindow().getAttributes();
        wl.width = width;
        onWindowAttributesChanged(wl);
        return (T) this;
    }

    /**
     *setHeight
     * @param height  自定义的高度
     * @return
     */
    public T setHeight(int height) {
        WindowManager.LayoutParams wl = getWindow().getAttributes();
        wl.height = height;
        onWindowAttributesChanged(wl);
        return (T) this;
    }



}
