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
		// ����ִ��ʱ��
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);// ÿ��
		Date date = calendar.getTime();
		calendar.set(year, month, day, 9, 00, 00);
		Timer timer = new Timer();
		// ��һ��ִ��
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				DownUtil.downWallPaper();
			}
		};
		// ÿ��9:00ʱ��ִ�У�ÿ��30�����ظ�ִ��һ��
		int peroid = 30 * 60 * 1000;
		timer.schedule(task, date, peroid);
	}
}
