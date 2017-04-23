package example.marcbenedi.jedi.workshopjedi;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private String[] myInfo;

    public RecyclerViewAdapter(String[] list){
        myInfo = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_element, parent, false);

        ViewHolder m = new ViewHolder(v);

        return m;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.number.setText(String.valueOf(position));
        holder.text.setText(myInfo[position]);
        int colorID = (position%2==0) ? android.R.color.holo_orange_light : android.R.color.holo_blue_light;
        holder.v.setBackgroundColor(ContextCompat.getColor(holder.v.getContext(),colorID));
    }
    @Override
    public int getItemCount() {
        return myInfo.length;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView number;
        public TextView text;
        public View v;

        public ViewHolder(View itemView) {
            super(itemView);
            number = (TextView) itemView.findViewById(R.id.number);
            text = (TextView) itemView.findViewById(R.id.text);
            v = itemView;
        }
    }
}
