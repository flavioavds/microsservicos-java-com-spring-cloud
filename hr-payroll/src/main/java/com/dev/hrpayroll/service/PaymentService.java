package com.dev.hrpayroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.hrpayroll.entities.Payment;
import com.dev.hrpayroll.entities.Worker;
import com.dev.hrpayroll.feignclients.WorkerFeignClient;

@Service
public class PaymentService {
	
//	@Value("${hr-worker.host}")
//	private String workerHost;
	
//	@Autowired
//	private RestTemplate restTemplate;
	
	@Autowired
	private WorkerFeignClient workerFeignClient;
	
//	public Payment getPayment(long workerId, int days) {
//		Map<String, String> uriVariables = new HashMap<>();
//		uriVariables.put("id", ""+workerId);
//		
//		Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables);
//		return new Payment(worker.getName(), worker.getDailyIncome(), days);
//	}
	
	public Payment getPayment(long workerId, int days) {
		
		Worker worker = workerFeignClient.findById(workerId).getBody();
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
		
	}
	
}
