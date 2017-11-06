/*
 * This file is generated by jOOQ.
*/
package com.cimc.datahub.mining.jooq.tables.records;


import com.cimc.datahub.mining.jooq.tables.HkToMainland;

import java.sql.Date;
import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Row10;
import org.jooq.impl.UpdatableRecordImpl;


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
public class HkToMainlandRecord extends UpdatableRecordImpl<HkToMainlandRecord> implements Record10<Integer, String, String, String, String, Long, Double, Date, Timestamp, Timestamp> {

    private static final long serialVersionUID = 1258833645;

    /**
     * Setter for <code>basedata.hk_to_mainland.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>basedata.hk_to_mainland.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>basedata.hk_to_mainland.hk_code</code>.
     */
    public void setHkCode(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>basedata.hk_to_mainland.hk_code</code>.
     */
    public String getHkCode() {
        return (String) get(1);
    }

    /**
     * Setter for <code>basedata.hk_to_mainland.hk_name</code>.
     */
    public void setHkName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>basedata.hk_to_mainland.hk_name</code>.
     */
    public String getHkName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>basedata.hk_to_mainland.code</code>.
     */
    public void setCode(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>basedata.hk_to_mainland.code</code>.
     */
    public String getCode() {
        return (String) get(3);
    }

    /**
     * Setter for <code>basedata.hk_to_mainland.name</code>.
     */
    public void setName(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>basedata.hk_to_mainland.name</code>.
     */
    public String getName() {
        return (String) get(4);
    }

    /**
     * Setter for <code>basedata.hk_to_mainland.amount</code>.
     */
    public void setAmount(Long value) {
        set(5, value);
    }

    /**
     * Getter for <code>basedata.hk_to_mainland.amount</code>.
     */
    public Long getAmount() {
        return (Long) get(5);
    }

    /**
     * Setter for <code>basedata.hk_to_mainland.percentage</code>.
     */
    public void setPercentage(Double value) {
        set(6, value);
    }

    /**
     * Getter for <code>basedata.hk_to_mainland.percentage</code>.
     */
    public Double getPercentage() {
        return (Double) get(6);
    }

    /**
     * Setter for <code>basedata.hk_to_mainland.event_date</code>.
     */
    public void setEventDate(Date value) {
        set(7, value);
    }

    /**
     * Getter for <code>basedata.hk_to_mainland.event_date</code>.
     */
    public Date getEventDate() {
        return (Date) get(7);
    }

    /**
     * Setter for <code>basedata.hk_to_mainland.created_date</code>.
     */
    public void setCreatedDate(Timestamp value) {
        set(8, value);
    }

    /**
     * Getter for <code>basedata.hk_to_mainland.created_date</code>.
     */
    public Timestamp getCreatedDate() {
        return (Timestamp) get(8);
    }

    /**
     * Setter for <code>basedata.hk_to_mainland.modified_date</code>.
     */
    public void setModifiedDate(Timestamp value) {
        set(9, value);
    }

    /**
     * Getter for <code>basedata.hk_to_mainland.modified_date</code>.
     */
    public Timestamp getModifiedDate() {
        return (Timestamp) get(9);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record10 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row10<Integer, String, String, String, String, Long, Double, Date, Timestamp, Timestamp> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row10<Integer, String, String, String, String, Long, Double, Date, Timestamp, Timestamp> valuesRow() {
        return (Row10) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return HkToMainland.HK_TO_MAINLAND.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return HkToMainland.HK_TO_MAINLAND.HK_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return HkToMainland.HK_TO_MAINLAND.HK_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return HkToMainland.HK_TO_MAINLAND.CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return HkToMainland.HK_TO_MAINLAND.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field6() {
        return HkToMainland.HK_TO_MAINLAND.AMOUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field7() {
        return HkToMainland.HK_TO_MAINLAND.PERCENTAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Date> field8() {
        return HkToMainland.HK_TO_MAINLAND.EVENT_DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field9() {
        return HkToMainland.HK_TO_MAINLAND.CREATED_DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field10() {
        return HkToMainland.HK_TO_MAINLAND.MODIFIED_DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getHkCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getHkName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value6() {
        return getAmount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value7() {
        return getPercentage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date value8() {
        return getEventDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value9() {
        return getCreatedDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value10() {
        return getModifiedDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HkToMainlandRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HkToMainlandRecord value2(String value) {
        setHkCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HkToMainlandRecord value3(String value) {
        setHkName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HkToMainlandRecord value4(String value) {
        setCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HkToMainlandRecord value5(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HkToMainlandRecord value6(Long value) {
        setAmount(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HkToMainlandRecord value7(Double value) {
        setPercentage(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HkToMainlandRecord value8(Date value) {
        setEventDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HkToMainlandRecord value9(Timestamp value) {
        setCreatedDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HkToMainlandRecord value10(Timestamp value) {
        setModifiedDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HkToMainlandRecord values(Integer value1, String value2, String value3, String value4, String value5, Long value6, Double value7, Date value8, Timestamp value9, Timestamp value10) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached HkToMainlandRecord
     */
    public HkToMainlandRecord() {
        super(HkToMainland.HK_TO_MAINLAND);
    }

    /**
     * Create a detached, initialised HkToMainlandRecord
     */
    public HkToMainlandRecord(Integer id, String hkCode, String hkName, String code, String name, Long amount, Double percentage, Date eventDate, Timestamp createdDate, Timestamp modifiedDate) {
        super(HkToMainland.HK_TO_MAINLAND);

        set(0, id);
        set(1, hkCode);
        set(2, hkName);
        set(3, code);
        set(4, name);
        set(5, amount);
        set(6, percentage);
        set(7, eventDate);
        set(8, createdDate);
        set(9, modifiedDate);
    }
}
