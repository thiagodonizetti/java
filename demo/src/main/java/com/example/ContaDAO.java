package com.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class ContaDAO implements IContaDAO {
  private PreparedStatement criarStmt;
  private PreparedStatement lerTodasStmt;
  private PreparedStatement buscarPorNumeroStmt;
  private PreparedStatement atualizarStmt;
  private PreparedStatement apagarStmt;

  public ContaDAO(Connection connection) throws SQLException {
    criarStmt = connection.prepareStatement("INSERT INTO contas (nro_conta, saldo) VALUES (?, ?)");
    lerTodasStmt = connection.prepareStatement("SELECT * FROM contas");
    buscarPorNumeroStmt = connection.prepareStatement("SELECT * FROM contas WHERE nro_conta = ?");
    atualizarStmt = connection.prepareStatement("UPDATE contas SET saldo = ? WHERE nro_conta = ?");
    apagarStmt = connection.prepareStatement("DELETE FROM contas WHERE nro_conta = ?");
  }

  @Override
  public boolean criarConta(Conta conta) throws SQLException {
    criarStmt.setInt(1, conta.getNumero());
    criarStmt.setDouble(2, conta.getSaldo());
    if (criarStmt.executeUpdate() == 1) {
      return true;
    }
    return false;

  }

  @Override
  public List<Conta> lerTodasContas() throws SQLException {
    List<Conta> contas = new ArrayList<Conta>();
    ResultSet rs = lerTodasStmt.executeQuery();
    while (rs.next()) {
      int numero = rs.getInt("nro_conta");
      double saldo = rs.getDouble("saldo");
      contas.add(new Conta(numero, saldo));
    }
    return contas;
  }

  @Override
  public Conta buscarPorNumero(int numeroConta) throws SQLException {
    buscarPorNumeroStmt.setInt(1, numeroConta);
    ResultSet rs = buscarPorNumeroStmt.executeQuery();
    if (rs.next()) {
      double saldo = rs.getDouble("saldo");
      return new Conta(numeroConta, saldo);
    }
    return null;
  }

  @Override
  public void atualizarConta(Conta conta) throws SQLException {
    atualizarStmt.setDouble(1, conta.getSaldo());
    atualizarStmt.setInt(2, conta.getNumero());
    atualizarStmt.executeUpdate();
  }

  @Override
  public void apagarConta(int numeroConta) throws SQLException {
    apagarStmt.setInt(1, numeroConta);
    int res = apagarStmt.executeUpdate();
    System.out.println("Contas deletadas: "+res);
  }
}
