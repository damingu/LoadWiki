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
	final static int[] PAGESIZE = new int[] { 10 };

	@Autowired
	RoadmapRepo roadmaprepo;

	@Override
	public Object create(String nowuid, Roadmap map) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int nowuidnum = Integer.parseInt(nowuid);
			int uidnum = map.getUid();
			if (nowuidnum != uidnum)
				throw new RuntimeException("wrong user");

			if (roadmaprepo.insert(map) != 1)
				throw new RuntimeException("Query wrong");

		} catch (Exception e) {
			logger.error("Service create : Something wrong");
			throw e;
		}
		return result;
	}

	@Override
	public Object modify(String nowuid, Roadmap map) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int nowuidnum = Integer.parseInt(nowuid); 
			if (nowuidnum != map.getUid())
				throw new RuntimeException("wrong user");

			if (roadmaprepo.update(map) != 1)
				throw new RuntimeException("Query wrong");
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("Service modify : Something wrong");
		}
		return result;
	}

	@Override
	public Object deleteRoadmap(String nowuid, String uid, String rmorder) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int nowuidnum = Integer.parseInt(nowuid);
			int uidnum = Integer.parseInt(uid);
			if (nowuidnum != uidnum)
				throw new RuntimeException("wrong user");

			int rmordernum = Integer.parseInt(rmorder);
			if (roadmaprepo.delete(rmordernum, uidnum) != 1)
				throw new RuntimeException("Query wrong");

		} catch (Exception e) {
			logger.error("Service deleteRoadmap : Something wrong");
		}
		return result;
	}

	@Override
	public Object getRoadmapListByUid(String page, String nowuid, String uid) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int pageSt = (Integer.parseInt(page) - 1) * PAGESIZE[0];
			int uidnum = Integer.parseInt(uid);
			int nowuidnum = Integer.parseInt(nowuid);

			if (nowuidnum == uidnum)
				result.put("roadmaps", roadmaprepo.selectMyRoadmapListByUid(pageSt, PAGESIZE[0], uidnum));
			else
				result.put("roadmaps", roadmaprepo.selectOtherRoadmapListByUid(pageSt, PAGESIZE[0], uidnum));

		} catch (Exception e) {
			logger.error("Service getRoadmapListByUid : Something wrong");
		}
		return result;
	}

	@Override
	public Object getRoadmapListByRmorder(String page, String nowuid, String uid, String rmorder) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int uidnum = Integer.parseInt(uid);
			int nowuidnum = Integer.parseInt(nowuid);
			int pageSt = (Integer.parseInt(page) - 1) * PAGESIZE[0];
			int rmordernum = Integer.parseInt(rmorder);

			if (uidnum != nowuidnum)
				throw new RuntimeException("wrong user");

			result.put("roadmaps", roadmaprepo.selectRoadmapListByRmorder(pageSt, PAGESIZE[0], rmordernum, uidnum));
		} catch (Exception e) {
			logger.error("Service getRoadmapListByRmorder : Something wrong");
		}
		return result;
	}

	@Override
	public Object getRoadmap(String nowuid, String rmid) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int rmidnum = Integer.parseInt(rmid);
			int uidnum = roadmaprepo.selectUidByRmid(rmidnum);
			int nowuidnum = Integer.parseInt(nowuid);
			Object roadmap = null;

			if (nowuidnum == uidnum)
				roadmap = roadmaprepo.selectMyRoadmap(rmidnum);
			else
				roadmap = roadmaprepo.selectOtherRoadmap(rmidnum);

			if (roadmap == null)
				new RuntimeException("access denied");
			result.put("roadmaps", roadmap);
		} catch (Exception e) {
			logger.error("Service getRoadmap : Something wrong");
		}
		return result;
	}

}
