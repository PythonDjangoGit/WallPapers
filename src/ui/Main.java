package ui;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.xhban.util.DownUtil;

public class Main {
	public static void main(String[] args) {
		downloadTimer();
	}
	public static void downloadTimer() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				DownUtil.downWallPaper();
			}
		};
		// ����ִ��ʱ��
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);// ÿ��
		calendar.set(year, month, day, 9, 00, 00);
		// ����ÿ���09:10:00ִ�У����ظ�ִ��
		Date date = calendar.getTime();
		Timer timer = new Timer();
		timer.schedule(task, date);
		// ÿ���dateʱ��ִ�У�ÿ��2s�ظ�ִ��
		// int peroid = 2 * 1000;
		// timer.schedule(task, date, peroid);
	}

}
