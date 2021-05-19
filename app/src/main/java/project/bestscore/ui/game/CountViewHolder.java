package project.bestscore.ui.game;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountViewHolder extends RecyclerView.ViewHolder {
    private TextView tvArrow;
    private EditText txtZone1;
    private EditText txtZone2;
    private EditText txtZone3;
    private EditText txtZone4;

    public CountViewHolder(@NonNull View itemView,TextView tvArrow,
                           EditText txtZone1, EditText txtZone2,
                           EditText txtZone3, EditText txtZone4) {
        super(itemView);
        this.tvArrow = tvArrow;
        this.txtZone1 = txtZone1;
        this.txtZone2 = txtZone2;
        this.txtZone3 = txtZone3;
        this.txtZone4 = txtZone4;
    }

    public TextView getTvArrow() {
        return (TextView) tvArrow;
    }

    public EditText getTxtZone1() {
        return txtZone1;
    }

    public EditText getTxtZone2() {
        return txtZone2;
    }

    public EditText getTxtZone3() {
        return txtZone3;
    }

    public EditText getTxtZone4() {
        return txtZone4;
    }
}
