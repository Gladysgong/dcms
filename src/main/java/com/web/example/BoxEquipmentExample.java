package com.web.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BoxEquipmentExample {
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database table t_b_box_equipment
	 *
	 * @mbggenerated
	 */
	protected String orderByClause;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database table t_b_box_equipment
	 *
	 * @mbggenerated
	 */
	protected boolean distinct;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database table t_b_box_equipment
	 *
	 * @mbggenerated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_b_box_equipment
	 *
	 * @mbggenerated
	 */
	public BoxEquipmentExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_b_box_equipment
	 *
	 * @mbggenerated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_b_box_equipment
	 *
	 * @mbggenerated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_b_box_equipment
	 *
	 * @mbggenerated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_b_box_equipment
	 *
	 * @mbggenerated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_b_box_equipment
	 *
	 * @mbggenerated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_b_box_equipment
	 *
	 * @mbggenerated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_b_box_equipment
	 *
	 * @mbggenerated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_b_box_equipment
	 *
	 * @mbggenerated
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_b_box_equipment
	 *
	 * @mbggenerated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_b_box_equipment
	 *
	 * @mbggenerated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to
	 * the database table t_b_box_equipment
	 *
	 * @mbggenerated
	 */
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

		public Criteria andIdEqualTo(String value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(String value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(String value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(String value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(String value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(String value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLike(String value) {
			addCriterion("id like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotLike(String value) {
			addCriterion("id not like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<String> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<String> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(String value1, String value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(String value1, String value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdIsNull() {
			addCriterion("equipment_id is null");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdIsNotNull() {
			addCriterion("equipment_id is not null");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdEqualTo(String value) {
			addCriterion("equipment_id =", value, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdNotEqualTo(String value) {
			addCriterion("equipment_id <>", value, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdGreaterThan(String value) {
			addCriterion("equipment_id >", value, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdGreaterThanOrEqualTo(String value) {
			addCriterion("equipment_id >=", value, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdLessThan(String value) {
			addCriterion("equipment_id <", value, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdLessThanOrEqualTo(String value) {
			addCriterion("equipment_id <=", value, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdLike(String value) {
			addCriterion("equipment_id like", value, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdNotLike(String value) {
			addCriterion("equipment_id not like", value, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdIn(List<String> values) {
			addCriterion("equipment_id in", values, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdNotIn(List<String> values) {
			addCriterion("equipment_id not in", values, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdBetween(String value1, String value2) {
			addCriterion("equipment_id between", value1, value2, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andEquipmentIdNotBetween(String value1, String value2) {
			addCriterion("equipment_id not between", value1, value2, "equipmentId");
			return (Criteria) this;
		}

		public Criteria andCabinetIdIsNull() {
			addCriterion("cabinet_id is null");
			return (Criteria) this;
		}

		public Criteria andCabinetIdIsNotNull() {
			addCriterion("cabinet_id is not null");
			return (Criteria) this;
		}

		public Criteria andCabinetIdEqualTo(String value) {
			addCriterion("cabinet_id =", value, "cabinetId");
			return (Criteria) this;
		}

		public Criteria andCabinetIdNotEqualTo(String value) {
			addCriterion("cabinet_id <>", value, "cabinetId");
			return (Criteria) this;
		}

		public Criteria andCabinetIdGreaterThan(String value) {
			addCriterion("cabinet_id >", value, "cabinetId");
			return (Criteria) this;
		}

		public Criteria andCabinetIdGreaterThanOrEqualTo(String value) {
			addCriterion("cabinet_id >=", value, "cabinetId");
			return (Criteria) this;
		}

		public Criteria andCabinetIdLessThan(String value) {
			addCriterion("cabinet_id <", value, "cabinetId");
			return (Criteria) this;
		}

		public Criteria andCabinetIdLessThanOrEqualTo(String value) {
			addCriterion("cabinet_id <=", value, "cabinetId");
			return (Criteria) this;
		}

		public Criteria andCabinetIdLike(String value) {
			addCriterion("cabinet_id like", value, "cabinetId");
			return (Criteria) this;
		}

		public Criteria andCabinetIdNotLike(String value) {
			addCriterion("cabinet_id not like", value, "cabinetId");
			return (Criteria) this;
		}

		public Criteria andCabinetIdIn(List<String> values) {
			addCriterion("cabinet_id in", values, "cabinetId");
			return (Criteria) this;
		}

		public Criteria andCabinetIdNotIn(List<String> values) {
			addCriterion("cabinet_id not in", values, "cabinetId");
			return (Criteria) this;
		}

		public Criteria andCabinetIdBetween(String value1, String value2) {
			addCriterion("cabinet_id between", value1, value2, "cabinetId");
			return (Criteria) this;
		}

		public Criteria andCabinetIdNotBetween(String value1, String value2) {
			addCriterion("cabinet_id not between", value1, value2, "cabinetId");
			return (Criteria) this;
		}

		public Criteria andPosIsNull() {
			addCriterion("pos is null");
			return (Criteria) this;
		}

		public Criteria andPosIsNotNull() {
			addCriterion("pos is not null");
			return (Criteria) this;
		}

		public Criteria andPosEqualTo(Byte value) {
			addCriterion("pos =", value, "pos");
			return (Criteria) this;
		}

		public Criteria andPosNotEqualTo(Byte value) {
			addCriterion("pos <>", value, "pos");
			return (Criteria) this;
		}

		public Criteria andPosGreaterThan(Byte value) {
			addCriterion("pos >", value, "pos");
			return (Criteria) this;
		}

		public Criteria andPosGreaterThanOrEqualTo(Byte value) {
			addCriterion("pos >=", value, "pos");
			return (Criteria) this;
		}

		public Criteria andPosLessThan(Byte value) {
			addCriterion("pos <", value, "pos");
			return (Criteria) this;
		}

		public Criteria andPosLessThanOrEqualTo(Byte value) {
			addCriterion("pos <=", value, "pos");
			return (Criteria) this;
		}

		public Criteria andPosIn(List<Byte> values) {
			addCriterion("pos in", values, "pos");
			return (Criteria) this;
		}

		public Criteria andPosNotIn(List<Byte> values) {
			addCriterion("pos not in", values, "pos");
			return (Criteria) this;
		}

		public Criteria andPosBetween(Byte value1, Byte value2) {
			addCriterion("pos between", value1, value2, "pos");
			return (Criteria) this;
		}

		public Criteria andPosNotBetween(Byte value1, Byte value2) {
			addCriterion("pos not between", value1, value2, "pos");
			return (Criteria) this;
		}

		public Criteria andDirectionIsNull() {
			addCriterion("direction is null");
			return (Criteria) this;
		}

		public Criteria andDirectionIsNotNull() {
			addCriterion("direction is not null");
			return (Criteria) this;
		}

		public Criteria andDirectionEqualTo(Boolean value) {
			addCriterion("direction =", value, "direction");
			return (Criteria) this;
		}

		public Criteria andDirectionNotEqualTo(Boolean value) {
			addCriterion("direction <>", value, "direction");
			return (Criteria) this;
		}

		public Criteria andDirectionGreaterThan(Boolean value) {
			addCriterion("direction >", value, "direction");
			return (Criteria) this;
		}

		public Criteria andDirectionGreaterThanOrEqualTo(Boolean value) {
			addCriterion("direction >=", value, "direction");
			return (Criteria) this;
		}

		public Criteria andDirectionLessThan(Boolean value) {
			addCriterion("direction <", value, "direction");
			return (Criteria) this;
		}

		public Criteria andDirectionLessThanOrEqualTo(Boolean value) {
			addCriterion("direction <=", value, "direction");
			return (Criteria) this;
		}

		public Criteria andDirectionIn(List<Boolean> values) {
			addCriterion("direction in", values, "direction");
			return (Criteria) this;
		}

		public Criteria andDirectionNotIn(List<Boolean> values) {
			addCriterion("direction not in", values, "direction");
			return (Criteria) this;
		}

		public Criteria andDirectionBetween(Boolean value1, Boolean value2) {
			addCriterion("direction between", value1, value2, "direction");
			return (Criteria) this;
		}

		public Criteria andDirectionNotBetween(Boolean value1, Boolean value2) {
			addCriterion("direction not between", value1, value2, "direction");
			return (Criteria) this;
		}

		public Criteria andConfirmedIsNull() {
			addCriterion("confirmed is null");
			return (Criteria) this;
		}

		public Criteria andConfirmedIsNotNull() {
			addCriterion("confirmed is not null");
			return (Criteria) this;
		}

		public Criteria andConfirmedEqualTo(Integer value) {
			addCriterion("confirmed =", value, "confirmed");
			return (Criteria) this;
		}

		public Criteria andConfirmedNotEqualTo(Integer value) {
			addCriterion("confirmed <>", value, "confirmed");
			return (Criteria) this;
		}

		public Criteria andConfirmedGreaterThan(Integer value) {
			addCriterion("confirmed >", value, "confirmed");
			return (Criteria) this;
		}

		public Criteria andConfirmedGreaterThanOrEqualTo(Integer value) {
			addCriterion("confirmed >=", value, "confirmed");
			return (Criteria) this;
		}

		public Criteria andConfirmedLessThan(Integer value) {
			addCriterion("confirmed <", value, "confirmed");
			return (Criteria) this;
		}

		public Criteria andConfirmedLessThanOrEqualTo(Integer value) {
			addCriterion("confirmed <=", value, "confirmed");
			return (Criteria) this;
		}

		public Criteria andConfirmedIn(List<Integer> values) {
			addCriterion("confirmed in", values, "confirmed");
			return (Criteria) this;
		}

		public Criteria andConfirmedNotIn(List<Integer> values) {
			addCriterion("confirmed not in", values, "confirmed");
			return (Criteria) this;
		}

		public Criteria andConfirmedBetween(Integer value1, Integer value2) {
			addCriterion("confirmed between", value1, value2, "confirmed");
			return (Criteria) this;
		}

		public Criteria andConfirmedNotBetween(Integer value1, Integer value2) {
			addCriterion("confirmed not between", value1, value2, "confirmed");
			return (Criteria) this;
		}

		public Criteria andCreateDateIsNull() {
			addCriterion("create_date is null");
			return (Criteria) this;
		}

		public Criteria andCreateDateIsNotNull() {
			addCriterion("create_date is not null");
			return (Criteria) this;
		}

		public Criteria andCreateDateEqualTo(Date value) {
			addCriterion("create_date =", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotEqualTo(Date value) {
			addCriterion("create_date <>", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateGreaterThan(Date value) {
			addCriterion("create_date >", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
			addCriterion("create_date >=", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateLessThan(Date value) {
			addCriterion("create_date <", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateLessThanOrEqualTo(Date value) {
			addCriterion("create_date <=", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateIn(List<Date> values) {
			addCriterion("create_date in", values, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotIn(List<Date> values) {
			addCriterion("create_date not in", values, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateBetween(Date value1, Date value2) {
			addCriterion("create_date between", value1, value2, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotBetween(Date value1, Date value2) {
			addCriterion("create_date not between", value1, value2, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateNameIsNull() {
			addCriterion("create_name is null");
			return (Criteria) this;
		}

		public Criteria andCreateNameIsNotNull() {
			addCriterion("create_name is not null");
			return (Criteria) this;
		}

		public Criteria andCreateNameEqualTo(String value) {
			addCriterion("create_name =", value, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameNotEqualTo(String value) {
			addCriterion("create_name <>", value, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameGreaterThan(String value) {
			addCriterion("create_name >", value, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameGreaterThanOrEqualTo(String value) {
			addCriterion("create_name >=", value, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameLessThan(String value) {
			addCriterion("create_name <", value, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameLessThanOrEqualTo(String value) {
			addCriterion("create_name <=", value, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameLike(String value) {
			addCriterion("create_name like", value, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameNotLike(String value) {
			addCriterion("create_name not like", value, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameIn(List<String> values) {
			addCriterion("create_name in", values, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameNotIn(List<String> values) {
			addCriterion("create_name not in", values, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameBetween(String value1, String value2) {
			addCriterion("create_name between", value1, value2, "createName");
			return (Criteria) this;
		}

		public Criteria andCreateNameNotBetween(String value1, String value2) {
			addCriterion("create_name not between", value1, value2, "createName");
			return (Criteria) this;
		}

		public Criteria andUpdateDateIsNull() {
			addCriterion("update_date is null");
			return (Criteria) this;
		}

		public Criteria andUpdateDateIsNotNull() {
			addCriterion("update_date is not null");
			return (Criteria) this;
		}

		public Criteria andUpdateDateEqualTo(Date value) {
			addCriterion("update_date =", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateNotEqualTo(Date value) {
			addCriterion("update_date <>", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateGreaterThan(Date value) {
			addCriterion("update_date >", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
			addCriterion("update_date >=", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateLessThan(Date value) {
			addCriterion("update_date <", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
			addCriterion("update_date <=", value, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateIn(List<Date> values) {
			addCriterion("update_date in", values, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateNotIn(List<Date> values) {
			addCriterion("update_date not in", values, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateBetween(Date value1, Date value2) {
			addCriterion("update_date between", value1, value2, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
			addCriterion("update_date not between", value1, value2, "updateDate");
			return (Criteria) this;
		}

		public Criteria andUpdateNameIsNull() {
			addCriterion("update_name is null");
			return (Criteria) this;
		}

		public Criteria andUpdateNameIsNotNull() {
			addCriterion("update_name is not null");
			return (Criteria) this;
		}

		public Criteria andUpdateNameEqualTo(String value) {
			addCriterion("update_name =", value, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameNotEqualTo(String value) {
			addCriterion("update_name <>", value, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameGreaterThan(String value) {
			addCriterion("update_name >", value, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameGreaterThanOrEqualTo(String value) {
			addCriterion("update_name >=", value, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameLessThan(String value) {
			addCriterion("update_name <", value, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameLessThanOrEqualTo(String value) {
			addCriterion("update_name <=", value, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameLike(String value) {
			addCriterion("update_name like", value, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameNotLike(String value) {
			addCriterion("update_name not like", value, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameIn(List<String> values) {
			addCriterion("update_name in", values, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameNotIn(List<String> values) {
			addCriterion("update_name not in", values, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameBetween(String value1, String value2) {
			addCriterion("update_name between", value1, value2, "updateName");
			return (Criteria) this;
		}

		public Criteria andUpdateNameNotBetween(String value1, String value2) {
			addCriterion("update_name not between", value1, value2, "updateName");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to
	 * the database table t_b_box_equipment
	 *
	 * @mbggenerated do_not_delete_during_merge
	 */
	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to
	 * the database table t_b_box_equipment
	 *
	 * @mbggenerated
	 */
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