package com.web.blog.model.repo;

import org.springframework.stereotype.Repository;

import com.web.blog.model.dto.Roadmap;

@Repository
public interface RoadmapRepo {
	public int insert(Roadmap map);
	public Roadmap[] selectRoadmapListByUid(int uid);
	public Roadmap selectRoadmap(int rmid);

}
