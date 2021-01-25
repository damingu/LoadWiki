package com.web.blog.model.service;

import com.web.blog.model.dto.Roadmap;

public interface RoadmapService {
	
	public Object create(String nowuid,Roadmap map);
	public Object modify(String nowuid,Roadmap map);
	public Object getRoadmapListByUid(String page,String nowuid,String uid); // rmorder createdate가장나중
	public Object getRoadmap(String nowuid,String rmid);
	public Object getRoadmapListByRmorder(String page,String nowuid, String uid,String rmorder); // 추가적인 요소 끄트 ?
	public Object deleteRoadmap(String nowuid,String uid,String rmorder);
	
}	
