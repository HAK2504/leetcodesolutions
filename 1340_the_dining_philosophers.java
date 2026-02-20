import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

class DiningPhilosophers {

    private final Semaphore semaphore = new Semaphore(4); 
    private final ReentrantLock[] forks = new ReentrantLock[5];

    public DiningPhilosophers() {
        for (int i = 0; i < 5; i++) {
            forks[i] = new ReentrantLock(true); // fair lock
        }
    }

    public void wantsToEat(
            int philosopher,
            Runnable pickLeftFork,
            Runnable pickRightFork,
            Runnable eat,
            Runnable putLeftFork,
            Runnable putRightFork) throws InterruptedException {

        int left = philosopher;
        int right = (philosopher + 1) % 5;

        semaphore.acquire(); // allow max 4 philosophers

        forks[left].lock();
        forks[right].lock();

        pickLeftFork.run();
        pickRightFork.run();

        eat.run();

        putRightFork.run();
        putLeftFork.run();

        forks[right].unlock();
        forks[left].unlock();

        semaphore.release();
    }
}