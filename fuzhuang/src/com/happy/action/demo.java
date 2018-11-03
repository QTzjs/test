package com.happy.action;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class demo {
	public static void main(String[] args) {
		new demo().init();
		
	}
	
	private void init(){
		final Outputer outper = new Outputer();
		
		new Thread(new Runnable() {
			
			public void run() {
				while(true){
					try{
						Thread.sleep(5);
					}catch (InterruptedException e){
						e.printStackTrace();
					}
					outper.output("douxiancheng");
				}
				
			}
		}).start();
		
		new Thread(new Runnable() {
			
			public void run() {
				while(true){
					try{
						Thread.sleep(5);
					}catch (InterruptedException e){
						e.printStackTrace();
					}
					outper.output("eson15");
				}
				
			}
		}).start();
	}

}

class Outputer{
	Lock lock = new ReentrantLock();
	//字符串打印
	public void output(String name){
		int len = name.length();
		lock.lock();
		try{
			for(int i=0;i<len;i++){
				System.out.print(name.charAt(i));
			}
			System.out.println("");
		}finally{
			lock.unlock();
		}
	}
}

