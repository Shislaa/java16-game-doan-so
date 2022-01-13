package cybersoft.javabackend.gamedoanso.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.javabackend.gamedoanso.util.UrlConst;

@WebServlet(name = "homeServlet", urlPatterns = "/")
public class HomeServlet extends HttpServlet {
	/*
	 * TODO: Game đoán số
	 * 
	 * Khi truy cập vào game, một số ngẫu nhiên trong khoản
	 * [1 - 1000] sẽ được sinh ra.
	 * Người dùng có nhiệm vụ đoán chính xác con số ngẫu nhiên này.
	 * 
	 * Mỗi lượt đoán nếu đoán sai, người dùng sẽ được gợi ý:
	 *  - Số vừa đoán nhỏ hơn đáp án!
	 *  - Số vừa đoán lớn hơn đáp án!
	 *  
	 * Thành tích người dùng sẽ được sắp xếp dựa trên số lần đoán.
	 * Ai có số lần đoán ít nhất đến khi đoán đúng số sẽ xếp hạng nhất.
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath() + UrlConst.GAME_LOGIN);
	}
}
