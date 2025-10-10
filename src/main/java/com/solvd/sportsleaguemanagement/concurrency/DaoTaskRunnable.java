package com.solvd.sportsleaguemanagement.concurrency;

import com.solvd.sportsleaguemanagement.db.AccountDao;
import com.solvd.sportsleaguemanagement.db.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaoTaskRunnable implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(DaoTaskRunnable.class);

    private final ConnectionPool pool;
    private final int userId;

    public DaoTaskRunnable(ConnectionPool pool, int userId) {
        this.pool = pool;
        this.userId = userId;
    }

    @Override
    public void run() {
        AccountDao conn = null;
        try {
            LOGGER.info("Runnable-{} waiting for connection...", userId);
            conn = pool.getConnection();
            LOGGER.info("Runnable-{} GOT {}", userId, conn.getName());

            conn.create("user-" + userId);
            conn.get(userId);
            conn.update(userId);
            conn.delete(userId);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.warn("Runnable-{} interrupted", userId);
        } finally {
            if (conn != null) {
                try {
                    pool.releaseConnection(conn);
                    LOGGER.info("Runnable-{} RELEASED {}", userId, conn.getName());
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
