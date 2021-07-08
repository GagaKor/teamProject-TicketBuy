package com.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.PerformDao;
import com.dao.PlaceDao;
import com.dao.SchDao;
import com.vo.MainList;
import com.vo.Perform;

public class ShowMainListAction implements Action {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {	
		PerformDao performDao = PerformDao.getInstance();
		SchDao schDao = SchDao.getInstance();
		PlaceDao placeDao = PlaceDao.getInstance();
		String adm = request.getParameter("adm");
		
		List<MainList> list = new ArrayList<>();
		List<Perform> perform = performDao.getPerformAllList();
		for (int i = 0; i < perform.size(); i++) {
			int performId = perform.get(i).getPerformId();
			int placeId = schDao.getPlaceId(performId);
			String performName = perform.get(i).getPerformName();
			String performImg = perform.get(i).getPerformImg();
			String categoryName = performDao.getCategoryName(performId);
			String placeName =  placeDao.getPlaceOne(placeId).getPlaceName();
			String seasonDate = schDao.getSeasonDate(performId);
			
			list.add(new MainList(performId, placeId,performImg, performName, categoryName, placeName, seasonDate));
		}
		
		request.setAttribute("list", list);
		request.setAttribute("adm",adm);
		return new ActionForward(false, "/usr/perform/mainList");
	}

}
