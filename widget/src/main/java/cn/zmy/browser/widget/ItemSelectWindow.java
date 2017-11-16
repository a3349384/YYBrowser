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
        final WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Window window = ReflectUtil.getFieldValue(windowManager, "mParentWindow");
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
                windowManager.removeView(mBackPressAwareFrameLayout);
            }
        });
        mBackPressAwareFrameLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                windowManager.removeView(mBackPressAwareFrameLayout);
            }
        });
        windowManager.addView(mBackPressAwareFrameLayout, layoutParams);
    }

    private BackPressAwareFrameLayout onCreateView()
    {
        BackPressAwareFrameLayout backPressAwareFrameLayout = (BackPressAwareFrameLayout) LayoutInflater.from(mContext).inflate(R.layout.items_selecter, null);
        ViewGroup viewItemsContainer = backPressAwareFrameLayout.findViewById(R.id.viewItemsContainer);
        if (!TextUtils.isEmpty(mTitle))
        {
            //如果有标题，就添加标题
            ItemViewHolder titleViewHolder = new ItemViewHolder(mContext, viewItemsContainer);
            titleViewHolder.imageViewIcon.setVisibility(View.GONE);
            titleViewHolder.textViewContent.setText(mTitle);
            titleViewHolder.textViewContent.setTextColor(mContext.getResources().getColor(R.color.colorGrayMiddle));
            viewItemsContainer.addView(titleViewHolder.root);
        }
        if (mAdapter != null)
        {
            int count = mAdapter.getCount();
            for (int i = 0; i < count; i++)
            {
                ItemViewHolder titleViewHolder = new ItemViewHolder(mContext,viewItemsContainer);
                Drawable icon = mAdapter.getIcon(i);
                String content = mAdapter.getContent(i);
                if (icon == null)
                {
                    titleViewHolder.imageViewIcon.setVisibility(View.GONE);
                }
                else
                {
                    titleViewHolder.imageViewIcon.setImageDrawable(icon);
                }
                titleViewHolder.textViewContent.setText(content);
                viewItemsContainer.addView(titleViewHolder.root);
            }
        }
        return backPressAwareFrameLayout;
    }

    private static class ItemViewHolder
    {
        private ViewGroup root;
        private ImageView imageViewIcon;
        private TextView textViewContent;

        public ItemViewHolder(Context context, ViewGroup parent)
        {
            root = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.items_selector_item, parent, false);
            imageViewIcon = root.findViewById(R.id.imageViewIcon);
            textViewContent = root.findViewById(R.id.textViewContent);
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

        public ItemSelectWindow build()
        {
            ItemSelectWindow itemSelectWindow = new ItemSelectWindow(mContext);
            itemSelectWindow.mAdapter = mAdapter;
            itemSelectWindow.mTitle = mTitle;
            return itemSelectWindow;
        }
    }
}
