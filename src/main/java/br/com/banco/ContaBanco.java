package br.com.banco;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe que representa uma Conta Bancária simplificada.
 * Implementa regras de negócio para abertura, fechamento e movimentação.
 * * @author Sandra Regina de Jesus Andrade
 * @version 1.0
 */
@Getter
@Setter
public class ContaBanco {

    public int numConta;
    protected TipoConta tipo;
    private String dono;
    private float saldo;
    private boolean status;

    /**
     * Construtor padrão da classe.
     * Define o status inicial como falso (fechada) e o saldo como zero.
     */
    public ContaBanco() {
        this.saldo = 0;
        this.status = false;
    }

    /**
     * Abre uma conta bancária e aplica o bônus inicial conforme o tipo.
     * * @param tipo Tipo da conta: "CC" para Conta Corrente ou "CP" para Conta Poupança.
     */
    public void abrirConta(TipoConta tipo) {
        this.setTipo(tipo);
        this.setStatus(true);
        if (tipo == TipoConta.CC) { // se o tipo da conta for CC
            this.setSaldo(50.0f); // saldo inicial de 50
        } else if (tipo == TipoConta.CP) { // se o tipo da conta for CP
            this.setSaldo(150.0f); // saldo inicial de 150
        }
        System.out.println("Conta aberta com sucesso para "  + this.getDono());
    }

    /**
     * Fecha a conta bancária se o saldo estiver zerado.
     * Exibe mensagens de erro se houver saldo positivo ou negativo.
     */
    public void fecharConta() {
        if (this.getSaldo() > 0) {
            System.out.println("ERRO: Conta com dinheiro. Saque antes de fechar.");
            return;
        }
        else if (this.getSaldo() < 0) {
            System.out.println("ERRO: Conta em débito. Regularize o saldo.");
            return;
        }
        else {
            this.setStatus(false);
            System.out.println("Conta de " + this.getDono() + " fechada com sucesso.");
        }
    }

    /**
     * Realiza um depósito na conta, caso ela esteja ativa.
     * * @param valor O valor real a ser depositado.
     */

    public void depositar(float valor) {
        if (this.isStatus()) { // Verifica se status é verdadeiro
            this.setSaldo(this.getSaldo() + valor);
            System.out.println("Depósito de R$" + valor + " realizado na conta de " + this.getDono());
        } else {
            System.out.println("ERRO: Não é possível depositar em uma conta fechada.");
        }
    }

    /**
     * Realiza um saque na conta, caso ela esteja ativa e haja saldo suficiente.
     * * @param valor O valor real a ser sacado.
     */
    public void sacar(float valor) {
        if (this.isStatus()) { // Verifica se a conta está ativa
            if (this.getSaldo() >= valor) { // Verifica se há saldo suficiente
                this.setSaldo(this.getSaldo() - valor);
                System.out.println("Saque de R$" + valor + " realizado na conta de " + this.getDono());
            } else {
                System.out.println("ERRO: Saldo insuficiente para saque.");
            }
        } else {
            System.out.println("ERRO: Não é possível sacar de uma conta fechada.");
        }
    }

    /**
     * Paga a mensalidade da conta conforme o tipo.
     * Conta Corrente (CC): R$ 12,00.
     * Conta Poupança (CP): R$ 20,00.
     */
    public void pagarMensal() {
        int valorMensal = 0;
        if (this.getTipo() == TipoConta.CC) {
            valorMensal = 12; // Valor para CC
        } else if (this.getTipo() == TipoConta.CP) {
            valorMensal = 20; // Valor para CP
        }

        if (this.isStatus()) {
            this.setSaldo(this.getSaldo() - valorMensal);
            System.out.println("Mensalidade de R$" + valorMensal + " debitada da conta de " + this.getDono());
        } else {
            System.out.println("ERRO: Impossível pagar em uma conta fechada!");
        }
    }
}
