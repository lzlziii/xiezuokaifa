package xx.yy.qian;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.util.Util;

import java.util.List;

public class AnimListView extends View {
    //这个是显示的标题，默认的显示五个
    private String[] titles = {"标题1", "标题2", "标题3", "标题4", "标题5"};
    //底部文字的距离
    private int textPadding = 18;
    //柱状图开始X轴
    private int[] startX;
    //柱状图开始Y轴FF
    private int[] startY;
    //柱状图结束X轴
    private int[] endX;
    //柱状图结束Y轴
    private int[] endY;
    //这是文字的高度
    private int[] tvHeight;
    //传递过来的人数
    private int[] peoNumber;
    //柱状图高度
    private int[] viewHeight;
    //控件高和宽
    private int mHeight, mWidth;
    //画笔
    protected Paint paint;

    //柱子的宽度
    private int viewWidth = 28;

    public AnimListView(Context context) {
        super(context, null);
    }

    public AnimListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //首先测量控件的宽度和高度
        //测量宽度的模型和宽
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        //测量高度的模型和高
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        //设置柱状图的分布情况,默认是五条数据
        setViewState(5);
        setMeasuredDimension(mWidth, mHeight);
    }

    //设置柱状图的分布情况,默认是五条数据
    private void setViewState(int i) {
        viewHeight = new int[i];
        tvHeight = new int[i];
        peoNumber = new int[i];
        startX = new int[i];
        startY = new int[i];
        endX = new int[i];
        endY = new int[i];
        //设置柱子间距，第一个的距离是24，只有改为宽度减去24减去控件总宽度
        int viewPadding = (mWidth - Util.dip2px(getContext(), 44) - Util.dip2px(getContext(), viewWidth) * i) / i;
        for (int k = 0; k < i; k++) {
            //结束为止开始计算
            endX[k] = startX[k] = Util.dip2px(getContext(), 24) + Util.dip2px(getContext(), viewWidth) * (k + 1) + viewPadding * k;
            //设置当前的起始Y和结束Y都为控件的最底部
            startY[k] = mHeight - Util.dip2px(getContext(), textPadding);
            endY[k] = mHeight - Util.dip2px(getContext(), 1 + textPadding);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < startX.length; i++) {
            //设置柱子的颜色和默认的宽度
            paint.setColor(getResources().getColor(R.color.c_1495eb));
            paint.setStrokeWidth(Util.dip2px(getContext(), viewWidth));
            paint.setTextSize(Util.dip2px(getContext(), 14));
            //循环设置控件
            if (viewHeight[i] > 0) {
                //柱状图
                if (startY[i] >= viewHeight[i]) {
                    startY[i] -= 10;
                    postInvalidateDelayed(10);//如果没有达到数据要求的高度则一直重绘
                }
                paint.setColor(getResources().getColor(R.color.c_1495eb));

                //柱状图的绘制
                canvas.drawLine(startX[i], startY[i], endX[i], endY[i], paint);
                paint.setColor(getResources().getColor(R.color.c_2b3857));
                //数字的位数和旁边的距离
                int moveNum = 0;
                if (peoNumber[i] >= 10) {
                    moveNum = 10;
                } else {
                    moveNum = 6;
                }
                //底部文字的绘制
                canvas.drawText(peoNumber[i] + "", startX[i] - Util.dip2px(getContext(), moveNum), startY[i] - Util.dip2px(getContext(), 8), paint);
            } else {
                tvHeight[i] = mHeight - Util.dip2px(getContext(), 13 + textPadding);
                paint.setColor(getResources().getColor(R.color.c_2b3857));
                //为0的时候，底部文字的绘制
                canvas.drawText(peoNumber[i] + "", startX[i] - Util.dip2px(getContext(), 7), tvHeight[i], paint);
            }

            //设置底部的标题
            paint.setColor(getResources().getColor(R.color.c_2b3857));
            paint.setTextSize(Util.dip2px(getContext(), 12));
            canvas.drawText(titles[i] + "", startX[i] - Util.dip2px(getContext(), 12), mHeight - Util.dip2px(getContext(), 2), paint);
        }
        //画坐标线
        //默认颜色
        paint.setColor(getResources().getColor(R.color.c_e8e9eb));
        paint.setStrokeWidth(Util.dip2px(getContext(), 2));
        int lineHeight = Util.dip2px(getContext(), 21);
        int lineWidth = mWidth - Util.dip2px(getContext(), 17);


        //y轴
        canvas.drawLine(Util.dip2px(getContext(), 17), mHeight - Util.dip2px(getContext(), textPadding), Util.dip2px(getContext(), 17), lineHeight, paint);
        //x轴
        canvas.drawLine(Util.dip2px(getContext(), 17), mHeight - Util.dip2px(getContext(), textPadding), lineWidth, mHeight - Util.dip2px(getContext(), textPadding), paint);
    }


    /**
     * 应为是按照剩余的来减少的
     * 所以顶部就是多余父控件高度减去子控件的高度
     */
    public void setList(List<Integer> list) {
        //设置控件的各个状态
        setViewState(list.size());
        tvHeight = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            peoNumber[i] = list.get(i);
            startY[i] = mHeight - Util.dip2px(getContext(), +textPadding);
            endY[i] = mHeight - Util.dip2px(getContext(), 1 + textPadding);
        }

        if (list.get(0) == 0) {
            invalidate();
            return;
        }
        //数据作比较，不会超过全部的高度
        if (list.size() > 1) {
            for (int i = 1; i < list.size(); i++) {
                list.set(i, list.get(i) > list.get(0) ? list.get(0) : list.get(i));
            }
        }

        //控件总高度和内容高度
        mHeight = Util.dip2px(getContext(), 154);
        //内容的高度，实际上是从上面算起的，所以是减去
        int num = Util.dip2px(getContext(), 96);
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                viewHeight[i] = mHeight - num - Util.dip2px(getContext(), textPadding);
            } else {
                //因为下面加了17个边距用于显示标题，说以说要减去17
                double number = (list.get(i) * num / list.get(0));
                viewHeight[i] = (int) (mHeight - number) - Util.dip2px(getContext(), textPadding);
                if (list.get(i) == 0) {
                    viewHeight[i] = 0;
                }
            }
        }
        invalidate();
    }

    //这个是带标题的
    public void setList(List<Integer> list, List<String> titleList) {
        titles = new String[titleList.size()];
        for (int i = 0; i < titleList.size(); i++) {
            if (!TextUtils.isEmpty(titleList.get(i))) {
                titles[i] = titleList.get(i);
            } else {
                titles[i] = "";
            }
        }
        setList(list);
    }
}
