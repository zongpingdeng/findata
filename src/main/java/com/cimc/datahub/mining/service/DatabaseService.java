package com.cimc.datahub.mining.service;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import com.cimc.datahub.mining.jooq.Tables;
import com.cimc.datahub.mining.jooq.tables.records.MutualMarketOverviewDailyRecord;
import com.cimc.datahub.mining.jooq.tables.records.MutualMarketTop10DailyRecord;

public class DatabaseService {

	private static final Logger log = Logger.getLogger(DatabaseService.class);

	private static DatabaseService databaseService = null;

	private DSLContext dslContext;

	private DatabaseService() {
		super();
		this.dslContext = createDSLContext();
	}

	private DSLContext createDSLContext() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/basedata", "netment",
					"sf123456");
			return DSL.using(connection, SQLDialect.MYSQL);
		} catch (Exception e) {
			log.error("Failed to create connection!!", e);
		}
		return null;
	}

	public DSLContext getDSLContex() {
		return this.dslContext;
	}

	public static synchronized DatabaseService getInstance() {
		if (databaseService == null) {
			databaseService = new DatabaseService();
		}
		return databaseService;
	}

	public void saveMutualMarketOverviewDailyRecord(MutualMarketOverviewDailyRecord record) {
		MutualMarketOverviewDailyRecord result = dslContext.newRecord(Tables.MUTUAL_MARKET_OVERVIEW_DAILY, record);
		result.insert();
	}

	public void saveMutualMarketTop10DailyRecord(MutualMarketTop10DailyRecord record) {
		MutualMarketTop10DailyRecord result = dslContext.newRecord(Tables.MUTUAL_MARKET_TOP10_DAILY, record);
		result.insert();
	}

}
