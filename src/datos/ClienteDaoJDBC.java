package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Cliente;

public class ClienteDaoJDBC 
{
	//	constantes SQL's.
	private static final String SQL_SELECT = "SELECT id_cliente, nombre, apellido, email, telefono, saldo "
										 	+ "FROM clientes";
	
	private static final String SQL_SELECT_BY_ID = "SELECT id_cliente, nombre, apellido, email, telefono, saldo "
											+ "FROM clientes WHERE id_cliente = ?";
	
	private static final String SQL_INSERT = "INSERT INTO clientes (nombre, apellido, email, telefono, saldo)"
											+ "VALUES (?,?,?,?,?)";
	
	private static final String SQL_UPDATE = "UPDATE clientes SET nombre = ?, apellido = ?, email = ?, telefono = ?, saldo = ?"
											+ "WHERE id_cliente = ?";
	
	private static final String SQL_DELETE = "DELETE FROM clientes WHERE id_cliente = ?";
	
	public List<Cliente> listar()
	{
		Connection cnx = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Cliente cliente = null;
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		try 
		{
			cnx = Conexion.getConnection();
			stmt = cnx.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();
			
			while (rs.next())
			{
				int idCliente = rs.getInt("id_cliente");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String email = rs.getString("email");
				String telefono = rs.getString("telefono");
				double saldo = rs.getDouble("saldo");
				
				cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);
				clientes.add(cliente);
			}
		}
		catch (SQLException e) 
		{
			//	salida estandar.
			e.printStackTrace(System.out);
		}
		finally 
		{
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(cnx);
		}
		
		return clientes;		
	}
	
	public Cliente encontrar(Cliente cliente)
	{
		Connection cnx = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try 
		{
			cnx = Conexion.getConnection();
			stmt = cnx.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setInt(1, cliente.getIdCliente());
			rs = stmt.executeQuery();
			//	necesario. A priori es para saltar un registro vacio que viene.
			rs.next();
		
			//rs.absolute(1); 
			
			String nombre = rs.getString("nombre");
			String apellido = rs.getString("apellido");
			String email = rs.getString("email");
			String telefono = rs.getString("telefono");
			double saldo = rs.getDouble("saldo");
			
			cliente.setNombre(nombre);
			cliente.setApellido(apellido);
			cliente.setEmail(email);
			cliente.setTelefono(telefono);
			cliente.setSaldo(saldo);
		}
		catch (SQLException e) 
		{
			//	salida estandar.
			e.printStackTrace(System.out);
		}
		finally 
		{
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(cnx);
		}
		
		return cliente;
	}
	
	public int insertar(Cliente cliente)
	{
		Connection cnx = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try 
		{
			cnx = Conexion.getConnection();
			stmt = cnx.prepareStatement(SQL_INSERT);
			
			stmt.setString(1, cliente.getNombre());
			stmt.setString(2, cliente.getApellido());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getTelefono());
			stmt.setDouble(5, cliente.getSaldo());
			
			rows = stmt.executeUpdate();
		}
		catch (SQLException e) 
		{
			//	salida estandar.
			e.printStackTrace(System.out);
		}
		finally 
		{
			Conexion.close(cnx);
			Conexion.close(stmt);
		}
	
		return rows;
	}
	
	public int actualizar(Cliente cliente)
	{
		Connection cnx = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try 
		{
			cnx = Conexion.getConnection();
			stmt = cnx.prepareStatement(SQL_UPDATE);
			
			stmt.setString(1, cliente.getNombre());
			stmt.setString(2, cliente.getApellido());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getTelefono());
			stmt.setDouble(5, cliente.getSaldo());
			stmt.setInt(6, cliente.getIdCliente());
			
			rows = stmt.executeUpdate();
		}
		catch (SQLException e) 
		{
			//	salida estandar.
			e.printStackTrace(System.out);
		}
		finally 
		{
			Conexion.close(cnx);
			Conexion.close(stmt);
		}
	
		return rows;
	}
	
	public int eliminar(Cliente cliente)
	{
		Connection cnx = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try 
		{
			cnx = Conexion.getConnection();
			stmt = cnx.prepareStatement(SQL_DELETE);
			
			stmt.setInt(1, cliente.getIdCliente());
			
			rows = stmt.executeUpdate();
		}
		catch (SQLException e) 
		{
			//	salida estandar.
			e.printStackTrace(System.out);
		}
		finally 
		{
			Conexion.close(cnx);
			Conexion.close(stmt);
		}
	
		return rows;
	}
	
	
}
