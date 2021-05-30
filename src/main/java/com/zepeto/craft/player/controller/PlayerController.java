
package com.zepeto.craft.player.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zepeto.craft.credit.model.Credit;
import com.zepeto.craft.item.model.ItemRequest;
import com.zepeto.craft.player.service.PlayerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PlayerController {
	private final PlayerService playerService;

	@PostMapping("/api/v1/players/credits")
	public Credit chargeCredit(@RequestBody Credit creditRequest) {
		return playerService.chargeCredit(creditRequest);
	}

	@PostMapping("/api/v1/players/items")
	public String buyItem(@RequestBody ItemRequest itemRequest) {
		String message = "success";

		try {
			playerService.buyItem(itemRequest);
		} catch (Exception exception) {
			message = "fail";
		}

		return message;
	}
}
