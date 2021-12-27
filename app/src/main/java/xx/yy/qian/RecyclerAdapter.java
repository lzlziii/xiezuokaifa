package xx.yy.qian;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    public List<Thing> thinglist;

    public RecyclerAdapter(List<Thing> thinglist) {
        this.thinglist = thinglist;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewHolder.delbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.detil.setText(thinglist.get(position).getDetil());
        holder.begintime.setText(thinglist.get(position).getCal());
        holder.endtime.setText(thinglist.get(position).getCal2());
    }

    @Override
    public int getItemCount() {
        return thinglist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView detil;
        TextView begintime;
        TextView endtime;
        Button editbutton;
        Button delbutton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.detil = itemView.findViewById(R.id.textView);
            this.begintime = itemView.findViewById(R.id.begintime);
            this.endtime = itemView.findViewById(R.id.endtime);
            this.editbutton=itemView.findViewById(R.id.editbutton);
            this.delbutton=itemView.findViewById(R.id.delbutton);
        }

    }


}


