package com.zepeto.craft.player.service;

import com.zepeto.craft.credit.model.Credit;
import com.zepeto.craft.item.model.ItemRequest;

public interface PlayerService {

	Credit chargeCredit(Credit creditRequest);

	void buyItem(ItemRequest itemRequest);

}
