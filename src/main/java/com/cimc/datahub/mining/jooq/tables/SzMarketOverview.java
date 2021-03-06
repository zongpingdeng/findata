/*
 * This file is generated by jOOQ.
*/
package com.cimc.datahub.mining.jooq.tables;


import com.cimc.datahub.mining.jooq.Basedata;
import com.cimc.datahub.mining.jooq.Keys;
import com.cimc.datahub.mining.jooq.tables.records.SzMarketOverviewRecord;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class SzMarketOverview extends TableImpl<SzMarketOverviewRecord> {

    private static final long serialVersionUID = -1978136071;

    /**
     * The reference instance of <code>basedata.sz_market_overview</code>
     */
    public static final SzMarketOverview SZ_MARKET_OVERVIEW = new SzMarketOverview();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SzMarketOverviewRecord> getRecordType() {
        return SzMarketOverviewRecord.class;
    }

    /**
     * The column <code>basedata.sz_market_overview.id</code>.
     */
    public final TableField<SzMarketOverviewRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>basedata.sz_market_overview.szcz_index</code>.
     */
    public final TableField<SzMarketOverviewRecord, Double> SZCZ_INDEX = createField("szcz_index", org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>basedata.sz_market_overview.szzz_index</code>.
     */
    public final TableField<SzMarketOverviewRecord, Double> SZZZ_INDEX = createField("szzz_index", org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>basedata.sz_market_overview.zxb_index</code>.
     */
    public final TableField<SzMarketOverviewRecord, Double> ZXB_INDEX = createField("zxb_index", org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>basedata.sz_market_overview.cyb_index</code>.
     */
    public final TableField<SzMarketOverviewRecord, Double> CYB_INDEX = createField("cyb_index", org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>basedata.sz_market_overview.listed_companies</code>.
     */
    public final TableField<SzMarketOverviewRecord, Integer> LISTED_COMPANIES = createField("listed_companies", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>basedata.sz_market_overview.listed_stocks</code>.
     */
    public final TableField<SzMarketOverviewRecord, Integer> LISTED_STOCKS = createField("listed_stocks", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>basedata.sz_market_overview.market_trade_amount</code>.
     */
    public final TableField<SzMarketOverviewRecord, Long> MARKET_TRADE_AMOUNT = createField("market_trade_amount", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>basedata.sz_market_overview.total_stock_count</code>.
     */
    public final TableField<SzMarketOverviewRecord, Long> TOTAL_STOCK_COUNT = createField("total_stock_count", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>basedata.sz_market_overview.total_flow_stock_count</code>.
     */
    public final TableField<SzMarketOverviewRecord, Long> TOTAL_FLOW_STOCK_COUNT = createField("total_flow_stock_count", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>basedata.sz_market_overview.total_market_value</code>.
     */
    public final TableField<SzMarketOverviewRecord, Long> TOTAL_MARKET_VALUE = createField("total_market_value", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>basedata.sz_market_overview.total_flow_market_value</code>.
     */
    public final TableField<SzMarketOverviewRecord, Long> TOTAL_FLOW_MARKET_VALUE = createField("total_flow_market_value", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>basedata.sz_market_overview.stock_trade_amount</code>.
     */
    public final TableField<SzMarketOverviewRecord, Double> STOCK_TRADE_AMOUNT = createField("stock_trade_amount", org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>basedata.sz_market_overview.avg_stock_price</code>.
     */
    public final TableField<SzMarketOverviewRecord, Double> AVG_STOCK_PRICE = createField("avg_stock_price", org.jooq.impl.SQLDataType.FLOAT.nullable(false), this, "");

    /**
     * The column <code>basedata.sz_market_overview.avg_pe</code>.
     */
    public final TableField<SzMarketOverviewRecord, Double> AVG_PE = createField("avg_pe", org.jooq.impl.SQLDataType.FLOAT.nullable(false), this, "");

    /**
     * The column <code>basedata.sz_market_overview.avg_turnover</code>.
     */
    public final TableField<SzMarketOverviewRecord, Double> AVG_TURNOVER = createField("avg_turnover", org.jooq.impl.SQLDataType.FLOAT.nullable(false), this, "");

    /**
     * The column <code>basedata.sz_market_overview.event_date</code>.
     */
    public final TableField<SzMarketOverviewRecord, Date> EVENT_DATE = createField("event_date", org.jooq.impl.SQLDataType.DATE.nullable(false), this, "");

    /**
     * The column <code>basedata.sz_market_overview.created_date</code>.
     */
    public final TableField<SzMarketOverviewRecord, Timestamp> CREATED_DATE = createField("created_date", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");

    /**
     * The column <code>basedata.sz_market_overview.modified_date</code>.
     */
    public final TableField<SzMarketOverviewRecord, Timestamp> MODIFIED_DATE = createField("modified_date", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");

    /**
     * Create a <code>basedata.sz_market_overview</code> table reference
     */
    public SzMarketOverview() {
        this("sz_market_overview", null);
    }

    /**
     * Create an aliased <code>basedata.sz_market_overview</code> table reference
     */
    public SzMarketOverview(String alias) {
        this(alias, SZ_MARKET_OVERVIEW);
    }

    private SzMarketOverview(String alias, Table<SzMarketOverviewRecord> aliased) {
        this(alias, aliased, null);
    }

    private SzMarketOverview(String alias, Table<SzMarketOverviewRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Basedata.BASEDATA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<SzMarketOverviewRecord, Long> getIdentity() {
        return Keys.IDENTITY_SZ_MARKET_OVERVIEW;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<SzMarketOverviewRecord> getPrimaryKey() {
        return Keys.KEY_SZ_MARKET_OVERVIEW_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<SzMarketOverviewRecord>> getKeys() {
        return Arrays.<UniqueKey<SzMarketOverviewRecord>>asList(Keys.KEY_SZ_MARKET_OVERVIEW_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SzMarketOverview as(String alias) {
        return new SzMarketOverview(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SzMarketOverview rename(String name) {
        return new SzMarketOverview(name, null);
    }
}
