package com.imac.ItemView;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.example.blockeditor.R;
import com.imac.Framework.MainParentLayout;
import com.imac.ViewGroup.BlockView;

public class BlockItemView extends MainParentLayout {
	private BlockView blockView;
	private RelativeLayout rel;
	public BlockItemView(Context context) {
		super(context);
	}

	protected void init() {
		setParent();
		ImageView();
	}
	private void setParent() {
		LayoutParams LP =getLayoutParams(WH.getH(15) , WH.getH(15));
		rel = new RelativeLayout(context );
		rel.setLayoutParams(LP);
		this.addView(rel);
	}
	private void ImageView() {
		LayoutParams LP =getLayoutParams(WH.getH(15) , WH.getH(15));
		blockView = new BlockView(context , 720 , 1280);
		blockView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ic_launcher));
		blockView.setLayoutParams(LP);
		this.addView(blockView);
	}
}
