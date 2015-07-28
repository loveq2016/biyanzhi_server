package com.biyanzhi.dao;

import java.util.List;

import com.biyanzhi.bean.PKVote;

public interface PKVoteDao {
	int addPKVode(PKVote vote);

	int findPKVote(PKVote vote);

	List<PKVote> getPKVoteListByPKID(int pk_id);
}
