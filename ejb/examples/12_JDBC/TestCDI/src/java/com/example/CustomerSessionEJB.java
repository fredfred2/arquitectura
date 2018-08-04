/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.sql.DataSource;

/**
 *
 * @author tmcginn
 */
@Stateless
@LocalBean
public class CustomerSessionEJB {

  @Resource(lookup = "jdbc/sample")
  private DataSource dataSource;

  public List<String> getCustomerList() {
    List<String> custList = new ArrayList<>();
    try (Connection conn = dataSource.getConnection()) {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM Customer");
      while (rs.next()) {
        custList.add(rs.getString("NAME"));
      }
    } catch (SQLException ex) {
      System.out.println("SQL Exception: " + ex);
    }
    return custList;
  }
}
