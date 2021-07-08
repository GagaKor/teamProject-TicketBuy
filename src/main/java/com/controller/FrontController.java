package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.Action;
import com.action.ActionForward;
import com.action.BuyTicketAction;
import com.action.BuyTicketCheckAction;
import com.action.FindPlaceAction;
import com.action.FindUserAction;
import com.action.InsertViewAction;
import com.action.JoinAction;
import com.action.LoginAction;
import com.action.LogoutAction;
import com.action.MainAction;
import com.action.SeatViewAction;
import com.action.ShowDetailAction;
import com.action.ShowListAction;
import com.action.ShowMainListAction;
import com.action.UpdatePerformAction;
import com.action.UpdateViewAction;
import com.action.myTicketAction;
import com.action.delete.DeletePerformAction;
import com.action.insert.InsertPerformActorAction;
import com.action.insert.InsertPlaceAction;
import com.action.insert.InsertSchSeatAction;
import com.action.insert.InsertTicket;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		boolean isRedirect = false;

		String url = "/usr/main";
		String spath = req.getServletPath();

		Action action = null;
		ActionForward forward = null;
		System.out.println("spath : " + spath);

		if (spath.equals("/usr/main.do")) {
			action = new MainAction();
			forward = action.excute(req, resp);
		} else if (spath.equals("/usr/perform/showList.do")) {
			action = new ShowListAction();
			forward = action.excute(req, resp);
		} else if (spath.equals("/usr/perform/showMainList.do")) {
			action = new ShowMainListAction();
			forward = action.excute(req, resp);
		} else if (spath.equals("/usr/perform/detail.do")) {
			action = new ShowDetailAction();
			forward = action.excute(req, resp);
		} else if (spath.equals("/usr/perform/insertView.do")) {
			action = new InsertViewAction();
			forward = action.excute(req, resp);
		} else if (spath.equals("/usr/perform/find.do")) {
			action = new FindPlaceAction();
			forward = action.excute(req, resp);
		} else if (spath.equals("/usr/perform/insertPlace.do")) {
			action = new InsertPlaceAction();
			forward = action.excute(req, resp);
		} else if (spath.equals("/usr/perform/insertSeatView.do")) {
			action = new SeatViewAction();
			forward = action.excute(req, resp);
		} else if (spath.equals("/usr/perform/insertSchSeat.do")) {
			action = new InsertSchSeatAction();
			forward = action.excute(req, resp);
		} else if (spath.equals("/usr/perform/insertPerform.do")) {
			action = new InsertPerformActorAction();
			forward = action.excute(req, resp);
		} else if (spath.equals("/usr/perform/insertTicket.do")) {
			action = new InsertTicket();
			forward = action.excute(req, resp);
		} else if (spath.equals("/usr/perform/deletePerform.do")) {
			action = new DeletePerformAction();
			forward = action.excute(req, resp);
		} else if (spath.equals("/usr/perform/updatePerformView.do")) {
			action = new UpdateViewAction();
			forward = action.excute(req, resp);
		} else if (spath.equals("/usr/perform/updatePerform.do")) {
			action = new UpdatePerformAction();
			forward = action.excute(req, resp);
		}else if (spath.equals("/usr/perform/buyCheck.do")) {
			action = new BuyTicketCheckAction();
			forward = action.excute(req, resp);
		}else if(spath.equals("/usr/perform/buyTicket.do")){
			action = new BuyTicketAction();
			forward = action.excute(req, resp);
		}else if (spath.equals("/usr/login.do")) {
			action = new LoginAction();
			forward = action.excute(req, resp);
		} else if (spath.equals("/usr/logout.do")) {
			action = new LogoutAction();
			forward = action.excute(req, resp);
		}else if (spath.equals("/usr/join.do")) {
			action = new JoinAction();
			forward = action.excute(req, resp);
		}else if (spath.equals("/usr/findUser.do")) {
			action = new FindUserAction();
			forward = action.excute(req, resp);
		}else if (spath.equals("/usr/myTicket.do")) {
			action = new myTicketAction();
			forward = action.excute(req, resp);
		}

		if (forward != null) {
			isRedirect = forward.isRedirect();
			url = forward.getUrl();
		}

		if (isRedirect) {
			resp.sendRedirect(url);
		} else {
			RequestDispatcher rd = req.getRequestDispatcher(url + ".jsp");
			rd.forward(req, resp);
		}
	}

}
