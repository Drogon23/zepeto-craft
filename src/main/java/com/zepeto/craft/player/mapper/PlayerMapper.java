package com.zepeto.craft.player.mapper;

import org.springframework.stereotype.Repository;

import com.zepeto.craft.player.model.LitePlayer;

@Repository
public interface PlayerMapper {
	LitePlayer selectPlayer(long playerId);

}
