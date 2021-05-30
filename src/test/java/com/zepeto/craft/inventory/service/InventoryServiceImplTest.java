package com.zepeto.craft.inventory.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zepeto.craft.inventory.mapper.InventoryMapper;
import com.zepeto.craft.inventory.model.InventoryItem;
import com.zepeto.craft.item.model.ItemRequest;

@ExtendWith(MockitoExtension.class)
class InventoryServiceImplTest {
	@Mock
	private InventoryMapper inventoryMapper;

	@InjectMocks
	private InventoryServiceImpl inventoryService;

	@Test
	@DisplayName("아이템 추가 - 인벤토리에 없음")
	void addItem_notExists() {
		ItemRequest itemRequest = new ItemRequest();
		itemRequest.setPlayerId(1L);
		itemRequest.setItemId(1L);

		when(inventoryMapper.selectItem(anyLong(), anyLong())).thenReturn(null);

		inventoryService.addItem(itemRequest);

		verify(inventoryMapper).selectItem(anyLong(), anyLong());
		verify(inventoryMapper).insertItem(any());
		verify(inventoryMapper, never()).updateItem(any());
	}

	@Test
	@DisplayName("아이템 추가 - 인벤토리에 있음")
	void addItem_exists() {
		ItemRequest itemRequest = new ItemRequest();
		itemRequest.setPlayerId(1L);
		itemRequest.setItemId(1L);
		itemRequest.setItemCount(1);

		InventoryItem inventoryItem = new InventoryItem();
		inventoryItem.setItemCount(1);

		when(inventoryMapper.selectItem(anyLong(), anyLong())).thenReturn(inventoryItem);

		inventoryService.addItem(itemRequest);

		verify(inventoryMapper).selectItem(anyLong(), anyLong());
		verify(inventoryMapper, never()).insertItem(any());
		verify(inventoryMapper).updateItem(any());
	}
}