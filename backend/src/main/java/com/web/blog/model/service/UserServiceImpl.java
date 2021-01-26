package com.web.blog.model.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.blog.model.dto.User;
import com.web.blog.model.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	LoginService loginServ;

	@Override
	public Object getInfo(String email) {
		try {
			Map <String, Object> result = new HashMap<String, Object>();
			User tmp = userRepo.select(email);
			if(tmp == null) {
				result.put("msg", "fail");
				return result;
			}
			result.put("uid", tmp.getUid());
			result.put("email", tmp.getEmail());
			result.put("name", tmp.getName());
			result.put("createDate", tmp.getCreateDate());
			result.put("msg", "success");
			result.put("keywords", userRepo.selectkeyword(tmp.getUid()));
			return result;
		} catch (Exception e) {
			throw new RuntimeException("sql error");
		}
	}

	@Transactional
	@Override
	public Object join(User user) {
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			if(userRepo.insert(user) == 1) {
				result.put("msg", "success");
				int uid = userRepo.select(user.getEmail()).getUid();
				System.out.println(user.getKeyword());
				for (int i = 0; i < user.getKeyword().length; i++) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("uid", Integer.toString(uid));
					map.put("kwid", user.getKeyword()[i]);
					map.put("priority", Integer.toString(i+1));
					userRepo.insertkeyword(map);
				}
			}
			else { 
				result.put("msg", "join fail");
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException("sql error");
		}
	}

	@Transactional
	@Override
	public Object modify(User user) {
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			if(userRepo.update(user) == 1) 
				result.put("msg", "success");
			else 
				result.put("msg", "Non-existent user");
			return result;
		} catch (Exception e) {
			throw new RuntimeException("sql error");
		}
	}

	@Transactional
	@Override
	public Object withdraw(String email) {
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			if(userRepo.delete(email) == 1) 
				result.put("msg", "success");
			else 
				result.put("msg", "Non-existent user");
			return result;
		} catch (Exception e) {
			throw new RuntimeException("sql error");
		}
	}

	@Override
	public Object login(User user) {
		try {
			User cur = userRepo.select(user.getEmail());
			if (!user.getPassword().equals(cur.getPassword())) {
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("msg", "login fail");
				return result;
			}
			Map<String, Object> result = new HashMap<String, Object>();
			String token = loginServ.generate(cur);
			result.put("authorizationToken", token);
			result.put("uid", cur.getUid());
			result.put("email", cur.getEmail());
			result.put("name", cur.getName());
			result.put("createDate", cur.getCreateDate());
			result.put("msg", "SUCCESS");
			return result;
		} catch (Exception e) {
			throw new RuntimeException("login error");
		}
	}

}
