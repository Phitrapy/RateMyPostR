package fr.eseo.dis.begnaucle.ratemypostr;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.begnaucle.ratemypostr.model.Projet;


public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjetViewHolder> {

    private AbstractActivity activity;
    private List<Projet> projets;

    public ProjectAdapter(AbstractActivity activity, List<Projet> projets) {
        this.activity = activity;
        this.projets = projets;
    }

    public void setProjects(List<Projet> projects){
        this.projets = projects;
    }

    @Override
    public int getItemCount() {
        return projets.size();
    }

    @Override
    public ProjetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View filmView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_project_card, parent, false);
        Log.d("ProjectAdapter","onCreateViewHolder()");
        return new ProjetViewHolder(filmView);
    }

    @Override
    public void onBindViewHolder(ProjetViewHolder holder, final int position) {
        Log.d("ProjectAdapter","onBindViewHolder()");
        final Projet projet = projets.get(position);
        holder.projetNom.setText(""+projet.getTitle());
        //TODO LOAD THUMBNAIL :
        //holder.projetImage.setImageDrawable(new ColorDrawable(projet.getImageProjet()));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ProjetAdapter","Item 'clicked'");
                activity.clickProject(projet);
            }
        });
    }

    class ProjetViewHolder extends RecyclerView.ViewHolder {

        private final View view;

        public TextView projetNom;
        public TextView projetDescription;
        public ImageView projetImage;

        public ProjetViewHolder(View itemView) {
            super(itemView);
            Log.d("ProjetViewHolder","ProjetViewHolder()");
            this.view = (RelativeLayout) itemView.findViewById(R.id.ProjectCard);;
            projetNom = (TextView) view.findViewById(R.id.ProjectCard_Name);

            //projetImage = (ImageView) view.findViewById(R.id.ProjectCard_Thumbnail);
        }
    }
}
