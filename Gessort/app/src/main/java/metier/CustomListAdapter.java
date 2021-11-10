package metier;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.afpa.gessort.R;

public  class CustomListAdapter extends BaseAdapter {
    private ArrayList<Spell>listSpell;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter(Context aContext,
                             ArrayList<Spell>listData){
       this.context = aContext;
       this.listSpell = listData;
       layoutInflater= LayoutInflater.from(aContext);
    }
    /////////////////Getter////////////////

    /**
     * Get the number of element into the list
     * @return
     */
    @Override
    public int getCount() {
        return listSpell.size();
    }

    /**
     * Get the position of the item
     * @return
     */
    @Override
    public Object getItem(int position) {
        return listSpell.get(position);
    }

    /**
     * Get the element thanks of the id
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get the view
     * @return
     */
    @Override
    public View getView(int position,
                        View convertView,
                        ViewGroup parent) {
        ViewHolder holder ;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder() {
            };
            holder.spellNameView = (TextView) convertView.findViewById(R.id.spellName);
            holder.shortDescriptionView= (TextView) convertView.findViewById(R.id.textofshortdescr);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Spell spell = this.listSpell.get(position);
        holder.spellNameView.setText(spell.getName()+" : ");
        holder.shortDescriptionView.setText(spell.getShortDescription());




        return convertView;
    }

    static class ViewHolder{
        TextView spellNameView;
        TextView shortDescriptionView;
    }
}
