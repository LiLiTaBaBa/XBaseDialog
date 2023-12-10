package com.zlj.dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * Created by ZhangLiJun on 2018/10/24.
 * @email：282384507@qq.com
 * @Word：Thought is the foundation of understanding
 */
public final class DialogViewHolder {
    private final SparseArray<View> mViews;
    private View mDialogView;

    private DialogViewHolder(Context context, int layoutId) {
        this(View.inflate(context, layoutId, null));
    }

    private DialogViewHolder(View view) {
        this.mViews = new SparseArray<>();
        mDialogView =view;
    }

    public static DialogViewHolder get(Context context, int layoutId) {
        return new DialogViewHolder(context, layoutId);
    }

    public static DialogViewHolder get(View view) {
        return new DialogViewHolder(view);
    }

    public View getConvertView() {
        return mDialogView;
    }

    /**
     * Set the string for TextView
     *
     * @param viewId
     * @param text
     * @return
     */
    public DialogViewHolder setText(int viewId, CharSequence text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * set view visible
     *
     * @param viewId
     * @return
     */
    public DialogViewHolder setViewInVisible(int viewId) {
        TextView view = getView(viewId);
        view.setVisibility(View.INVISIBLE);
        return this;
    }

    /**
     * set view visible
     *
     * @param viewId
     * @return
     */
    public DialogViewHolder setViewVisible(int viewId) {
        TextView view = getView(viewId);
        view.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * set view gone
     *
     * @param viewId
     * @return
     */
    public DialogViewHolder setViewGone(int viewId) {
        TextView view = getView(viewId);
        view.setVisibility(View.GONE);
        return this;
    }

    /**
     * 设置点击
     */
    public DialogViewHolder setOnClick(int viewId, OnClickListener onClick) {
        View view = getView(viewId);
        view.setOnClickListener(onClick);
        return this;
    }

    /**
     * Through control the Id of the access to control, if not join views
     *
     * @param viewId
     * @return
     */

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mDialogView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
}