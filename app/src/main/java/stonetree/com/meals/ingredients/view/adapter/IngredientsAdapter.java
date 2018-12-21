package stonetree.com.meals.ingredients.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import stonetree.com.meals.R;
import stonetree.com.meals.constants.Constants;
import stonetree.com.meals.core.provider.ImageDownloaderCallback;
import stonetree.com.meals.ingredients.model.Ingredient;
import stonetree.com.meals.ingredients.model.IngredientsResponse;
import stonetree.com.meals.ingredients.presenter.IngredientsPresenter;
import stonetree.com.meals.ingredients.view.IIngredientsView;
import stonetree.com.meals.utils.Collections;

public class IngredientsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private IngredientsResponse response;

    private IIngredientsView view;

    private IngredientsPresenter presenter;

    public IngredientsAdapter(IIngredientsView view, IngredientsPresenter presenter, IngredientsResponse response) {
        this.view = view;
        this.context = view.getContext();

        this.presenter = presenter;
        this.response = response;
    }

    public void update(IngredientsResponse response) {
        this.response = response;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout card;

        private ImageView thumbnail;

        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            card = (LinearLayout) itemView.findViewById(R.id.card);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new IngredientsAdapter.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_row, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int rowPosition) {
        final ViewHolder holder = ((ViewHolder) viewHolder);

        Ingredient ingredient = response.getIngredients().get(rowPosition);

        setThumbnail(holder, ingredient);
        setName(holder, ingredient);
        setListener(holder, ingredient);
    }

    private void setListener(final ViewHolder holder, final Ingredient ingredient) {
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View selectedCard) {

            }
        });

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.card.performClick();
            }
        });

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.card.performClick();
            }
        });
    }

    private void setThumbnail(ViewHolder holder, Ingredient ingredient) {
        final String launcherPath = String.valueOf(R.mipmap.ic_launcher);
        final int launcherId = context.getResources().getIdentifier(launcherPath, Constants.MIPMAP, context.getPackageName());

        boolean isNotPlaceholder = launcherId == holder.thumbnail.getId();
        if (isNotPlaceholder)
            return;

        final ImageDownloaderCallback imageDownloaderCallback = getImageDownloaderCallback(holder.thumbnail);
        presenter.getIngredientImage(ingredient, imageDownloaderCallback);
    }

    private ImageDownloaderCallback getImageDownloaderCallback(final ImageView thumbnail) {
        return new ImageDownloaderCallback() {
            @Override
            public void onDownloadedImage(Object response) {
                final Ingredient ingredient = (Ingredient) response;
                thumbnail.setImageBitmap(ingredient.getBitmap());
            }
        };
    }

    private void setName(ViewHolder holder, Ingredient ingredient) {
        holder.name.setText(ingredient.getName());
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (response == null)
            return Constants.EMPTY_LIST;

        final List<Ingredient> ingredients = response.getIngredients();
        if (Collections.isNullOrEmpty(ingredients))
            return Constants.EMPTY_LIST;
        else
            return ingredients.size();
    }

}
