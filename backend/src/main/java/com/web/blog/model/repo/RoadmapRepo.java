package com.web.blog.model.repo;

import org.springframework.stereotype.Repository;

import com.web.blog.model.dto.Roadmap;

@Repository
public interface RoadmapRepo {
	public int insert(Roadmap map);
	public int update(Roadmap map);

	public int selectUidByRmid(int rmid);
	
	public Roadmap[] selectMyRoadmapListByUid(int page,int cnt,int uid);
	public Roadmap[] selectOtherRoadmapListByUid(int page,int cnt,int uid);
	public Roadmap selectMyRoadmap(int rmid);
	public Roadmap selectOtherRoadmap(int rmid);
	public Roadmap[] selectRoadmapListByRmorder(int page, int cnt,int rmorder,int uid);
	public int delete(int uid,int rmorder);
}
