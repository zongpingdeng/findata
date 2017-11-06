/*
 * This file is generated by jOOQ.
*/
package com.cimc.datahub.mining.jooq;


import com.cimc.datahub.mining.jooq.tables.HkToMainland;
import com.cimc.datahub.mining.jooq.tables.MutualMarketOverviewDaily;
import com.cimc.datahub.mining.jooq.tables.MutualMarketTop10Daily;
import com.cimc.datahub.mining.jooq.tables.SzMarketOverview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Basedata extends SchemaImpl {

    private static final long serialVersionUID = -1239591218;

    /**
     * The reference instance of <code>basedata</code>
     */
    public static final Basedata BASEDATA = new Basedata();

    /**
     * The table <code>basedata.hk_to_mainland</code>.
     */
    public final HkToMainland HK_TO_MAINLAND = com.cimc.datahub.mining.jooq.tables.HkToMainland.HK_TO_MAINLAND;

    /**
     * The table <code>basedata.mutual_market_overview_daily</code>.
     */
    public final MutualMarketOverviewDaily MUTUAL_MARKET_OVERVIEW_DAILY = com.cimc.datahub.mining.jooq.tables.MutualMarketOverviewDaily.MUTUAL_MARKET_OVERVIEW_DAILY;

    /**
     * The table <code>basedata.mutual_market_top10_daily</code>.
     */
    public final MutualMarketTop10Daily MUTUAL_MARKET_TOP10_DAILY = com.cimc.datahub.mining.jooq.tables.MutualMarketTop10Daily.MUTUAL_MARKET_TOP10_DAILY;

    /**
     * The table <code>basedata.sz_market_overview</code>.
     */
    public final SzMarketOverview SZ_MARKET_OVERVIEW = com.cimc.datahub.mining.jooq.tables.SzMarketOverview.SZ_MARKET_OVERVIEW;

    /**
     * No further instances allowed
     */
    private Basedata() {
        super("basedata", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            HkToMainland.HK_TO_MAINLAND,
            MutualMarketOverviewDaily.MUTUAL_MARKET_OVERVIEW_DAILY,
            MutualMarketTop10Daily.MUTUAL_MARKET_TOP10_DAILY,
            SzMarketOverview.SZ_MARKET_OVERVIEW);
    }
}
