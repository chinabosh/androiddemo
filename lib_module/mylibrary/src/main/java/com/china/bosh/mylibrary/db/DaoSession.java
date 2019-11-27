package com.china.bosh.mylibrary.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.china.bosh.mylibrary.db.table.tableTest;
import com.china.bosh.mylibrary.db.table.PriceEntity;

import com.china.bosh.mylibrary.db.tableTestDao;
import com.china.bosh.mylibrary.db.PriceEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig tableTestDaoConfig;
    private final DaoConfig priceEntityDaoConfig;

    private final tableTestDao tableTestDao;
    private final PriceEntityDao priceEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        tableTestDaoConfig = daoConfigMap.get(tableTestDao.class).clone();
        tableTestDaoConfig.initIdentityScope(type);

        priceEntityDaoConfig = daoConfigMap.get(PriceEntityDao.class).clone();
        priceEntityDaoConfig.initIdentityScope(type);

        tableTestDao = new tableTestDao(tableTestDaoConfig, this);
        priceEntityDao = new PriceEntityDao(priceEntityDaoConfig, this);

        registerDao(tableTest.class, tableTestDao);
        registerDao(PriceEntity.class, priceEntityDao);
    }
    
    public void clear() {
        tableTestDaoConfig.clearIdentityScope();
        priceEntityDaoConfig.clearIdentityScope();
    }

    public tableTestDao getTableTestDao() {
        return tableTestDao;
    }

    public PriceEntityDao getPriceEntityDao() {
        return priceEntityDao;
    }

}
