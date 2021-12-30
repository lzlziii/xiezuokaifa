package xx.yy.qian;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter4 extends RecyclerView.Adapter<Adapter4.ViewHolder> {
    public List<Thing> thinglist;

    public Adapter4(List<Thing> thinglist) {
        this.thinglist = thinglist;
    }

    @NonNull
    @Override
    public Adapter4.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
        final ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.shanchu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewHolder.gengxin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter4.ViewHolder holder, int position) {
        holder.textv4.setText(thinglist.get(position).getDetil());
    }

    @Override
    public int getItemCount() {
        return thinglist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textv4;
        Button shanchu4;
        Button gengxin4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textv4 = itemView.findViewById(R.id.textv2);
            this.shanchu4 = itemView.findViewById(R.id.shanchu2);
            this.gengxin4=itemView.findViewById(R.id.gengxin2);
        }

    }


}
