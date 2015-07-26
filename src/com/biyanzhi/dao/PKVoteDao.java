package com.biyanzhi.dao;

import com.biyanzhi.bean.PKVote;

public interface PKVoteDao {
	int addPKVode(PKVote vote);

	int findPKVote(PKVote vote);
}
