package anurag.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.util.Log;

import java.util.Random;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnLongClickListener{

    private final static String TAG="Mymessage";
    MyButton buttons[][];
    int n=10;
    String username;
    LinearLayout mainLayout;
    LinearLayout rowsLayout[];
    boolean Gameover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout=(LinearLayout)findViewById(R.id.mainLayout);
        Intent i=getIntent();
        username=i.getStringExtra("username");
        setUpBoard();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       int id=item.getItemId();
        if(id==R.id.reset)
        {
        setUpBoard();
        }
        return  true;
    }

    public void setUpBoard()
    {
        Gameover=false;
        if(Gameover)
        {
            return;
        }
        buttons=new MyButton[n][n];
        mainLayout.removeAllViews();
        rowsLayout=new LinearLayout[n];
        for(int i=0;i<n;i++)
        {
            rowsLayout[i]=new LinearLayout(this);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1f);
            rowsLayout[i].setLayoutParams(params);
            rowsLayout[i].setOrientation(LinearLayout.HORIZONTAL);
            mainLayout.addView(rowsLayout[i]);
        }
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                buttons[i][j]=new MyButton(this);
                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1f);
                buttons[i][j].setLayoutParams(params);
                buttons[i][j].p=i;
                buttons[i][j].q=j;
                buttons[i][j].setOnClickListener(this);
                buttons[i][j].setOnLongClickListener( this);
                rowsLayout[i].addView(buttons[i][j]);
            }
        }
        Toast.makeText(this,"Welcome"+username,Toast.LENGTH_SHORT).show();
        puttingMines(buttons);
    }
    public void puttingMines(MyButton[][] buttons)
    {
        Random r = new Random();
        for(int k=0;k<14;k++) {
            int i1 = r.nextInt(10 - 0) + 0;
            int j1 = r.nextInt(10 - 0) + 0;
            if(buttons[i1][j1].number<10)
            {
                buttons[i1][j1].number = 10;
                putnumbers(buttons, i1, j1);
            }

        }
    }
    public void putnumbers(MyButton[][] buttons,int i,int j)
    {
        if(i==0&&j==0)
        {
            buttons[i+1][j].number=buttons[i+1][j].number+1;
            buttons[i][j+1].number=buttons[i][j+1].number+1;
            buttons[i+1][j+1].number=buttons[i+1][j+1].number+1;

        }
        else if(i==0&&j==n-1)
        {
            buttons[i+1][j].number=buttons[i+1][j].number+1;
            buttons[i][j-1].number=buttons[i][j-1].number+1;
            buttons[i+1][j-1].number=buttons[i+1][j-1].number+1;
        }
        else if(j==0&&i==n-1)
        {
            buttons[i-1][j].number=buttons[i-1][j].number+1;
            buttons[i][j+1].number=buttons[i][j+1].number+1;
            buttons[i-1][j+1].number=buttons[i-1][j+1].number+1;
        }
        else if(i==n-1&&j==n-1)
        {
            buttons[i-1][j].number=buttons[i-1][j].number+1;
            buttons[i][j-1].number=buttons[i][j-1].number+1;
            buttons[i-1][j-1].number=buttons[i-1][j-1].number+1;
        }
        else if(i==0)
        {
            buttons[i+1][j].number=buttons[i+1][j].number+1;
            buttons[i+1][j-1].number=buttons[i+1][j-1].number+1;
            buttons[i][j-1].number=buttons[i][j-1].number+1;
            buttons[i][j+1].number=buttons[i][j+1].number+1;
            buttons[i+1][j+1].number=buttons[i+1][j+1].number+1;
        }
        else if(j==0)
        {
            buttons[i-1][j].number=buttons[i-1][j].number+1;
            buttons[i+1][j].number=buttons[i+1][j].number+1;
            buttons[i][j+1].number=buttons[i][j+1].number+1;
            buttons[i-1][j+1].number=buttons[i-1][j+1].number+1;
            buttons[i+1][j+1].number=buttons[i+1][j+1].number+1;
        }
        else if(i==n-1)
        {
            buttons[i-1][j].number=buttons[i-1][j].number+1;
            buttons[i-1][j-1].number=buttons[i-1][j-1].number+1;
            buttons[i][j+1].number=buttons[i][j+1].number+1;
            buttons[i-1][j+1].number=buttons[i-1][j+1].number+1;
            buttons[i][j-1].number=buttons[i][j-1].number+1;
        }
        else if(j==n-1)
        {
            buttons[i-1][j].number=buttons[i-1][j].number+1;
            buttons[i+1][j].number=buttons[i+1][j].number+1;
            buttons[i][j-1].number=buttons[i][j-1].number+1;
            buttons[i-1][j-1].number=buttons[i-1][j-1].number+1;
            buttons[i+1][j-1].number=buttons[i+1][j-1].number+1;
        }
        else
        {
            buttons[i+1][j+1].number=buttons[i+1][j+1].number+1;
            buttons[i-1][j-1].number=buttons[i-1][j-1].number+1;
            buttons[i-1][j].number=buttons[i-1][j].number+1;
            buttons[i+1][j].number=buttons[i+1][j].number+1;
            buttons[i][j+1].number=buttons[i][j+1].number+1;
            buttons[i][j-1].number=buttons[i][j-1].number+1;
            buttons[i-1][j+1].number=buttons[i-1][j+1].number+1;
            buttons[i+1][j-1].number=buttons[i+1][j-1].number+1;
        }

    }


    @Override
    public void onClick(View view) {
        int a=0;

        MyButton button=(MyButton) view;
        if(button.number==0)
        {
           recursivecheck(buttons,button.p,button.q);
            a=1;
        }

        if(a==0) {
            if (button.number >= 10) {
                button.setText("*");
                Toast.makeText(this, "You Lost", Toast.LENGTH_SHORT).show();
                Gameover = true;
            } else {
                String str = Integer.toString(button.number);
                button.setText(str);
            }
        }
        checkstatus();

    }
    public void checkstatus()
    {
        int a=1;
        for(int i=0;i<buttons.length;i++)
        {
            int j;
            for( j=0;j<buttons[i].length;j++)
            {
                if(buttons[i][j].number>=10)
                {
                    String check=buttons[i][j].getText().toString();
                    if(check.equals("#"))
                    {
                        Gameover= false;
                        a=1;
                    }
                    else
                    {
                        a=0;
                        break;
                    }
                }
            }
            if(a==0)
            {
                break;
            }
        }

    }
    public void recursivecheck(MyButton[][] buttons,int indexofi,int indexofj)
    {
        Log.i(TAG,Integer.toString(indexofi));
        if(indexofi<0||indexofj<0)
        {
            return;
        }
        if(indexofi>9||indexofj>9)
        {
            return;
        }
        if(buttons[indexofi][indexofj].pass==0)
        {
            buttons[indexofi][indexofj].pass=1;
            buttons[indexofi][indexofj].setText(Integer.toString(buttons[indexofi][indexofj].number));
           if(indexofi<9&&indexofj<9) {
               if (buttons[indexofi + 1][indexofj + 1].number == 0&&buttons[indexofi + 1][indexofj + 1].pass==0) {
                   recursivecheck(buttons, indexofi + 1, indexofj + 1);
               }
               else
               {
                   buttons[indexofi+1][indexofj+1].setText(Integer.toString(buttons[indexofi+1][indexofj+1].number));
               }
           }
            if(indexofi>0&&indexofj>0) {
                if (buttons[indexofi - 1][indexofj - 1].number == 0&&buttons[indexofi - 1][indexofj - 1].pass==0) {
                    recursivecheck(buttons, indexofi - 1, indexofj - 1);
                }
                else
                {
                    buttons[indexofi-1][indexofj-1].setText(Integer.toString(buttons[indexofi-1][indexofj-1].number));
                }
            }
            if(indexofi>0) {
                if (buttons[indexofi - 1][indexofj ].number == 0&&buttons[indexofi - 1][indexofj ].pass==0) {
                    recursivecheck(buttons, indexofi - 1, indexofj);
                }
                else
                {
                    buttons[indexofi-1][indexofj].setText(Integer.toString(buttons[indexofi-1][indexofj].number));
                }
            }
            if(indexofi<9) {
                if (buttons[indexofi + 1][indexofj].number == 0&&buttons[indexofi + 1][indexofj].pass==0) {
                    recursivecheck(buttons, indexofi + 1, indexofj);
                }
                else
                {
                    buttons[indexofi+1][indexofj].setText(Integer.toString(buttons[indexofi+1][indexofj].number));
                }
            }
            if(indexofj<9) {
                if (buttons[indexofi][indexofj + 1].number == 0&&buttons[indexofi][indexofj + 1].pass==0) {
                    recursivecheck(buttons, indexofi, indexofj + 1);
                }
                else
                {
                    buttons[indexofi][indexofj+1].setText(Integer.toString(buttons[indexofi][indexofj+1].number));
                }
            }
            if(indexofj>0) {
                if (buttons[indexofi][indexofj - 1].number == 0&&buttons[indexofi][indexofj - 1].pass==0) {
                    recursivecheck(buttons, indexofi, indexofj - 1);
                }
                else
                {
                    buttons[indexofi][indexofj-1].setText(Integer.toString(buttons[indexofi][indexofj-1].number));
                }
            }
            if(indexofi>0&&indexofj<9) {
                if (buttons[indexofi - 1][indexofj + 1].number == 0&&buttons[indexofi - 1][indexofj + 1].pass==0) {
                    recursivecheck(buttons, indexofi - 1, indexofj + 1);
                }
                else
                {
                    buttons[indexofi-1][indexofj+1].setText(Integer.toString(buttons[indexofi-1][indexofj+1].number));
                }
            }
            if(indexofi<9&&indexofj>0) {
                if (buttons[indexofi + 1][indexofj - 1].number == 0&&buttons[indexofi + 1][indexofj - 1].pass==0) {
                    recursivecheck(buttons, indexofi + 1, indexofj - 1);
                }
                else
                {
                    buttons[indexofi+1][indexofj-1].setText(Integer.toString(buttons[indexofi+1][indexofj-1].number));
                }
            }
        }
    }

    @Override
    public boolean onLongClick(View view) {
        MyButton button=(MyButton) view;
        button.setText("#");
        Toast.makeText(MainActivity.this,"Flagged",Toast.LENGTH_SHORT).show();
        checkstatus();
        return true;
    }
}
