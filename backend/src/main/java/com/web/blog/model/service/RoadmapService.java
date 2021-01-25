package com.web.blog.model.service;

import com.web.blog.model.dto.Roadmap;

public interface RoadmapService {
	
	
	//삭제 rmorder 같은거 다 날리기 나중엔 cascade로 커리큘럼까지.
	//update 새로 만들기. 이해 완료
	
	//수정할때는 rmorder를 준다 -> 그냥 집어 넣으면 된다.
	//처음 넣을때 rmorder max + 1 한 값 넣어준다. 탐색식 왜여 어째서져 왜여 ins 
	//로드맵을 킬때 rmorder는 같아야 되는거 아닌가요
	//직관과 효율
	//uid + rmorder
	//update 있긴해야되나 
	
	public Object save(Roadmap map);
	public Object getRoadmapListByUid(String page,String uid); // rmorder createdate가장나중
	public Object getRoadmap(String rmid);
	public Object getRoadmapListByRmorder(String page,String rmorder); // 추가적인 요소 끄트 ?
	
}	
