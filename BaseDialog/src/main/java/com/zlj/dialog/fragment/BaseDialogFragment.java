package com.zlj.dialog.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.zlj.dialog.DialogViewHolder;
import com.zlj.dialog.R;

/**
 * Created by zlj on 2021/4/22.
 * @Word：Thought is the foundation of understanding
 * @since 1.0.0
 * DialogFragment  方便模块化开发，直接使用系统的就行
 */
public abstract class BaseDialogFragment<T extends BaseDialogFragment> extends DialogFragment {
    //定义Key
    private static final String LEFT_MARGIN = "left_margin";
    private static final String RIGHT_MARGIN = "right_margin";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String DIM = "dim_amount";
    private static final String CANCEL = "out_cancel";
    private static final String ANIM = "anim_style";
    private static final String LAYOUT = "layout_id";
    //View复用Holder
    private DialogViewHolder dialogViewHolder;
    //默认背景透明度
    private float dimAmount=0.4f;
    //宽度
    private int width;
    //高度
    private int height;
    //是否可以取消
    private boolean outCancel=true;
    //动画样式
    private int animStyle;
    //对齐方式
    private int gravity;
    //左外边距
    private int leftMargin;
    //由外边距
    private int rightMargin;

    /**
     * onStart
     */
    @Override
    public void onStart() {
        super.onStart();
        // 初始化对话框背景显示       此处初始化因为Fragment已被添加到任务栈中
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            //调节灰色背景透明度[0-1]，默认0.5f
            lp.dimAmount = dimAmount;
            //设置dialog宽度
            if (width == 0) {//默认跟屏幕等宽
                lp.width = getScreenWidth(getContext()) -   leftMargin-rightMargin;
            } else if (width == WindowManager.LayoutParams.WRAP_CONTENT) {
                // 包裹内容
                lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            } else { // 宽度
                int ww = getScreenWidth(getContext());
                if (width > (ww -leftMargin-rightMargin)
                        ||width==ViewGroup.LayoutParams.MATCH_PARENT) {
                    //如果设置的宽度大于屏幕宽度减去左右边距，显示最大宽
                    lp.width = ww - leftMargin-rightMargin;
                } else {
                    lp.width = width;
                }
            }
            //设置dialog高度
            if (height <= 0&&height!=ViewGroup.LayoutParams.MATCH_PARENT) {
                //默认包裹内容
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {// 如果设置了高度值，显示高度减去边距
                lp.height = height;
            }
            lp.gravity = gravity;
            //设置dialog进入、退出的动画
            window.setWindowAnimations(animStyle);
            //lp.flags |=WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;//突破窗口限制
            window.setAttributes(lp);
        }
        setCancelable(outCancel);
    }

    /**
     * onCreateDialog
     * @param savedInstanceState
     * @return
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog= super.onCreateDialog(savedInstanceState);
        dialog.setCancelable(outCancel);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    /**
     * onCreate
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.quick_dialog_fragment);
        //恢复保存的数据
        if (savedInstanceState != null) {
            leftMargin = savedInstanceState.getInt(LEFT_MARGIN);
            rightMargin = savedInstanceState.getInt(RIGHT_MARGIN);
            width = savedInstanceState.getInt(WIDTH);
            height = savedInstanceState.getInt(HEIGHT);
            dimAmount = savedInstanceState.getFloat(DIM);
            outCancel = savedInstanceState.getBoolean(CANCEL);
            animStyle = savedInstanceState.getInt(ANIM);
        }
    }

    /**
     * onSaveInstanceState
     * @param outState
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(LEFT_MARGIN, leftMargin);
        outState.putInt(RIGHT_MARGIN, rightMargin);
        outState.putInt(WIDTH, width);
        outState.putInt(HEIGHT, height);
        outState.putFloat(DIM, dimAmount);
        outState.putBoolean(CANCEL, outCancel);
        outState.putInt(ANIM, animStyle);
        outState.putInt(LAYOUT, getContentViewId());
    }

    /**
     * onCreateView
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = getLayoutInflater().inflate(getContentViewId(),container,false);

        dialogViewHolder=DialogViewHolder.get(view);

        bindView(dialogViewHolder);

        return view;

    }

    /**
     * 绑定View上的数据
     * @param dialogViewHolder
     */
    protected abstract void bindView(DialogViewHolder dialogViewHolder);

    /**
     * 获取布局文件ID
     * @return
     */
    public abstract int getContentViewId();

    /**
     * setDimAmount
     * @param dimAmount
     * @return
     */
    public T setDimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
        return (T) this;
    }

    /**
     * setOutCancel
     * @param outCancel
     * @return
     */
    public T setOutCancel(boolean outCancel) {
        this.outCancel = outCancel;
        return (T) this;
    }

    /**
     * setLeftMargin
     * @param leftMargin
     * @return
     */
    public T setLeftMargin(int leftMargin) {
        this.leftMargin = leftMargin;
        return (T) this;
    }

    /**
     * setRightMargin
     * @param rightMargin
     * @return
     */
    public T setRightMargin(int rightMargin) {
        this.rightMargin = rightMargin;
        return (T) this;
    }

    /**
     * show
     * @param manager
     */
    public void show(FragmentManager manager) {
        super.show(manager, String.valueOf(System.currentTimeMillis()));
    }

    /**
     * 设置动画样式
     * @param animStyle
     */
    public T setAnimationStyle(int animStyle) {
        this.animStyle = animStyle;
        return (T) this;
    }

    /**
     * 从底部一直弹到中间
     */
    public T fromBottomToMiddle() {
        setAnimationStyle(R.style.window_bottom_in_bottom_out);
        return (T) this;
    }

    /**
     * 从底部弹出
     */
    public T fromBottom() {
        fromBottomToMiddle();
        setGravity(Gravity.CENTER | Gravity.BOTTOM);
        return (T) this;
    }

    /**
     * 设置对齐方向
     * @param gravity
     * @return
     */
    public T setGravity(int gravity) {
        this.gravity=gravity;
        return (T) this;
    }

    /**
     * 从左边一直弹到中间退出也是到左边
     */
    public T fromLeftToMiddle() {
        setAnimationStyle(R.style.window_left_in_left_out);
        setGravity(Gravity.CENTER | Gravity.LEFT);
        return (T) this;
    }

    /**
     *  从右边一直弹到中间退出也是到右边
     * @return
     */
    public T fromRightToMiddle() {
        setAnimationStyle(R.style.window_right_in_right_out);
        setGravity(Gravity.RIGHT);
        return (T) this;
    }

    /**
     * 从顶部弹出 从顶部弹出  保持在顶部
     * @return
     */
    public T fromTop() {
        fromTopToMiddle();
        setGravity(Gravity.CENTER | Gravity.TOP);
        return (T) this;
    }

    /**
     * 从顶部谈到中间  从顶部弹出  保持在中间
     * @return
     */
    public T fromTopToMiddle() {
        setAnimationStyle(R.style.window_top_in_top_out);
        return (T) this;
    }

    /**
     * 从正中间缩放动画
     * @return
     */
    public T fromCenterScale() {
        setAnimationStyle(R.style.dialog_scale);
        return (T) this;
    }

    /**
     * 全屏显示T
     */
    public T fullScreen() {
        return (T) setHeight(ViewGroup.LayoutParams.MATCH_PARENT).
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
    }

    /**
     * 全屏宽度
     */
    public T fullWidth() {
        return  setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
    }

    /**
     * 全屏高度
     */
    public T fullHeight() {
        return   setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
    }

    /**
     *setWidthAndHeight
     * @param width  自定义的宽度
     * @param height  自定义的高度
     * @return
     */
    public T setWidthAndHeight(int width, int height) {
        return (T) setWidth(width).setHeight(height);
    }

    /**
     *setWidth
     * @param width  自定义的宽度
     * @return
     */
    public T setWidth(int width) {
        this.width=width;
        return (T) this;
    }

    /**
     *setHeight
     * @param height  自定义的高度
     * @return
     */
    public T setHeight(int height) {
        this.height=height;
        return (T) this;
    }

    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    protected int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }
}
