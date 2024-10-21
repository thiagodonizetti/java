package com.example;
import java.sql.SQLException;

import java.util.List;

interface IContaDAO {
  public boolean criarConta(Conta conta) throws SQLException;

  List<Conta> lerTodasContas() throws SQLException;

  Conta buscarPorNumero(int numeroConta) throws SQLException;

  void atualizarConta(Conta conta) throws SQLException;

  void apagarConta(int numeroConta) throws SQLException;
}