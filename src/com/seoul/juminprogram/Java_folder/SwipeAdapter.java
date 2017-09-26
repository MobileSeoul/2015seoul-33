package com.seoul.juminprogram.Java_folder;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.seoul.juminprogram.R;


public class SwipeAdapter extends BaseAdapter {

	private Context mContext = null;

	private List<HashMap<String, String>> dataList;

	private int mRightWidth = 0;

	private IOnItemRightClickListener mListener = null;

	public interface IOnItemRightClickListener {
		void onRightClick(View v, int position);
	}

	/**
	 * @param mainActivity
	 */
	public SwipeAdapter(Context ctx, List<HashMap<String, String>> list, int rightWidth, IOnItemRightClickListener l) {
		mContext = ctx;
		dataList = list;
		mRightWidth = rightWidth;
		mListener = l;
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder item;
		final int thisPosition = position;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.tab_favorite_swipe, parent, false);
			item = new ViewHolder();
			item.item_left = (View) convertView.findViewById(R.id.item_left);
			item.item_right = (View) convertView.findViewById(R.id.item_right);
			item.item_left_txt1 = (TextView) convertView.findViewById(R.id.item_left_dong);
			item.item_left_txt2 = (TextView) convertView.findViewById(R.id.item_left_name);
			item.item_left_txt3 = (TextView) convertView.findViewById(R.id.item_left_time);
			item.item_right_txt = (TextView) convertView.findViewById(R.id.item_right_txt);
			convertView.setTag(item);
		} else {
			item = (ViewHolder) convertView.getTag();
		}
		LinearLayout.LayoutParams lp1 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		item.item_left.setLayoutParams(lp1);
		LinearLayout.LayoutParams lp2 = new LayoutParams(mRightWidth, LayoutParams.MATCH_PARENT);
		item.item_right.setLayoutParams(lp2);

		HashMap<String, String> data = new HashMap<String, String>();
		data = dataList.get(position);

		item.item_left_txt1.setText(data.get("dong"));
		item.item_left_txt2.setText(data.get("name"));
		item.item_left_txt3.setText(data.get("time"));
		item.item_right_txt.setText("delete");
		item.item_right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mListener != null) {
					mListener.onRightClick(v, thisPosition);
				}
			}
		});
		return convertView;
	}

	private class ViewHolder {
		View item_left;

		View item_right;

		TextView item_left_txt1;
		TextView item_left_txt2;
		TextView item_left_txt3;
		TextView item_right_txt;
	}
}
