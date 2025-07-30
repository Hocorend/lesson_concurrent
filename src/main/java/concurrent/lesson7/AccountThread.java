package concurrent.lesson7;

public class AccountThread extends Thread {

    private final Account accountFrom;
    private final Account accountTo;

    public AccountThread(Account accountFrom, Account accountTo) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
    }

    /**
     * 1 ------- account1 -> account2
     * <p>
     * 2 ------- account2 -> account1
     */
    @Override
    public void run() {
        for (int i = 0; i < 2000; i++) {
            accountsLock();
            try {
                if (accountFrom.takeOff(10)) {
                    accountTo.add(10);
                }
            } finally {
                accountUnlock();
            }
        }
    }

    private void accountsLock() {
        while (true) {
            boolean lockAccountTo = accountTo.getLock().tryLock();
            boolean lockAccountFrom = accountFrom.getLock().tryLock();

            if (lockAccountFrom && lockAccountTo) {
                break;
            }
            if (lockAccountFrom) {
                accountFrom.getLock().unlock();
            }
            if (lockAccountTo) {
                accountTo.getLock().unlock();
            }
        }
    }

    private void accountUnlock() {
        accountTo.getLock().unlock();
        accountFrom.getLock().unlock();
    }
}
