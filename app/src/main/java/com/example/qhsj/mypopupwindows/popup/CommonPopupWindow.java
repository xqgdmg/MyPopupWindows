package com.example.qhsj.mypopupwindows.popup;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

import com.example.qhsj.mypopupwindows.other.PopupController;
import com.example.qhsj.mypopupwindows.utils.CommonUtil;

public class CommonPopupWindow extends PopupWindow {
    final PopupController controller;

    @Override
    public int getWidth() {
        return controller.mPopupView.getMeasuredWidth();
    }

    @Override
    public int getHeight() {
        return controller.mPopupView.getMeasuredHeight();
    }

    public interface ViewInterface {
        void getChildView(View view, int layoutResId);
    }

    private CommonPopupWindow(Context context) {  // private 不用 public ，只能内部创建对象
        controller = new PopupController(context, this);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        controller.setBackGroundLevel(1.0f);
    }

    /*
     * 建造者设计模式 return this;
     */
    public static class Builder {
        private final PopupController.PopupParams params;  // 静态类
        private ViewInterface listener;

        public Builder(Context context) {
            params = new PopupController.PopupParams(context);
        }

        public Builder setView(int layoutResId) {
            params.mView = null;
            params.layoutResId = layoutResId;
            return this;
        }

        public Builder setView(View view) {
            params.mView = view;
            params.layoutResId = 0;
            return this;
        }

        public Builder setViewOnclickListener(ViewInterface listener) {
            this.listener = listener;
            return this;
        }

        public Builder setWidthAndHeight(int width, int height) {
            params.mWidth = width;
            params.mHeight = height;
            return this;
        }

        /**
         * 设置背景灰色程度
         */
        public Builder setBackGroundLevel(float level) {
            params.isShowBg = true;
            params.bg_level = level;
            return this;
        }

        /**
         * 是否可点击Outside消失
         */
        public Builder setOutsideTouchable(boolean touchable) {
            params.isTouchable = touchable;
            return this;
        }

        /**
         * 设置动画
         */
        public Builder setAnimationStyle(int animationStyle) {
            params.isShowAnim = true;
            params.animationStyle = animationStyle;
            return this;
        }

        public CommonPopupWindow create() {

            final CommonPopupWindow popupWindow = new CommonPopupWindow(params.mContext);

            params.apply(popupWindow.controller);

            if (listener != null && params.layoutResId != 0) {
                listener.getChildView(popupWindow.controller.mPopupView, params.layoutResId);
            }

            CommonUtil.measureWidthAndHeight(popupWindow.controller.mPopupView);

            return popupWindow;
        }
    }
}
