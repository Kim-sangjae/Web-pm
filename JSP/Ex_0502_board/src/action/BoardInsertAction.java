package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.BoardVO;

/**
 * Servlet implementation class BoardInsertAction
 */
@WebServlet("/insert.do")
public class BoardInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		String subjcet = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String ip = request.getRemoteAddr();
		
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setSubject(subjcet);
		vo.setContent(content);
		vo.setPwd(pwd);
		vo.setIp(ip);
		System.out.println("이름 : " +vo.getName());
		System.out.println("제목 : " +vo.getSubject());
		System.out.println("내용 : " +vo.getContent());
		System.out.println("pwd : " +vo.getPwd());
		System.out.println("ip : " +vo.getIp());
		
	
		int res = BoardDAO.getInstance().insert(vo);
		
		if(res > 0 ) {
			
			response.sendRedirect("board_list.do");
		}
	
		
	}

}
