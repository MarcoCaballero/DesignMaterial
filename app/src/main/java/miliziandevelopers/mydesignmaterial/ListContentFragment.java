package miliziandevelopers.mydesignmaterial;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import miliziandevelopers.mydesignmaterial.base_de_datos.Bbdd;
import miliziandevelopers.mydesignmaterial.base_de_datos.Operaciones;
import miliziandevelopers.mydesignmaterial.modelo.Productos;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class ListContentFragment extends Fragment {
    private static int getLongClickedSize = 0;
    //Operaciones data;


    public ListContentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);

        //data = Operaciones.obtenerInstancia(getApplicationContext());

        //final List<Productos> items;
        //data.obtenerProductos();

        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avator;
        public TextView name;
        public TextView description;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_list_content, parent, false));
            avator = (ImageView) itemView.findViewById(R.id.list_avatar);
            name = (TextView) itemView.findViewById(R.id.list_title);
            description = (TextView) itemView.findViewById(R.id.list_desc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getLongClickedSize > 0) {
                        if (itemView.getDrawingCacheBackgroundColor() == Color.WHITE) {

                            getLongClickedSize++;
                            itemView.setBackgroundColor(Color.RED);
                        } else {
                            getLongClickedSize--;
                            itemView.setBackgroundColor(Color.WHITE);
                        }


                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
                        context.startActivity(intent);
                    }

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {


                @Override
                public boolean onLongClick(View v) {
                    getLongClickedSize++;
                    itemView.setBackgroundColor(Color.RED);
                    return true;
                }
            });
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {

        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 18;
        private final String[] mPlaces;
        private final String[] mPlaceDesc;
        private final Drawable[] mPlaceAvators;
        List<Productos> items;

        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            mPlaces = resources.getStringArray(R.array.places);
            mPlaceDesc = resources.getStringArray(R.array.place_desc);
            TypedArray a = resources.obtainTypedArray(R.array.place_avator);
            mPlaceAvators = new Drawable[a.length()];
            for (int i = 0; i < mPlaceAvators.length; i++) {
                mPlaceAvators[i] = a.getDrawable(i);
            }
            a.recycle();
            //this.items = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            /*Log.d("Control", "Paso por aqui?");
            Productos p = items.get(position);
            holder.name.setText(p.getNombre());
            holder.description.setText(p.getDescripcion());*/
            holder.avator.setImageDrawable(mPlaceAvators[position % mPlaceAvators.length]);
            holder.name.setText(mPlaces[position % mPlaces.length]);
            holder.description.setText(mPlaceDesc[position % mPlaceDesc.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        // Make sure that we are currently visible
        if (this.isVisible()) {
            ((MainActivity) getActivity()).reappearFloatingButton(this);

            // If we are becoming invisible, then...
            if (!isVisibleToUser) {

            }
        }
    }
}