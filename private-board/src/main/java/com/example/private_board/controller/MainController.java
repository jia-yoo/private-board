package com.example.private_board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.private_board.dao.IBoardDao;
import com.example.private_board.dao.ICommentDao;
import com.example.private_board.dao.IMemberDao;
import com.example.private_board.dto.BoardDto;
import com.example.private_board.dto.CommentDto;
import com.example.private_board.dto.MemberDto;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class MainController {
	// 프로젝트의 규모가 커지면, 특정부분의 문제를 빠르게 찾기위해 분야별로 나눠서 컨트롤러 작성을
	@Autowired
	IBoardDao Bdao;
	
	@Autowired
	IMemberDao Mdao;
	
	@Autowired
	ICommentDao Cdao;
	
	@RequestMapping({"/", "/main"})
	public String root(Model model) {

		model.addAttribute("list", Bdao.getAllBoard());
		return "main";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", Bdao.getAllBoard());
		
		return "list";
	}
	
	@RequestMapping("/regForm")
	public String regForm() {
		return "regForm";
	}
	
	@RequestMapping("/regist")
	public String regist(@RequestParam("id") String id, @RequestParam("pw") String pw, @RequestParam("name") String name, Model model) {
		MemberDto member = new MemberDto(id, pw, name);
		Mdao.regMember(member);
		model.addAttribute("result", true);
		return "regForm";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpServletRequest request, Model model) {
		boolean result = false;
		if(Mdao.getMember(id) == null || !Mdao.getMember(id).getPw().equals(pw)) {
			log.info("로그인 실패.....");
			model.addAttribute("result", result);
			return "loginForm";
		}
		HttpSession session = request.getSession();
		MemberDto mem = Mdao.getMember(id);
		session.setAttribute("id", mem.getId());
		session.setAttribute("pw", mem.getPw());
		session.setAttribute("name", mem.getName());
		session.setAttribute("loginInfo", Mdao.getMember(id));
		result = true;
		model.addAttribute("result", result);
		log.info("로그인 성공......");
		return "loginForm";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping("/registBoard")
	public String registBoard(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("receiver") String receiver, HttpServletRequest request) {
		BoardDto board = new BoardDto();
		board.setTitle(title);
		board.setContent(content);
		HttpSession session = request.getSession();
		MemberDto dto = (MemberDto)session.getAttribute("loginInfo");
		board.setWriter(dto.getId());
		board.setReceiver(receiver);
		Bdao.regBoard(board);
		return "redirect:main";
	}
	
	@RequestMapping("/write") // 게시글 등록 폼
	public String write() {
		return "write";
	}
	
	@RequestMapping("/detail")
	public String detail(@RequestParam("bno") String bno, Model model) {
		int bno_ = Integer.parseInt(bno);
		model.addAttribute("board", Bdao.getBoard(bno_));
		model.addAttribute("commentList", Cdao.getCommentList(bno_));
		return "detail";
	}
	
	@RequestMapping("/myPage")
	public String myPage(Model model) {
		
		return "myPage";
	}
	
	@RequestMapping("/myList")
	public String myList(HttpServletRequest request, String writer, Model model) {
		HttpSession session = request.getSession();
		MemberDto dto = (MemberDto)session.getAttribute("loginInfo");
		writer = dto.getId();
		model.addAttribute("list", Bdao.getMyList(writer));
		
		return "myList";
	}
	
	@RequestMapping("/receiveList")
	public String receiverList(HttpServletRequest request, String receiver, Model model) {
		HttpSession session = request.getSession();
		MemberDto dto = (MemberDto)session.getAttribute("loginInfo");
		receiver = dto.getId();
		model.addAttribute("list", Bdao.getReceiveList(receiver));
		return "receiveList";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("bno") String bno) {
		int bno_ = Integer.parseInt(bno);
		Bdao.deleteBoard(bno_);
		
		return "redirect:myList";
	}
	
	@RequestMapping("/checkReceiverId")
	public @ResponseBody String root(HttpServletRequest request, Model model) {
		if(Mdao.getMember(request.getParameter("Rid")) != null) {
			MemberDto mem = Mdao.getMember(request.getParameter("Rid"));
			return "1";
		}
		return "0";
	}
	
	@RequestMapping("/regLoginCheck")
	public @ResponseBody String regLoginCheck(@RequestParam("id")String id) {

		MemberDto member = Mdao.getMember(id);
		
		String str = "";
		
		if(member == null) {
			str = "사용가능한 ID입니다.";
		}else {
			str = "이미 사용중인 ID입니다.";
		}

		return str;
	}
	
	@RequestMapping("/regComment")
	public String regComment(@RequestParam("comment_content") String content, HttpServletRequest request, @RequestParam("bno") String bno) {
		CommentDto dto = new CommentDto();
		dto.setContent(content);
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		dto.setWriter(id);
		
		int bno_ = Integer.parseInt(bno);
		dto.setBno(bno_);
		
		Cdao.regComment(dto);
		
		return "redirect:detail?bno=" + bno;
	}
	
	@RequestMapping("/deleteComment")
	public String deleteComment(@RequestParam("cno") String cno, @RequestParam("bno") String bno) {
		int cno_ = Integer.parseInt(cno);
		Cdao.deleteComment(cno_);
		
		return "redirect:detail?bno=" + bno;
	}
	
	
	
	
}
