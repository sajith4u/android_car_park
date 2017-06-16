package park.innova.dev.carpark.view;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

import java.util.List;

import park.innova.dev.carpark.R;
import park.innova.dev.carpark.domain.Categories;
import park.innova.dev.carpark.fragments.HomeFragment;
import park.innova.dev.carpark.service.CarParkLocationHandler;
import park.innova.dev.carpark.service.CarWashLocationHandler;

/**
 * Created by sajith on 11/27/16.
 */

public class CategoryViewAdapter extends RecyclerView.Adapter<CategoryViewAdapter.CategoryViewHolder> {

    List<Categories> categories;

    private Activity activity;

    public int mSelectedItem = -1;

    private HomeFragment homeFragment;

    private CarWashLocationHandler carWashLocationHandler;

    private CarParkLocationHandler carParkLocationHandler;

    public CategoryViewAdapter(List<Categories> categories, Activity activity, HomeFragment homeFragment) {
        this.categories = categories;
        this.activity = activity;
        this.homeFragment = homeFragment;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_categories, null);
        CategoryViewHolder viewHolder = new CategoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        Categories category = categories.get(position);
        holder.categoryName.setText(category.getName());
        holder.image.setImageDrawable(activity.getResources().getDrawable(category.getCategoryImage()));
        holder.linearLayout.setBackgroundColor(activity.getResources().getColor(category.getColor()));
    }

    @Override
    public int getItemCount() {
        return (null != categories ? categories.size() : 0);
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout linearLayout;

        private TextView categoryName;

        private ImageView image;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            this.linearLayout = (LinearLayout) itemView.findViewById(R.id.categoryView);
            this.categoryName = (TextView) itemView.findViewById(R.id.categoryName);
            this.image = (ImageView) itemView.findViewById(R.id.categoryImage);
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = getAdapterPosition();
                    carWashLocationHandler = new CarWashLocationHandler(homeFragment.getGoogleMap());
                    carParkLocationHandler = new CarParkLocationHandler(homeFragment.getGoogleMap());
                    String categoryName = categories.get(mSelectedItem).getName();
                    if(categoryName.equals("CAR WASH")){
                        carWashLocationHandler.showLocations();
                    }else if(categoryName.equals("CAR PARK")){
                        carParkLocationHandler.showLocations();
                    }

                    notifyItemRangeChanged(0, categories.size());
                }
            };
            itemView.setOnClickListener(listener);
            linearLayout.setOnClickListener(listener);
        }
    }

}
