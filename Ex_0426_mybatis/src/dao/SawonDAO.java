package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MybatisConnector;
import vo.SawonVO;

public class SawonDAO {
	
	
	SqlSessionFactory factory;
	
	
	public SawonDAO() {
		
		factory = MybatisConnector.getInstance().getFactory();
		
	}
	
	
	// single-ton pattern: 
	// 객체1개만생성해서 지속적으로 서비스하자
	static SawonDAO single = null;
	

	public static SawonDAO getInstance() {
		//생성되지 않았으면 생성
		if (single == null)
			single = new SawonDAO();
		//생성된 객체정보를 반환
		return single;
	}
	
	
	
	
	// 사원 목록전체 조회
	public List<SawonVO> select(){
		SqlSession sqlSession = factory.openSession();
		
		List<SawonVO> list = sqlSession.selectList("s.sawon_list");
	
		// conn,pstmt,rs를 close하는 내용이 포함
		sqlSession.close();
		return list;
	}
	
	
	
	// 특정부서 사원만 조회 오버로드
	public List<SawonVO> select(int deptno){
		//1.처리객체 얻어오기
		SqlSession sqlSession = factory.openSession();
		
		//2. 쿼리 작업 수행
		List<SawonVO> deptlist = sqlSession.selectList("s.sawon_list_no",deptno);
		
		sqlSession.close();
		
		return deptlist;
		
	}
	
	
	
	
	
	

}
