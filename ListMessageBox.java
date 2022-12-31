package rs.ac.bg.etf.kdp.neo;

import java.util.*;
import java.util.concurrent.locks.*;

public class ListMessageBox<T> implements MessageBox<T> {

	public static final int MAX = 2;

	List<Message<T>> buffer;
	int cap;

	Lock lock;
	Condition full, empty;

	public ListMessageBox() {
		buffer = new LinkedList<Message<T>>();
		cap = MAX;

		lock = new ReentrantLock();
		full = lock.newCondition();
		empty = lock.newCondition();
	}

	@Override
	public void put(Message<T> m, Priority priority, long timeToLive) {
		lock.lock();
		try {
			while(buffer.size() == cap) {
				try {
					empty.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			buffer.add(m);
			full.signal();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public Message<T> get(long timeToWait, Status status) {
		lock.lock();
		try {
			while(buffer.size() == 0) {
				try {
					full.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			empty.signal();
			return buffer.remove(0);
		} finally {
			lock.unlock();
		}
	}

}
