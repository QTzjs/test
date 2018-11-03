package com.happy.dao;

import java.util.List;

import com.happy.model.weixin.ToKen;


public interface ToKenDao {
	public List<ToKen> findToken();
	public int addToken(ToKen token);
	public int updateToken(ToKen token);
	public void deleteToken(ToKen token);
}
