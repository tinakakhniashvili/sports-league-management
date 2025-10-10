package com.solvd.sportsleaguemanagement.concurrency;

import com.solvd.sportsleaguemanagement.db.AccountDao;
import com.solvd.sportsleaguemanagement.db.ConnectionPool;

import java.util.concurrent.Callable;

public class DaoCallable implements Callable<String> {
    private final ConnectionPool pool;
    private final int userId;

    public DaoCallable(ConnectionPool pool, int userId) {
        this.pool = pool;
        this.userId = userId;
    }

    @Override
    public String call() throws Exception {
        AccountDao conn = null;
        try {
            conn = pool.getConnection();
            conn.create("user-" + userId);
            conn.get(userId);
            conn.update(userId);
            conn.delete(userId);
            return "OK-" + userId;
        } finally {
            if (conn != null) {
                pool.releaseConnection(conn);
            }
        }
    }
}
