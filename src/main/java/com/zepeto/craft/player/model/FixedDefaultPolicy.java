package com.zepeto.craft.player.model;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.zepeto.craft.credit.model.Credit;

@Component
public class FixedDefaultPolicy implements CreditPolicy {
	private static final int ZERO = 0;

	@Override
	public void consumeCredit(Credit credit, long price) {
		if (Objects.isNull(credit) || (credit.getTotalCredit() - price < ZERO)) {
			throw new IllegalStateException("잔고가 부족합니다.");
		}

		if (credit.getFreeCredit() >= price) {
			credit.setFreeCredit(credit.getFreeCredit() - price);
		} else {
			credit.setPaidCredit(credit.getPaidCredit() + credit.getFreeCredit() - price);
			credit.setFreeCredit(ZERO);
		}
	}
}
