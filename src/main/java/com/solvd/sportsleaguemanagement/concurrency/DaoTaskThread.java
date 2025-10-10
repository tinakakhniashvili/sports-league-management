package com.solvd.sportsleaguemanagement.concurrency;

import com.solvd.sportsleaguemanagement.db.AccountDao;
import com.solvd.sportsleaguemanagement.db.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaoTaskThread extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(DaoTaskThread.class);

    private final ConnectionPool pool;
    private final int userId;

    public DaoTaskThread(ConnectionPool pool, int userId) {
        super("DaoTaskThread-" + userId);
        this.pool = pool;
        this.userId = userId;
    }

    @Override
    public void run() {
        AccountDao conn = null;
        try {
            LOGGER.info("{} waiting for connection...", getName());
            conn = pool.getConnection();
            LOGGER.info("{} GOT {}", getName(), conn.getName());

            conn.create("user-" + userId);
            conn.get(userId);
            conn.update(userId);
            conn.delete(userId);

        } catch (InterruptedException e) {
            interrupt();
            LOGGER.warn("{} interrupted", getName());
        } finally {
            if (conn != null) {
                try {
                    pool.releaseConnection(conn);
                    LOGGER.info("{} RELEASED {}", getName(), conn.getName());
                } catch (InterruptedException ignored) {
                    interrupt();
                }
            }
        }
    }
}
