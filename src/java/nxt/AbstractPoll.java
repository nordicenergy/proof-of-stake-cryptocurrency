/*
 * Copyright © 2020-2020 The Nordic Energy Core Developers
 *
 * See the LICENSE.txt file at the top-level directory of this distribution
 * for licensing information.
 *
 * Unless otherwise agreed in a custom licensing agreement with Nordic Energy.,
 * no part of the Nxt software, including this file, may be copied, modified,
 * propagated, or distributed except according to the terms contained in the
 * LICENSE.txt file.
 *
 * Removal or modification of this copyright notice is prohibited.
 *
 */

package nxt;

import java.sql.ResultSet;
import java.sql.SQLException;

abstract class AbstractPoll {

    final long id;
    final VoteWeighting voteWeighting;
    final long accountId;
    final int finishHeight;

    AbstractPoll(long id, long accountId, int finishHeight, VoteWeighting voteWeighting) {
        this.id = id;
        this.accountId = accountId;
        this.finishHeight = finishHeight;
        this.voteWeighting = voteWeighting;
    }

    AbstractPoll(ResultSet rs) throws SQLException {
        this.id = rs.getLong("id");
        this.accountId = rs.getLong("account_id");
        this.finishHeight = rs.getInt("finish_height");
        this.voteWeighting = new VoteWeighting(rs.getByte("voting_model"), rs.getLong("holding_id"),
                rs.getLong("min_balance"), rs.getByte("min_balance_model"));
    }

    public final long getId() {
        return id;
    }

    public final long getAccountId() {
        return accountId;
    }

    public final int getFinishHeight() {
        return finishHeight;
    }

    public final VoteWeighting getVoteWeighting() {
        return voteWeighting;
    }

}

