package com.action.insert;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.Action;
import com.action.ActionForward;
import com.dao.ActorDao;
import com.dao.PerformDao;
import com.dao.PlaceDao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.vo.Actor;
import com.vo.Member;
import com.vo.Perform;

public class InsertPerformActorAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");

		String url = "/usr/perform/redirect";

		if (member.getMemberCode() == 0) {
			url = "/usr/perform/insertSeatView";
			PerformDao perfDao = PerformDao.getInstance();
			ActorDao actorDao = ActorDao.getInstance();
			PlaceDao placeDao = PlaceDao.getInstance();
			
			// img
			String path = request.getServletContext().getRealPath("/") +"static/img";
			int size = 10 * 1024 * 1024;

			MultipartRequest multi_request = new MultipartRequest(request, path, size, "UTF-8",
					new DefaultFileRenamePolicy());

			System.out.println(multi_request.getParameter("categoryCode"));
			int categoryCode = Integer.parseInt(multi_request.getParameter("categoryCode"));
			String performName = multi_request.getParameter("performName");
			String performDetail = multi_request.getParameter("performDetail");
			String runTime = multi_request.getParameter("runTime");
			String performImg = multi_request.getFilesystemName("performPic");
			String detailImg = multi_request.getFilesystemName("detailPic");
			System.out.println(performImg);
			perfDao.insertPerform(
					new Perform(0, categoryCode, performName, performDetail, performImg, detailImg, runTime));

			int performId = perfDao.getMaxPerformId();
			String mode = multi_request.getParameter("actorInfo");
			if (mode.equals("y")) {
				String[] name = multi_request.getParameterValues("name");
				String[] part = multi_request.getParameterValues("part");
				int[] actorCode = Arrays.stream(multi_request.getParameterValues("actorCode"))
						.mapToInt(Integer::parseInt).toArray();
				for (int i = 1; i < actorCode.length; i++) {
					actorDao.insertActor(new Actor(0, performId, name[i], part[i], actorCode[i]));
				}
			}
			int placeId = Integer.parseInt(multi_request.getParameter("placeId"));
			int placeRow = placeDao.getPlaceOne(placeId).getPlaceRow();
			int placeCol = placeDao.getPlaceOne(placeId).getPlaceCol();

			request.setAttribute("performId", performId);
			request.setAttribute("placeId", placeId);
			request.setAttribute("placeRow", placeRow);
			request.setAttribute("placeCol", placeCol);
		} else {
			request.setAttribute("alertMsg", "잘못된 접근입니다.");
			request.setAttribute("replaceUrl", "./main.do");
		}

		return new ActionForward(false, url);
	}

}
