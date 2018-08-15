package zhonghuass.ssml.utils.decoration;


import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by yuvaraj on 3/4/16.
 */
public class DynamicHeightImageView extends AppCompatImageView {

    private float whRatio = 0;

    public DynamicHeightImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DynamicHeightImageView(Context context) {
        super(context);
    }

    public void setRatio(float ratio) {
        whRatio = ratio;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (whRatio != 0) {
            int width = getMeasuredWidth();
            int height = (int)(whRatio * width);
            setMeasuredDimension(width, height);
        }
    }
}