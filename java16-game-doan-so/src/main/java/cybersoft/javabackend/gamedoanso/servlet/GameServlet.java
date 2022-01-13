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
					message = "Chúc mừng bạn đã đoán chính xác!";
					message_2 = "(づ｡◕‿‿◕｡)づ(づ｡◕‿‿◕｡)づ Omedetou (づ｡◕‿‿◕｡)づ(づ｡◕‿‿◕｡)づ";
				} else {
					message = getHint(record.getNumber(), tryNumber);
					message_2 = "Nhập số muốn đoán";
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
		
		message = "Lần đầu tiên hãy nhập con số mà bạn cảm thấy may mắn!";
		req.setAttribute("message", message);
		
		loadGame(req, resp, username, password);
	}

	private String getHint(int number, int tryNumber) {
		if(number > tryNumber)
			return "Số vừa đoán nhỏ hơn kết quả!";
		return "Số vừa đoán lớn hơn kết quả!";
	}

	private void loadGame(HttpServletRequest req, HttpServletResponse resp, String username, String password)
			throws ServletException, IOException {
		GameRecord record = service.loadGame(username, password);
		req.setAttribute("record", record);
		
		req.getRequestDispatcher(JspConst.GAME_PLAY)
			.forward(req, resp);
	};
}
