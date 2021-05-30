package com.zepeto.craft.inventory.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zepeto.craft.inventory.model.InventoryItem;
import com.zepeto.craft.item.model.ItemRequest;

@Repository
public interface InventoryMapper {
	InventoryItem selectItem(@Param("playerId") long playerId, @Param("itemId") long itemId);

	void insertItem(ItemRequest itemRequest);

	void updateItem(ItemRequest itemRequest);
}
