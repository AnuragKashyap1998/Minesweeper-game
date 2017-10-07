package anurag.minesweeper;

import android.content.Context;
import android.widget.Button;

/**
 * Created by anurag on 16-06-2017.
 */
public class MyButton extends Button {
    int number;
    int p;
    int q;
    int pass;
    public MyButton(Context context) {
        super(context);
        number=0;
        p=0;
        q=0;
        pass=0;
    }
}
