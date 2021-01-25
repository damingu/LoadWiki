package com.web.blog.model.service;

import com.web.blog.model.dto.Roadmap;

public interface RoadmapService {
	
	public Object save(Roadmap map);
	public Object modify(Roadmap map);
	public Object getRoadmapListByUid(String page,String uid); // rmorder createdate가장나중
	public Object getRoadmap(String rmid);
	public Object getRoadmapListByRmorder(String page,String rmorder); // 추가적인 요소 끄트 ?
	public Object deleteRoadmap(String rmorder);
	
}	
