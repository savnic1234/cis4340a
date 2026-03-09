// Rule 09. Locking (LCK)
// LCK03-J. Do not synchronize on the intrinsic locks of high-level concurrency objects


private final Lock lock = new ReentrantLock();

public void doSomething() {
  synchronized(lock) {
    // ...
  }
}



private final Lock lock = new ReentrantLock();

public void doSomething() {
  lock.lock();
  try {
    // ...
  } finally {
    lock.unlock();
  }
}
