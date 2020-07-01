package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.ClienteDaoJDBC;
import dominio.Cliente;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		String accion = request.getParameter("accion");

		if (accion != null)
		{
			switch (accion)
			{
				case "editar":
					editarCliente(request, response);	
					break;
				case "eliminar":
					eliminarCliente(request, response);
					break;
				default:
					accionDefault(request, response);
			}
		}
		else
		{
			accionDefault(request, response);
		}
	} 
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String accion = request.getParameter("accion");
		
		if (accion != null)
		{
			switch (accion)
			{
				case "insertar":
					insertarCliente(request, response);	
					break;
				case "modificar":
					modificarCliente(request, response);
					break;
				default:
					accionDefault(request, response);
			}
		}
		else
		{
			accionDefault(request, response);
		}
	}
	
	private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<Cliente> clientes = new ClienteDaoJDBC().listar();
		System.out.println("Clientes: " + clientes);
		HttpSession sesion = request.getSession();
		sesion.setAttribute("clientes", clientes);
		sesion.setAttribute("totalClientes", clientes.size());
		sesion.setAttribute("saldoTotal", calcularSaldoTotal(clientes));
		response.sendRedirect("clientes.jsp");
	}
	
	private double calcularSaldoTotal(List<Cliente> clientes)
	{
		double saldoTotal = 0;
		for (Cliente cliente : clientes) 
		{
			saldoTotal += cliente.getSaldo();
		}
		
		return saldoTotal;
			
	}
	
	private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//	recuperamos el idCliente.
		int idCliente = Integer.parseInt(request.getParameter("idCliente"));
		Cliente cliente =  new ClienteDaoJDBC().encontrar(new Cliente(idCliente));
		System.out.println("Cliente encontrado: " + cliente);
		request.setAttribute("cliente", cliente);
		String jspEditar = "/WEB-INF/paginas/cliente/editarCliente.jsp";
		request.getRequestDispatcher(jspEditar).forward(request, response);
		
	}
	
	private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//	recuperamos los valores del formulario agregarCliente.
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String telefono = request.getParameter("telefono");
		double saldo = 0;
		String saldoString = request.getParameter("saldo");
		
		if (saldoString!=null && !saldoString.equals("")) 
		{
			saldo = Double.parseDouble(saldoString);
		}
		
		//	creamos el objeto cliente.
		Cliente cliente = new Cliente(nombre, apellido, email, telefono, saldo);
		
		int registrosModificados = new ClienteDaoJDBC().insertar(cliente);
		System.out.println("Registros modificados: " + registrosModificados);
		
		//	
		accionDefault(request, response);
	}
	
	private void modificarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int idCliente = Integer.parseInt(request.getParameter("idCliente"));
		//	recuperamos los valores del formulario agregarCliente.
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String telefono = request.getParameter("telefono");
		double saldo = 0;
		String saldoString = request.getParameter("saldo");
		
		if (saldoString!=null && !saldoString.equals("")) 
		{
			saldo = Double.parseDouble(saldoString);
		}
		
		//	creamos el objeto cliente.
		Cliente cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);
		System.out.println("Enviando Cliente: " + cliente);
		
		
		int registrosModificados = new ClienteDaoJDBC().actualizar(cliente);
		System.out.println("Registros modificados: " + registrosModificados);
		
		accionDefault(request, response);
	}
	
	private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int idCliente = Integer.parseInt(request.getParameter("idCliente"));
		
		int registrosModificados = new ClienteDaoJDBC().eliminar(new Cliente(idCliente));
		System.out.println("Registros modificados: " + registrosModificados);
		
		accionDefault(request, response);
	}
		
}
