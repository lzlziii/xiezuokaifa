package xx.yy.qian;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.ViewHolder> {
    public List<Thing> thinglist;

    public Adapter1(List<Thing> thinglist) {
        this.thinglist = thinglist;
    }

    @NonNull
    @Override
    public Adapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item1, parent, false);
        final ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.shanchu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewHolder.gengxin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewHolder.zanting1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter1.ViewHolder holder, int position) {
        holder.textv1.setText(thinglist.get(position).getDetil());
    }

    @Override
    public int getItemCount() {
        return thinglist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textv1;
        Button shanchu1;
        Button gengxin1;
        Button zanting1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textv1 = itemView.findViewById(R.id.textv1);
            this.shanchu1 = itemView.findViewById(R.id.shanchu1);
            this.gengxin1=itemView.findViewById(R.id.gengxin1);
            this.zanting1=itemView.findViewById(R.id.zanting1);
        }

    }


}
