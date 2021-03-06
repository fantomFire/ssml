package com.github.library.layoutView.behavior;

import android.content.Context;
        import android.support.design.widget.AppBarLayout;
        import android.support.design.widget.CoordinatorLayout;
        import android.support.v4.view.ViewCompat;
        import android.util.AttributeSet;
        import android.view.View;

/**
 * Created by 苏坡坡要吃婆婆酥 on 2018/1/26 12:39.
 *
 * 自定义BottomNavigationView底部菜单栏上滑隐藏效果(需要配合ToolBar使用)
 *
 */

public class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<View> {

    public BottomNavigationViewBehavior() {

    }

    public BottomNavigationViewBehavior(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {

        ((CoordinatorLayout.LayoutParams) child.getLayoutParams()).topMargin = parent.getMeasuredHeight() - child.getMeasuredHeight();
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {

        //因为Behavior只对CoordinatorLayout的直接子View生效，因此将依赖关系转移到AppBarLayout
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

        //得到依赖View的滑动距离
        int top = ((AppBarLayout.Behavior)((CoordinatorLayout.LayoutParams)dependency.getLayoutParams()).getBehavior()).getTopAndBottomOffset();
        //因为BottomNavigation的滑动与ToolBar是反向的，所以取-top值
        ViewCompat.setTranslationY(child, -top);
        return false;
    }
}
