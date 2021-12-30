package xx.yy.qian;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHolder> {
    public List<Thing> thinglist;

    public Adapter2(List<Thing> thinglist) {
        this.thinglist = thinglist;
    }

    @NonNull
    @Override
    public Adapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
        final ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.shanchu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewHolder.gengxin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter2.ViewHolder holder, int position) {
        holder.textv2.setText(thinglist.get(position).getDetil());
    }

    @Override
    public int getItemCount() {
        return thinglist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textv2;
        Button shanchu2;
        Button gengxin2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textv2 = itemView.findViewById(R.id.textv2);
            this.shanchu2 = itemView.findViewById(R.id.shanchu2);
            this.gengxin2=itemView.findViewById(R.id.gengxin2);
        }

    }


}
