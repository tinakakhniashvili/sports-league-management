package com.solvd.sportsleaguemanagement.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountDao {
    private static final Logger LOGGER = LogManager.getLogger(AccountDao.class);

    private final int id;

    public AccountDao(int id) {
        this.id = id;
    }

    public String getName() {
        return "AccountDao-" + id;
    }

    public void create(String user) {
        slowLog("CREATE " + user);
    }

    public void get(int userId) {
        slowLog("GET id=" + userId);
    }

    public void update(int userId) {
        slowLog("UPDATE id=" + userId);
    }

    public void delete(int userId) {
        slowLog("DELETE id=" + userId);
    }

    private void slowLog(String op) {
        LOGGER.info("[{}] {}", getName(), op);
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
