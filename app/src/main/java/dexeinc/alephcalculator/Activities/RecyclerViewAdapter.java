package dexeinc.alephcalculator.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

import dexeinc.alephcalculator.Evaluation.Operation;
import dexeinc.alephcalculator.R;
import dexeinc.alephcalculator.Activities.History;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private LinkedList<Operation> history;
    private Context mContext;

    RecyclerViewAdapter(LinkedList<Operation> history, Context mContext) {
        this.history = history;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.operation.setText(history.get(position).operation);
        holder1.result.setText(history.get(position).result);
        holder1.parentLayout.setOnClickListener(e -> {
            Intent cal = new Intent(mContext, Calculator.class);
            cal.putExtra("hOperation", history.get(position).operation);
            cal.putExtra("hResult", history.get(position).result);
            mContext.startActivity(cal);
            History.killActivity();
        });
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView operation, result;
        ConstraintLayout parentLayout;

        ViewHolder(View itemView) {
            super(itemView);
            operation = (TextView) itemView.findViewById(R.id.operation_history);
            result = (TextView) itemView.findViewById(R.id.result_history);
            parentLayout = (ConstraintLayout) itemView.findViewById(R.id.list_layout);
        }
    }
}
