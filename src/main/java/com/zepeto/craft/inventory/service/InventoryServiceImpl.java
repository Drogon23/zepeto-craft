package com.zepeto.craft.inventory.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.zepeto.craft.inventory.mapper.InventoryMapper;
import com.zepeto.craft.inventory.model.InventoryItem;
import com.zepeto.craft.item.model.ItemRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
	private final InventoryMapper inventoryMapper;

	@Override
	public void addItem(ItemRequest itemRequest) {
		InventoryItem inventoryItem = inventoryMapper.selectItem(itemRequest.getPlayerId(), itemRequest.getItemId());

		if (Objects.isNull(inventoryItem)) {
			inventoryMapper.insertItem(itemRequest);
		} else {
			itemRequest.setItemCount(inventoryItem.getItemCount() + itemRequest.getItemCount());
			inventoryMapper.updateItem(itemRequest);
		}
	}
}
