package com.lotteryCommend.service.system;

import com.lotteryCommend.exception.LotteryException;
import com.lotteryCommend.model.user.User;

public interface UserManager {

	User findUserByUsername(String username) throws LotteryException;
	
}
