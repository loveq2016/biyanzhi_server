package com.biyanzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biyanzhi.bean.PK;

public interface PKDao {
	int addPK(PK pk);

	List<PK> getPKList(String pk_time);

	List<PK> loadMorePKList(String pk_time);// 上拉加载更多

	int upDatePK2(@Param("pk_id") int pk_id,
			@Param("pk2_user_id") int pk2_user_id,
			@Param("pk2_user_picture") String pk2_user_picture);

	int upDatePK2TicketCount(@Param("pk_id") int pk_id,
			@Param("pk2_ticket_count") int pk2_ticket_count);

	int upDatePK1TicketCount(@Param("pk_id") int pk_id,
			@Param("pk1_ticket_count") int pk1_ticket_count);

	int upDatePKState(@Param("pk_id") int pk_id,
			@Param("pk_state") int pk_state,
			@Param("pk_winer_user_id") int pk_winer_user_id,
			@Param("pk_finish_time") String pk_finish_time);

	List<PK> getPKIngList(String pk_time);

	List<PK> loadPKIngMorePKList(String pk_time);

	List<PK> getPKFinishedList(String pk_time);

	List<PK> loadPKFinishedMorePKList(String pk_time);

	PK getPKByPKID(int pk_id);

	List<PK> getPKListByUserID(@Param("page") int page,
			@Param("user_id") int user_id);

	// List<PK> getPKListByUserID(@Param("pk_finish_time") String pk_time,
	// @Param("user_id") int user_id);

	List<PK> loadMorePKListByUserID(@Param("pk_finish_time") String pk_time,
			@Param("user_id") int user_id);
}
