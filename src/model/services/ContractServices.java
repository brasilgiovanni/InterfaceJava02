package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractServices {
	
	private OnlinePaymentService onlinePaymentService;
	
	
	
	public ContractServices(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}
	
	
	public void processContract(Contract contract, Integer months) {
		
		double basicQuota = contract.getTotalValue() / months; // valor basico mensal das parcelas
		
		for (int k = 1; k <= months; k++) {
			LocalDate mesSeguinte = contract.getDate().plusMonths(k);
			
			double interest = onlinePaymentService.interest(basicQuota, k); // juros de cada mes
			double fee = onlinePaymentService.paymentFee(basicQuota+interest); // taxa do serviço
			
			double totalQuota = basicQuota + interest + fee;
			
			contract.getInstallments().add(new Installment(mesSeguinte, totalQuota)); // passando a informação da data e valor da parcela
			
			contract.totalPayment(totalQuota); // somando o valor de cada parcela de cada mes ao valor total de pagamento
		}
	}

}
