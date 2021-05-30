package com.zepeto.craft.player.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.zepeto.craft.credit.mapper.CreditMapper;
import com.zepeto.craft.credit.model.Credit;
import com.zepeto.craft.inventory.service.InventoryService;
import com.zepeto.craft.item.model.Item;
import com.zepeto.craft.item.model.ItemRequest;
import com.zepeto.craft.player.mapper.PlayerMapper;
import com.zepeto.craft.player.model.LitePlayer;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
	private final InventoryService inventoryService;
	private final CreditMapper creditMapper;
	private final PlayerMapper playerMapper;
	private final Map<Long, Item> itemMap = new HashMap<>();

	@PostConstruct
	private void postConstruct() {
		Item item1 = new Item(1L, 1000);
		Item item2 = new Item(2L, 2000);
		Item item3 = new Item(3L, 500);

		itemMap.put(1L, item1);
		itemMap.put(2L, item2);
		itemMap.put(3L, item3);
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public Credit chargeCredit(Credit creditRequest) {
		Credit credit = creditMapper.selectCredit(creditRequest.getPlayerId());

		if (Objects.isNull(credit)) {
			creditMapper.insertCredit(creditRequest);
		} else {
			credit.setPaidCredit(credit.getPaidCredit() + creditRequest.getPaidCredit());
			credit.setFreeCredit(credit.getFreeCredit() + creditRequest.getFreeCredit());
			creditMapper.updateCredit(credit);
		}

		return credit;
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void buyItem(ItemRequest itemRequest) {
		LitePlayer player = playerMapper.selectPlayer(itemRequest.getPlayerId());
		Item item = itemMap.get(itemRequest.getItemId());

		long itemTotalPrice = item.getPrice() * itemRequest.getItemCount();
		player.consumeCredit(itemTotalPrice);

		creditMapper.updateCredit(player.getCredit());
		inventoryService.addItem(itemRequest);
	}
}
