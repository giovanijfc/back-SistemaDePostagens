package com.giovanijfc.sistemadepostagens.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.giovanijfc.sistemadepostagens.domain.enums.Cargo;

public class UserSS implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public UserSS() {
		
	}
	public UserSS(Integer id, String email, String senha, Set<Cargo> cargos) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = cargos.stream().map(x-> new SimpleGrantedAuthority(x.getCargos())).collect(Collectors.toList());
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public boolean hasRole(Cargo cargo) {
		return getAuthorities().contains(new SimpleGrantedAuthority(cargo.getCargos()));
	}
}
