package xx.yy.qian;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter5 extends RecyclerView.Adapter<Adapter5.ViewHolder> {
    public List<Thing> thinglist;

    public Adapter5(List<Thing> thinglist) {
        this.thinglist = thinglist;
    }

    @NonNull
    @Override
    public Adapter5.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
        final ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.shanchu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewHolder.gengxin5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter5.ViewHolder holder, int position) {
        holder.textv5.setText(thinglist.get(position).getDetil());
    }

    @Override
    public int getItemCount() {
        return thinglist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textv5;
        Button shanchu5;
        Button gengxin5;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textv5 = itemView.findViewById(R.id.textv2);
            this.shanchu5 = itemView.findViewById(R.id.shanchu2);
            this.gengxin5=itemView.findViewById(R.id.gengxin2);
        }

    }


}
