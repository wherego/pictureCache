package cn.xie.zhbj.utlis;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class MyBitMapUtils {
	private NetCacheUtils mNetUtils;
	private LocalCacheUtils localCacheUtils;
	private MemoryCacheUtils mMemoryUtils;

	public MyBitMapUtils() {
		localCacheUtils = new LocalCacheUtils();
		mMemoryUtils = new MemoryCacheUtils();
		mNetUtils = new NetCacheUtils(localCacheUtils, mMemoryUtils);
	}

	public void disPlay(ImageView imageView, String url) {
/*		// �����ڴ滺��
		Bitmap bitmap = mMemoryUtils.getMemoryCache(url);
		if (bitmap != null) {
			imageView.setImageBitmap(bitmap);
			System.out.println("���ڴ���ػ�����");
			return;
		}

		// ���ر��ػ���
		bitmap = localCacheUtils.getLocalCache(url);
		if (bitmap != null) {
			// ���ػ������
			imageView.setImageBitmap(bitmap);
			System.out.println("�ӱ��ؼ��ػ�����");

			// д�ڴ滺��
			mMemoryUtils.setMemoryCache(bitmap, url);
			return;
		}*/

		mNetUtils.getBitmapFromNet(imageView, url);
	}

}
