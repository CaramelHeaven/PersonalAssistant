package com.volgagas.personalassistant.presentation.about_user;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.info.PersonSkills;

import java.util.List;

/**
 * Created by CaramelHeaven on 12:27, 05/03/2019.
 */
public class SkillsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PersonSkills> personSkills;

    public SkillsAdapter(List<PersonSkills> personSkills) {
        this.personSkills = personSkills;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View skills = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_info_skills, viewGroup, false);
        return new SkillsVH(skills);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        SkillsVH skillsVH = (SkillsVH) viewHolder;

        skillsVH.tvTitle.setText("Уровень владения " + personSkills.get(i).getSkillName());
        skillsVH.tvLevel.setText("Уровень: " + personSkills.get(i).getLevel());

        if (personSkills.get(i).getImageReference() != 0) {
            skillsVH.ivImage.setImageDrawable(skillsVH.ivImage
                    .getContext()
                    .getResources()
                    .getDrawable(personSkills.get(i).getIntId(skillsVH.ivImage.getContext(),
                            personSkills.get(i).getImageReference())));
        }
    }

    public void updateAdapter(List<PersonSkills> personSkills) {
        this.personSkills = personSkills;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return personSkills.size();
    }

    class SkillsVH extends RecyclerView.ViewHolder {
        TextView tvTitle, tvLevel;
        ImageView ivImage;

        public SkillsVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvLevel = itemView.findViewById(R.id.tv_level);
            ivImage = itemView.findViewById(R.id.iv_image);
        }
    }
}
