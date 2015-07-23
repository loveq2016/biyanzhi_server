package com.biyanzhi.dao;

import java.util.List;

import com.biyanzhi.bean.PK;

public interface PKDao {
	int addPK(PK pk);

	List<PK> getPKList(String pk_time);
}
