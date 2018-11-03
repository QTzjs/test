package com.happy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.happy.dao.ToKenDao;
import com.happy.model.weixin.ToKen;
import com.happy.service.ToKenService;
@Service("toKenService")
public class ToKenServiceImpl implements ToKenService {
	@Resource
	private ToKenDao tokenDao;
	public ToKenDao getTokenDao() {
		return tokenDao;
	}
	public void setTokenDao(ToKenDao tokenDao) {
		this.tokenDao = tokenDao;
	}

	public List<ToKen> findToken() {
		return tokenDao.findToken();
	}

	public int addToken(ToKen token) {
		return tokenDao.addToken(token);
	}

	public int updateToken(ToKen token) {
		return tokenDao.updateToken(token);
	}

	public void deleteToken(ToKen token) {
		tokenDao.deleteToken(token);
	}

}
