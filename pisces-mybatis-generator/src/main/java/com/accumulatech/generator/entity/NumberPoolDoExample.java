package com.accumulatech.generator.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NumberPoolDoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NumberPoolDoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(String value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(String value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(String value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(String value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(String value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(String value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLike(String value) {
            addCriterion("number like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotLike(String value) {
            addCriterion("number not like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<String> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<String> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(String value1, String value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(String value1, String value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andRentTimeIsNull() {
            addCriterion("rent_time is null");
            return (Criteria) this;
        }

        public Criteria andRentTimeIsNotNull() {
            addCriterion("rent_time is not null");
            return (Criteria) this;
        }

        public Criteria andRentTimeEqualTo(Date value) {
            addCriterion("rent_time =", value, "rentTime");
            return (Criteria) this;
        }

        public Criteria andRentTimeNotEqualTo(Date value) {
            addCriterion("rent_time <>", value, "rentTime");
            return (Criteria) this;
        }

        public Criteria andRentTimeGreaterThan(Date value) {
            addCriterion("rent_time >", value, "rentTime");
            return (Criteria) this;
        }

        public Criteria andRentTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("rent_time >=", value, "rentTime");
            return (Criteria) this;
        }

        public Criteria andRentTimeLessThan(Date value) {
            addCriterion("rent_time <", value, "rentTime");
            return (Criteria) this;
        }

        public Criteria andRentTimeLessThanOrEqualTo(Date value) {
            addCriterion("rent_time <=", value, "rentTime");
            return (Criteria) this;
        }

        public Criteria andRentTimeIn(List<Date> values) {
            addCriterion("rent_time in", values, "rentTime");
            return (Criteria) this;
        }

        public Criteria andRentTimeNotIn(List<Date> values) {
            addCriterion("rent_time not in", values, "rentTime");
            return (Criteria) this;
        }

        public Criteria andRentTimeBetween(Date value1, Date value2) {
            addCriterion("rent_time between", value1, value2, "rentTime");
            return (Criteria) this;
        }

        public Criteria andRentTimeNotBetween(Date value1, Date value2) {
            addCriterion("rent_time not between", value1, value2, "rentTime");
            return (Criteria) this;
        }

        public Criteria andRentFeeMonthIsNull() {
            addCriterion("rent_fee_month is null");
            return (Criteria) this;
        }

        public Criteria andRentFeeMonthIsNotNull() {
            addCriterion("rent_fee_month is not null");
            return (Criteria) this;
        }

        public Criteria andRentFeeMonthEqualTo(Integer value) {
            addCriterion("rent_fee_month =", value, "rentFeeMonth");
            return (Criteria) this;
        }

        public Criteria andRentFeeMonthNotEqualTo(Integer value) {
            addCriterion("rent_fee_month <>", value, "rentFeeMonth");
            return (Criteria) this;
        }

        public Criteria andRentFeeMonthGreaterThan(Integer value) {
            addCriterion("rent_fee_month >", value, "rentFeeMonth");
            return (Criteria) this;
        }

        public Criteria andRentFeeMonthGreaterThanOrEqualTo(Integer value) {
            addCriterion("rent_fee_month >=", value, "rentFeeMonth");
            return (Criteria) this;
        }

        public Criteria andRentFeeMonthLessThan(Integer value) {
            addCriterion("rent_fee_month <", value, "rentFeeMonth");
            return (Criteria) this;
        }

        public Criteria andRentFeeMonthLessThanOrEqualTo(Integer value) {
            addCriterion("rent_fee_month <=", value, "rentFeeMonth");
            return (Criteria) this;
        }

        public Criteria andRentFeeMonthIn(List<Integer> values) {
            addCriterion("rent_fee_month in", values, "rentFeeMonth");
            return (Criteria) this;
        }

        public Criteria andRentFeeMonthNotIn(List<Integer> values) {
            addCriterion("rent_fee_month not in", values, "rentFeeMonth");
            return (Criteria) this;
        }

        public Criteria andRentFeeMonthBetween(Integer value1, Integer value2) {
            addCriterion("rent_fee_month between", value1, value2, "rentFeeMonth");
            return (Criteria) this;
        }

        public Criteria andRentFeeMonthNotBetween(Integer value1, Integer value2) {
            addCriterion("rent_fee_month not between", value1, value2, "rentFeeMonth");
            return (Criteria) this;
        }

        public Criteria andCallFeeMinuteIsNull() {
            addCriterion("call_fee_minute is null");
            return (Criteria) this;
        }

        public Criteria andCallFeeMinuteIsNotNull() {
            addCriterion("call_fee_minute is not null");
            return (Criteria) this;
        }

        public Criteria andCallFeeMinuteEqualTo(Integer value) {
            addCriterion("call_fee_minute =", value, "callFeeMinute");
            return (Criteria) this;
        }

        public Criteria andCallFeeMinuteNotEqualTo(Integer value) {
            addCriterion("call_fee_minute <>", value, "callFeeMinute");
            return (Criteria) this;
        }

        public Criteria andCallFeeMinuteGreaterThan(Integer value) {
            addCriterion("call_fee_minute >", value, "callFeeMinute");
            return (Criteria) this;
        }

        public Criteria andCallFeeMinuteGreaterThanOrEqualTo(Integer value) {
            addCriterion("call_fee_minute >=", value, "callFeeMinute");
            return (Criteria) this;
        }

        public Criteria andCallFeeMinuteLessThan(Integer value) {
            addCriterion("call_fee_minute <", value, "callFeeMinute");
            return (Criteria) this;
        }

        public Criteria andCallFeeMinuteLessThanOrEqualTo(Integer value) {
            addCriterion("call_fee_minute <=", value, "callFeeMinute");
            return (Criteria) this;
        }

        public Criteria andCallFeeMinuteIn(List<Integer> values) {
            addCriterion("call_fee_minute in", values, "callFeeMinute");
            return (Criteria) this;
        }

        public Criteria andCallFeeMinuteNotIn(List<Integer> values) {
            addCriterion("call_fee_minute not in", values, "callFeeMinute");
            return (Criteria) this;
        }

        public Criteria andCallFeeMinuteBetween(Integer value1, Integer value2) {
            addCriterion("call_fee_minute between", value1, value2, "callFeeMinute");
            return (Criteria) this;
        }

        public Criteria andCallFeeMinuteNotBetween(Integer value1, Integer value2) {
            addCriterion("call_fee_minute not between", value1, value2, "callFeeMinute");
            return (Criteria) this;
        }

        public Criteria andCallEndTimeIsNull() {
            addCriterion("call_end_time is null");
            return (Criteria) this;
        }

        public Criteria andCallEndTimeIsNotNull() {
            addCriterion("call_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andCallEndTimeEqualTo(Date value) {
            addCriterion("call_end_time =", value, "callEndTime");
            return (Criteria) this;
        }

        public Criteria andCallEndTimeNotEqualTo(Date value) {
            addCriterion("call_end_time <>", value, "callEndTime");
            return (Criteria) this;
        }

        public Criteria andCallEndTimeGreaterThan(Date value) {
            addCriterion("call_end_time >", value, "callEndTime");
            return (Criteria) this;
        }

        public Criteria andCallEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("call_end_time >=", value, "callEndTime");
            return (Criteria) this;
        }

        public Criteria andCallEndTimeLessThan(Date value) {
            addCriterion("call_end_time <", value, "callEndTime");
            return (Criteria) this;
        }

        public Criteria andCallEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("call_end_time <=", value, "callEndTime");
            return (Criteria) this;
        }

        public Criteria andCallEndTimeIn(List<Date> values) {
            addCriterion("call_end_time in", values, "callEndTime");
            return (Criteria) this;
        }

        public Criteria andCallEndTimeNotIn(List<Date> values) {
            addCriterion("call_end_time not in", values, "callEndTime");
            return (Criteria) this;
        }

        public Criteria andCallEndTimeBetween(Date value1, Date value2) {
            addCriterion("call_end_time between", value1, value2, "callEndTime");
            return (Criteria) this;
        }

        public Criteria andCallEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("call_end_time not between", value1, value2, "callEndTime");
            return (Criteria) this;
        }

        public Criteria andCallTimesIsNull() {
            addCriterion("call_times is null");
            return (Criteria) this;
        }

        public Criteria andCallTimesIsNotNull() {
            addCriterion("call_times is not null");
            return (Criteria) this;
        }

        public Criteria andCallTimesEqualTo(Integer value) {
            addCriterion("call_times =", value, "callTimes");
            return (Criteria) this;
        }

        public Criteria andCallTimesNotEqualTo(Integer value) {
            addCriterion("call_times <>", value, "callTimes");
            return (Criteria) this;
        }

        public Criteria andCallTimesGreaterThan(Integer value) {
            addCriterion("call_times >", value, "callTimes");
            return (Criteria) this;
        }

        public Criteria andCallTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("call_times >=", value, "callTimes");
            return (Criteria) this;
        }

        public Criteria andCallTimesLessThan(Integer value) {
            addCriterion("call_times <", value, "callTimes");
            return (Criteria) this;
        }

        public Criteria andCallTimesLessThanOrEqualTo(Integer value) {
            addCriterion("call_times <=", value, "callTimes");
            return (Criteria) this;
        }

        public Criteria andCallTimesIn(List<Integer> values) {
            addCriterion("call_times in", values, "callTimes");
            return (Criteria) this;
        }

        public Criteria andCallTimesNotIn(List<Integer> values) {
            addCriterion("call_times not in", values, "callTimes");
            return (Criteria) this;
        }

        public Criteria andCallTimesBetween(Integer value1, Integer value2) {
            addCriterion("call_times between", value1, value2, "callTimes");
            return (Criteria) this;
        }

        public Criteria andCallTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("call_times not between", value1, value2, "callTimes");
            return (Criteria) this;
        }

        public Criteria andCallMinutesIsNull() {
            addCriterion("call_minutes is null");
            return (Criteria) this;
        }

        public Criteria andCallMinutesIsNotNull() {
            addCriterion("call_minutes is not null");
            return (Criteria) this;
        }

        public Criteria andCallMinutesEqualTo(Integer value) {
            addCriterion("call_minutes =", value, "callMinutes");
            return (Criteria) this;
        }

        public Criteria andCallMinutesNotEqualTo(Integer value) {
            addCriterion("call_minutes <>", value, "callMinutes");
            return (Criteria) this;
        }

        public Criteria andCallMinutesGreaterThan(Integer value) {
            addCriterion("call_minutes >", value, "callMinutes");
            return (Criteria) this;
        }

        public Criteria andCallMinutesGreaterThanOrEqualTo(Integer value) {
            addCriterion("call_minutes >=", value, "callMinutes");
            return (Criteria) this;
        }

        public Criteria andCallMinutesLessThan(Integer value) {
            addCriterion("call_minutes <", value, "callMinutes");
            return (Criteria) this;
        }

        public Criteria andCallMinutesLessThanOrEqualTo(Integer value) {
            addCriterion("call_minutes <=", value, "callMinutes");
            return (Criteria) this;
        }

        public Criteria andCallMinutesIn(List<Integer> values) {
            addCriterion("call_minutes in", values, "callMinutes");
            return (Criteria) this;
        }

        public Criteria andCallMinutesNotIn(List<Integer> values) {
            addCriterion("call_minutes not in", values, "callMinutes");
            return (Criteria) this;
        }

        public Criteria andCallMinutesBetween(Integer value1, Integer value2) {
            addCriterion("call_minutes between", value1, value2, "callMinutes");
            return (Criteria) this;
        }

        public Criteria andCallMinutesNotBetween(Integer value1, Integer value2) {
            addCriterion("call_minutes not between", value1, value2, "callMinutes");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}