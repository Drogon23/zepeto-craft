/*
 * Copyright 2021 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zepeto.craft.player.model;

import org.springframework.stereotype.Component;

import com.zepeto.craft.credit.model.Credit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Player {
	private final CreditPolicy creditPolicy;

	private long playerId;
	private Credit credit;

	public void consumeCredit(long price) {
		creditPolicy.consumeCredit(credit, price);
	}

}
