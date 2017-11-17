package cn.zmy.browser.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import cn.zmy.common.utils.ReflectUtil;

/**
 * Created by zmy on 2017/11/15.
 */

public class ItemSelectWindow
{
    private Context mContext;
    private Adapter mAdapter;
    private String mTitle;
    private int mPreSelectedPosition;
    private View.OnClickListener mOnItemClickListener;
    private OnSelectedChangedListener mOnSelectedChangedListener;

    private WindowManager mWindowManager;
    private BackPressAwareFrameLayout mBackPressAwareFrameLayout;

    private ItemSelectWindow(Context context)
    {
        this.mContext = context;
    }

    public void show()
    {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                                                                                        WindowManager.LayoutParams.MATCH_PARENT,
                                                                                        0, 0,
                                                                                        PixelFormat.TRANSPARENT);
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Window window = ReflectUtil.getFieldValue(mWindowManager, "mParentWindow");
        if (window != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            layoutParams.flags = WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            layoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        }
        layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION;
        layoutParams.gravity = Gravity.NO_GRAVITY;

        mBackPressAwareFrameLayout = onCreateView();
        mBackPressAwareFrameLayout.setOnBackPressed(new Runnable()
        {
            @Override
            public void run()
            {
                closeWindow();
            }
        });
        mBackPressAwareFrameLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                closeWindow();
            }
        });
        mWindowManager.addView(mBackPressAwareFrameLayout, layoutParams);
    }

    private BackPressAwareFrameLayout onCreateView()
    {
        BackPressAwareFrameLayout backPressAwareFrameLayout = (BackPressAwareFrameLayout) LayoutInflater.from(mContext).inflate(R.layout.items_selecter, null);
        ViewGroup viewItemsContainer = backPressAwareFrameLayout.findViewById(R.id.viewItemsContainer);
        if (!TextUtils.isEmpty(mTitle))
        {
            //如果有标题，就添加标题
            ItemViewHolder titleViewHolder = new ItemViewHolder(mContext, viewItemsContainer);
            titleViewHolder.setup(null, mTitle, false, false);
            titleViewHolder.textViewContent.setTextColor(mContext.getResources().getColor(R.color.colorGrayMiddle));
            viewItemsContainer.addView(titleViewHolder.root);
        }
        if (mAdapter != null)
        {
            //响应子项点击事件
            mOnItemClickListener = new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (mOnSelectedChangedListener != null)
                    {
                        int clickedPosition = (int) v.getTag();
                        mOnSelectedChangedListener.onSelectedChanged(clickedPosition);
                        closeWindow();
                    }
                }
            };
            //填充子项内容
            int count = mAdapter.getCount();
            for (int i = 0; i < count; i++)
            {
                Drawable icon = mAdapter.getIcon(i);
                String content = mAdapter.getContent(i);
                ItemViewHolder titleViewHolder = new ItemViewHolder(mContext,viewItemsContainer);
                titleViewHolder.setup(icon, content, i == mPreSelectedPosition, true);
                titleViewHolder.root.setTag(i);
                titleViewHolder.root.setOnClickListener(mOnItemClickListener);
                viewItemsContainer.addView(titleViewHolder.root);
            }
        }
        return backPressAwareFrameLayout;
    }

    private void closeWindow()
    {
        mWindowManager.removeView(mBackPressAwareFrameLayout);
        mContext = null;
        mAdapter = null;
        mOnItemClickListener = null;
        mOnSelectedChangedListener = null;
    }

    private static class ItemViewHolder
    {
        private ViewGroup root;
        private ImageView imageViewIcon;
        private ImageView imageViewSelected;
        private TextView textViewContent;

        public ItemViewHolder(Context context, ViewGroup parent)
        {
            root = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.items_selector_item, parent, false);
            imageViewIcon = root.findViewById(R.id.imageViewIcon);
            imageViewSelected = root.findViewById(R.id.imageViewSelected);
            textViewContent = root.findViewById(R.id.textViewContent);
        }

        public void setup(Drawable icon, String content, boolean selected, boolean enabled)
        {
            this.root.setEnabled(enabled);
            this.textViewContent.setText(content);
            if (icon == null)
            {
                this.imageViewIcon.setVisibility(View.GONE);
            }
            else
            {
                this.imageViewIcon.setImageDrawable(icon);
            }
            if (selected)
            {
                this.imageViewSelected.setVisibility(View.VISIBLE);
            }
        }
    }

    public interface Adapter
    {
        int getCount();

        Drawable getIcon(int position);

        String getContent(int position);
    }

    public static class Builder
    {
        private Context mContext;
        private Adapter mAdapter;
        private String mTitle;
        private int mPreSelectedPosition;
        private OnSelectedChangedListener mOnSelectedChangedListener;

        public Builder(Context context)
        {
            this.mContext = context;
        }

        public Builder setAdapter(Adapter adapter)
        {
            this.mAdapter = adapter;
            return this;
        }

        public Builder setTitle(@StringRes int titleId)
        {
            this.mTitle = mContext.getResources().getString(titleId);
            return this;
        }

        public Builder setPreSelectedPosition(int position)
        {
            mPreSelectedPosition = position;
            return this;
        }

        public Builder setOnSelectedChangedListener(OnSelectedChangedListener listener)
        {
            this.mOnSelectedChangedListener = listener;
            return this;
        }

        public ItemSelectWindow build()
        {
            ItemSelectWindow itemSelectWindow = new ItemSelectWindow(mContext);
            itemSelectWindow.mAdapter = mAdapter;
            itemSelectWindow.mTitle = mTitle;
            itemSelectWindow.mPreSelectedPosition = mPreSelectedPosition;
            itemSelectWindow.mOnSelectedChangedListener = mOnSelectedChangedListener;
            return itemSelectWindow;
        }
    }

    public interface OnSelectedChangedListener
    {
        void onSelectedChanged(int position);
    }
}
