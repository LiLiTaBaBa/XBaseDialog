package com.zlj.dialog.fragment;

import androidx.fragment.app.FragmentManager;
import com.zlj.dialog.DialogViewHolder;
import com.zlj.dialog.OnBindViewListener;

/**
 * Created by zlj on 2021/4/22.
 * @Word：Thought is the foundation of understanding
 * @since 1.0.1
 * DialogFragment快速构建类
 */
public class QuickDialogFragment extends BaseDialogFragment<QuickDialogFragment> {
    //布局ID
    private int mContentViewId;
    //bindView的回调
    private OnBindViewListener onBindViewListener;
    //Fragment管理器
    private FragmentManager mFragmentManager;

    /**
     * 使用静态方法创建Fragment
     * @param fragmentManager
     * @return
     */
    public static QuickDialogFragment create(FragmentManager fragmentManager){
        QuickDialogFragment quickDialogFragment=new QuickDialogFragment();
        return quickDialogFragment.setFragmentManager(fragmentManager);
    }

    /**
     * 设置Fragment管理器
     * @param mFragmentManager
     * @return
     */
    public QuickDialogFragment setFragmentManager(FragmentManager mFragmentManager) {
        this.mFragmentManager = mFragmentManager;
        return this;
    }

    /**
     * 设置BindView回调接口
     * @param onBindViewListener
     * @return
     */
    public QuickDialogFragment setOnBindViewListener(OnBindViewListener onBindViewListener) {
        this.onBindViewListener = onBindViewListener;
        return this;
    }

    /**
     * 设置布局ID
     * @param contentViewId
     * @return
     */
    public QuickDialogFragment setContentViewId(int contentViewId) {
        this.mContentViewId = contentViewId;
        return this;
    }

    /**
     * convert
     * @param dialogViewHolder
     */
    @Override
    protected void bindView(DialogViewHolder dialogViewHolder) {
        if(onBindViewListener!=null){
            onBindViewListener.bindView(dialogViewHolder);
        }
    }

    /**
     * 获取布局ID
     * @return
     */
    @Override
    public int getContentViewId() {
        return mContentViewId;
    }

    /**
     * 显示
     */
    public void show(){
        super.show(mFragmentManager);
    }
}
