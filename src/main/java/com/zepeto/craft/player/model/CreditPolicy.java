package com.zepeto.craft.player.model;

import com.zepeto.craft.credit.model.Credit;

public interface CreditPolicy {

	void consumeCredit(Credit credit, long price);
}
