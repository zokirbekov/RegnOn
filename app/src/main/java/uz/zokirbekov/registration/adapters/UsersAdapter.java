package uz.zokirbekov.registration.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import uz.zokirbekov.registration.R;
import uz.zokirbekov.registration.models.Person;
import uz.zokirbekov.registration.ui.PersonImageText;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.VH> {

    private List<Person> people;
    private Context context;
    private LayoutInflater inflater;

    public UsersAdapter(Context context, List<Person> people)
    {
        this.context = context;
        this.people = people;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.layout_item_person,parent,false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Person person = people.get(position);

        holder.textPassport.setText(person.getPassport());
        holder.textName.setText(person.getName());
        holder.textSurname.setText(person.getSurname());

        holder.personImageText.setText(String.valueOf(person.getName().charAt(0)));
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    class VH extends RecyclerView.ViewHolder
    {

        public TextView textName, textSurname, textPassport;
        public PersonImageText personImageText;

        public VH(View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.text_name);
            textSurname = itemView.findViewById(R.id.text_surname);
            textPassport = itemView.findViewById(R.id.text_passport);
            personImageText = itemView.findViewById(R.id.person_image);

        }
    }
}
