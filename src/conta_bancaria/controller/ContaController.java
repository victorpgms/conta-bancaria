package conta_bancaria.controller;

import java.util.*;
import java.util.stream.Collectors;

import conta_bancaria.model.Conta;
import conta_bancaria.repository.ContaRepository;

public class ContaController implements ContaRepository {

	private List<Conta> listaConta = new ArrayList<Conta>();
	int numero = 0;

	@Override
	public void listarTodas() {
		for (var conta : listaConta) {
			conta.visualizar();
		}
	}

	@Override
	public void cadastrar(Conta conta) {
		listaConta.add(conta);
		System.out.printf("\nA conta número %d foi criada com sucesso!\n", conta.getNumero());

	}

	@Override
	public void procurarPorNumero(int numero) {
		Optional<Conta> conta = buscarNaCollection(numero);

		if (conta.isPresent()) {
			conta.get().visualizar();
		} else {
			System.out.printf("\n A conta número %d não foi encontrada!", numero);
		}

	}

	@Override
	public void atualizar(Conta conta) {

		Optional<Conta> buscaConta = buscarNaCollection(conta.getNumero());

		if (buscaConta.isPresent()) {
			listaConta.set(listaConta.indexOf(buscaConta.get()), conta);
			System.out.printf("A conta número %d foi atualizada com sucesso!", conta.getNumero());
		} else {
			System.out.printf("\n A conta número %d não foi encontrada!", conta.getNumero());
		}
	}

	@Override
	public void deletar(int numero) {
		Optional<Conta> conta = buscarNaCollection(numero);

		if (conta.isPresent()) {
			if (listaConta.remove(conta.get())) {
				System.out.printf("A conta número %d foi excluida com sucesso!", numero);
			}
		} else {
			System.out.printf("\nA conta número %d não foi encontrada!", numero);
		}
	}

	@Override
	public void sacar(int numero, float valor) {
		Optional<Conta> conta = buscarNaCollection(numero);

		if (conta.isPresent()) {
			if (conta.get().sacar(valor)) {
				System.out.printf("\nO saque no valor de %.2f na conta número %d foi efetuado com sucesso!", valor,
						numero);
			} else {
				System.out.printf("\nATENÇÃO! O saque no valor de %.2f na conta número %d não foi efetuado por falta de saldo!", valor,
						numero);

			}
		} else {
			System.out.printf("\nA conta número %d não foi encontrada!", numero);
		}

	}

	@Override
	public void depositar(int numero, float valor) {
		Optional<Conta> conta = buscarNaCollection(numero);

		if (conta.isPresent()) {
			conta.get().depositar(valor);
				System.out.printf("\nO deposito no valor de %.2f na conta número %d foi efetuado com sucesso!", valor,
						numero);
		} else {
			System.out.printf("\nA conta número %d não foi encontrada!", numero);
		}
	}

	@Override
	public void transferir (int numeroOrigem, int numeroDestino, float valor) {
		Optional<Conta> contaOrigem = buscarNaCollection(numeroOrigem);
		Optional<Conta> contaDestino = buscarNaCollection(numeroDestino);
				
		if (contaOrigem.isPresent() && contaDestino.isPresent()) {
			if (contaOrigem.get().sacar(valor)) {
				contaDestino.get().depositar(valor);
				System.out.printf("\nTransferência no valor de %.2f da conta %d para a conta %d foi efetuado com sucesso!", valor, numeroOrigem, numeroDestino);
			} else {
				System.out.printf("\nATENÇÃO! Transferência no valor %.2f da conta %d para a conta %d não foi efetuada por falta de saldo!", valor, numeroOrigem, numeroDestino);
			}
			
		} else {
			System.out.printf("\nA conta %d e/ou %d não foi encontrada!", numeroOrigem, numeroDestino);
		}
		
		
		
	}
	
	
	@Override
	public void listarPorTitular(String titular) {
		
		List<Conta> listaTitulares = listaConta.stream()
				.filter(conta -> conta.getTitular().toUpperCase().contains(titular.toUpperCase()))
				.collect(Collectors.toList());
		
		if (listaTitulares.isEmpty()) {
			System.out.printf("\nNenhum titular com o nome %s foi encontrado.", titular);
		}
		
		listaTitulares.forEach(conta -> conta.visualizar());
		
		
	}


	// METODO AUXILIAR
	

	public int gerarNumero() {
		return ++numero;
	}

	public Optional<Conta> buscarNaCollection(int numero) {
		for (var conta : listaConta) {
			if (conta.getNumero() == numero)
				return Optional.of(conta);
		}

		return Optional.empty();

	}


}
