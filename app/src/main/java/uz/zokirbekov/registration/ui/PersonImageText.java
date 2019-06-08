package uz.zokirbekov.registration.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import uz.zokirbekov.registration.R;

public class PersonImageText extends ConstraintLayout {

    private Context context;

    private String text;
    private int textColor;
    private Drawable backGround;

    private TextView textView;
    private ImageView imageView;

    public void setText(String text)
    {
        if (textView != null)
            textView.setText(text);
        this.text = text;
    }
    public String getText()
    {
        return text;
    }

    public PersonImageText(Context context) {
        super(context);
        init();
    }

    public PersonImageText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PersonImageText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PersonImageText(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context,attrs,defStyleRes);
        init();
        TypedArray g = context.obtainStyledAttributes(attrs,R.styleable.PersonImageText,defStyleAttr,R.style.AppTheme);

        text = g.getString(R.styleable.PersonImageText_text);
        textColor = g.getColor(R.styleable.PersonImageText_textColor,0);
        backGround = g.getDrawable(R.styleable.PersonImageText_backGround);

        if (backGround != null)
            imageView.setBackground(backGround);

        textView.setText(text);
        textView.setTextColor(textColor);
    }


    private void init()
    {
        View v = inflate(getContext(), R.layout.layout_person_image_text,this);
        textView = v.findViewById(R.id.image_text);
        imageView = v.findViewById(R.id.imageView);
    }
}
