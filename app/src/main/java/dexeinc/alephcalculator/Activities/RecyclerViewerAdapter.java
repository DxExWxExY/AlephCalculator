package dexeinc.alephcalculator.Activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

import dexeinc.alephcalculator.Evaluation.Operation;
import dexeinc.alephcalculator.R;

public class RecyclerViewerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "RecyclerViewerAdapter";

    private LinkedList<Operation> history;
    private Context mContext;

    public RecyclerViewerAdapter(LinkedList<Operation> history, Context mContext) {
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
            Toast.makeText(mContext, "WORKS", Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView operation, result;
        public RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            operation = (TextView) itemView.findViewById(R.id.operation_history);
            result = (TextView) itemView.findViewById(R.id.result_history);
            parentLayout = (RelativeLayout) itemView.findViewById(R.id.list_layout);
        }
    }
}
