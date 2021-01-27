package com.web.blog.model.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.web.blog.model.dto.FileInfoDto;
import com.web.blog.model.dto.User;
import com.web.blog.model.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	LoginService loginServ;

	@Autowired
	ServletContext servletContext;
	
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

	@Override
	public void upload(MultipartFile[] files, Model model, HttpSession session) throws Exception, IOException {
		String realPath = servletContext.getRealPath("/upload");
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		//String saveFolder = realPath + File.separator + today;
		String saveFolder = File.separator + today;
		System.out.println(saveFolder);
		File folder = new File(saveFolder);
		if(!folder.exists())
			folder.mkdirs();
		List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
		for (MultipartFile mfile : files) {
			FileInfoDto fileInfoDto = new FileInfoDto();
			String originalFileName = mfile.getOriginalFilename();
			if (!originalFileName.isEmpty()) {
				String saveFileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf('.'));
				fileInfoDto.setSaveFolder(today);
				fileInfoDto.setOriginFile(originalFileName);
				fileInfoDto.setSaveFile(saveFileName);
				System.out.println(mfile.getOriginalFilename() + "   " + saveFileName);
				mfile.transferTo(new File(folder, saveFileName));
			}
			fileInfos.add(fileInfoDto);
		}
		
	}
}
