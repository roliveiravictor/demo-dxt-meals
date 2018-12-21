package stonetree.com.meals.mealselection.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
import stonetree.com.meals.mealselection.model.Meal;
import stonetree.com.meals.mealselection.model.MealSelectionResponse;
import stonetree.com.meals.mealselection.presenter.MealSelectionPresenter;
import stonetree.com.meals.mealselection.view.IMealSelection;
import stonetree.com.meals.core.provider.ImageDownloaderCallback;
import stonetree.com.meals.utils.Collections;

public class MealSelectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private MealSelectionResponse response;

    private IMealSelection view;

    private MealSelectionPresenter presenter;

    public MealSelectionAdapter(IMealSelection view, MealSelectionPresenter presenter, MealSelectionResponse response) {
        this.view = view;
        this.context = view.getContext();

        this.presenter = presenter;
        this.response = response;
    }

    public void update(MealSelectionResponse response) {
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
        return new MealSelectionAdapter.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_selection_row, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int rowPosition) {
        final ViewHolder holder = ((ViewHolder) viewHolder);

        Meal meal = response.getMeals().get(rowPosition);

        setThumbnail(holder, meal);
        setName(holder, meal);
        setListener(holder, meal);
    }

    private void setListener(final ViewHolder holder, final Meal meal) {
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View selectedCard) {
                final BitmapDrawable drawable = (BitmapDrawable) holder.thumbnail.getDrawable();
                final Bitmap bitmap = drawable.getBitmap();
                final String mealId = meal.getId();
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

    private void setThumbnail(ViewHolder holder, Meal meal) {
        final String launcherPath = String.valueOf(R.mipmap.ic_launcher);
        final int launcherId = context.getResources().getIdentifier(launcherPath, Constants.MIPMAP, context.getPackageName());

        boolean isNotPlaceholder = launcherId == holder.thumbnail.getId();
        if (isNotPlaceholder)
            return;

        final ImageDownloaderCallback imageDownloaderCallback = getImageDownloaderCallback(holder.thumbnail);
        presenter.getMealImage(meal, imageDownloaderCallback);
    }

    private ImageDownloaderCallback getImageDownloaderCallback(final ImageView thumbnail) {
        return new ImageDownloaderCallback() {
            @Override
            public void onDownloadedImage(Meal meal) {
                thumbnail.setImageBitmap(meal.getBitmap());
            }
        };
    }

    private void setName(ViewHolder holder, Meal meal) {
        holder.name.setText(meal.getName());
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

        final List<Meal> meals = response.getMeals();
        if (Collections.isNullOrEmpty(meals))
            return Constants.EMPTY_LIST;
        else
            return meals.size();
    }

}
