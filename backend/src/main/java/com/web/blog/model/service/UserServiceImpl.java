package com.web.blog.model.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.blog.model.dto.User;
import com.web.blog.model.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Override
	public User getInfo(String email) {
		try {
			User tmp = userRepo.select(email);
			User result = new User();
			result.setEmail(tmp.getEmail());
			result.setName(tmp.getName());
			result.setCreateDate(tmp.getCreateDate());
			return result;
		} catch (Exception e) {
			throw new RuntimeException("sql error");
		}
	}

	@Transactional
	@Override
	public int join(User user) {
		try {
			return userRepo.insert(user);
		} catch (Exception e) {
			throw new RuntimeException("sql error");
		}
	}

	@Transactional
	@Override
	public int modify(User user) {
		try {
			if(userRepo.update(user) == 1) return 1;
			else throw new RuntimeException("Non-existent user");
		} catch (Exception e) {
			throw new RuntimeException("sql error");
		}
	}

	@Transactional
	@Override
	public int withdraw(String email) {
		try {
			if(userRepo.delete(email) == 1) return 1;
			else throw new RuntimeException("Non-existent user");
		} catch (Exception e) {
			throw new RuntimeException("sql error");
		}
	}

	@Override
	public User login(User user) {
		try {
			User cur = userRepo.select(user.getEmail());
			if (!user.getPassword().equals(cur.getPassword())) 
				throw new RuntimeException("login fail");
			User result = new User();
			result.setEmail(cur.getEmail());
			result.setUid(cur.getUid());
			result.setName(cur.getName());
			result.setCreateDate(cur.getCreateDate());
			System.out.println(result);
			return result;
		} catch (Exception e) {
			throw new RuntimeException("login fail");
		}
	}

}
