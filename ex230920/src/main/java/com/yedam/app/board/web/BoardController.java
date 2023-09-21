package com.yedam.app.board.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

@Controller
public class BoardController {
	
	// 가장 기본적인 형태
	
	@Autowired
	BoardService boardService;
	// 1) Service => method 2) mapping 3) 매개변수: 객체 4) 페이지, 데이터(@Responsebody), redirect
	// 서버에 알려주는 모습
	// 전체조회 : URI - boardList / RETURN - board/boardList
	@GetMapping("boardList")
	public String boardList(Model model) {
		List<BoardVO> list = boardService.getBoardList();
		model.addAttribute("boardList", list);
		return "board/boardList";
	}
	
	// 단건조회 : URI - boardInfo / Parameter - BoardVO / RETURN - board.boardInfo 
	@GetMapping("boardInfo")
	public String boardInfo(BoardVO boardVO, Model model) {
//		model.addAttribute("boardInfo", boardService.getBoardInfo(boardVO));
		BoardVO findVO = boardService.getBoardInfo(boardVO);
		model.addAttribute("boardInfo", findVO);
		return "board/boardInfo";
	}
	// 등록 - 페이지 : URI - boardInsert / RETURN - board/boardInsert
	@GetMapping("boardInsert")
	public String boardInsertForm() {
		// 등록 페이지를 호출할 때 미리 primary key를 보여줘야 하는 경우 Model이 필요함 => selectkey 사용 안 함
		return "board/boardInsert";
	}
	// 등록 - 처리 : URI - boardInsert / Parameter - BoardVO / RETURN - 전체조회 다시 호출 - redirect(문자로 넘어감): 컨트롤러로 넘어가란 말
	// 등록할 땐 무조건 data가 필요함
	@PostMapping("boardInsert")
	public String boardInsertProcess(BoardVO boardVO) {
		boardService.insertBoardInfo(boardVO);		
		return "redirect:boardList";
//		String result = null;
//		if (bno == 1) {
//			result = "정상등록";
//		} else {
//			result = "등록실패";
//		}
//		attr.addAttribute("type", "insert");
//		attr.addFlashAttribute("result", result);
//		
//		return "board/boardList?type=insert";
	}
	// 수정 - 페이지 : URI - boardUpdate / Parameter - BoardVO / RETURN - board/boardUpdate
	// 무엇을 수정할 지 받아와야 되니깐 파라미터가 필요하다.
	@GetMapping("boardUpdate")
	public String boardUpdateForm(BoardVO boardVO, Model model) {
		BoardVO findVO = boardService.getBoardInfo(boardVO);
		model.addAttribute("boardInfo", findVO);
		return "board/boardUpdate";
	}
	// 데이터가 리턴되면 ajax
	// 수정 - 처리 : URI - boardUpdate / Parameter - BoardVO / RETURN - 실행결과
	@PostMapping("boardUpdate")
	@ResponseBody
	public Map<String, Object> boardUpdateProcess(@RequestBody BoardVO boardVO) {
		Map<String, Object> map = new HashMap<>();
		int result = boardService.updateBoardInfo(boardVO);
		if(result > -1) {
			map.put("result", true);
			map.put("bno", result);
		}else {
			map.put("result", false);
		}
		return map;
	}
	// 삭제 - 처리 : URI - boardDelete / Parameter - bno / RETURN - 전체조회 다시 호출
	// 단건삭제 @RequestParam
	// 이 값 하나 보내자고 Post는 귀찮다.
	@GetMapping("boardDelete")
	public String boardDelete(@RequestParam Integer bno) {
		boardService.deleteBoardInfo(bno);
		return "redirect:boardList";
	}
//	@PostMapping("boardDelete")
//	public String boardDeleteProcess(int boardNo, RedirectAttributes attr) {
//		int bno = boardService.deleteBoardInfo(boardNo);
//		
//		String result = null;
//		if(bno == 1) {
//			result = "삭제완료";
//		} else {
//			result = "삭제실패";
//		}
//		
//		attr.addAttribute("type", "delete");
//		attr.addFlashAttribute("result", result);
//		
//		return "board/boardList?type=delete";
//	}
}
