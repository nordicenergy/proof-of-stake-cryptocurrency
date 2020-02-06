/*
 * Copyright © 2013-2016 The Pwrc Core Developers.
 * Copyright © 2016-2018 Jelurida IP B.V.
 *
 * See the LICENSE.txt file at the top-level directory of this distribution
 * for licensing information.
 *
 * Unless otherwise agreed in a custom licensing agreement with Jelurida B.V.,
 * no part of the Pwrc software, including this file, may be copied, modified,
 * propagated, or distributed except according to the terms contained in the
 * LICENSE.txt file.
 *
 * Removal or modification of this copyright notice is prohibited.
 *
 */

package pwrc;

import pwrc.db.BasicDb;
import pwrc.db.TransactionalDb;

public final class Db {

    public static final String PREFIX = Constants.isTestnet ? "pwrc.testDb" : "pwrc.db";
    public static final TransactionalDb db = new TransactionalDb(new BasicDb.DbProperties()
            .maxCacheSize(Pwrc.getIntProperty("pwrc.dbCacheKB"))
            .dbUrl(Pwrc.getStringProperty(PREFIX + "Url"))
            .dbType(Pwrc.getStringProperty(PREFIX + "Type"))
            .dbDir(Pwrc.getStringProperty(PREFIX + "Dir"))
            .dbParams(Pwrc.getStringProperty(PREFIX + "Params"))
            .dbUsername(Pwrc.getStringProperty(PREFIX + "Username"))
            .dbPassword(Pwrc.getStringProperty(PREFIX + "Password", null, true))
            .maxConnections(Pwrc.getIntProperty("pwrc.maxDbConnections"))
            .loginTimeout(Pwrc.getIntProperty("pwrc.dbLoginTimeout"))
            .defaultLockTimeout(Pwrc.getIntProperty("pwrc.dbDefaultLockTimeout") * 1000)
            .maxMemoryRows(Pwrc.getIntProperty("pwrc.dbMaxMemoryRows"))
    );

    public static void init() {
        db.init(new NxtDbVersion());
    }

    static void shutdown() {
        db.shutdown();
    }

    private Db() {} // never

}
