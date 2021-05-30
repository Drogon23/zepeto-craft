package com.zepeto.craft.player.service;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.assertj.core.util.Maps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.zepeto.craft.credit.mapper.CreditMapper;
import com.zepeto.craft.credit.model.Credit;
import com.zepeto.craft.inventory.service.InventoryService;
import com.zepeto.craft.item.model.Item;
import com.zepeto.craft.item.model.ItemRequest;
import com.zepeto.craft.player.model.FixedDefaultPolicy;
import com.zepeto.craft.player.model.Player;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {
	@Mock
	private CreditMapper creditMapper;
	@Mock
	private InventoryService inventoryService;

	@InjectMocks
	private PlayerServiceImpl playerService;

	@BeforeEach
	void init() {
		Item item = new Item(1L, 1000);
		Map<Long, Item> itemMap = Maps.newHashMap(1L, item);
		ReflectionTestUtils.setField(playerService, "itemMap", itemMap);
		ReflectionTestUtils.setField(playerService, "creditPolicy", new FixedDefaultPolicy());
	}

	@Test
	@DisplayName("충전된 정보가 없음")
	void chargeCredit_notExists() {
		Credit credit = new Credit();
		credit.setPlayerId(1L);

		when(creditMapper.selectCredit(anyLong())).thenReturn(null);

		playerService.chargeCredit(credit);

		verify(creditMapper).selectCredit(anyLong());
		verify(creditMapper).insertCredit(any());
		verify(creditMapper, never()).updateCredit(any());
	}

	@Test
	@DisplayName("충전된 정보 존재")
	void chargeCredit_charge() {
		Credit credit = new Credit();
		credit.setPlayerId(1L);
		credit.setPaidCredit(1L);
		credit.setFreeCredit(1L);

		when(creditMapper.selectCredit(anyLong())).thenReturn(new Credit());

		playerService.chargeCredit(credit);

		verify(creditMapper).selectCredit(anyLong());
		verify(creditMapper, never()).insertCredit(any());
		verify(creditMapper).updateCredit(any());
	}

	@Test
	@DisplayName("아이템 구매- 잔고 부족")
	void buyItem_notEnoughCredit() {
		ItemRequest itemRequest = new ItemRequest();
		itemRequest.setPlayerId(1L);
		itemRequest.setItemId(1L);
		itemRequest.setItemCount(1);

		Credit credit = new Credit();
		when(creditMapper.selectCredit(anyLong())).thenReturn(credit);

		assertThrows(IllegalStateException.class, () -> playerService.buyItem(itemRequest));

		verify(creditMapper).selectCredit(anyLong());
		verify(creditMapper, never()).updateCredit(any());
		verify(inventoryService, never()).addItem(any());
	}

	@Test
	@DisplayName("아이템 구매 성공")
	void buyItem_success() {
		ItemRequest itemRequest = new ItemRequest();
		itemRequest.setPlayerId(1L);
		itemRequest.setItemId(1L);
		itemRequest.setItemCount(1);

		Credit credit = new Credit();
		credit.setFreeCredit(1000);

		when(creditMapper.selectCredit(anyLong())).thenReturn(credit);

		playerService.buyItem(itemRequest);

		assertThat(0L, is(credit.getTotalCredit()));

		verify(creditMapper).selectCredit(anyLong());
		verify(creditMapper).updateCredit(any());
		verify(inventoryService).addItem(any());
	}
}