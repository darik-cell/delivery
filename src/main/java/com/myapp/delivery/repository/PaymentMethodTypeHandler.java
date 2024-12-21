package com.myapp.delivery.repository;

import com.myapp.delivery.domain.order.PaymentMethod;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMethodTypeHandler extends BaseTypeHandler<PaymentMethod> {
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, PaymentMethod parameter, JdbcType jdbcType) throws SQLException {
    ps.setString(i, parameter.getDbValue());
  }

  @Override
  public PaymentMethod getNullableResult(ResultSet rs, String columnName) throws SQLException {
    String dbValue = rs.getString(columnName);
    return PaymentMethod.fromDbValue(dbValue);
  }

  @Override
  public PaymentMethod getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    String dbValue = rs.getString(columnIndex);
    return PaymentMethod.fromDbValue(dbValue);
  }

  @Override
  public PaymentMethod getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    String dbValue = cs.getString(columnIndex);
    return PaymentMethod.fromDbValue(dbValue);
  }
}
