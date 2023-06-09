package com.korea.tier.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.korea.tier.dao.OrderDAO;
import com.korea.tier.dao.ProductDAO;
import com.korea.tier.vo.OrderDTO;
import com.korea.tier.vo.OrderVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderDAO orderDAO;
	private final ProductDAO productDAO;
	
	
	// 주문하기 동시에 재고 수정
	public void order(OrderVO orderVO) {
		
		productDAO.setProductStock(orderVO);
		orderDAO.save(orderVO);
		
	}
	
	public List<OrderDTO> getList (String sort) {
		
		List<OrderDTO> list = orderDAO.selectAll(sort);
		
		return list;
		
	}
	
	

}
