package project.bestscore.ui.home.game;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import project.bestscore.R;
import project.bestscore.ui.teammates.TeammateAdd;

public class GamePlayerHolder extends RecyclerView.ViewHolder{

    private TextView tvPlayerName;
    private TextView arrow31;
    private TextView arrow32;
    private TextView arrow33;
    //private TextView arrow44;
    private Context context;
    private  GamePlayerAdapter adapter;
    private View itemView;

    private int arrowIndex;

    private ArrayList<int[]> arrows;

    public GamePlayerHolder(@NonNull View itemView, Context context, ArrayList<int[]> arrows, GamePlayerAdapter adapter, TextView tvPlayerName, TextView arrow31, TextView arrow32, TextView arrow33) {
        super(itemView);
        this.itemView = itemView;
        this.tvPlayerName = tvPlayerName;
        this.arrow31 = arrow31;
        this.arrow32 = arrow32;
        this.arrow33 = arrow33;
        this.context = context;
        this.adapter = adapter;
        this.arrows = arrows;

        this.arrow31.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int arrow = 0;
                // inflate the layout of the popup window
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                Button button1 = popupView.findViewById(R.id.button1);
                Button button2 = popupView.findViewById(R.id.button2);
                Button button3 = popupView.findViewById(R.id.button3);
                Button button4 = popupView.findViewById(R.id.button4);

                TextView tvPlayerName = popupView.findViewById(R.id.tvPlayerName);

                button1.setText(arrows.get(arrow)[0] + "");
                button2.setText(arrows.get(arrow)[1] + "");
                button3.setText(arrows.get(arrow)[2] + "");
                button4.setText(arrows.get(arrow)[3] + "");

                popupWindow.showAtLocation(itemView, Gravity.CENTER, 0, 0);

                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrow31.setText(arrows.get(arrow)[0] + "");
                        popupWindow.dismiss();
                    }
                });

                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrow31.setText(arrows.get(arrow)[1] + "");
                        popupWindow.dismiss();
                    }
                });

                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrow31.setText(arrows.get(arrow)[2] + "");
                        popupWindow.dismiss();
                    }
                });

                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrow31.setText(arrows.get(arrow)[3] + "");
                        popupWindow.dismiss();
                    }
                });

                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });
        this.arrow32.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int arrow = 1;
                // inflate the layout of the popup window
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                Button button1 = popupView.findViewById(R.id.button1);
                Button button2 = popupView.findViewById(R.id.button2);
                Button button3 = popupView.findViewById(R.id.button3);
                Button button4 = popupView.findViewById(R.id.button4);

                TextView tvPlayerName = popupView.findViewById(R.id.tvPlayerName);

                button1.setText(arrows.get(arrow)[0] + "");
                button2.setText(arrows.get(arrow)[1] + "");
                button3.setText(arrows.get(arrow)[2] + "");
                button4.setText(arrows.get(arrow)[3] + "");

                popupWindow.showAtLocation(itemView, Gravity.CENTER, 0, 0);

                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrow32.setText(arrows.get(arrow)[0] + "");
                        popupWindow.dismiss();
                    }
                });

                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrow32.setText(arrows.get(arrow)[1] + "");
                        popupWindow.dismiss();
                    }
                });

                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrow32.setText(arrows.get(arrow)[2] + "");
                        popupWindow.dismiss();
                    }
                });

                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrow32.setText(arrows.get(arrow)[3] + "");
                        popupWindow.dismiss();
                    }
                });

                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });
        this.arrow33.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int arrow = 2;
                // inflate the layout of the popup window
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                Button button1 = popupView.findViewById(R.id.button1);
                Button button2 = popupView.findViewById(R.id.button2);
                Button button3 = popupView.findViewById(R.id.button3);
                Button button4 = popupView.findViewById(R.id.button4);

                TextView tvPlayerName = popupView.findViewById(R.id.tvPlayerName);

                button1.setText(arrows.get(arrow)[0] + "");
                button2.setText(arrows.get(arrow)[1] + "");
                button3.setText(arrows.get(arrow)[2] + "");
                button4.setText(arrows.get(arrow)[3] + "");

                popupWindow.showAtLocation(itemView, Gravity.CENTER, 0, 0);

                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrow33.setText(arrows.get(arrow)[0] + "");
                        popupWindow.dismiss();
                    }
                });

                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrow33.setText(arrows.get(arrow)[1] + "");
                        popupWindow.dismiss();
                    }
                });

                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrow33.setText(arrows.get(arrow)[2] + "");
                        popupWindow.dismiss();
                    }
                });

                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrow33.setText(arrows.get(arrow)[3] + "");
                        popupWindow.dismiss();
                    }
                });

                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

    }


    public TextView getTvPlayerName() {
        return tvPlayerName;
    }

    public TextView getArrow31() {
        return arrow31;
    }

    public TextView getArrow32() {
        return arrow32;
    }

    public TextView getArrow33() {
        return arrow33;
    }
}
