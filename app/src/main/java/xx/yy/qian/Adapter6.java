package xx.yy.qian;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter6 extends RecyclerView.Adapter<Adapter6.ViewHolder> {
    public List<Thing> thinglist;

    public Adapter6(List<Thing> thinglist) {
        this.thinglist = thinglist;
    }

    @NonNull
    @Override
    public Adapter6.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item4, parent, false);
        final ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.shanchu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewHolder.huifu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter6.ViewHolder holder, int position) {
        holder.textv4.setText(thinglist.get(position).getDetil());
    }

    @Override
    public int getItemCount() {
        return thinglist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textv4;
        Button shanchu4;
        Button huifu4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textv4 = itemView.findViewById(R.id.textv4);
            this.shanchu4 = itemView.findViewById(R.id.shanchu4);
            this.huifu4=itemView.findViewById(R.id.huifu4);
        }

    }


}
