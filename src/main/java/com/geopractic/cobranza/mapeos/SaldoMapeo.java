package com.geopractic.cobranza.mapeos;

import com.geopractic.cobranza.modelo.Saldo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class SaldoMapeo implements RowMapper<Saldo> {

  @Override
  public Saldo map(ResultSet rs, StatementContext ctx) throws SQLException {
    Saldo saldo = new Saldo();
    saldo.setId(rs.getLong("Id"));
    saldo.setSaldo(rs.getLong("Saldo"));
    saldo.setVencimiento(rs.getDate("Vencimiento"));
    saldo.setCuota(rs.getInt("Cuota"));
    saldo.setTotalCuota(rs.getInt("TotalCuota"));
    saldo.setCuenta(rs.getInt("Cuenta"));
    saldo.setCliente(rs.getLong("Cliente"));
    saldo.setFechaGestion(rs.getDate("FechaGestion"));
    saldo.setEmpleadoAsignado(rs.getLong("EmpleadoAsignado"));
    return saldo;
  } //Fin de mapeo

} //Fin del a clase SaldoMapeo