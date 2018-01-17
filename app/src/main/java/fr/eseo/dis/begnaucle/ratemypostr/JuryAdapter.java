package fr.eseo.dis.begnaucle.ratemypostr;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import fr.eseo.dis.begnaucle.ratemypostr.model.Jury;


public class JuryAdapter extends RecyclerView.Adapter<JuryAdapter.JuryCardViewHolder> {

    private AbstractActivity context;
    private List<Jury> values;

    public JuryAdapter(AbstractActivity activity, List<Jury> juries) {
        this.context = activity;
        this.values = juries;
    }


    @Override
    public JuryCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View juryCardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_jury_card, parent, false);
        return new JuryCardViewHolder(juryCardView);
    }

    @Override
    public void onBindViewHolder(JuryCardViewHolder holder, int position) {
        if(this.values.size()>0) {
            final Jury jury = values.get(position);
            holder.juryId.setText("Jury #"+jury.getIdJury());
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.clickJury(jury);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if(this.values != null){
            return this.values.size();
        }
        return 0;
    }

    static class JuryCardViewHolder extends RecyclerView.ViewHolder {
        private View view = null;

        public TextView juryId = null;

        public JuryCardViewHolder(View itemView) {
            super(itemView);
            juryId = (TextView)itemView.findViewById(R.id.JuryCard_Id);
            view = (LinearLayout) itemView.findViewById(R.id.JuryCard);
        }
    }

}
