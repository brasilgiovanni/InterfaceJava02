package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractServices;
import model.services.PayPalService;

public class Program {

	public static void main(String[] args) {
		/*
		 * Uma empresa deseja automatizar o processamento de seus contratos. O
		 * processamento de um contrato consiste em gerar as parcelas a serem pagas para
		 * aquele contrato, com base no número de meses desejado. A empresa utiliza um
		 * serviço de pagamento online para realizar o pagamento das parcelas. Os
		 * serviços de pagamento online tipicamente cobram um juro mensal, bem como uma
		 * taxa por pagamento. Por enquanto, o serviço contratado pela empresa é o do
		 * Paypal, que aplica juros simples de 1% a cada parcela, mais uma taxa de
		 * pagamento de 2%. Fazer um programa para ler os dados de um contrato (número
		 * do contrato, data do contrato, e valor total do contrato). Em seguida, o
		 * programa deve ler o número de meses para parcelamento do contrato, e daí
		 * gerar os registros de parcelas a serem pagas (data e valor), sendo a primeira
		 * parcela a ser paga um mês após a data do contrato, a segunda parcela dois
		 * meses após o contrato e assim por diante. Mostrar os dados das parcelas na
		 * tela.
		 */

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Entre com os dados do contrato");
		System.out.print("Número: "); int numContrato = sc.nextInt();
		sc.nextLine();

		System.out.print("Data (dd/MM/yyyy): ");
		LocalDate dataContrato = LocalDate.parse(sc.nextLine(), fmt1);

		System.out.print("Valor do contrato: "); double valorContrato = sc.nextDouble();
		System.out.println();
		
		Contract contrato = new Contract(numContrato, dataContrato, valorContrato);
		
		System.out.print("Entre com o número de parcelas desejadas: "); int numParcelas = sc.nextInt();

		System.out.println();
		
		ContractServices contractService = new ContractServices(new PayPalService());
		
		contractService.processContract(contrato, numParcelas);

		System.out.println("PARCELAS");
		
		for (Installment installment : contrato.getInstallments()) {
			System.out.println(installment);
		}
		
		System.out.println("----------------------------");
		
		System.out.println("VALOR TOTAL PAGO");
		System.out.println(contrato.getTotalPayment());
		
		sc.close();

	}

}
