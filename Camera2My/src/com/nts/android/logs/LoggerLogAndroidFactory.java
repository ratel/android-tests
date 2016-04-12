package com.nts.android.logs;

import com.nts.cash.log.LogsWriter;
import com.nts.cash.log.LogsWriterFactory;

public class LoggerLogAndroidFactory implements LogsWriterFactory {
	private static LogsWriter log;
	
	@Override
	public LogsWriter createLogsWriter(String nameLogger) {
		return createLogsWriter();
	}

	@Override
	public LogsWriter createLogsWriter(Class<?> classLogger) {
		return createLogsWriter();
	}

	protected LogsWriter createLogsWriter() {
		if (log == null)
			log= new LoggerLogAndroid();

		return log;
	}
}
