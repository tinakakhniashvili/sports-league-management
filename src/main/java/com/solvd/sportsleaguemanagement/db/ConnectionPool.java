package com.solvd.sportsleaguemanagement.db;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static volatile ConnectionPool INSTANCE;

    private final BlockingQueue<AccountDao> pool;

    private ConnectionPool(int size) {
        this.pool = new LinkedBlockingQueue<>(size);
        for (int i = 1; i <= size; i++) {
            pool.add(new AccountDao(i));
        }
    }

    public static ConnectionPool getInstance(int size) {
        ConnectionPool local = INSTANCE;
        if (local == null) {
            synchronized (ConnectionPool.class) {
                local = INSTANCE;
                if (local == null) {
                    INSTANCE = local = new ConnectionPool(size);
                }
            }
        }
        return local;
    }

    public AccountDao getConnection() throws InterruptedException {
        return pool.take();
    }

    public void releaseConnection(AccountDao conn) throws InterruptedException {
        if (conn != null) {
            pool.put(conn);
        }
    }
}
