package cybersoft.javabackend.gamedoanso.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.javabackend.gamedoanso.model.GameRecord;
import cybersoft.javabackend.gamedoanso.model.Player;
import cybersoft.javabackend.gamedoanso.service.GameService;
import cybersoft.javabackend.gamedoanso.util.JspConst;
import cybersoft.javabackend.gamedoanso.util.UrlConst;

@WebServlet(name = "gameServlet", urlPatterns = {
		UrlConst.GAME_ROOT,
		UrlConst.GAME_PLAY,
		UrlConst.GAME_LOGIN
})
public class GameServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4241740329722099042L;

	private GameService service;
	
	@Override
	public void init() throws ServletException {
		super.init();
		service = new GameService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch(path) {
		case UrlConst.GAME_PLAY: // when player logout
			req.getSession(false).invalidate();
			resp.sendRedirect(req.getContextPath() + UrlConst.GAME_LOGIN);
			break;
		default:
			req.getRequestDispatcher(JspConst.GAME_LOGIN)
			.forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		String path = req.getServletPath();
		String message;
		String message_2;
		int recordId, tryNumber;
		GameRecord record;
		
		switch (path) {
		case UrlConst.GAME_LOGIN: // load game
			startGame(req, resp);
			break;
		case UrlConst.GAME_PLAY: // when user input a try
			if( req.getParameterValues("Retry") != null) {
				System.out.println("Retry worked");
				startGame(req, resp);
			}
			else {
				recordId = Integer.parseInt(req.getParameter("recordId"));
				tryNumber = Integer.parseInt(req.getParameter("tryNumber"));
				
				record = service.playGame(recordId, tryNumber);
				
				if (record.getIsFinished()) {
					message = "Ch??c m???ng b???n ???? ??o??n ch??nh x??c!";
					message_2 = "(?????????????????????)???(?????????????????????)??? Omedetou (?????????????????????)???(?????????????????????)???";
				} else {
					message = getHint(record.getNumber(), tryNumber);
					message_2 = "Nh???p s??? mu???n ??o??n";
				}
				
				req.setAttribute("message", message);
				req.setAttribute("message_2", message_2);
				req.setAttribute("record", record);
				
				req.getRequestDispatcher(JspConst.GAME_PLAY)
					.forward(req, resp);
			}
			break;
		}
		
		
	}

	private void startGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String message;
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		message = "L???n ?????u ti??n h??y nh???p con s??? m?? b???n c???m th???y may m???n!";
		req.setAttribute("message", message);
		
		loadGame(req, resp, username, password);
	}

	private String getHint(int number, int tryNumber) {
		if(number > tryNumber)
			return "S??? v???a ??o??n nh??? h??n k???t qu???!";
		return "S??? v???a ??o??n l???n h??n k???t qu???!";
	}

	private void loadGame(HttpServletRequest req, HttpServletResponse resp, String username, String password)
			throws ServletException, IOException {
		GameRecord record = service.loadGame(username, password);
		req.setAttribute("record", record);
		
		req.getRequestDispatcher(JspConst.GAME_PLAY)
			.forward(req, resp);
	};
}
