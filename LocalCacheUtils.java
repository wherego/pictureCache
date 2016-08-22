package cn.xie.zhbj.utlis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class LocalCacheUtils {

	public static final String LOCAL_CACHE_DIR = Environment
			.getExternalStorageDirectory().getAbsolutePath() + "/zhbj/cache";

	// д���ػ���
	public void setLocalCache(Bitmap bitmap, String url) {
		// �����ļ���
		File dir = new File(LOCAL_CACHE_DIR);
		if (!dir.exists() || !dir.isDirectory()) {// �ж��ļ����Ƿ����
			dir.mkdirs();// �����ļ���
		}

		try {
			// MD5(url)
			File cacheFile = new File(dir, MD5Encoder.encode(url));
			// ��ͼƬ����ѹ�������ڱ����ļ���
			// ��1:ѹ����ʽ; ��2:ѹ������, 0-100, 100��ʾ��ѹ��;��3:�����
			bitmap.compress(CompressFormat.JPEG, 100, new FileOutputStream(
					cacheFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �����ػ���
	public Bitmap getLocalCache(String url) {
		try {
			File cacheFile = new File(LOCAL_CACHE_DIR, MD5Encoder.encode(url));

			if (cacheFile.exists()) {// �������
				Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(
						cacheFile));
				return bitmap;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
