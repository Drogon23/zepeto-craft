/*
 * Copyright 2021 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zepeto.craft.inventory.model;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("inventoryItem")
public class InventoryItem {
	private long playerId;
	private long itemId;
	private int itemCount;
}
