package br.com.banco;

public class Main {
    public static void main(String[] args) {
        // --- TESTE DE FECHAMENTO COM SUCESSO ---
        ContaBanco p1 = new ContaBanco();
        p1.setNumConta(1111);
        p1.setDono("Jubileu");
        p1.abrirConta(TipoConta.CC); // Saldo inicial: 50

        System.out.println("Tentando fechar conta com saldo...");
        p1.fecharConta(); // Deve exibir ERRO: Conta com dinheiro

        p1.sacar(50); // Zera o saldo
        System.out.println("Tentando fechar conta com saldo zerado...");
        p1.fecharConta(); // Deve fechar com SUCESSO

        // --- TESTE DE FECHAMENTO COM DÉBITO ---
        ContaBanco p2 = new ContaBanco();
        p2.setNumConta(2222);
        p2.setDono("Creuza");
        p2.abrirConta(TipoConta.CP); // Saldo inicial: 150

        p2.sacar(160); // Provoca saldo negativo de -10
        System.out.println("Tentando fechar conta em débito...");
        p2.fecharConta(); // Deve exibir ERRO: Conta em débito

        // --- RESUMO FINAL ---
        System.out.println("\n--- ESTADO DOS OBJETOS ---");
        exibirStatus(p1);
        exibirStatus(p2);
    }

    public static void exibirStatus(ContaBanco c) {
        System.out.println("Dono: " + c.getDono() +
                " | Saldo: R$" + c.getSaldo() +
                " | Status: " + (c.isStatus() ? "Aberta" : "Fechada"));
    }
}