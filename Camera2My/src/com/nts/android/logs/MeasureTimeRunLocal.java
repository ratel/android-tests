package com.nts.android.logs;

import com.nts.cash.log.LogsWriter;
import com.nts.cash.log.OuterLogs;


public class MeasureTimeRunLocal {
	private long startTime;
	private long endTime;
	final LogsWriter log = OuterLogs.createLogsWriter(this.getClass());              // ������.
	
	public void execute() {
		startTime= System.currentTimeMillis();
		/*try {
			String s= "��������� ������";
			throw new Exception(s);
		} catch(Exception e) {
			String s= null;
			log.w("logTest", s, e);
		}*/
		for (int i= 0; i < 10000; i++)
			log.i("logTest", "�������� ������ ��� �������![%d]", i);
		
		endTime= System.currentTimeMillis();
	}

	public void execute(Runnable proc) {
		if (proc != null) {
			startTime= System.currentTimeMillis();
			proc.run();
			endTime= System.currentTimeMillis();
		}
	}
	
	public long getTimeExec() {
		return endTime - startTime;
	}
}
