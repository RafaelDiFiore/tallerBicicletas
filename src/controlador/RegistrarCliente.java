/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import bd.bd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

/**
 *
 * @author Rafael Diaz
 */
public class RegistrarCliente {

    public boolean RegistrarCliente(Cliente cli) {
        Connection cnx = null;
        try {

            //obtener conexión a la BD
            bd cbd = new bd();
            cnx = cbd.obtenerConeccion();

            String query = "INSERT INTO cliente(idCliente,nombre,apellido,celular,fecha,modalidad,modelo,tipoServicio,valor,estado ) VALUES (?,?,?,?,?,?,?,?,?,?)";
            try (PreparedStatement stmt = cnx.prepareStatement(query)) {

                stmt.setInt(1, cli.getIdCliente());
                stmt.setString(2, cli.getNombre());
                stmt.setString(3, cli.getApellido());
                stmt.setInt(4, cli.getCelular());
                stmt.setDate(5, (java.sql.Date) cli.getFecha());
                stmt.setString(6, cli.getModalidad());
                stmt.setString(7, cli.getModelo());
                stmt.setString(8, cli.getTipoServicio());
                stmt.setInt(9, cli.getValor());
                stmt.setString(10, cli.getEstado());

                stmt.executeUpdate(); //inserta cliente
            }
            cnx.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error SQL al agregar Cliente" + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error al agregar Cliente" + e.getMessage());
            return false;
        }
    }

    public boolean egresarCliente(int id) {
        Connection cnx = null;
        try {

            bd cbd = new bd();
            cnx = cbd.obtenerConeccion();

            String query = "DELETE FROM cliente WHERE id=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setInt(1, id);

            stmt.executeUpdate(); //elimina el cliente
            stmt.close();
            cnx.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error SQL al eliminar cliente" + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error al eliminar cliente" + e.getMessage());
            return false;
        }
    }

    //Listar clientes
    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();

        Connection cnx = null;
        try {

            bd cbd = new bd();
            cnx = cbd.obtenerConeccion();

            String query = "SELECT idCliente,nombre,apellido,celular,fecha,modalidad,modelo,tipoServicio,valor,estado FROM cliente order by idCliente";
            PreparedStatement stmt = cnx.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente();

             cli.setIdCliente(rs.getInt("idCliente"));
             cli.setNombre(rs.getString("nombre"));
             cli.setApellido(rs.getString("apellido"));
             cli.setCelular(rs.getInt("celular"));
             cli.setFecha(rs.getDate("fecha"));
             cli.setModalidad(rs.getString("modalidad"));
             cli.setModelo(rs.getString("modelo"));
             cli.setTipoServicio(rs.getString("tipoServicio"));
             cli.setValor(rs.getInt("valor"));
             cli.setEstado(rs.getString("estado"));
            
             


                lista.add(cli);
            }
            rs.close();
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            System.out.println("Error SQL al listar Clientes" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al listar Clientes" + e.getMessage());
        }
        return lista;
    }

    //buscar por fecha
    public List<Cliente> buscarPorModalidad(String modalidad) {
        List<Cliente> lista = new ArrayList<>();

        Connection cnx = null;
        try {

            bd cbd = new bd();
            cnx = cbd.obtenerConeccion();

            String query = "SELECT idCliente,nombre,apellido,celular,fecha,modalidad,modelo,tipoServicio,valor,estado  FROM cliente WHERE modalidad = ? order by idCliente ";
            PreparedStatement stmt = cnx.prepareStatement(query);
    

       stmt.setString(1, modalidad);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente();

           cli.setIdCliente(rs.getInt("idCliente"));
             cli.setNombre(rs.getString("nombre"));
             cli.setApellido(rs.getString("apellido"));
             cli.setCelular(rs.getInt("celular"));
             cli.setFecha(rs.getDate("fecha"));
             cli.setModalidad(rs.getString("modalidad"));
             cli.setModelo(rs.getString("modelo"));
             cli.setTipoServicio(rs.getString("tipoServicio"));
             cli.setValor(rs.getInt("valor"));
             cli.setEstado(rs.getString("estado"));

                lista.add(cli);
            }
            rs.close();
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            System.out.println("Error SQL al listar Cliente por fecha" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al mostrar Cliente por fecha" + e.getMessage());
        }
        return lista;
    }
}
