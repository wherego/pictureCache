package cn.xie.zhbj.utlis;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class MemoryCacheUtils {
	// ��������ڴ����
	// private HashMap<String, Bitmap> mMemoryCache = new HashMap<String,
	// Bitmap>();
	// �����ø���
	// �� Android 2.3 (API Level 9)��ʼ��������������������ڻ��ճ��������û������õĶ������������ú������ñ�ò��ٿɿ���
	// private HashMap<String, SoftReference<Bitmap>> mMemoryCache = new
	// HashMap<String, SoftReference<Bitmap>>();

	// LruCache
	private LruCache<String, Bitmap> mLruCache;

	public MemoryCacheUtils() {
		// LruCache: LRU, least recenttly used; �������ʹ���㷨, �ܹ����������ʹ�õ�ͼƬ�����Զ��Ƴ�
		// ԭ��: ����һ���ڴ�����, һ������, �͸���LRU�㷨�Ƴ�ͼƬ����, �Ӷ���֤�ڴ�һֱ�ں���ķ�Χ֮��
		// 16M
		long maxMemory = Runtime.getRuntime().maxMemory();// ��ǰ��������������ڴ�
		mLruCache = new LruCache<String, Bitmap>((int) maxMemory / 8) {// �����ڴ�����Ϊ���ڴ�˷�֮һ

			// ����ÿ��ͼƬ����Ĵ�С
			@Override
			protected int sizeOf(String key, Bitmap value) {
				// value.getByteCount();
				// return getRowBytes() * getHeight();
				// ͼƬ��С = ÿһ�����ֽ��� * �߶�
				int size = value.getRowBytes() * value.getHeight();

				return size;
			}

		};

	}

	// д�ڴ滺��
	public void setMemoryCache(Bitmap bitmap, String url) {
		// ��bitmap���������ð�װ
		// SoftReference<Bitmap> softBitmap = new SoftReference<Bitmap>(bitmap);
		// mMemoryCache.put(url, softBitmap);
		mLruCache.put(url, bitmap);
	}

	// ���ڴ滺��
	public Bitmap getMemoryCache(String url) {
		// SoftReference<Bitmap> softBitmap = mMemoryCache.get(url);
		//
		// if (softBitmap != null) {//һ��Ҫ�ж��Ƿ�Ϊ��, ��Ϊ���ܱ�����
		// Bitmap bitmap = softBitmap.get();
		// return bitmap;
		// }

		return mLruCache.get(url);
	}

}
