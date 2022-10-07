package com.example.demo.domain.user.service;

import java.util.List;

import com.example.demo.domain.user.model.MUser;

public interface UserService {

	public void signup(MUser user);

	public List<MUser> getUsers(MUser user);

	public MUser getUserOne(String userId);

	public void updateUserOne(String userId, String password, String userName);

	public void deletUserOne(String userId);

	public MUser findLoginUser(String userId);
}
