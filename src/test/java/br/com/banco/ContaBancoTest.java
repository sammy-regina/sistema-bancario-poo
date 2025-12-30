package br.com.banco;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Suíte de Testes de Cobertura Total - ContaBanco")
class ContaBancoTest {

    private ContaBanco conta;

    @BeforeEach
    void setup() {
        conta = new ContaBanco();
        conta.setDono("Sandra");
        conta.setNumConta(123);
    }

    // --- TESTES DE ABERTURA ---
    @Test
    @DisplayName("CC: Deve iniciar com status true e saldo 50")
    void deveAbrirContaCorrente() {
        conta.abrirConta(TipoConta.CC);
        assertThat(conta.isStatus()).isTrue();
        assertThat(conta.getSaldo()).isEqualTo(50.0f);
    }

    @Test
    @DisplayName("CP: Deve iniciar com status true e saldo 150")
    void deveAbrirContaPoupanca() {
        conta.abrirConta(TipoConta.CP);
        assertThat(conta.isStatus()).isTrue();
        assertThat(conta.getSaldo()).isEqualTo(150.0f);
    }

    // --- TESTES DE DEPÓSITO ---
    @Test
    @DisplayName("Depósito: Deve aumentar saldo se conta estiver aberta")
    void deveDepositarComSucesso() {
        conta.abrirConta(TipoConta.CC); // saldo 50
        conta.depositar(100);
        assertThat(conta.getSaldo()).isEqualTo(150.0f);
    }

    @Test
    @DisplayName("Depósito: Não deve alterar saldo se conta estiver fechada")
    void naoDeveDepositarSeFechada() {
        conta.depositar(100);
        assertThat(conta.getSaldo()).isEqualTo(0.0f);
    }

    // --- TESTES DE SAQUE ---
    @Test
    @DisplayName("Saque: Deve subtrair saldo se houver fundos e conta aberta")
    void deveSacarComSucesso() {
        conta.abrirConta(TipoConta.CC); // saldo 50
        conta.sacar(30);
        assertThat(conta.getSaldo()).isEqualTo(20.0f);
    }

    @Test
    @DisplayName("Saque: Não deve permitir saque se saldo insuficiente")
    void naoDeveSacarSemSaldo() {
        conta.abrirConta(TipoConta.CC); // saldo 50
        conta.sacar(60);
        assertThat(conta.getSaldo()).isEqualTo(50.0f); // saldo permanece o mesmo
    }

    // --- TESTES DE MENSALIDADE ---
    @Test
    @DisplayName("Mensalidade CC: Deve descontar 12 reais")
    void deveCobrarMensalidadeCC() {
        conta.abrirConta(TipoConta.CC); // saldo 50
        conta.pagarMensal();
        assertThat(conta.getSaldo()).isEqualTo(38.0f);
    }

    @Test
    @DisplayName("Mensalidade CP: Deve descontar 20 reais")
    void deveCobrarMensalidadeCP() {
        conta.abrirConta(TipoConta.CP); // saldo 150
        conta.pagarMensal();
        assertThat(conta.getSaldo()).isEqualTo(130.0f);
    }

    // --- TESTES DE FECHAMENTO ---
    @Test
    @DisplayName("Fechar: Deve falhar se houver saldo positivo")
    void naoDeveFecharComSaldoPositivo() {
        conta.abrirConta(TipoConta.CC);
        conta.fecharConta();
        assertThat(conta.isStatus()).isTrue();
    }

    @Test
    @DisplayName("Fechar: Deve falhar se houver saldo negativo (débito)")
    void naoDeveFecharComDebito() {
        conta.abrirConta(TipoConta.CC);
        // Simulando um débito (forçando via setSaldo para teste de fechamento)
        conta.setSaldo(-10.0f);
        conta.fecharConta();
        assertThat(conta.isStatus()).isTrue();
    }
}