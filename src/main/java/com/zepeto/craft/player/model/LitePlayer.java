package com.zepeto.craft.player.model;

import java.util.Objects;

import com.zepeto.craft.credit.model.Credit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LitePlayer extends Player {
	private static final int ZERO = 0;

	private long playerId;
	private Credit credit;

	@Override
	public void consumeCredit(long price) {
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
