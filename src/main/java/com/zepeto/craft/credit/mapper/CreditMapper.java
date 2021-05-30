package com.zepeto.craft.credit.mapper;

import org.springframework.stereotype.Repository;

import com.zepeto.craft.credit.model.Credit;

@Repository
public interface CreditMapper {
	Credit selectCredit(long playerId);

	void insertCredit(Credit credit);

	void updateCredit(Credit credit);
}
