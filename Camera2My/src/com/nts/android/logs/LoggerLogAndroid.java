package com.nts.android.logs;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.nts.cash.log.LogsWriter;


import android.util.Log;


public class LoggerLogAndroid implements LogsWriter {
	//static private boolean enabled= true;
	static private boolean enabledT= true;
	static private boolean enabledD= true;
	static private boolean enabledI= true;
	static private boolean enabledW= true;
	static private boolean enabledE= true;
	static private boolean enabledF= true;
	//final static private boolean enabledLocation= true;
	
	public void setEnabled(boolean enabled) {
	}
	
	
	@Override
	synchronized public void t(String tag, String message) {
		if (isT()) Log.v(tag, message);
	}
	
	@Override
	synchronized public void d(String tag, String message) {
		if (isD()) Log.d(tag, new SimpleDateFormat("mm:ss:SSS", Locale.getDefault()).format(System.currentTimeMillis()) + "_" + message);
	}
	
	@Override
	synchronized public void i(String tag, String message) {
		if (isI()) Log.i(tag, message);
	}
	
	@Override
	synchronized public void w(String tag, String message) {
		if (isW()) Log.w(tag, message);
	}
	
	@Override
	synchronized public void e(String tag, String message) {
		if (isE()) Log.e(tag, message);
	}
	
	@Override
	synchronized public void f(String tag, String message) {
		if (isF()) Log.e(tag, message);
	}
	
	
	synchronized public void t(String tag, String format, Object... args) {
		//if (isT()) Log.v(tag, String.format(format, args) + ((enabledLocation) ? getLocation() : ""));
		if (isT()) Log.v(tag, (format != null) ? String.format(format, args) : String.valueOf(format));
    }

	synchronized public void d(String tag, String format, Object... args) {
    	//if (isD()) Log.d(tag, String.format(format, args) + ((enabledLocation) ? getLocation() : ""));
		if (isD()) Log.d(tag, (format != null) ? String.format(format, args) : String.valueOf(format));
    }

	synchronized public void i(String tag, String format, Object... args) {
        //if (isI()) Log.i(tag, String.format(format, args) + ((enabledLocation) ? getLocation() : ""));
        if (isI()) Log.i(tag, (format != null) ? String.format(format, args) : String.valueOf(format));
    }

	synchronized public void w(String tag, String format, Object... args) {
        //if (isW()) Log.w(tag, String.format(format, args) + ((enabledLocation) ? getLocation() : ""));
        if (isW()) Log.w(tag, (format != null) ? String.format(format, args) : String.valueOf(format));
    }

	synchronized public void e(String tag, String format, Object... args) {
    	//if (isE()) Log.e(tag, String.format(format, args) + ((enabledLocation) ? getLocation() : ""));
		if (isE()) Log.e(tag, (format != null) ? String.format(format, args) : String.valueOf(format));
    }

	@Override
	synchronized public void f(String tag, String format, Object... args) {
		//if (isF()) Log.e(tag, String.format(format, args) + ((enabledLocation) ? getLocation() : ""));
		if (isF()) Log.e(tag, (format != null) ? String.format(format, args) : String.valueOf(format));
	}
	
	synchronized public void w(String tag, String message, Throwable e) {
        //if (isW()) Log.w(tag, message + ((enabledLocation) ? getLocation() : ""), e);
		if (isW()) Log.w(tag, message, e);
    }
    
	synchronized public void e(String tag, String message, Throwable e) {
        //if (isE()) Log.e(tag, message + ((enabledLocation) ? getLocation() : ""), e);
		if (isE()) Log.e(tag, message, e);
    }

	@Override
	synchronized public void f(String tag, String message, Throwable e) {
        //if (isF()) Log.e(tag, message + ((enabledLocation) ? getLocation() : ""), e);
        if (isF()) Log.e(tag, message, e);
	}

	
	@Override
	public boolean isT() {
		return enabledT;
	}
	@Override
	public boolean isD() {
		return enabledD;
	}
	@Override
	public boolean isI() {
		return enabledI;
	}
	@Override
	public boolean isW() {
		return enabledW;
	}
	@Override
	public boolean isE() {
		return enabledE;
	}
	@Override
	public boolean isF() {
		return enabledF;
	}
	
	private static String getLocation() {
		final String className = "";
		final StackTraceElement[] traces = Thread.currentThread().getStackTrace();
		boolean found = false;

		for (int i = 0; i < traces.length; i++) {
			StackTraceElement trace = traces[i];

			try {
				if (found) {
					if (!trace.getClassName().startsWith(className)) {
						Class<?> clazz = Class.forName(trace.getClassName());
						return "[" + getClassName(clazz) + ":" + trace.getMethodName() + ":" + trace.getLineNumber() + "]: ";
					}
				}
				else if (trace.getClassName().startsWith(className)) {
					found = true;
					continue;
				}
			}
			catch (ClassNotFoundException e) {
			}
		}

		return " ";
	}

	private static String getClassName(Class<?> clazz) {
		if (clazz != null) {
			if (clazz.getSimpleName() != null) {
				if (!clazz.getSimpleName().isEmpty())
					return clazz.getSimpleName();
			}

			return getClassName(clazz.getEnclosingClass());
		}

		return "";
	}
}
