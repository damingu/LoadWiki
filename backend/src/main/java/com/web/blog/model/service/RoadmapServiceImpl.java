package com.web.blog.model.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.model.dto.Roadmap;
import com.web.blog.model.repo.RoadmapRepo;

@Service
public class RoadmapServiceImpl implements RoadmapService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	final static int[] PAGESIZE = new int[]{10};
	@Autowired
	RoadmapRepo roadmaprepo;

	@Override
	public Object save(Roadmap map) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (roadmaprepo.insert(map) == 1)
				result.put("msg", "success");
			else
				result.put("msg", "fail");
		} catch (NumberFormatException e) {
			logger.error("input data type error");
			result.put("msg", "fail");
		} catch (Exception e) {
			logger.error("Something wrong");
			result.put("msg", "fail");
		}
		return result;
	}

	@Override
	public Object getRoadmapListByUid(String page,String uid) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int pageSt = Integer.parseInt(page) * PAGESIZE[0];
			int pageFin = pageSt + PAGESIZE[0];
			int uidnum = Integer.parseInt(uid);
			//페이징 적용 아직 안됨
			result.put("roadmaps", roadmaprepo.selectRoadmapListByUid(uidnum));
			result.put("msg", "success");
		} catch(NumberFormatException e){
			logger.error("input data type error");
			result.put("msg", "fail");
		} catch(Exception e) {
			logger.error("Something wrong");
			result.put("msg", "fail");
		}
		return result;
	}

	@Override
	public Object getRoadmap(String rmid) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int rmidnum = Integer.parseInt(rmid);
			result.put("roadmaps", roadmaprepo.selectRoadmap(rmidnum));
			result.put("msg", "success");
		} catch(NumberFormatException e){
			logger.error("input data type error");
			result.put("msg", "fail");
		} catch(Exception e) {
			logger.error("Something wrong");
			result.put("msg", "fail");
		}
		return result;
	}

}
