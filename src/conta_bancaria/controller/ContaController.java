package conta_bancaria.controller;

import java.util.*;

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
		Optional <Conta> conta = buscarNaCollection (numero);
		
		if (conta.isPresent()) {
			conta.get().visualizar();
		}
		else {
			System.out.printf("\n A conta número %d não foi encontrada!", numero);
		}

	}

	@Override
	public void atualizar(Conta conta) {

		Optional <Conta> buscaConta = buscarNaCollection (conta.getNumero());
		
		if (buscaConta.isPresent()) {
			listaConta.set(listaConta.indexOf(buscaConta.get()), conta);
				System.out.printf("A conta número %d foi atualizada com sucesso!", conta.getNumero());
			}
		else {
			System.out.printf("\n A conta número %d não foi encontrada!", conta.getNumero());
		}
	}

		

	@Override
	public void deletar(int numero) {
		Optional <Conta> conta = buscarNaCollection (numero);
		
		if (conta.isPresent()) {
			if (listaConta.remove(conta.get())) {
				System.out.printf("A conta número %d foi excluida com sucesso!", numero);
			}
		}
		else {
			System.out.printf("\n A conta número %d não foi encontrada!", numero);
		}
	}

	@Override
	public void sacar(int numero, float valor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void depositar(int numero, float valor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void transferi(int numeroOrigem, int numeroDestino, float valor) {
		// TODO Auto-generated method stub

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
