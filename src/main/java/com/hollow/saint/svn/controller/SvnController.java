package com.hollow.saint.svn.controller;

import com.hollow.saint.aop.LogAnnotation;
import com.hollow.saint.svn.service.OrderService;
import com.hollow.saint.svn.service.SvnSevice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//@RestController
public class SvnController {

//	@Resource
//	SvnSevice svnSevice;
//
//	@Resource
//	OrderService orderService;
//
//	/**
//	 * @return
//	 */
//	@PutMapping("/svn/open")
//	public ResponseEntity<String> openSvn() throws IOException {
//		if (svnSevice.openSvn()) {
//			return ResponseEntity.ok().body("open successfully");
//		}
//		return ResponseEntity.badRequest().body("request failed");
//	}
//
//	/**
//	 * @return
//	 */
//	@PutMapping("/svn/close")
//	public ResponseEntity<String> closeSvn() throws IOException {
//		if (svnSevice.closeSvn()) {
//			return ResponseEntity.ok().body("close successfully");
//		}
//		return ResponseEntity.badRequest().body("request failed");
//	}
//
//	@PutMapping("/svn/add")
//	public ResponseEntity<String> add(@RequestParam String num) throws IOException {
//		if (svnSevice.add(num)) {
//			return ResponseEntity.ok().body("successfully");
//		}
//		return ResponseEntity.badRequest().body("request failed");
//	}
//
//	/**
//	 * @return
//	 */
//	@GetMapping("/salary/list")
//	public ResponseEntity<Object> getSalaryListByYearAndMonth(@RequestParam String yearAndMonth) {
//		List<Map<String, Object>> list = svnSevice.getSalaryListByYearAndMonth(yearAndMonth);
//		if (list.isEmpty()) {
//			return ResponseEntity.ok().body("no data");
//		}
//		return ResponseEntity.ok().body(list);
//	}
//
//	@GetMapping("/log/list")
//	public ResponseEntity<?> getLogList() {
//		return ResponseEntity.ok().body(svnSevice.getLogList());
//	}
//
//	private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
//
//			200, 200,
//
//			1, TimeUnit.HOURS,
//
//			new ArrayBlockingQueue<>(100),
//
//			new ThreadPoolExecutor.CallerRunsPolicy());
//
//	public static ThreadPoolExecutor getPool() {
//		return threadPool;
//	}
//
//	@GetMapping("/pool")
//	public void test() {
//		threadPool.submit(new Callable<Integer>() {
//			@Override
//			public Integer call() throws Exception {
//				TimeUnit.MILLISECONDS.sleep(10);
//				return 1;
//			}
//		});
//	}
//
//	@GetMapping("/order")
//	@LogAnnotation(module = "订单", operate = "下单")
//	public void getOrder(String name, String gender){
//		orderService.printOrder("张三", "男");
//	}
}
