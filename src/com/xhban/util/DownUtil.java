package com.xhban.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xhban.listener.DownloadFinishListener;
import com.xhban.listener.RequestFinishListener;

public class DownUtil {
	private final static int FAILED = 0;
	private final static int SUCCEED = 1;

	/**
	 * ��鵱��������Ƿ��Ѿ�ִ�У���ֹ�ظ�����
	 * 
	 * @return
	 */
	private static boolean check() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String name = sdf.format(date);
		File file1 = new File("D:\\wallpapers\\logs\\" + name + ".txt");
		File file2 = new File("D:\\wallpapers\\errors\\" + name + ".txt");
		if (file1.exists() || file2.exists()) {
			return true;
		}
		return false;
	}

	public static void downWallPaper() {
		if (!check()) {
			String requestAddress = "http://guolin.tech/api/bing_pic";
			HttpUtil.doGet(requestAddress, new RequestFinishListener() {

				@Override
				public void finish(String response) {
					downPhoto(response);
				}

				@Override
				public void error(Exception ex) {
					CommUtil.saveLog(FAILED, "����ͼƬ��ַʱ�쳣: " + ex.toString());
				}
			});
		}
	}

	// ����ͼƬ
	public static void downPhoto(String picAddress) {
		String dir = "D:\\wallpapers";
		File saveDir = new File(dir);
		if (!saveDir.exists()) {
			saveDir.mkdir();
		}
		File[] allFiles = saveDir.listFiles();
		List<File> files = new ArrayList<File>();
		for (File file : allFiles) {
			if (file.isFile()) {
				files.add(file);
			}
		}
		if (files.size() == 0) {
			// �ļ��в�����ͼƬ����1��ʼ����
			String name = "1.jpg";
			HttpUtil.download(picAddress, dir, name, new DownloadFinishListener() {

				@Override
				public void finish() {
					CommUtil.saveLog(SUCCEED, "���سɹ�");
				}

				@Override
				public void error(Exception ex) {
					CommUtil.saveLog(FAILED, "����ͼƬʱ�쳣�� " + ex.toString());
				}
			});
		} else {
			// �ļ��д���ͼƬ,��ȡ������ƣ������1��Ϊ��ͼƬ������
			int names[] = new int[files.size()];
			for (int i = 0; i < files.size(); i++) {
				String fileName = files.get(i).getName();
				names[i] = Integer.parseInt(fileName.substring(0, fileName.lastIndexOf(".")));
			}
			String name = CommUtil.getMax(names) + 1 + ".jpg";
			HttpUtil.download(picAddress, dir, name, new DownloadFinishListener() {

				@Override
				public void finish() {
					CommUtil.saveLog(SUCCEED, "���سɹ�");
				}

				@Override
				public void error(Exception ex) {
					CommUtil.saveLog(FAILED, "����ͼƬʱ�쳣�� " + ex.toString());
				}
			});
		}
	}

}
