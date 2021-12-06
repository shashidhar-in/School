package com.example.school.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.school.databinding.FragmentHappeningsBinding;
import com.example.school.ui.placeholder.PlaceholderContent.PlaceholderItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class HappeningsRecyclerViewAdapter extends RecyclerView.Adapter<HappeningsRecyclerViewAdapter.ViewHolder> {

    private final List<PlaceholderItem> mValues;

    public HappeningsRecyclerViewAdapter(List<PlaceholderItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    return new ViewHolder(FragmentHappeningsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public PlaceholderItem mItem;

    public ViewHolder(FragmentHappeningsBinding binding) {
      super(binding.getRoot());
      mIdView = binding.itemNumber;
      mContentView = binding.content;
    }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}