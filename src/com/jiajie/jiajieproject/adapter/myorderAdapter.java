/**
 * 
 */
package com.jiajie.jiajieproject.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiajie.jiajieproject.R;
import com.jiajie.jiajieproject.activity.OrderInformationActivity;
import com.jiajie.jiajieproject.contents.Constants;
import com.jiajie.jiajieproject.utils.ImageLoad;
import com.jiajie.jiajieproject.utils.IntentUtil;
import com.jiajie.jiajieproject.utils.ToastUtil;
import com.mrwujay.cascade.model.produceClass;

/**
 * 项目名称：HaiChuanProject 类名称：FaBuSearchAdapter 类描述： 创建人：王蕾 创建时间：2015-7-29
 * 下午2:19:53 修改备注：
 */
public class myorderAdapter extends BaseAdapter implements OnClickListener {

	private ArrayList<produceClass> list = new ArrayList<produceClass>();
	private Activity activity;
	private ImageLoad imageLoad;
	public myorderAdapter(Activity activity, ImageLoad imageLoad) {
		this.activity = activity;
		this.imageLoad = imageLoad;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public void setdata(ArrayList<produceClass> list) {
		this.list.addAll(list);
	}


	public ArrayList<produceClass> getdata() {
		return list;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView == null) {
			vh = new ViewHolder();
			convertView = LayoutInflater.from(activity).inflate(
					R.layout.myorderitem_layout, null);
			vh.myorderitem_layoutImgeView1 = (ImageView) convertView
					.findViewById(R.id.myorderitem_layoutImgeView1);
			vh.myorderitem_layouttext1 = (TextView) convertView
					.findViewById(R.id.myorderitem_layouttext1);
			vh.myorderitem_layouttext11 = (TextView) convertView
					.findViewById(R.id.myorderitem_layouttext11);
			vh.myorderitem_layouttext2 = (TextView) convertView
					.findViewById(R.id.myorderitem_layouttext2);
			vh.myorderitem_layouttext3 = (TextView) convertView
					.findViewById(R.id.myorderitem_layouttext3);
	
			vh.myorderitem_layouttext4 = (TextView) convertView
					.findViewById(R.id.myorderitem_layouttext4);
			vh.myorderitem_layouttext5 = (TextView) convertView
					.findViewById(R.id.myorderitem_layouttext5);
			vh.myorderitem_layoutlayout1 = (RelativeLayout) convertView
					.findViewById(R.id.myorderitem_layoutlayout1);
			vh.myorder_all=(Button) convertView.findViewById(R.id.myorder_all);
			vh.myorder_yingfu=(Button) convertView.findViewById(R.id.myorder_yingfu);
			vh.go_pay=(ImageView) convertView.findViewById(R.id.go_pay);
			vh.cancle_order=(ImageView) convertView.findViewById(R.id.cancle_order);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		vh.myorderitem_layoutlayout1.setTag(position);
		vh.myorderitem_layoutlayout1.setOnClickListener(this);
		vh.myorderitem_layouttext1
				.setText("订单号" + list.get(position).order_code);
		vh.myorderitem_layouttext2.setText(list.get(position).product_name);
		vh.myorderitem_layouttext4.setText(list.get(position).price);
		vh.myorderitem_layouttext5.setText(list.get(position).order_qty);
		vh.myorder_all.setText(list.get(position).order_qty);
		vh.myorder_yingfu.setText(list.get(position).total_price);
		vh.go_pay.setOnClickListener(this);
		vh.cancle_order.setOnClickListener(this);
		return convertView;
	}

	class ViewHolder {
		ImageView myorderitem_layoutImgeView1;
		TextView myorderitem_layouttext1, myorderitem_layouttext11,
				myorderitem_layouttext2, myorderitem_layouttext3,
				myorderitem_layouttext4, myorderitem_layouttext5;
		RelativeLayout myorderitem_layoutlayout1;
		Button myorder_all,myorder_yingfu;
		ImageView go_pay,cancle_order;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.myorderitem_layoutlayout1:
			int position=(Integer) v.getTag();
			Bundle bundle=new Bundle();
			bundle.putString("myorderid", list.get(position).id);
			bundle.putString("myorderadressid", list.get(position).address_id);
			IntentUtil.activityForward(activity, OrderInformationActivity.class, bundle, false);
			break;
		case R.id.go_pay:
			ToastUtil.showToast(activity, "去支付");
			break;
		case R.id.cancle_order:
			ToastUtil.showToast(activity, "取消订单");
			break;

		default:
			break;
		}

	}

	

}
