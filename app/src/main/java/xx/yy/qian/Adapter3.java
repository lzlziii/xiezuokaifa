package xx.yy.qian;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter3 extends RecyclerView.Adapter<Adapter3.ViewHolder> {
    public List<Thing> thinglist;

    public Adapter3(List<Thing> thinglist) {
        this.thinglist = thinglist;
    }

    @NonNull
    @Override
    public Adapter3.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item3, parent, false);
        final ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter3.ViewHolder holder, int position) {
        holder.textv3.setText(thinglist.get(position).getDetil());
    }

    @Override
    public int getItemCount() {
        return thinglist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textv3;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textv3 = itemView.findViewById(R.id.textv3);

        }

    }


}
