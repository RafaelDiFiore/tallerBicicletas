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


    public boolean RegistrarCliente(Cliente cli) { //la variable es cli, ya que antes se llamaba cliente y no donador, y eran muchos cli para cambiarlos.
        Connection cnx = null;
        try {

            //obtener conexión a la BD
            bd cbd = new bd();
            cnx = cbd.obtenerConeccion();
           
     
       
            String query = "INSERT INTO cliente(id, nombre,apellido,celular,modadlidad,modelo,tipoServicio,valor ) VALUES (?,?,?,?,?,?,?,?,?)";
            try (PreparedStatement stmt = cnx.prepareStatement(query)) {
                stmt.setInt(1, cli.getId());
                stmt.setString(2, cli.getNombre());
                stmt.setString(3, cli.getApellido());
                stmt.setInt(4, cli.getCelular());
                stmt.setString(5, cli.getModalidad());
                stmt.setString(6, cli.getModelo());
                stmt.setString(7, cli.getTipoServicio());
                stmt.setInt(8, cli.getCelular());
                
                
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

    //buscar todos los clientes
    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();

        Connection cnx = null;
        try {

            bd cbd = new bd();
            cnx = cbd.obtenerConeccion();

            String query = "SELECT id, nombre, apellido,celular,modadlidad,modelo,tipoServicio,valor FROM cliente order by id";
            PreparedStatement stmt = cnx.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente();
                
             stmt.setInt(1, cli.getId());
                stmt.setString(2, cli.getNombre());
                stmt.setString(3, cli.getApellido());
                stmt.setInt(4, cli.getCelular());
                stmt.setString(5, cli.getModalidad());
                stmt.setString(6, cli.getModelo());
                stmt.setString(7, cli.getTipoServicio());
                stmt.setInt(8, cli.getCelular());
                

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

    //buscar por tipo de sangre
    public List<Cliente> buscarTodosServicios(String tipoServicio) {
        List<Cliente> lista = new ArrayList<>();

        Connection cnx = null;
        try {

             bd cbd = new bd();
            cnx = cbd.obtenerConeccion();

            String query = "SELECT rut_cliente, nombre, appaterno, apmaterno, calular FROM cliente WHERE tiposangre = ? order by rut_cliente";
            PreparedStatement stmt = cnx.prepareStatement(query);

            stmt.setString(1, tipoServicio);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente();

                cli.setRut_cliente(rs.getString("rut_cliente"));
                cli.setNombre(rs.getString("nombre"));
                cli.setAppaterno(rs.getString("appaterno"));
                cli.setApmaterno(rs.getString("apmaterno"));
                cli.setCelular(rs.getInt("celular"));
                cli.setTipoSangre(rs.getString("tiposangre"));

                lista.add(cli);
            }
            rs.close();
            stmt.close();
            cnx.close();
        } catch (SQLException e) {
            System.out.println("Error SQL al listar Donador/Cliente por Tipo de Sangre" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al mostrar Donador/Cliente por Tipo de Sangre" + e.getMessage());
        }
        return lista;
    }
}

    

