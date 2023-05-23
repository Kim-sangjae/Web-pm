package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.BoardVO;

public class BoardDAO {
	
	SqlSession sqlSession;
	
	
	public BoardDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	
	// 페이지별 게시글 조회
	public List<BoardVO> selectList(HashMap<String, Integer> map){
		List<BoardVO> list = sqlSession.selectList("b.board_list",map);
		return list;
	}
	
	// 전체 게시물 수 조회
	public int getRowTotal() {
		int res = sqlSession.selectOne("b.board_count");
		return res;
	}
	
	
	// idx에 해당하는 게시물 한건 조회
	public BoardVO selectOne(int idx) {
		BoardVO vo = sqlSession.selectOne("b.board_one",idx);
		
		return vo;
	}
	
	// 조회수 증가
	public int update_readhit(int idx) {
		int res = sqlSession.update("b.update_readhit",idx);
		return res;
	}
	
	
	// 새글 작성
	public int insert(BoardVO vo) {
		int res = sqlSession.insert("b.board_insert",vo);
		
		return res;
	}
	
	
	// 게시글이 삭제된것 처럼 처리
	public int del_update(BoardVO vo) {
		
		int res = sqlSession.update("b.del_update",vo);
		
		return res;
	}
	
	

}