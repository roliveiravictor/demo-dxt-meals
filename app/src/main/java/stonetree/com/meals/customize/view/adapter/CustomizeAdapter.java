package stonetree.com.meals.customize.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import stonetree.com.meals.R;
import stonetree.com.meals.constants.Constants;
import stonetree.com.meals.core.provider.ImageDownloaderCallback;
import stonetree.com.meals.customize.model.CustomizeResponse;
import stonetree.com.meals.customize.presenter.CustomizePresenter;
import stonetree.com.meals.customize.view.ICustomizeView;
import stonetree.com.meals.ingredients.model.Ingredient;
import stonetree.com.meals.core.model.Session;
import stonetree.com.meals.utils.Collections;

public class CustomizeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private CustomizeResponse response;

    private ICustomizeView view;

    private CustomizePresenter presenter;

    public CustomizeAdapter(ICustomizeView view, CustomizePresenter presenter, CustomizeResponse response) {
        this.view = view;
        this.context = view.getContext();

        this.presenter = presenter;
        this.response = response;
    }

    public void update(CustomizeResponse response) {
        this.response = response;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout card;

        private ImageView thumbnail;

        private TextView name;
        private TextView amount;

        public ViewHolder(View itemView) {
            super(itemView);
            card = (RelativeLayout) itemView.findViewById(R.id.card);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            name = (TextView) itemView.findViewById(R.id.name);
            amount = (TextView) itemView.findViewById(R.id.amount);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomizeAdapter.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.customize_row, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int rowPosition) {
        final ViewHolder holder = ((ViewHolder) viewHolder);

        Ingredient ingredient = response.getAllIngredients().get(rowPosition);

        setThumbnail(holder, ingredient);
        setName(holder, ingredient);
        setListener(holder, ingredient);
    }

    private void setListener(final ViewHolder holder, final Ingredient ingredient) {
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View selectedCard) {
                final int ingredientId = Integer.valueOf(ingredient.getId());

                final List<Integer> customIngredients = Session.getInstance().getCart().getMeal().getCustomIngredients();
                customIngredients.add(ingredientId);

                final int ingredientAmount = presenter.countSingleIngredient(ingredientId);

                setAmount(holder, ingredientAmount);
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

    public void setAmount(ViewHolder holder, int amount) {
        holder.amount.setText("x" + amount);
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

        final List<Ingredient> ingredients = response.getAllIngredients();
        if (Collections.isNullOrEmpty(ingredients))
            return Constants.EMPTY_LIST;
        else
            return ingredients.size();
    }

}
