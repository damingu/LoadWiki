package com.web.blog.model.service;

import com.web.blog.model.dto.Roadmap;

public interface RoadmapService {
	
	
	//public Object save(Map<String, Object> map);

	//public Object load(Map<String, Object> map);
	public Object save(Roadmap map);

	public Object getRoadmapListByUid(String page,String uid);
	public Object getRoadmap(String rmid);
	public Object getRoadmapListByRmorder(String page,String rmorder);
	
}	
