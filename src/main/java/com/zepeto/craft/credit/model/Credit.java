
package com.zepeto.craft.credit.model;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("credit")
public class Credit {
	private long playerId;
	private long paidCredit;
	private long freeCredit;

	public long getTotalCredit() {
		return paidCredit + freeCredit;
	}

}
