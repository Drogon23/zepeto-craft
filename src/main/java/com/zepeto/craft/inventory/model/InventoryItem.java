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
