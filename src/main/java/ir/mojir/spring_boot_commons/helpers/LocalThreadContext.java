package ir.mojir.spring_boot_commons.helpers;

import ir.mojir.spring_boot_commons.dtos.ThreadContextData;

public class LocalThreadContext {

	private static ThreadLocal<ThreadContextData> threadLocal = new ThreadLocal<>();
	
	public static ThreadContextData getData() {
		return threadLocal.get();
	}
	
	public static void setData(ThreadContextData data)
	{
		threadLocal.set(data);
	}
}
