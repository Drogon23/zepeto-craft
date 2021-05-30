
package com.zepeto.craft.item.model;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("itemRequest")
public class ItemRequest {
	private long playerId;
	private long itemId;
	private int itemCount;
}
