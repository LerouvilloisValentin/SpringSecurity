package fr.diginamic.hello.entities;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserAccount {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;
  private String userName;
  private String password;
  @ElementCollection(fetch=FetchType.EAGER)
  private List<GrantedAuthority> authorities;
 
  
public UserAccount() {
	super();
	
}

public UserAccount(String userName, String password, String role) {
	GrantedAuthority roleAuthority = new SimpleGrantedAuthority(role);
	authorities = new ArrayList<>();
	authorities.add(roleAuthority);
	this.userName = userName;
	this.password = password;
}

public Integer getId() {
	return id;
}

public String getUserName() {
	return userName;
}

public String getPassword() {
	return password;
}

public List<GrantedAuthority> getAuthorities() {
	return authorities;
}

public void setId(Integer id) {
	this.id = id;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public void setPassword(String password) {
	this.password = password;
}

public void setAuthorities(List<GrantedAuthority> authorities) {
	this.authorities = authorities;
}
  
  
}
