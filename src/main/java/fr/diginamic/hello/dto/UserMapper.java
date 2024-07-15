package fr.diginamic.hello.dto;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

import fr.diginamic.hello.entities.UserAccount;

public class UserMapper {

	public static UserDetails toUserDetails(UserAccount userAccount) {
		return User.builder().username(userAccount.getUserName()).password(userAccount.getPassword()).authorities(userAccount.getAuthorities()).build();
	}
}
